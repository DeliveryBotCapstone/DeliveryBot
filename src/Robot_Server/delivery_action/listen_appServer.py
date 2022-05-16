import socket
import os
import time
import sys

import rospy
from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal
import actionlib
from actionlib_msgs.msg import *
from geometry_msgs.msg import Pose, Point, Quaternion

from go_specific_point import GoToPose

# 소캣 생성시 필요한 변수들
IP = ''
PORT = 9999
SIZE = 1024
ADDR = (IP,PORT)

# 목적지 호실 리스트
deliver_list = ["0","A1406\n","A1402\n","A1401\n","A1403\n","A1405\n","A1407\n","A1408\n","A1409\n","A1410\n"]

# 목적지 좌표 리스트
position_list = [{'x' : -1.5, 'y' : 3.93},{'x' : -0.715, 'y' : -0.623},
        {'x' : -0.346, 'y' : -10.7},{'x' : 0.415, 'y': -10.7},
        {'x' : 0.306, 'y' : -4.78}, {'x' : 0.183, 'y': -2.04},
        {'x' : 8.75, 'y' : 0.0923}, {'x' : 9.59, 'y' : 0.967},
        {'x' : 0.101, 'y' : 2.08},{'x' : -0.392, 'y' : 8.71}]

# 목적지 도착시 로봇 뱡향 리스트
quaternion_list = [{'r1' : 0.000, 'r2' : 0.000, 'r3' : -1.000, 'r4' : 0.000},
        {'r1' : 0.000, 'r2' : 0.000, 'r3' : 0.000, 'r4' : -1.000},
        {'r1' : 0.000, 'r2' : 0.000, 'r3' : -0.500, 'r4' : -0.500},
        {'r1' : 0.000, 'r2' : 0.000, 'r3' : 0.500, 'r4' : -0.500}]

# 목적지로 가는 동작 함수
def goToPosition (position_index):
    try:
        rospy.init_node('nav_test', anonymous=False) # 노드초기화
        navigator = GoToPose() # 객체 생성
        
        # 목적지 좌표
        position = position_list[position_index]
        
        # 목적지 좌표별로 로봇 방향 정의
        if position_index == 1 or position_index == 2:
            quaternion = quaternion_list[1]
        elif position_index == 6:
            quaternion = quaternion_list[2]
        elif position_index == 7 or position_index == 0:
            quaternion = quaternion_list[3]
        else:
            quaternion = quaternion_list[0]

        rospy.loginfo("Go to (%s, %s) pose", position['x'], position['y'])
        success = navigator.goto(position, quaternion) # 목적지 좌표로 navigation
        
        # 목적지 도달 여부 판단
        if success:
            rospy.loginfo("Reached the desired pose")
            ret = True
        else:
            rospy.loginfo("The base failed to reach the desired pose")
            ret = False

        rospy.sleep(3)

    except rospy.ROSInterruptException:
        rospy.loginfo("Ctrl-C caught. Quitting")

    return ret

# 로봇 캐리어(컨배이어 벨트,모터)를 동작 시키기위한 함수 (로봇 서버 <--> 로봇)
def box_motor_activate():
    IP = '192.168.0.188'
    PORT = 9998
    SIZE = 1024
    ADDR = (IP, PORT)
    
    # 소캣 open, connect, 자동 close
    with socket.socket(socket.AF_INET,socket.SOCK_STREAM) as box_motor_socket:
        box_motor_socket.connect(ADDR)
        box_motor_socket.send('/Motor/ON'.encode()) #모터 동작 메세지

        msg = box_motor_socket.recv(SIZE) #모터 동작 끝나면 받는 메세지
        msg = msg.decode()

        print("[{}] message : {}".format(ADDR,msg))



if __name__ == "__main__":
    ret=False
    
    # 소켓 open (app 서버 <--> 로봇 서버)
    with socket.socket(socket.AF_INET,socket.SOCK_STREAM) as robot_server_socket:
        robot_server_socket.bind(ADDR)
        robot_server_socket.listen()

        while True:
            print('listening...')
            app_server_socket, app_server_addr = robot_server_socket.accept()
            msg = app_server_socket.recv(SIZE) # 배달 요청 메세지 + 호실(주소)
            msg = msg.decode()
            print("[{}] message : {}".format(app_server_addr,msg))

            # 요청한 호실 찾기
            for index, deliver_host in enumerate(deliver_list):
                if msg == deliver_host:
                    ret = goToPosition(position_index=index)

            # 배달 성공/실패
            if ret == True:
                # 성공시 캐리어 동작 함수 호출
                box_motor_activate()
                app_server_socket.send("success\n".encode())
            else:
                app_server_socket.send("fail\n".encode())


            # 초기 시작 위치로 돌아가기
            goToPosition(position_index=0)

            app_server_socket.close()
