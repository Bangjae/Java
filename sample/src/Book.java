import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

/*public class userID{
	BookID id = new BookID();
	String Iss = BookID.signIn.getName();
}*/
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
		System.out.println("┌────[메뉴 선택]────┐");
		System.out.println("│1.    로 그 인     │");
		System.out.println("│2.    회원가입     │");
		System.out.println("│3.  비밀번호찾기   │");
		System.out.println("│4.      종료       │");
		System.out.println("└───────────────────┘");
		System.out.print("     선택 : ");
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
	ResultSet rs = null;
	
	public static void main(String[] args) {
		Book bid = new Book();
		bid.doIdRun();
	}
	
	public void doIdRun() {
		connectDB();	
		int choice;
		while(true) {
			showIdMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
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
		
	public void showAdminMenu()
	{
		System.out.println("┌────[메뉴 선택]────┐");
		System.out.println("│1.    책 등록      │");
		System.out.println("│2.    도서조회     │");
		System.out.println("│3.전체 리스트 조회 │");
		System.out.println("│4. 낡은 책 버리기  │");
		System.out.println("│5. 도서 전체 삭제  │");
		System.out.println("│6.관리자 모드 종료 │");
		System.out.println("└───────────────────┘");
		System.out.print("     선택 : ");
	}
	
	public void doAdminRun() {
		int choice;
		while(true) {
			showAdminMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
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
	
	public void showUserMenu() {
		System.out.println("┌────[메뉴 선택]────┐");
		System.out.println("│1.    도서조회     │");
		System.out.println("│2.    전체조회     │");
		System.out.println("│3.      대여       │");
		System.out.println("│4.      반납       │");
		System.out.println("│5.      연장       │");
		System.out.println("│6.    로그아웃     │");
		System.out.println("│7.    회원탈퇴     │");
		System.out.println("└───────────────────┘");
		System.out.print("     선택 : ");
	}
	
	public void doUserRun() {
		int choice;
		while(true) {
			showUserMenu();
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
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
				exBook();
				break;
			case 6:
				System.out.println("정상 로그아웃 되었습니다.");
				return;
			case 7:
				delUser();
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;	
			}
		}
	}
		
	public void signIn() {
		System.out.print("ID : ");
		String id = sc.nextLine();
		System.out.print("Password : ");
		String pw = sc.nextLine();
		try{	
			String sql = "select * from book_id where id = ? and pw = ?";
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, id);
			pstmt1.setString(2, pw);
			rs = pstmt1.executeQuery();
			int nResult = 0;
			while(rs.next()) {
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
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.(#3)");
		}
		
	}
	//pstmt1
	public void signUp() {
		System.out.print("ID : ");
		String id = sc.nextLine();
			try{	
			String sql = "select * from book_id where id = ?";
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
					String sql2 = "insert into book_id values(?, ?, ?, ?)";
					pstmt2 = con.prepareStatement(sql2);
					pstmt2.setString(1, id);
					pstmt2.setString(2, pw);
					pstmt2.setString(3, name);
					pstmt2.setString(4, "0");
					int updateCount = pstmt2.executeUpdate();
				if(updateCount == 1) {
					System.out.println("회원가입이 정상적으로 처리 되었습니다.");
				}else {
					System.out.println("데이터 입력에 실패했습니다.(#가입오류)");
				}
				//System.out.println("insertCount : " + updateCount);
			
				}catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("데이터 입력에 실패했습니다.(#데이터예외)");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.(#3)");
		}	
	}
	//pstmt2,10
	public void allBook() {
		try{	
			String sql = "select * from mybook order by booknum";
			pstmt3 = con.prepareStatement(sql);
			rs = pstmt3.executeQuery();
		
			while(rs.next()) {
				System.out.println("책번호 : "+rs.getString("bookNum"));
				System.out.println("제  목 : "+rs.getString("bookName"));
				System.out.println("수  량 : " + rs.getInt("quant"));
				System.out.println("----------------------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//pstmt3
	public void delBook() {
		System.out.print("삭제할 책 제목 : ");
		String bookName = sc.nextLine();
		try{	
			String sql = "delete from mybook where bookName = ?";
			pstmt4 = con.prepareStatement(sql);
			pstmt4.setString(1, bookName);
			System.out.println("데이터베이스에서 삭제되었습니다.");
			int updateCount = pstmt4.executeUpdate();
			System.out.println("DropCount : " + updateCount);
		}catch(Exception e) {
			System.out.println("데이터베이스 삭제 에러입니다.");
		}
	}
	//pstmt4
	public void alldelBook() {
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("1. 예 / 2. 아니오 : ");
		int choice;
		while(true) {
			choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				try{	
					String sql = "delete from mybook";
					pstmt5 = con.prepareStatement(sql);
					System.out.println("데이터베이스에서 삭제되었습니다.");
					int updateCount = pstmt5.executeUpdate();
					//System.out.println("DropCount : " + updateCount);
				}catch(Exception e) {
					System.out.println("데이터베이스 삭제 에러입니다.");
				}
				return;
			default:
				System.out.println("작업을 취소합니다.");
				return;
			}
		}
	}
	//pstmt5
	public void delUser() {
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
				String sql = "delete from book_id where id = ? and pw = ?";
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
	//pstmt6
	public void addBook() {
		System.out.print("책 번호 : ");
		String bookNum = sc.nextLine();
		System.out.print("제목 : ");
		String bookName = sc.nextLine();
		System.out.print("권수 : ");
		int quant = sc.nextInt();
		if (quant == 0)
			quant = 0;
		try
		{
			String sql = "insert into mybook values(?, ?, ?)";
			pstmt7 = con.prepareStatement(sql);
			pstmt7.setString(1, bookNum);
			pstmt7.setString(2, bookName);
			pstmt7.setInt(3, quant);
			int updateCount = pstmt7.executeUpdate();
			//System.out.println("InsertCount : " + updateCount);
		if(updateCount == 1) {
			System.out.println("데이터가 정상적으로 추가되었습니다.");
		}else {
			System.out.println("데이터 입력에 실패했습니다.(#추가오류)");
		}
		System.out.println("insertCount : " + updateCount);
	
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.(#데이터예외)");
		}
	}
	//pstmt7
	public void selBook() {
		System.out.print("조회할 책 제목 : ");
		String bookName = sc.nextLine();
		try{	
			String sql = "select * from mybook where bookName = ?";
			pstmt8 = con.prepareStatement(sql);
			pstmt8.setString(1, bookName);
			rs = pstmt8.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				System.out.println("책번호 : " + rs.getString("bookNum"));
				System.out.println("제  목 : " + rs.getString("bookName"));
				System.out.println("수  량 : " + rs.getInt("quant"));
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
	//pstmt8
	public void findPw() {
		System.out.print("비밀번호 조회할 아이디를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("본인의 성함을 입력하세요 : ");
		String name = sc.nextLine();
		try{	
			String sql = "select * from book_id where id = ? and name = ?";
			pstmt9 = con.prepareStatement(sql);
			pstmt9.setString(1, id);
			pstmt9.setString(2, name);
			rs = pstmt9.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				rs.getString("id");
				rs.getString("pw");
				System.out.println(rs.getString("name")+"님의 비밀번호는 "+rs.getString("pw")+" 입니다.");
			}
			if (nResult == 0)
			{
				System.out.println("입력된 정보가 다르거나");
				System.out.println("등록되어 있지 않은 계정입니다.");
				System.out.println("----------------------------------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("입력에 실패했습니다.(#3)");
		}	
	}
	//pstmt9
	public void rentBook() 
	{
		System.out.print("본인확인을 위해 비밀번호를 입력해주세요 : ");
		String pw = sc.nextLine();
			
			try{	
				String sql = "select * from book_id where pw = ?";
				pstmt11 = con.prepareStatement(sql);
				pstmt11.setString(1, pw);
				rs = pstmt11.executeQuery();
				int nResult = 0;
				while(rs.next()) {
					nResult++;
					String id = rs.getString("id");
					LocalDate sysdate = LocalDate.now();
					LocalDate rentdate = sysdate;
					LocalDate backdate = sysdate.plusWeeks(1);
					System.out.print("대여할 도서의 번호를 입력하세요 : ");
					String bookNum = sc.nextLine(); 
					
					try
					{	
						sql = "insert into rentalbook values(?, ?, ?, ?, 0)";
						pstmt12 = con.prepareStatement(sql);
						pstmt12.setString(1, id);
						pstmt12.setString(2, bookNum);
						pstmt12.setDate(3,java.sql.Date.valueOf(rentdate));
						pstmt12.setDate(4,java.sql.Date.valueOf(backdate));
						System.out.println("");
						
						System.out.println("대여가 정상적으로 완료되었습니다");
						
						int updateCount = pstmt12.executeUpdate();
						//System.out.println("InsertCount : " + updateCount);
						
					}catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("입력에 실패했습니다.(#4)");
					}
					
				}
				if (nResult == 0){
					System.out.println("회원 정보가 일치하지 않습니다.");
				}
			}
		catch(Exception e) {
			
			System.out.println("입력에 실패했습니다.(#3)");
			e.printStackTrace();
		}
	}
	//pstmt11,12
	public void returnBook() {
		System.out.print("본인확인을 위해 비밀번호를 입력해주세요 : ");
		String pw = sc.nextLine();
			try{	
				String sql = "select * from book_id where pw = ?";
				pstmt13 = con.prepareStatement(sql);
				pstmt13.setString(1, pw);
				rs = pstmt13.executeQuery();
				int nResult = 0;
				while(rs.next()) {
					nResult++;
					String id = rs.getString("id");
					System.out.print("반납할 책 번호 입력하세요 : ");
					String bookNum = sc.nextLine();
			    	sql = "delete from rentalbook where bookNum = ? and id = ?";
    	
			    	try {
			    		pstmt14 = con.prepareStatement(sql);
						pstmt14.setString(1, bookNum);
						pstmt14.setString(2, id);
						System.out.println("반납이 정상처리 되었습니다." );
						
						int updateCount = pstmt14.executeUpdate();
						//System.out.println("DropCount : " + updateCount);
						
			    	}catch(Exception e) {
			    		System.out.println("반납 에러입니다." );
			    		e.printStackTrace();
		    	}if (nResult == 0){
					System.out.println("회원 정보가 일치하지 않습니다.");
				}
			}
		}catch(Exception e) {
			
			System.out.println("입력에 실패했습니다.(#3)");
			e.printStackTrace();
		}	
	}
	//pstmt13,14
	public void exBook() {
		System.out.println("본인의 ID를 입력하세요 : ");
		String id = sc.nextLine();
		System.out.print("연장할 책 번호 입력하세요 : ");
		String bookNum = sc.nextLine();
		try{	
			String sql = "select * from rentalbook where booknum = ? and id = ?";
			pstmt15 = con.prepareStatement(sql);
			pstmt15.setString(1, id);
			rs = pstmt15.executeQuery();
			int nResult = 0;
			while(rs.next()) {
				nResult++;
				rs.getInt("excount");
				int count = rs.getInt("excount");
				if(count==0) {
					try 
			    	{
			    		sql = "update rentalbook set backdate = backdate +7, "
				    			+ "excount = 1 "
				    			+ "where bookNum = ? and id = ?";
			    		pstmt16 = con.prepareStatement(sql);
						pstmt16.setString(1, bookNum);
						pstmt16.setString(2, id);
						
						System.out.println("연장이 정상처리 되었습니다." );
						int updateCount = pstmt16.executeUpdate();
						
						System.out.println("ExCount : " + updateCount);
			    	}catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("데이터 입력에 실패했습니다.(#6)");
					}
				}
				else 
				{
					System.out.println("2회 연속으로 연장할 수 없습니다.");
				}
				
			}if (nResult == 0){
				System.out.println("등록된 정보가 없습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("데이터 입력에 실패했습니다.(#7)");
		}	
	}
	

	/*public void exBook() {
		
		System.out.print("본인확인을 위해 비밀번호를 입력해주세요 : ");
		String pw = sc.nextLine();
			try{	
				String sql = "select * from book_id where pw = ?";
				pstmt15 = con.prepareStatement(sql);
				pstmt15.setString(1, pw);
				rs = pstmt15.executeQuery();
				int nResult = 0;
				while(rs.next()) {
					nResult++;
					String id = rs.getString("id");
					System.out.print("연장할 책 번호 입력하세요 : ");
					String bookNum = sc.nextLine();
			    	
			    	try 
			    	{
			    		sql = "update rentalbook set backdate = backdate +7, "
				    			+ "excount = 1 "
				    			+ "where bookNum = ? and id = ?";
			    		pstmt16 = con.prepareStatement(sql);
						pstmt16.setString(1, bookNum);
						pstmt16.setString(2, id);
						
						System.out.println("연장이 정상처리 되었습니다." );
						int updateCount = pstmt16.executeUpdate();
						
						System.out.println("ExCount : " + updateCount);
						
			    	}catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("2회 연속으로 연장할 수 없습니다.(#6)");
					}
			    	
		    	}if (nResult == 0){
					System.out.println("회원 정보가 일치하지 않습니다.");
				}
			}
    	catch(Exception e) {
			
			System.out.println("입력에 실패했습니다.(#7)");
			e.printStackTrace();
		}
	}*/
	
	//pstmt15,16
	public void connectDB() {
		try 
		{
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"scott",
					"TIGER");
		}catch(Exception e){
			e.printStackTrace();
		}finally {}
	}
	public void disconnectDB() {
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
			
			if(con != null) con.close();
		}catch(Exception e) {
			
		}
	}
}
