package definition;

public class Network {
/*
  
** 주소 
=>다른 컴퓨터에서 접속하기 위해서 필요한 주소: 컴퓨터 주소(IP)  +포트번호(서비스번호)        
 IP는 컴퓨터를 찾아가는 것이고 포트번호는 프로그램을 찾아가는 것 입니다. 
 
** 통신분류 
1. 프로토콜에 따른 분류 
1)TCP(연결형): 상대방과 가상으로 연결해서 데이터를 주고받는 형태로 통신하는 방식 
=> 중요한 메세지 전송에 사용 - 상대방이 받았다는 사실을 입증해야 하는 경우 사용

2)UDP(비연결형): 보내는 쪽에서 일방적으로 받는 쪽으로 데이터를 보내기만 하는 방식 
=> 중요하지 않은 메세지를 여러 곳에 보내야 하는 경우에 사용 - 상대방이 받았다는 사실을 입증하지 않아도 되는 
경우에 사용 

2. 레벨에 따른 분류 
1)Low Level통신 : 소켓을 직접 만들어서 통신하는 방식 
=> 효율이 좋지만 네트워크에 대해서 기반 지직이 있어야 하고 어렵습니다. 

2)High Level 통신: 이미 만들어진 프로토콜을 이용해서 통신하는 방식 
=>효율은 떨어지지만 기기에 상관없이 전부 동일한 방식으로 통신 할 수 있고 쉽습니다. 
   ## Web Socket, Web RTC ## 따로 공부 

3.Low Level통신 절차 
1) 요청을 받는 쪽이 먼저 실행 
=> 소켓을 생성 (포트번호를 가지고 생성) 
=>소켓을 이용해서 읽을 수 있는 스트림(문자열-문자스트림, 문자열 이외의 데이터-바이트스트림)을 생성 
=> 상대방의 연결을 기다림 
=> 상대방이 연겨랗고 데이터를 전송하면 스트림을 이용해서 읽기 
=> 통신이 끝나면 close() 

2)요청을 보내는 쪽 
=> 소켓을 생성하는데 TCP인 경우는 접속할려는 컴퓨터의 IP 와 port번호를 가지고 생성 
=> 소켓을 이용해서 전송할 수 있는 스트림 (문자열-문자스트림, 문자열 이외의 데이터-바이트스트림)을 생성
=>데이터 전송 
=> 통신이 끝나면 close()  

** UDP통신을 이욧ㅇ해서 파일을 전송하기 
=> 파일을 전송할 때는 일반적으로 파일의 이름과 크기를  먼저 전송하고 파일의 내용을 전송 
이름은 받는 쪽에서 어떤 파일의 형태로 저장해야 하는지 알기 위해서고 크기는 너무 크면 
받지 않기 위해서 입니다. 

=> 받는 것과 보내는 것 2개의 프로그램을 생성해서 실습 
웬만하면 책들에서는 localhost나 127.0.0.1(자신의 컴퓨터 주소)를 가지고 통신을 설명하는데 되도록이면 
2대의 컴퓨터 또는 1대를 가지고 할때고 ip를 직접 입력해서 해보는 것이 좋습니다.
자신의 IP주소 확인 (window:ipconfig, 그이외:ifconfig) 

** 전송받는 대상에 따른 분류 
1. 1:1(point to point , p2p) 통신: Unicast 

2.1:Group통신 : multicast 
=>224.0.0.0 ~ 239.255.255.255 의 주소내에 접속한 후 메세지를 보내면 동일한 IP접속한 모든곳에 
메세지를  전송하는 통신 
=>화상회의에 많이 이용 

3. 1:전체(동일한 네트워크 그룹): broadcast
=>동일한 subnet mask를 사용하는 그룹 전체에 데이터를 전송 

**URL(Uniform Resource Locator)
=>인터넷 상에서의 자원의 위치 
=>구성 
프로토콜://도메인 또는 IP주소:포트번호/파일경로(요청경로)?파라미터이름=파라미터값&파라미터이름=파라미터값  
 ...
 
 프로토콜은 http(보안이 되지 않은 웹-브라우저에서 바로 접속이 안되는 경우가 많습니다. ), https(보안이 설정된
  웹), file(로컬컴퓨터 지정), ftp, telnet...: 생략할 수 없음 

 도메인 또는 IP주소: 도메인은 IP를 문자로 변경한 별명으로 나중에는 IP로 변경해서 접속할 컴퓨터를 찾습니다. :
 생략은 할 수 없음 
 
 포트번호: 접속한 컴퓨터에서 응용프로그램을 찾기 위한 번호로 0~65535번 까지 입니다. 
 
  생략하는 경우가 있는데 이경우는 컴퓨터에서 프로그램을 기본 포트 설정으로 한경우 입니다. 
  http:80
  https:443
  
  oracle:1521, 8080
  mysql:3306
  mongodb:27017
  
  tomcat:8080

파일 경로(요청 경로): 파일경로가 아니고 요청경로로 요청을 하면 서버가 그 요청에 해당하는 결과를 리턴해서 
출력합니다. 
생략하면 서버 설정에 따라서 필요한 파일을 출력을 합니다. 

파라미터: 이름과 값 쌍으로 전송되는 데이터로 클라이언트가 서버에게 전송하는 데이터 
get방식에서는 주소 표시줄에 보이지만 post방식에서는 주소 표시줄에 표시가 안됩니다. 

**URL Class -java에서 url을 표현하기 위한 클래스 
new URL(String url)을 이용해서 생성 

잘못된 URL이오면 예외가 발생할 수 있기 때문에 예외처리를 강제합니다. 

URL에 한글이 포함되어 있다면 인커딩해서 만들어야  합니다,. 
  
 인코딩할 때는 java.net.URLEncoder.encode(String url, String enctype)을 호출하면 url을 enctype으로 
 인코딩해서 문자열로 리턴합니다. 
 
** URL과 통신하는 클래스 - URLConnection(추상클래스) 
=>실제 구현된 클래스는 HttpURLConnection, HttpsURLConnection, JARURLConnection 
1.객체생성은 
URL객체.openConnection()으로 생성하는데 URL에 따라 HttpURLConnection이나 다른 Connection으로 강제형
변환해서 사용해야 합니다. 

HttpURLConnection con = (HttpURLConnection)url.openConnection(); 

2.메소드
1)setConnectTimeout(int timeout):접속 시도 시간 
2)setReadTimeout(int timeout)
3)setUseCashes(boolean newValue):캐시사용여부 
4)setDoInput, setDoOutput(boolean newValue):입출력 여부 설정 - 생략해도 됨 
5) setRequestProperty, addRequestProperty(String field, String newValue):헤더에 값을 설정하거나 추가할 때 
사용하는 메소드  
6)setRequestMethod(String method):요청방식 설정(get,post...)
=>6개의 메소드들은 get메소드도 존재 

7)int getResponseCode():서버의 응답코드 
400번대: 클라이언트 오류 (404-URL오류) 
500번대: 서버 오류 
200번대: 정상 응답

8)InputStream getInputStream():읽어올 수 있는 스트림 리턴 
 
 !!! GUI멀티채팅 코딩후 실습해보기 (집 복습)
 
** http://direct.hi.co.kr
 
 !!!Open API 
 데이터인 경우나 함수인 경우 2가지 
 
 **문자열을 분할해서 사용하는 방법 
 1. 특수한 기호를 이용해서 분할 
 String[] split("분할할 텍스트") 
 
 2.위치를 기준으로 분할 
 String substring(int start): start붙 분할해서 리턴 
 String substring(int start, int end):start부터 end 앞까지 분할 
 
 ** 동기와 비동기 
 => 동기(Synchronous)는 하나의 작업이 완료된 후 다음 작업을 하는 구조 또는 하나의 신호가 있어야만 
 다음 작업을 시작하는 구조  
 =>비동기(ASynchronous)는 하나의 작업이 동작중인데고 다른 작업을 수행할 수 있는 구조: 스레드로 구현 
 
 => 데이터를 다운로드 받는 작업은 오랜 시간이 걸리는 작업인데 이 작업으 동기적으로 수행하면 
 다른 작업은 다운로드가 끝날때까지 대기한 후 작업을 수행해야 합니다.
 다운로드받은 데이터를 가지고 수행해야 하는 작업이라면 어쩔 수 없지만 그렇지 않은 경우에는 
 별도의 스레드로 동작하도록 해서 데이터를 다운로드 받는 동안에도 수행할 수 있도록 해주는 것이 좋습니다. 
 
 **스레드를 만들어서 수행 
 1.Thread클래스로부터 상속받는 경우 
 Thread th =new Thread(){
     public void run(){
         스레드로 수행할 내용;
      }
 };
 th.start();
 
 2.Runnable인터페이스를 구현하는 경우 
 Runnable r = new Runnable(){
     public void run(){
         스레드로 수행할 내용 
     }
 };
Thread th = new Thread(r);
th.start(); 


 

 */
}
