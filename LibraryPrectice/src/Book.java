import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Book
{
	static {
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
		}
	}
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		Book bid = new Book();
		bid.doIdRun();
	}
	
	public void doIdRun()
	{	
		int choice;
		while(true) 
		{
			showIdMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice)
			{
			case 1:
				signIn();
				break;
			case 2:
				signUp();
				break;
			case 3:
				findPw();
				break;
			case 4:
				System.out.println("프로그램을 종료합니다.");
				System.out.println("이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;	
			}
		}
	}

	public static void admin()
	{
		Connection con = null;  
		PreparedStatement pstm =null;
		ResultSet rs = null;
		
		try 
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int choice;
		while(true) 
		{
			showMeun();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) 
			{
			case 1:
				signIn();
				break;
			case 2:
				signUp();
				break;
			case 3:
				findPw();
				break;
			case 4:
				disconnectDB();
				System.out.println("프로그램을 종료합니다.");
				System.out.println("이용해주셔서 감사합니다.");
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;	
			}
		}
	}
	
	public void showIdMenu()
	{
		System.out.println("도서관리 프로그램입니다.");
		System.out.println();
		System.out.println("========메뉴 선택========");
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("3.비밀번호찾기");
		System.out.println("4.종료");
		System.out.println("=======================");
		System.out.print("메뉴 선택 : ");
	}
	
	private static void disconnectDB()
	{
		// TODO Auto-generated method stub
		
	}

	private static void findPw()
	{
		// TODO Auto-generated method stub
		
	}

	private static void signUp()
	{
		// TODO Auto-generated method stub
		
	}

	private static void signIn()
	{
		Connection con = null;  
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pw = sc.nextLine();
		try{	
			con = DriverManager.getConnection( 
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from memberDB where id = ? and pw = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				rs.getString("id");
				rs.getString("pw");
				System.out.println(rs.getString("name")+"님의 방문을 환영합니다.");
				if(id.equals("admin") && pw.equals("1234"))
				{
//					doAdminRun();
				}
				else
				{
//					doUserRun();								
				}
				System.out.println("----------------------------------------");
			}
			if (nResult == 0)
			{
				System.out.println("아이디 또는 패스워드가 다르거나");
				System.out.println("등록되어 있지 않은 계정입니다.");
				System.out.println("----------------------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.");
		}
		
	}

	
/////////////////////////////////대여관리관련///////////////////////////////////////
	public static void showMeun()
	{
		while(true) 
		{
		
		System.out.println("도서관리 프로그램입니다.");
		System.out.println("============메뉴==============");
		System.out.println("1.도서전체리스트조회");
		System.out.println("2.신규도서등록");
		System.out.println("3.도서조회");
		System.out.println("4.낡은책버리기");
		System.out.println("0.프로그램 종료");
		
		int choice;
		
		System.out.println("선택하세요: ");
		choice = sc.nextInt();
		sc.nextLine();
		
		System.out.println("선택한 메뉴 "+ choice);
		
		System.out.println();
		switch(choice)
			{
			case 1 : 
				printBooks();
				break;
			case 2 :
				insertBook();
				break;
			case 3 :
				searchBook();
				break;
			case 4 :
				deleteBook();
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다.");
				break;
			default :
				System.out.println("올바른 번호를 다시 입력해주세요.");
			}
		}
	}

	private static void printBooks()
	{
		Connection con = null;  
		PreparedStatement pstm =null;
		ResultSet rs = null;
		
		try{	
			con = DriverManager.getConnection( 
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from BOOKDB";
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
		
			while(rs.next()) {
				System.out.println("책번호 : "+rs.getString("book_id"));
				System.out.println("제  목 : "+rs.getString("book_Name"));
				System.out.println("수  량 : " + rs.getInt("book_count"));
				System.out.println("----------------------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void insertBook()
	{
		System.out.println("신규도서등록");
		System.out.println("책 번호 : ");
		String bookNum = sc.nextLine();
		System.out.println("제목 : ");
		String bookName = sc.nextLine();
		System.out.println("권수 : ");
		int quant = sc.nextInt();
		if (quant == 0)
			quant = 0;
		
		Connection con = null;  
		PreparedStatement pstm =null;
//		ResultSet rs = null; //결과조회 함수
		try
		{
			 con = DriverManager.getConnection( 
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			
			String sql = "insert into BOOKDB values(?, ?, ?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, bookNum);
			pstm.setString(2, bookName);
			pstm.setInt(3, quant);
			int updateCount = pstm.executeUpdate();
			//System.out.println("InsertCount : " + updateCount);
		if(updateCount == 1) {
			System.out.println("데이터가 정상적으로 추가되었습니다.");
		}else {
			System.out.println("데이터 입력에 실패했습니다.(올바른 명령을 입력해주세요.)");
		}
		System.out.println("insertCount : " + updateCount);
	
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.(올바른 명령을 입력해주세요.)");
		}
		
	}

	
	private static void searchBook()
	{
		System.out.print("조회할 책 제목 : ");
		String bookName = sc.nextLine();
		
		Connection con = null;  
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		try{	
			con = DriverManager.getConnection( 
					"jdbc:oracle:thin:@localhost:1521:xe",
					"study",
					"1234");
			
			String sql = "select * from bookdb where book_Name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookName);
			rs = ps.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				System.out.println("책번호 : " + rs.getString("book_id"));
				System.out.println("제  목 : " + rs.getString("book_Name"));
				System.out.println("수  량 : " + rs.getInt("book_count"));
				System.out.println("----------------------------------------");
			}
			if (nResult == 0)
			{
				System.out.println("조회할 데이터가 없습니다.");
				System.out.println("----------------------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.(#3)");
		}
	}

	
	private static void deleteBook()
	{
		
		Connection con = null;  
		PreparedStatement ps =null;
		ResultSet rs = null;
		
		System.out.println("낡은책 버리기 목록입니다.");
		System.out.print("버릴책 제목 : ");
		String bookName = sc.nextLine();
		try{	
			 con = DriverManager.getConnection( 
						"jdbc:oracle:thin:@localhost:1521:xe",
						"study",
						"1234");
			 
			String sql = "delete from bookdb where book_Name = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, bookName);
			System.out.println("데이터베이스에서 삭제되었습니다.");
			int updateCount = ps.executeUpdate();
			System.out.println("DropCount : " + updateCount);
		}catch(Exception e) {
			System.out.println("데이터베이스 삭제 에러입니다.");
		}
	}




		
}