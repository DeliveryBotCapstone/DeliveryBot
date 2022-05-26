
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

애플리케이션의 전체 화면 초기 구상으로, 각 기능당 화면을 나누어 디자인하였음<br />
도움말과 같은 텍스트는 한림대 의료원에서 제공한 무료 글꼴인 한림 고딕체를 이용함<br /><br/>
각 화면들 순서대로
* 애플리케이션 실행시 처음으로 나오는 스플래시 화면
* 송장 촬영 혹은 송장 이미지 업로드를 위한 화면
* 송장 인식여부와 배달 과정에서 택배 수취인에게 SMS 발송 화면
* SMS 발송과 수화물 배달 완료화면  
#### 실제 적용 화면
![ㅌㅊㅌㅍㅌㅊㅍㅌ](https://user-images.githubusercontent.com/70936623/170524457-0db6077d-534d-454a-8b47-bc103a5f528f.PNG)
![ㅁㄴㅇㄻ](https://user-images.githubusercontent.com/70936623/170524247-cc166939-ed45-4bfa-ad1f-eb012f6bc856.PNG)






------------------

### BACK-END
 #### Google-Cloud-Vision
 ![cvision](https://user-images.githubusercontent.com/70936623/170508987-cf81a69b-2e3f-4a63-a3c6-833cbbec1434.png)
 
 [CLOUD-VISION 공식 GITHUB](https://github.com/GoogleCloudPlatform/cloud-vision)
 
 애플리케이션의 중요 기능인 송장 인식 기술로써 GOOGLE CLOUD VISION API - OCR을 사용.<br />
 택배 이미지 내, 모든 텍스트를 JSON포맷으로 추출, 호수 부분만 추출하여 애플리케이션 화면에 출력, 로봇 서버에게 위치 전송을 담당. 
 * 호수 추출 예시
 
 ![KakaoTalk_20220412_005820577](https://user-images.githubusercontent.com/70936623/170510971-6daa90dd-ef9d-4ceb-85bf-47fa2cd522c4.png)

 #### SERVER
 ##### DIAGRAM
 ![캡처](https://user-images.githubusercontent.com/70936623/170521827-18e388e0-d09b-4712-9ade-11d95f9011de.PNG)

 
 
 
 TOOLS
 
 ![EC@](https://user-images.githubusercontent.com/70936623/170514737-1711570f-73e5-46cd-8ca4-a958a4ad9999.png)![그림1](https://user-images.githubusercontent.com/70936623/170514848-e57e56c9-0cd7-47ab-b46c-f28616d7e500.png)![그림2](https://user-images.githubusercontent.com/70936623/170514858-9ba81889-08c9-42b9-a704-b0ff6cedaca7.png)<br />
 * 전체적인 애플리케이션의 데이터베이스 서버는 AWS EC2를 기반으로 데이터 베이스 서버를 구축<br />
 * 안드로이드의 보안상 이유로 EC2 내 UBUNTU 서버와 애플리케이션간 직접적인 연결의 제약이 있기 때문에, 웹 서버인 NGINX 를 중계 서버로 두어 애플리케이션의 외부 데이터베이스 접근과 보안 부분의 안전성을 확보함.<br />
 
 #### DATA BASE
 TOOLS
 
 ![그림3](https://user-images.githubusercontent.com/70936623/170515160-c380036e-b70f-4c70-a67c-f9418b0167f0.png)<br />
 ▲ EC2 내부에 구축되어있는 데이터 베이스<br />
 * 호수 - 수취인명 - 수취인 전화번호로 구성되어있으며, 개인정보 보호로 인해 운송장에 수취인명, 전화번호가 가려져있음을 감안 -> 데이터베이스에 사용자 정보 저장하여 응답 받게 설정<br /><br />
 ![DB 레코드](https://user-images.githubusercontent.com/70936623/170522869-cd3a3411-ad9a-4e6c-9da7-2f4c14ef10f1.png)<br />
  ▲ 실제 DB 레코드 삽입 페이지 <br /><br />
  ![DB](https://user-images.githubusercontent.com/70936623/170523189-3d13a036-3fc5-4627-b209-a407cca4b81e.PNG)<br />
  ▲ 실제 DB 화면 <br /><br />


 
 #### SOCKET
  * 로봇 서버와 애플리케이션 간 통신을 하기위한 자바 소켓프로그래밍<br />
  * 송장에서 추출한 호수를 로봇 서버에 전달, 데이터베이스 에서 가져온 사용자 정보를 토대로, 배송 완료 SMS를 수취인에게 전달한다.<br />SMS 전송은 안드로이드 제공하는 라이브러리인 SMS Manager를 사용하여 전송.
 
 ``` java
 private class TCPclient implements Runnable {
        private final String serverIP = IP;
        private static final int serverPort = 1111;
        private Socket inetSocket = null;
        private String msg;
        private String phoneNum = number;

        public TCPclient(String msg) {
            this.msg = msg;
        }

        public void run() {
            try {
                Log.d("TCP", "C: Connecting ... ");
                inetSocket = new Socket(serverIP, serverPort);
                try {
                    Log.d("TCP", "C: Sending ... " + msg);
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(inetSocket.getOutputStream())), true);
                    out.println(msg);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inetSocket.getInputStream()));

                    rmsg = in.readLine();
                    Log.d("TCP", "C: Server send to me this message --> " + rmsg);

                    String m = name + "님에게 배송이 완료되었습니다.";
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNum, null, m, null, null);

                    Intent intent = new Intent(MyService.this, CompleteActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("TCP", "C: Error1", e);
                } finally {
                    inetSocket.close();
                }
            } catch (Exception e) {
                Log.e("TCP", "C: Error2", e);
            }
        }
    }
 ```

<br />

 ## 안드로이드 어플리케이션 WIKI 바로가기

[Android Application](/src/application/google-cloud-vision) <br />

 <br />
 
 ## 데이터베이스 서버 WIKI 바로가기

[Database Server](/src/application/server) <br />

