import java.net.*;

public class NetworkEx2 {
	public static void  main(String args[]) {
		URL url = null;
		String address = "http://www.enjoypuzzle.com:80/" + 
				               "board/bbsview/nemonotice/260/1#1";

		try {
			url = new URL(address);
			URLConnection conn = url.openConnection();

			System.out.println("conn.toString():"+conn);
			// UserInteraction (사용자 상호작용)의 허용여부를 반환한다.
			System.out.println("getAllowUserInteraction():"+conn.getAllowUserInteraction());
			// 연결 종료시간을 천분의 일초로 반환한다.
			System.out.println("getConnectTimeout():"+conn.getConnectTimeout());
			// content 객체를 반환한다.
			System.out.println("getContent():"+conn.getContent());
			// content 객체의 인코딩을 반환한다.
			System.out.println("getContentEncoding():"+conn.getContentEncoding());
			// content 의 크기를 반환한다.
			System.out.println("getContentLength():"+conn.getContentLength());
			// content의 type을 반환한다.
			System.out.println("getContentType():"+conn.getContentType());
			// 헤더(header)의 date필드의 값을 반환한다. 밀리센커드 단위
			System.out.println("getDate():"+conn.getDate());
			// UseCache의 디폴트값을 얻는다.
			System.out.println("getDefaultUseCaches():"+conn.getDefaultUseCaches());
			// DoInput 필값을 얻는다.
			System.out.println("getDoInput():"+conn.getDoInput());
			// Dooutput 필드값을 얻는다.
			System.out.println("getDoOutput():"+conn.getDoOutput());
			// 자원의 만료일자를 얻는다.
			System.out.println("getExpiration():"+conn.getExpiration());
			// 헤더의 필더 읽어온다.
			System.out.println("getHeaderFields():"+conn.getHeaderFields());
			// IfModifiedSince(변경여부) 필드의 값을 반환한다.
			System.out.println("getIfModifiedSince():"+conn.getIfModifiedSince());
			// 최종 변경일 필드의 값을 바환한다.
			System.out.println("getLastModified():"+conn.getLastModified());
			// 일기 제한시간의 값을 반환한다.
			System.out.println("getReadTimeout():"+conn.getReadTimeout());
			// URLConnection의 URL를 반환한다.
			System.out.println("getURL():"+conn.getURL());
			// 캐쉬의 사용여부를 반환한다.
			System.out.println("getUseCaches():"+conn.getUseCaches());
		} catch(Exception e) {
			e.printStackTrace();
		}
	} // main
}