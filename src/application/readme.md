
# Android Application
## 개요
* 택배 송장에서 동 호수를 추출해 로봇 서버로 해당 정보를 보낼 수 있는 애플리케이션

## 참여 인원
* 박종건
* 양현규
<br />

## 기술 스택
### FRONT-END
<img alt="Android Studio" src ="https://img.shields.io/badge/Android Studio-3DDC84.svg?&style=for-the-badge&logo=Android Studio&logoColor=white"/> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> <img alt="FIGMA" src ="https://img.shields.io/badge/FIGMA-F24E1E.svg?&style=for-the-badge&logo=FIGMA&logoColor=white"/>

### BACK-END
<img alt="Android Studio" src ="https://img.shields.io/badge/Android Studio-3DDC84.svg?&style=for-the-badge&logo=Android Studio&logoColor=white"/> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> <img alt="AmazonAWS" src ="https://img.shields.io/badge/Amazon EC2-232F3E.svg?&style=for-the-badge&logo=AmazonAWS&logoColor=white"/> <img alt="php" src ="https://img.shields.io/badge/php-777BB4.svg?&style=for-the-badge&logo=php&logoColor=white"/> <img alt="NGINX" src ="https://img.shields.io/badge/NGINX-009639.svg?&style=for-the-badge&logo=NGINX&logoColor=white"/> <img alt="MySQL" src ="https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MYSQL&logoColor=white"/>

## Project Hierachy
```ruby   

 application
 │── server
 │   │── database
 │   └── socket.c
 └── google-cloud-vision/android
 ```  
 
 <br />
 
 ## 기술 상세
 
 ### FRONT-END
 #### LOGO
 <img src ="https://user-images.githubusercontent.com/70936623/170500824-c1c2ebf9-d54e-45b4-9777-20babfb4cd97.png" width="200" height="200">
 
* 택배 배송의 취지에 맞게, 택배 수화물로 대표되는 상자모양 이모티콘을 메인 로고로 디자인
* 한림대학교의 상징색인, 파란색과 청록색을 사용하여, 한림대 학생들의 작품이란 것을 보여줌

 #### ACTIVITY DESIGN
 FIGMA를 이용한 애플리케이션 화면 디자인
 ##### 초기 화면구상
 <img src ="https://user-images.githubusercontent.com/70936623/170503868-c541686b-e9a5-498c-b949-7c18902777a8.png" width="200" height="300"> <img src ="https://user-images.githubusercontent.com/70936623/170503877-58d53f1a-e140-47d5-b4d7-c6ae5c48e7e6.png" width="200" height="300"> <img src ="https://user-images.githubusercontent.com/70936623/170503870-6b92cb63-edc8-4f9a-a59e-6215be5221b2.png" width="200" height="300"> <img src ="https://user-images.githubusercontent.com/70936623/170503873-7eb241da-3e3a-4946-a63b-316e99de6a94.png" width="200" height="300">

애플리케이션의 전체 화면 초기 구상으로, 각 기능당 화면을 나누어 디자인하였음
* 애플리케이션 실행시 처음으로 나오는 스플래시 화면
* 송장 촬영 혹은 송장 이미지 업로드를 위한 화면
* 송장 인식여부와 배달 과정에서 택배 수취인에게 SMS 발송 화면
* SMS 발송과 수화물 배달 완료화면  
#### 결과 화면 

 
 
 
 

### BACK-END
 #### Google-Cloud-Vision
 #### SERVER
 #### DATA BASE
 #### SOCKET

<br />

 ## 안드로이드 어플리케이션 WIKI 바로가기

[Android Application](/src/application/google-cloud-vision) <br />

 <br />
 
 ## 데이터베이스 서버 WIKI 바로가기

[Database Server](/src/application/server) <br />
