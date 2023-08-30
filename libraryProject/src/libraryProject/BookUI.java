package libraryProject;

import java.util.Scanner;

public class BookUI
{
	private Scanner sc = new Scanner(System.in);
	private BookManager bm = new BookManager();
	private LoanManager lm = new LoanManager();
	
	public BookUI()
	{
		boolean run = true;
		while(run)
		{
			printMainMeun();
			int menu = sc.nextInt();
			
			switch(menu)
			{
			case 1 :
				bookInsert();
				break;
			case 2 :
				bookSelect();
				break;
			case 3 : 
				loanRegist();
				break;
			case 4 :
				loanSelect();
				break;
			case 5 :
				loanReturn();
				break;
			case 0 :
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			default : 
				break;
			}
		}
	}
	
	private void bookInsert() 
	{
		sc.nextLine();
		
		String isbn, bname;
		// isbn = 도서고유번호, 도서명
		int bcount;
		// 수량
		System.out.println("[ 도서 정보 등록]");
		System.out.println("도서 ISBN: ");
		isbn = sc.nextLine();
		System.out.println("도서명: ");
		bname = sc.nextLine();
		System.out.println("수량:");
		
		Book book = new Book(isbn,bname,bcount);
		
		int result = bm.productInsert(book);
		
		if(result ==1)
		{
			System.out.println("*등록되었습니다.");
		}
		else
		{
			System.out.println("*등록 실패하였습니다.");
		}
		
	}
	public class BookManager 
	{
		private BookDAO dao = new BookDAO();
		
		public int productInsert(Book book)
		{
			int result = dao.produckInsert(book);
			return result;
		}
	}
	public class BookDAO
	{
		private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
		
		public int productInsert(Book book)
		{
			SqlSession session = null;
			int result = 0;
		}
		
		try 
		{
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BOookMapper.class);
			result = mapper.bookInsert(book);
			session.commit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(session != null) session.close();
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		

	}
}
