package urldownload;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SyncURLDownload {

	public static void main(String[] args) {
		try {
			
			//다운로드받을 URL을 생성 
			URL url = new URL("https://direct.hi.co.kr/service.do?m=3293e8e708");
			//연결을 생성 
			//openConnection 은 추상 클래스인 URLConnection타입으로 리턴 
			//한다고 되어 있지만 필요한 메소드를 구현한 상태인 
			//HttpURLConnection이나 HttpsURLConnection으로 리턴합니다.
			//원래의 자료형으로 형 변환을 해주어야 합니다. 
			HttpURLConnection con = 
					(HttpURLConnection)url.openConnection();
			//옵션을 설정 
			con.setConnectTimeout(30000);
			//접속을 시도하는 최대 시간으로 30초동안 시도해보고 안되면 종료 
			con.setUseCaches(false);
			//cache-저장해두고 다음에 요청을 할 때 재사용( true면 재사용 false재사용하지 않겠다는 것(매번 새로운 것을 가지고 오겠다))
			
			//응답이 제대로 왔다면 
			//웹 서버에게 요청했을 때 정상 처리가 되면 200번대 코드로 응답을 합니다. 
			//200번대: 정상응답   300번대: 리다이렉트 중 
			//400번대: 클라이언트 오류   500번대: 서버 오류 
			if(con.getResponseCode() >= 200 && con.getResponseCode() < 300) {
				//다운로드 받기 위한 스트림 생성 -문자열을 다운로드 
			     BufferedReader br = new BufferedReader(
			    		 new InputStreamReader(con.getInputStream()));
			     
			     //문자열 자체에 연산을 수행하는 객체를 생성 
			     StringBuilder sb =new StringBuilder(); 
			     //줄 단위로 읽어서 읽은 내용을 sb에 추가  
			    while(true) {
			    	String line = br.readLine();
			    	if( line == null ) {
			    		break;
			    	}
			    	sb.append(line + "\n");
			    }
			    //닫기 
			    br.close(); 
			    con.disconnect();
			    System.out.printf("다운로드 받은 내용:%s\n", sb.toString());
			     
			}
			
		}catch(Exception e) {
			System.out.printf("다운로드 예외:%s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}
