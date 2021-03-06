# Robot Hardware Assembly 
## 로봇 하드웨어 구성

## 로봇 사진
<details>
<summary>전면</summary>

<img src="./Image/robot_front.jpg" width="300">
</details>

<details>
<summary>후면</summary>

<img src="./Image/robot_backside.jpg" width="300">
</details>

<details>
<summary>측면</summary>

<img src="./Image/robot_rightside.jpg" width="300">
<img src="./Image/robot_leftside.jpg" width="300">
</details>

## 주요 구성품

<img src="./Image/turtlebot3_burger.jpg" width="200">

[Turtlebot3 Burger (Store)](https://www.robotis.com/shop/item.php?it_id=901-0118-200)

<img src="./Image/lidar.jpg" width="200">

[YDLIDAR X4 (Store)](https://www.devicemart.co.kr/goods/view?no=12170775&gclid=Cj0KCQjw2MWVBhCQARIsAIjbwoN9Iv8heMrm4NfHnUY4umkwVT6_VuIS0tY5kDdMlNmJAYiZaz920vIaAkE4EALw_wcB)

<img src="./Image/rpi4.jpg" width="200">

[Raspberry Pi 4 Model B (Store)](https://www.devicemart.co.kr/goods/view?no=12553062)

<img src="./Image/opencr.jpg" width="200">

[OpenCR 1.0 (Store)](https://www.robotis.com/shop/item.php?it_id=903-0257-000)

<img src="./Image/conveyor.jpg" width="200">

[컨베이어 벨트 (Store)](http://ideaplay6173.cafe24.com/product/detail.html?product_no=371&cate_no=47&display_group=1)

<img src="./Image/motordriver.jpg" width="200">

[모터 드라이버 2A L298N (Store)](https://eduino.kr/product/detail.html?product_no=194&cate_no=55&display_group=1)

## Raspberry Pi 용 ROS 이미지 부팅 및 기본 설정

* [Raspberry Pi 4 ROS Noetic 이미지 다운로드](https://www.robotis.com/service/download.php?no=2066)

* [Raspberry Pi Imager 다운로드](https://www.raspberrypi.com/software/) <br>

* microSD 카드에 이미지 Write 이후 RPI에 장착/부팅<br>
<img src="./Image/RPI_imager.jpg" width="500">

* RPI에 모니터 연결  **( ID :ubuntu, password: turtlebot )**


*   ```bash
    $ cd /etc/netplan
    $ sudo vim 50-cloud-init.yaml
    ```
* 아래 예시와 같이 작성 **( "my-SSID" : 연결할 WIFI이름(SSID), "my-password" : 연결할 WIFI 의 비밀번호 )** <br>
<img src="./Image/staticip.jpg" width="300">

* `sudo netplan apply` 또는 `sudo reboot now`

*   ```bash
    $ ifconfig # ip확인
    $ vim ~/.bashrc
    ```
* 아래 그림과 같이 작성 **( {Remote_PC_IP}: 로봇 서버 ip, {LOCAL_PC_IP}: RPI ip )** <br>
<img src="./Image/RPI_bash.jpg" width="500">

## OpenCR 설정

RPI 에서 실행.

```bash
$ sudo dpkg --add-architecture armhf
$ sudo apt-get update
$ sudo apt-get install libc6:armhf

$ export OPENCR_PORT=/dev/ttyACM0
$ export OPENCR_MODEL=burger_noetic
$ rm -rf ./opencr_update.tar.bz2

$ wget https://github.com/ROBOTIS-GIT/OpenCR-Binaries/raw/master/turtlebot3/ROS1/latest/opencr_update.tar.bz2 
$ tar -xvf opencr_update.tar.bz2 

$ cd ./opencr_update
$ ./update.sh $OPENCR_PORT $OPENCR_MODEL.opencr
```
아래 처럼 나오면 성공<br>

<img src="./Image/opencrcli.jpg" width="500">

## YDLIDAR X4 드라이버 설치 및 셋팅

**방법 1**<br>

YDLIDAR 는 YDLIDAR/YDLidar-SDK에 의존성을 가지기 때문에 설치합니다.
```bash
$ sudo apt install cmake pkg-config
$ git clone https://github.com/YDLIDAR/YDLidar-SDK.git
$ cd YDLidar-SDK/build
$ cmake ..
$ make
$ sudo make install
```

YDLidar driver 설치
```bash
$ git clone https://github.com/YDLIDAR/ydlidar_ros_driver.git ydlidar_ws/src/ydlidar_ros_driver
$ cd ydlidar_ws
$ catkin_make

$ source ./devel/setup.sh
$ echo "source ~/ydlidar_ws/devel/setup.bash" >> ~/.bashrc
$ source ~/.bashrc

$ chmod 0777 src/ydlidar_ros_driver/startup/*
$ sudo sh src/ydlidar_ros_driver/startup/initenv.sh

$ cd ./src
$ git clone https://github.com/YDLIDAR/ydlidar_ros
$ git chectout master
$ cd ..
$ catkin_make

$ touch /dev/ydlidar
$ sudo chmode 777 /dev/ydlidar

$ roscd ydlidar_ros/startup
$ sudo chmod 777 ./*
$ sudo sh initenv.sh
```
