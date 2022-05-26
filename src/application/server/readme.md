
# Database Server
## 환경
 DIAGRAM<br/>
 ![캡처](https://user-images.githubusercontent.com/70936623/170521827-18e388e0-d09b-4712-9ade-11d95f9011de.PNG)<br/>
 
 TOOLS
 
 ![EC@](https://user-images.githubusercontent.com/70936623/170514737-1711570f-73e5-46cd-8ca4-a958a4ad9999.png)![그림1](https://user-images.githubusercontent.com/70936623/170514848-e57e56c9-0cd7-47ab-b46c-f28616d7e500.png)![그림2](https://user-images.githubusercontent.com/70936623/170514858-9ba81889-08c9-42b9-a704-b0ff6cedaca7.png)<br />
 * 전체적인 애플리케이션의 데이터베이스 서버는 AWS EC2를 기반으로 데이터 베이스 서버를 구축<br />
 * 안드로이드의 보안상 이유로 EC2 내 UBUNTU 서버와 애플리케이션간 직접적인 연결의 제약이 있기 때문에, 웹 서버인 NGINX 를 중계 서버로 두어 애플리케이션의 외부 데이터베이스 접근과 보안 부분의 안전성을 확보함.<br />
 
 ## DATA BASE
 TOOLS
 
 ![그림3](https://user-images.githubusercontent.com/70936623/170515160-c380036e-b70f-4c70-a67c-f9418b0167f0.png)<br />
 ▲ EC2 내부에 구축되어있는 데이터 베이스<br />
 * 호수 - 수취인명 - 수취인 전화번호로 구성되어있으며, 개인정보 보호로 인해 운송장에 수취인명, 전화번호가 가려져있음을 감안 -> 데이터베이스에 사용자 정보 저장하여 응답 받게 설정<br /><br />
 ![DB 레코드](https://user-images.githubusercontent.com/70936623/170522869-cd3a3411-ad9a-4e6c-9da7-2f4c14ef10f1.png)<br />
  ▲ 실제 DB 레코드 삽입 페이지 <br /><br />
  ![DB](https://user-images.githubusercontent.com/70936623/170523189-3d13a036-3fc5-4627-b209-a407cca4b81e.PNG)<br />
  ▲ 실제 DB 화면 <br /><br />
  
  ## SOCKET
  TOOL<br />
  ![images](https://user-images.githubusercontent.com/70936623/170528091-7e66389a-8bf2-4a6f-ae90-114e2200c78f.png)<br />
  * 애플리케이션과 로봇 서버간 통신을 위한 소켓프로그래밍.
  * 애플리케이션이 수취인에게 SMS를 보내기 위한 수취인 정보 제공함.
  

  



