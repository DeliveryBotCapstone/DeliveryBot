import rospy
from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal
import actionlib
from actionlib_msgs.msg import *
from geometry_msgs.msg import Pose, Point, Quaternion
import sys

class GoToPose():
    def __init__(self):
        
        # 목적지 action flag
        self.goal_sent = False
        
        # Ctrl+c 또는 실패하면 동작 종료
        rospy.on_shutdown(self.shutdown)
        
        # move_base server(node)에 연결
        self.move_base = actionlib.SimpleActionClient("move_base", MoveBaseAction)
        rospy.loginfo("Wait for the action server to come up")
        self.move_base.wait_for_server(rospy.Duration(5))

    def goto(self, pos, quat):
        
        self.goal_sent = True
        
        # 목적지 action 메세지 작성
        goal = MoveBaseGoal()
        goal.target_pose.header.frame_id = 'map'
        goal.target_pose.header.stamp = rospy.Time.now()
        goal.target_pose.pose = Pose(Point(pos['x'], pos['y'], 0.000),
                Quaternion(quat['r1'], quat['r2'], quat['r3'], quat['r4']))
        
        # 목적지 action 메세지 전송 (server/node 로)
        self.move_base.send_goal(goal)

        success = self.move_base.wait_for_result(rospy.Duration(60*2)) 

        state = self.move_base.get_state()
        result = False
        
        # 목적지 도달 실패/성공
        if success and state == GoalStatus.SUCCEEDED:
            result = True
        else:
            self.move_base.cancel_goal()

        self.goal_sent = False
        return result

    def shutdown(self):
        if self.goal_sent:
            self.move_base.cancel_goal()
        rospy.loginfo("Stop")
        rospy.sleep(1)

