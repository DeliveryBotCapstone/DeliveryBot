
# Android Application
## 개요
* 택배 송장에서 동 호수를 추출해 로봇 서버로 해당 정보를 보낼 수 있는 애플리케이션

<br /><br />

## 참여 인원
* 박종건
* 양현규

<br /><br />

## 기술 스택
### FRONT-END
<img alt="Android Studio" src ="https://img.shields.io/badge/Android Studio-3DDC84.svg?&style=for-the-badge&logo=Android Studio&logoColor=white"/> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> <img alt="FIGMA" src ="https://img.shields.io/badge/FIGMA-F24E1E.svg?&style=for-the-badge&logo=FIGMA&logoColor=white"/>

### BACK-END
<img alt="Android Studio" src ="https://img.shields.io/badge/Android Studio-3DDC84.svg?&style=for-the-badge&logo=Android Studio&logoColor=white"/> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/> <img alt="AmazonAWS" src ="https://img.shields.io/badge/Amazon EC2-232F3E.svg?&style=for-the-badge&logo=AmazonAWS&logoColor=white"/> <img alt="php" src ="https://img.shields.io/badge/php-777BB4.svg?&style=for-the-badge&logo=php&logoColor=white"/> <img alt="NGINX" src ="https://img.shields.io/badge/NGINX-009639.svg?&style=for-the-badge&logo=NGINX&logoColor=white"/> <img alt="MySQL" src ="https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MYSQL&logoColor=white"/>

<br /><br />

## Project Hierachy
```ruby   

 application
 │── server # 데이터베이스 서버
 │   └── database
 |       │── dbcon.php # DB 연동 정보 저장
 |       │── getjson.php # DB의 USER 테이블의 모든 데이터를 출력 
 |       └── insert.php # DB에 USER 테이블에 레코드 삽입
 |
 └── google-cloud-vision/android # 어플리케이션 구현
     │── MainActivity # 사진 촬영, OCR API, DB 연동
     │── IntroActivity # 어플리케이션 실행 시 초기 로딩 화면
     │── CompleteActivity # 배송 완료 시 
     │── MyService # 로봇 서버와의 소켓 통신 및 SMS 전송
     └── UserData # DB 데이터를 객체화 시키기 위한 클래스
 ```  

<br /><br />

 ## 안드로이드 어플리케이션 WIKI 바로가기

[Android Application](/src/application/google-cloud-vision) <br />

 <br />
 
 ## 데이터베이스 서버 WIKI 바로가기

[Database Server](/src/application/server) <br />

