import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class Book
{
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
	Scanner sc = new Scanner(System.in);
	
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
	Connection con = null;
	PreparedStatement pstmt1 = null;
	PreparedStatement pstmt2 = null;
	PreparedStatement pstmt3 = null;
	PreparedStatement pstmt4 = null;
	PreparedStatement pstmt5 = null;
	PreparedStatement pstmt6 = null;
	PreparedStatement pstmt7 = null;
	PreparedStatement pstmt8 = null;
	PreparedStatement pstmt9 = null;
	PreparedStatement pstmt10 = null;
	PreparedStatement pstmt11 = null;
	PreparedStatement pstmt12 = null;
	PreparedStatement pstmt13 = null;
	PreparedStatement pstmt14 = null;
	PreparedStatement pstmt15 = null;
	PreparedStatement pstmt16 = null;
	PreparedStatement pstmt17 = null;
	PreparedStatement pstmt18 = null;
	ResultSet rs = null;
	
	

	public static void main(String[] args)
	{
		Book bid = new Book();
		bid.doIdRun();
	}
	
	
	
	public void doIdRun()
	{
		connectDB();	
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


private void signIn()
	{
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pw = sc.nextLine();
	
		try
		{	
			String sql = "select * from memberDB where id = ? and pw = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			pstmt1.setString(2, pw);
			rs = pstmt1.executeQuery();
			int nResult = 0;
			while(rs.next()) 
			{
				nResult++;
				rs.getString("id");
				rs.getString("pw");
				System.out.println(rs.getString("name")+"님의 방문을 환영합니다.");
				
				if(id.equals("admin") && pw.equals("1234"))
				{
					doAdminRun();
				}
				else
				{
					doUserRun();								
				}	
				System.out.println("----------------------------------------");
			}
			if (nResult == 0)
			{
				System.out.println("아이디 또는 패스워드가 다르거나");
				System.out.println("등록되어 있지 않은 계정입니다.");
				System.out.println("----------------------------------------");
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();
		System.out.println("입력에 실패했습니다.");
		}
	}

public void showAdminMenu()
{
	System.out.println("=========메뉴 선택=========");
	System.out.println("1.책 등록");
	System.out.println("2.도서조회");
	System.out.println("3.전체 리스트 조회");
	System.out.println("4.낡은 책 버리기");
	System.out.println("5.도서 전체 삭제");
	System.out.println("6.관리자 모드 종료 ");
	System.out.println("===========================");
	System.out.print("선택 : ");
}

private void doAdminRun()
{
	int choice;
	while(true) 
	{
		showAdminMenu();
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
		case 1:
			addBook();
			break;
		case 2:
			selBook();
			break;
		case 3:
			allBook();
			break;
		case 4:
			delBook();
			break;
		case 5:
			alldelBook();
			break;				
		case 6:
			System.out.println("관리자 모드를 종료합니다.");
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;	
		}
	}
}

private void alldelBook()
{
	System.out.println("정말 삭제하시겠습니까?");
	System.out.println("1. 예 / 2. 아니오 : ");
	int choice;
	while(true) 
	{
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
		case 1:
			try
			{	
				String sql = "delete from BOOKDB";
				pstmt5 = con.prepareStatement(sql);
				System.out.println("전체삭제되었습니다.");
				int updateCount = pstmt5.executeUpdate();
				//System.out.println("DropCount : " + updateCount);
			}
			catch(Exception e)
			{
				System.out.println("삭제를 실패하였습니다.");
			}
			return;
		default:
			System.out.println("작업을 취소합니다.");
			return;
		}
	}
	
}



private void delBook()
{
	System.out.print("삭제할 책 제목 : ");
	String bookName = sc.nextLine();
	try{	
		String sql = "delete from BOOKDB where book_Name = ?";
		pstmt4 = con.prepareStatement(sql);
		pstmt4.setString(1, bookName);
		System.out.println("데이터베이스에서 삭제되었습니다.");
		int updateCount = pstmt4.executeUpdate();
		System.out.println("DropCount : " + updateCount);
	}catch(Exception e) {
		System.out.println("데이터베이스 삭제 에러입니다.");
	}
}



private void allBook()
{
	try
	{	
		String sql = "select * from BOOKDB order by book_ID";
		pstmt3 = con.prepareStatement(sql);
		rs = pstmt3.executeQuery();
	
		while(rs.next()) 
		{
			System.out.println("책번호 : "+rs.getString("book_ID"));
			System.out.println("제  목 : "+rs.getString("book_Name"));
			System.out.println("수  량 : " + rs.getInt("BOOK_COUNT"));
			System.out.println("----------------------------------------");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}



private void selBook()
{
	System.out.print("조회할 책 제목 : ");
	String bookName = sc.nextLine();
	try
	{	
		String sql = "select * from BOOKDB where BOOK_NAME = ?";
		pstmt8 = con.prepareStatement(sql);
		pstmt8.setString(1, bookName);
		rs = pstmt8.executeQuery();
		int nResult = 0;
		while(rs.next())
		{
			nResult++;
			System.out.println("책번호 : " + rs.getString("BOOK_ID"));
			System.out.println("제  목 : " + rs.getString("BOOK_NAME"));
			System.out.println("수  량 : " + rs.getInt("BOOK_COUNT"));
			System.out.println("----------------------------------------");
		}
		if (nResult == 0)
		{
			System.out.println();
			System.out.println("검색하신 이름의 책이 없습니다.");
			System.out.println("===============================");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("데이터 입력에 실패했습니다.");
	}
}



private void addBook()
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
	ResultSet rs = null; 
	
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
	if(updateCount == 1) 
	{
		System.out.println("데이터가 정상적으로 추가되었습니다.");
	}
	else 
	{
		System.out.println("데이터 입력에 실패했습니다.(올바른 명령을 입력해주세요.)");
	}
	System.out.println("insertCount : " + updateCount);

	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println("데이터 입력에 실패했습니다.(올바른 명령을 입력해주세요.)");
	}
	finally
	{
		try 
		{
			if(rs!=null)rs.close();
			if(pstm!=null)pstm.close();
			if(con!=null)con.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
}



public void showUserMenu() 
{
	System.out.println("=========메뉴 선택=========");
	System.out.println("1.도서조회");
	System.out.println("2.전체조회");
	System.out.println("3.대여");
	System.out.println("4.반납");
	System.out.println("5.로그아웃");
	System.out.println("6.회원탈퇴");
	System.out.println("=========================");
	System.out.print("선택 : ");
}


private void doUserRun()
{
	int choice;
	while(true) 
	{
		showUserMenu();
		choice = sc.nextInt();
		sc.nextLine();
		switch(choice)
		{
		case 1:
			selBook();
			break;
		case 2:
			allBook();
			break;
		case 3:
			rentBook();
			break;
		case 4:
			returnBook();
			break;
		case 5:
			System.out.println("정상 로그아웃 되었습니다.");
			return;
		case 6:
			delUser();
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			break;	
		}
	}
}



private void delUser()
{
	System.out.print("회원탈퇴를 위해 본인의 아이디를 입력하세요 : ");
	String id = sc.nextLine();
	System.out.print("비밀번호를 입력하세요 : ");
	String pw = sc.nextLine();
	if(id.equals("admin") && pw.equals("1234"))
	{
		System.out.println("관리자는 탈퇴할 수 없습니다.");
		System.out.println("----------------------------------------");
	}
	else
	{
		try{	
			String sql = "delete from memberDB where id = ? and pw = ?";
			pstmt6 = con.prepareStatement(sql);
			pstmt6.setString(1, id);
			pstmt6.setString(2, pw);
			System.out.println("");
			System.out.println("회원탈퇴가 완료 되었습니다.");
			System.out.println("시스템에서 로그아웃 됩니다.");
			int updateCount = pstmt6.executeUpdate();
			System.out.println("DropCount : " + updateCount);
		}catch(Exception e) {
			System.out.println("데이터베이스 삭제 에러입니다.");
		}							
	}
}

private void returnBook()
{
	System.out.print("본인확인을 위해 비밀번호를 입력해주세요 : ");
	String pw = sc.nextLine();
		try{	
			String sql = "select * from memberDB where pw = ?";
			pstmt13 = con.prepareStatement(sql);
			pstmt13.setString(1, pw);
			rs = pstmt13.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				String memeber_no = rs.getString("memeber_no");
				System.out.print("반납할 책 번호 입력하세요 : ");
				String bookNum = sc.nextLine();
		    	sql = "delete from rental where book_id = ? and memeber_no = ?";
	
		    	try {
		    		pstmt14 = con.prepareStatement(sql);
					pstmt14.setString(1, bookNum);
					pstmt14.setString(2, memeber_no);
					System.out.println("반납이 정상처리 되었습니다." );
					
					int updateCount = pstmt14.executeUpdate();
					//System.out.println("DropCount : " + updateCount);
					if(updateCount==1)
					{
						sql = "update BOOKDB set book_count = book_count+1 where BOOK_ID = ?";
						pstmt17 = con.prepareStatement(sql);
						pstmt17.setString(1, bookNum);
						pstmt17.executeUpdate();
					}
					return;
		    	}
		    	catch(Exception e)
		    	{
		    		System.out.println("반납 에러입니다." );
		    		e.printStackTrace();
		    	}
		    	
		    	if (nResult == 0){
				System.out.println("회원 정보가 일치하지 않습니다.");
			}
		}
	}catch(Exception e) {
		
		System.out.println("입력에 실패했습니다.");
		e.printStackTrace();
	}	
		
	
	
}



private void rentBook()
{
	System.out.print("본인확인을 위해 비밀번호를 입력해주세요 : ");
	String pw = sc.nextLine();
		
		try{	
			String sql = "select * from memberDB where pw = ?";
			pstmt11 = con.prepareStatement(sql);
			pstmt11.setString(1, pw);
			rs = pstmt11.executeQuery();
			int nResult = 0;
			
			while(rs.next()) {
				nResult++;
				String id = rs.getString("memeber_no");
				LocalDate sysdate = LocalDate.now();
				LocalDate rentdate = sysdate;
				LocalDate backdate = sysdate.plusWeeks(1);
				System.out.print("대여할 도서의 번호를 입력하세요 : ");
				String bookNum = sc.nextLine(); 
				
				try
				{	
					sql = "insert into rental values(returnbook.NextVal, ?, ?, ?, ?)";
					pstmt12 = con.prepareStatement(sql);
					pstmt12.setDate(1,java.sql.Date.valueOf(rentdate));
					pstmt12.setDate(2,java.sql.Date.valueOf(backdate));
					pstmt12.setString(3, id );
					pstmt12.setString(4, bookNum );
					
					System.out.println("");

					System.out.println("대여가 정상적으로 완료되었습니다");
					
					int updateCount = pstmt12.executeUpdate();	
					
					if(updateCount==1)
					{
						sql = "update BOOKDB set book_count = book_count-1 where Book_id=?";
						pstmt18 = con.prepareStatement(sql);
						pstmt18.setString(1, bookNum);
						pstmt18.executeUpdate();
					}
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("입력에 실패했습니다.");
				}
				return;
			}
			
			if (nResult == 0)
			{
				System.out.println("회원 정보가 일치하지 않습니다.");
			}
		}
	catch(Exception e) 
	{
		
		System.out.println("입력에 실패했습니다.");
		e.printStackTrace();
	}
	
}

private void signUp()
	{
	System.out.print("ID : ");
	String id = sc.nextLine();
		try{	
		String sql = "select * from memberDB where id = ?";
		pstmt10 = con.prepareStatement(sql);
		pstmt10.setString(1, id);
		rs = pstmt10.executeQuery();
		int nResult = 0;
		while(rs.next()) {
			nResult++;
			rs.getString("id");
			System.out.println(rs.getString("id")+"는 이미 사용중인 ID입니다.");
			System.out.println("");
		}
		if (nResult == 0)
		{
			System.out.println(id+"는 사용 가능한 ID입니다.");
			System.out.print("Password : ");
			String pw = sc.nextLine();
			System.out.print("성함 : ");
			String name = sc.nextLine();
			try
			{
				String sql2 = "insert into memberDB values(user_id.nextval, ?, ?, ?)";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setString(1, id);
				pstmt2.setString(2, name);
				pstmt2.setString(3, pw);
				int updateCount = pstmt2.executeUpdate();
			if(updateCount == 1) {
				System.out.println("회원가입이 정상적으로 처리 되었습니다.");
			}else {
				System.out.println("데이터 입력에 실패했습니다.(가입오류)");
			}
			//System.out.println("insertCount : " + updateCount);
		
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("데이터 입력에 실패했습니다.");
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("입력에 실패했습니다.");
	}	
		
	}



private void findPw()
	{
		System.out.print("비밀번호 조회할 아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("본인의 성함을 입력하세요 : ");
		String name = sc.nextLine();
		try
		{	
			String sql = "select * from memberDB where id = ? and name = ?";
			pstmt9 = con.prepareStatement(sql);
			pstmt9.setString(1, id);
			pstmt9.setString(2, name);
			rs = pstmt9.executeQuery();
			int nResult = 0;
			while(rs.next()) 
			{
				nResult++;
				rs.getString("id");
				rs.getString("pw");
			System.out.println(rs.getString("name")+"님의 비밀번호는 "
				+rs.getString("pw")+" 입니다.");
			}
			if (nResult == 0)
			{
				System.out.println("입력된 정보가 다르거나");
				System.out.println("등록되어 있지 않은 계정입니다.");
				System.out.println("----------------------------------------");
			}
		}
		catch(Exception e) 
		{
		e.printStackTrace();
		System.out.println("입력에 실패했습니다.");
		}	
	}

	////////////////////////////오라클 연결////////////////////////////////
	private void connectDB() 
	{
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
		finally {}
	}
	private void disconnectDB()
	{
		try
		{
			if(rs != null) rs.close();
			if(pstmt1 != null) pstmt1.close();
			if(pstmt2 != null) pstmt2.close();
			if(pstmt3 != null) pstmt3.close();
			if(pstmt4 != null) pstmt4.close();
			if(pstmt5 != null) pstmt5.close();
			if(pstmt6 != null) pstmt6.close();
			if(pstmt7 != null) pstmt7.close();
			if(pstmt8 != null) pstmt8.close();
			if(pstmt9 != null) pstmt9.close();
			if(pstmt10 != null) pstmt10.close();
			if(pstmt11 != null) pstmt11.close();
			if(pstmt12 != null) pstmt12.close();
			if(pstmt13 != null) pstmt13.close();
			if(pstmt14 != null) pstmt14.close();
			if(pstmt15 != null) pstmt14.close();
			if(pstmt16 != null) pstmt14.close();
			if(pstmt17 != null) pstmt17.close();
			if(pstmt18 != null) pstmt17.close();
			
			if(con != null) con.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
