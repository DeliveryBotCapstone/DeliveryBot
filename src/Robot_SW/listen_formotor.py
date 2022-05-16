import socket
from gpiozero import Motor
import time

# box_motor_socket infomation
IP = ''
PORT = 9998
SIZE = 1024
ADDR = (IP,PORT)

# Motor object (pin 20,21)
motor = Motor(forward = 20, backward= 21)

# Motor activate function
def motor_act():
    print("[Local] Log : Box_motor activate")
    motor.forward(speed=0.5) # motor act 
    time.sleep(5)
    motor.stop()
    print("[Local] Log : Box_motor done")


if __name__ == "__main__":
    
    # Open socket to listen
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as box_motor_socket:
        box_motor_socket.bind(ADDR)
        box_motor_socket.listen()

        # Waitting message 
        while True:
            print('Motor is waiting...')
            server_socket,server_addr = box_motor_socket.accept() # Connect with server
            msg = server_socket.recv(SIZE) # Recv motor activate msg
            msg = msg.decode()
            print('[{}] Recv Message : {}'.format(server_addr,msg))

            if msg == '/Motor/ON':
                # Motor act
                motor_act()
                server_socket.send('/Motor/SUCCESS'.encode())
            else:
                # If recv wrong message retry
                print("[Local] Error : Wrong message")
                server_socket.send('/Motor/RETRY'.encode())
                
            server_socket.close()
