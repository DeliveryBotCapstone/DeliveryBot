# Android Application
## FRONT-END
### LOGO
 <img src ="https://user-images.githubusercontent.com/70936623/170500824-c1c2ebf9-d54e-45b4-9777-20babfb4cd97.png" width="200" height="200">
 
* 택배 배송의 취지에 맞게, 택배 수화물로 대표되는 상자모양 이모티콘을 메인 로고로 디자인
* 한림대학교의 상징색인, 파란색과 청록색을 사용하여, 한림대 학생들의 작품이란 것을 보여줌

### ACTIVITY DESIGN
 ![figma_logo_icon_171159](https://user-images.githubusercontent.com/70936623/170531357-02ee641c-cdcf-41e2-a4ca-3a8c8ab67ae8.png)<br />

 FIGMA를 이용한 애플리케이션 화면 디자인
 #### 초기 화면구상
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
## BACK-END
### OCR
![cvision](https://user-images.githubusercontent.com/70936623/170508987-cf81a69b-2e3f-4a63-a3c6-833cbbec1434.png)<br />
 
 [CLOUD-VISION 공식 GITHUB](https://github.com/GoogleCloudPlatform/cloud-vision)
 
 애플리케이션의 중요 기능인 송장 인식 기술로써 GOOGLE CLOUD VISION API - OCR을 사용.<br />
 택배 이미지 내, 모든 텍스트를 JSON포맷으로 추출, 호수 부분만 추출하여 애플리케이션 화면에 출력, 로봇 서버에게 위치 전송을 담당. 

 ```java
 private void callCloudVision(final Bitmap bitmap) {
        // Switch text to loading
        description.setText(R.string.loading_message);
        description.setVisibility(View.VISIBLE);

        // Do the real work in an async task, because we need to use the network anyway
        try {
            AsyncTask<Object, Void, String> labelDetectionTask = new LableDetectionTask(this, prepareAnnotationRequest(bitmap));
            labelDetectionTask.execute();
        } catch (IOException e) {
            Log.d(TAG, "failed to make API request because of other IOException " +
                    e.getMessage());
        }
    }
  ```
 
 
 #### 호수 추출 예시
 ![KakaoTalk_20220412_005820577](https://user-images.githubusercontent.com/70936623/170510971-6daa90dd-ef9d-4ceb-85bf-47fa2cd522c4.png)
 
 
 
 ### SOCKET PROGRAMING
 * 자바에서 제공하는 라이브러리를 이용한 소켓프로그래밍
 * TCP 통신 대기중인 로봇 서버와 통신
 * 로봇 서버를 통해 성공메시지 전송받음 -> 사용자 정보를 토대로 배송 완료 메시지 전달

```java
try {
                Log.d("TCP", "C: Connecting ... ");
                inetSocket = new Socket(serverIP, serverPort); // 로봇 서버와 소켓 연결
                try {
                    Log.d("TCP", "C: Sending ... " + msg);
                    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(inetSocket.getOutputStream())), true); // 서버로 호수 데이터 전송
                    out.println(msg);
                    BufferedReader in = new BufferedReader(new InputStreamReader(inetSocket.getInputStream())); // 메시지 수신
                    rmsg = in.readLine();
                    Log.d("TCP", "C: Server send to me this message --> " + rmsg);
                } catch (Exception e) {
                    Log.e("TCP", "C: Error1", e);
                } finally {
                    inetSocket.close();
                }
            } catch (Exception e) {
                Log.e("TCP", "C: Error2", e);
            }
```



 ### SMS Manager
 * 안드로이드에서 제공하는 라이브러리
 * SMS 를 전송하는 주체
 * 로봇 서버에서 성공메시지를 받은 직후 데이터베이스에 있는 전화번호로 SMS 전송
 
 ``` java
 import android.telephony.SmsManager;
 SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phoneNum, null, m, null, null);
 ```
 
 ### BACKGROUND 재생
 * 애플리케이션 화면이 닫혀도 SMS 발송이 가능하게 함
 * Thread를 이용한 프로그래밍
 * 화면이 닫혀도 백그라운드에서 실행 -> SMS의 정상적인 발신 가능
