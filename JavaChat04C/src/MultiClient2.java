/*콘솔 멀티채팅 클라이언트 프로그램*/

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MultiClient2
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		System.out.println("이름을 입력해 주세요.");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		PrintWriter out = null;
		BufferedReader in =null;
		
		try
		{
			String ServerIP = "localhost";
			if(args.length > 0)
				ServerIP = args[0];
			Socket socket = new Socket(ServerIP, 9999);
			System.out.println("서버와 연결이 되었습니다......");
			
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
									socket.getInputStream() ));
			
			out.println(name);
			
			while (out!=null)
			{
				try
				{
					if(in!=null)
					{
						System.out.println("Receive : " + in.readLine());
					}
					
					String s = sc.nextLine();
					out.println(s);
					
					if( s.equals("q") || s.equals("Q"))
					{
						break;
					}
				}
				catch(IOException e)
				{
					System.out.println("예외:"+e);
				}
			}

			in.close();
			out.close();
			
			socket.close();
		}
		catch(Exception e)
		{
			System.out.println("예외[MultiClient class]:" + e);
		}
	}
}
