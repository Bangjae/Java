import java.sql.*;

/*
	JDBC프로그래밍 절차
	1.오라클용 JDBC 드라이버를 메모리에 로드한다. 
	: new를 사용하지 않고 클래스명으로 직접 객체를 생성한 후 메모리에
	로드하는 forName()이라는 정적메서드를 사용한다. 
	메모리에 로드된 드라이버가 DriverManager라는 클래스에 등록한다.
	
	2.오라클 연결을 위해 커넥션URL, 계정아이디, 패스워드를 준비한다. 
	커넥션URL => jdbc:oracle:thin:@오라클서버의IP주소:포트번호:SID명
	※서버환경에 따라 ip주소, 포트번호, sid는 변경될수 있다. 
	
	3.DriverManager 클래스의 정적메서드인 getConnection()을 통해 
	데이터베이스에 연결을 시도하고, 연결에 성공하면 Connection객체를
	반환한다. 
	
	4.우리는 반환된 Connection객체를 통해 DB작업(CRUD)을 처리하게된다.
 */
public class Main
{
	static
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			//오라클 데이터베이스 연결
			Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",  // localhost:1521:xe 여기만 바꿈
					"scott",     // 아이디
					"TIGER");    // 패스워드
			
			/*
			쿼리문 전송을 위해 Statement인터페이스를 통해 객체를 
			생성한다. 
			 */
			Statement stmt = con.createStatement();
			
			StringBuffer sb = new StringBuffer();

			/*
			쿼리문 작성
			:작성시 줄바꿈을 할때 앞뒤로 스페이스를 삽입하는것이 좋다.
			그렇지 않으면 문장이 서로 이어지게되어 SyntaxError가 
			발생하게된다. 
			 */
			sb.append("select * from emp ");  // SQL 문을 여기다 적음
			
			/*
			쿼리문을 오라클 데이터베이스로 전송한다. 실행한 결과는 
			ResultSet객체를 통해 반환받는다. 
			 */
			ResultSet rs = stmt.executeQuery(sb.toString());
					
			/*
			반환된 결과를 갯수만큼 반복하여 출력한다. next()메서드는
			출력할 레코드가 남았는지 확인하고, 더 이상 레코드가 
			남아있지 않다면 false를 반환하여 while문을 탈출한다. 
			 */
			while(rs.next())
			{
				/*
				getXXX() 메서드를 통해 각 컬럼에 접근한다. 오라클의
				자료형은 문자, 숫자, 날짜 3가지 형태이므로 메서드도 
				이와 동일한 형태를 가지고 있다. 
				각 컬럼에 접근시 인덱스와 컬럼명 두가지를 사용할 수
				있다.
				 */
				System.out.print("eno : " + rs.getInt(1) + ", ");       
				System.out.println("ename : " + rs.getString("ename"));
			}
			
			/*
			자원반납(해제) : 모든 작업을 마친후에는 메모리 절약을
			위해 연결했던 자원을 반납한다.			
			 */
			rs.close();
			stmt.close();
			con.close();
		}catch(SQLException sqle)
		{
			System.out.println("Connection Error");
			sqle.printStackTrace();
		}
	}
}
