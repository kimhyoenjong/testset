package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	LoginFlag flag;
	ArticleRepository repo = new ArticleRepository();
	ArticleView articleView = new ArticleView();	
	//boolean isLoginFlag = false;
	Member loginedMember = null;
	
	Scanner sc = new Scanner(System.in);
	
	public Board() {
		repo.makeTestData();
		//isLoginFlag = true;
		loginedMember = repo.getMemberByLoginId("hong123");
	}
	
	public void run() {	
		
		while (true) {
			
			String cmd = printInputCommand();

			if (cmd.equals("help")) {
				articleView.printHelp();

			} else if (cmd.equals("add")) {
				
				if(isLogined()) {					
					addArticle();
				}
				
				
			} else if (cmd.equals("list")) {
				articleView.printArticles(repo.getArticles());

			} else if (cmd.equals("update")) {
				updateArticle();
				articleView.printArticles(repo.getArticles());
				
			} else if(cmd.equals("search")) {
				searchArticles();				
				
			} else if(cmd.equals("read")) {
				if(isLogined()) {
					readArticle();					
				}
				
			} else if(cmd.equals("delete")) {
				deleteArticle();
				
			} else if(cmd.equals("signup")) {
				signup();
				
			} else if(cmd.equals("mlist")) {
				articleView.printMembers(repo.getMembers());
				
			} else if(cmd.equals("login")) {
				login();
				
			} else if(cmd.equals("logout")) {
				logout();
				
			} else if (cmd.equals("exit")) {
				System.out.println("프로그램을 종료합니다.");
				break;

			} else {
				System.out.println("알 수 없는 명령어입니다.");
			}
		}
	}
	
	private boolean isLogined() {
		if(loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}
		
		return true;
	}

	private String printInputCommand() {
		
		if(loginedMember != null) {
			System.out.printf("(%s(%s))  >>  ", loginedMember.getNickname(), loginedMember.getLoginId());
			
		} else {				
			System.out.print(">>  ");
			
		}
		
		String cmd = sc.nextLine();
		
		return cmd;
	}

	private void logout() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
		
	}

	private void login() {
		System.out.print("아이디 :");
		String loginId = sc.nextLine();

		System.out.print("비밀번호 :");
		String loginPw = sc.nextLine();
		
		LoginFlag result = repo.doLogin(loginId, loginPw);
		
		if(result == flag.LOGIN_SUCCESS) {
			Member member = repo.getMemberByLoginId(loginId);
			loginSuccessProcess(member);
			
		} else if(result == flag.NOT_EXIST_LOGIN_ID) {
			System.out.println("없는 아이디입니다.");
			
		} else {
			System.out.println("비밀번호를 틀렸습니다.");
			
		}
		
	}

	private void loginSuccessProcess(Member member) {
		// 1. 환영인사.
		System.out.printf("%s님 안녕하세요!!\n", member.getNickname());
	
		// 2. 로그인 유저 정보 세팅
		loginedMember = member;
		
	}

	private void signup() {
		System.out.print("아이디 :");
		String loginId = sc.nextLine();

		System.out.print("비밀번호 :");
		String loginPw = sc.nextLine();
		
		System.out.print("이름 :");
		String nickname = sc.nextLine();
		
		repo.addMember(loginId, loginPw, nickname);
		System.out.println("회원가입이 완료되었습니다.");
	}

	private void deleteArticle() {		
		System.out.print("삭제 할 게시물 번호 : ");
		int targetId = Integer.parseInt(sc.nextLine());
		
		Article article = repo.getArticleOne(targetId); 		
		repo.deleteArticle(article);		
		
		System.out.println("삭제가 완료되었습니다.");
		
	}

	private void readArticle() {
		
		System.out.print("상세보기 할 게시물 번호 : ");
		int targetId = Integer.parseInt(sc.nextLine());		
		
		Article article = repo.getArticleOne(targetId);
		
		if(article == null) {
			System.out.println("없는 게시물입니다.");
			
		} else {
			repo.increaseReadCnt(article);
			articleView.printArticleDetail(article);
			readProcess();
		}
	}
	
	private void readProcess() {
		
		while(true) {
			System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
			int readCmdNo = Integer.parseInt(sc.nextLine());
			
			if(readCmdNo == 1) {
				System.out.println("[댓글등록]");
			} else if(readCmdNo == 2) {
				System.out.println("[추천]");
			} else if(readCmdNo == 3) {
				System.out.println("[수정]");
			} else if(readCmdNo == 4) {
				System.out.println("[삭제]");
			} else if(readCmdNo == 5) {
				break;
			} 			
		}
		
		
		
		
	}

	private void searchArticles() {
		
		System.out.print("검색 키워드를 입력해주세요 : ");
		String keyword = sc.nextLine();
		
		ArrayList<Article> searchedList = repo.getSearchedArticleList(keyword);		
		articleView.printArticles(searchedList);
		
	}
	
	public void updateArticle() {
		// CRUD

		System.out.print("수정할 게시물 번호 : ");
		int targetId = Integer.parseInt(sc.nextLine());
		
		Article article = repo.getArticleOne(targetId);

		if(article == null) {
			System.out.println("없는 게시물입니다.");
		} else {		
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();
				
			repo.updateArticle(article, title, body);
	
			System.out.println("수정이 완료되었습니다.");
		}
//		printArticles();
		
	}

	public void addArticle() {
		System.out.print("제목 :");
		String title = sc.nextLine();

		System.out.print("내용 :");
		String body = sc.nextLine();
		
		repo.addArticle(title, body, loginedMember.getNickname());
		System.out.println("게시물이 저장되었습니다.");

	}
	
	
}