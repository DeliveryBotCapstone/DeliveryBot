
# Database Server
## 환경
 ### DIAGRAM<br/>
 ![캡처](https://user-images.githubusercontent.com/70936623/170521827-18e388e0-d09b-4712-9ade-11d95f9011de.PNG)<br/>
 
 ### TOOLS
 
 ![EC@](https://user-images.githubusercontent.com/70936623/170514737-1711570f-73e5-46cd-8ca4-a958a4ad9999.png)![그림1](https://user-images.githubusercontent.com/70936623/170514848-e57e56c9-0cd7-47ab-b46c-f28616d7e500.png)![그림2](https://user-images.githubusercontent.com/70936623/170514858-9ba81889-08c9-42b9-a704-b0ff6cedaca7.png)<br /><br />
 
* 애플리케이션의 데이터베이스(DB) 서버는 Amazon Web Services(AWS) EC2에서 Ubuntu Server를 생성하여 내부에 MySQL을 통해 구축하였다.
* 추가로 Ubuntu Server에 NGINX 웹 서버를 구축하였는데 이는 안드로이드에서 보안 상의 이유로 단말에서 외부 데이터베이스에 직접 접근할 수 없기 때문이다.

  - 안드로이드 단말이 데이터베이스에 직접 접근하게 될 경우 데이터베이스 연결 정보를 애플리케이션 내부에 적어놓게 된다. 이는 데이터베이스 서버의 IP 및 포트가 외부로 노출될 수 있다. 추가로 MySQL의 connection 개수는 한정되어 있기 때문에 모바일 애플리케이션마다 직접 connection하는 데 제한이 생긴다.

* 따라서 애플리케이션은 NGINX 웹 서버를 중계 서버로 두어 애플리케이션이 외부 데이터베이스에 접근할 수 있게 하고 보안과 관련된 부분에서 안전성을 가질 수 있게 된다.

#### 동작 방식 
1. 먼저 안드로이드가 NGINX 웹 서버로 PHP 페이지 http 요청을 보내고 PHP 페이지에 작성된 MySQL 연결 및 접근 코드를 수행한다.
2. PHP에서 MySQL 데이터베이스 결과를 가져와 JSON 포맷으로 만들고 이를 echo를 통해 출력한다.
3. 안드로이드에서 위에서 출력한 데이터베이스 결과를 읽음으로써 어플리케이션에서 MySQL 데이터를 받아올 수 있다.
 

 
 ## DATABASE
 ### TOOLS
 
 ![그림3](https://user-images.githubusercontent.com/70936623/170515160-c380036e-b70f-4c70-a67c-f9418b0167f0.png)<br />
 ▲ EC2 내부에 구축되어있는 데이터 베이스<br />
 * 호수 - 수취인명 - 수취인 전화번호로 구성되어있으며, 개인정보 보호로 인해 운송장에 수취인명, 전화번호가 가려져있음을 감안 -> 데이터베이스에 사용자 정보 저장하여 응답 받게 설정<br /><br />
 ![DB 레코드](https://user-images.githubusercontent.com/70936623/170522869-cd3a3411-ad9a-4e6c-9da7-2f4c14ef10f1.png)<br />
  ▲ 실제 DB 레코드 삽입 페이지 <br /><br />
  ![DB](https://user-images.githubusercontent.com/70936623/170523189-3d13a036-3fc5-4627-b209-a407cca4b81e.PNG)<br />
  ▲ 실제 DB 화면 <br /><br />


 ## USER TABLE
 
| 열 이름 | 데이터 형식 | 
| :--------: | :--------: |
| name | VARCHAR(20) | 
| number | VARCHAR(20) | 
| address | VARCHAR(20) |



