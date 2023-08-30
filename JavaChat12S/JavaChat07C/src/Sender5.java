import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender5 extends Thread
{
	Socket socket;
	PrintWriter out = null;
	String name;
	
	// 생성자
	
	public Sender5(Socket socket, String name)
	{
		this.socket = socket;
		try
		{
			out = new PrintWriter(this.socket.getOutputStream(), true);
			this.name = name;
		}catch(Exception e) 
		{
			System.out.println("예외S3:"+e);
		}
		
	}
	
	// run() 메소드 재정의
	@Override
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		try
		{
			// 입력한 사용자이름을 서버에 보내준다.
			out.println(name);
			
			while (out != null)
			{
				try
				{
					String s = sc.nextLine();
					out.println(s);
						
					if (s.equals("q") || s.equals("Q"))
						{
							break;
						}
						
				}catch(Exception e) 
				{
					System.out.println("예외S1:"+e);
				}
			}
			
			out.close();
			
			socket.close();
		}catch(Exception e) 
		{
			System.out.println("예외S2:"+e);
		}
		
		sc.close();
	}
	
		
	
}
