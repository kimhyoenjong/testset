package Main;

import java.util.ArrayList;

enum LoginFlag {
	LOGIN_SUCCESS, // 0
	NOT_EXIST_LOGIN_ID, // 1
	NO_MATCH_PASS // 2
}

public class ArticleRepository {
	
	
	// final -> 변수를 상수화.
	LoginFlag flag;
		
	private ArrayList<Article> articles = new ArrayList<>();
	private ArrayList<Member> members = new ArrayList<>();
	
	private int articleId = 4;
	private int memberId = 1;

	public void makeTestData() {		
		
		
		Article a1 = new Article(1, "제목1", "내용1", "이순신", Util.getCurrentDate(), 20);
		Article a2 = new Article(2, "제목2", "내용2", "이순신", Util.getCurrentDate(), 10);
		Article a3 = new Article(3, "제목3", "내용3", "황진이", Util.getCurrentDate(), 30);
		
		Member m1 = new Member(1, "hong123", "h1234", "홍길동");
		Member m2 = new Member(2, "lee123", "lee123", "순신");
		
		articles.add(a1);
		articles.add(a2);
		articles.add(a3);
		
		members.add(m1);
		members.add(m2);
		
	}
	
	// C
	
	public void addArticle(String title, String body, String nickname) {		
				
		Article article = new Article(articleId, title, body, nickname, Util.getCurrentDate(), 0);
		articles.add(article);
		articleId++;
	}
	
	// R
	public Article getArticleOne(int id) {
		
		// id를 번호로 하는 게시물이 존재하는지, 존재하면 리턴.
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).getId() == id) {
				return articles.get(i); 
			}
		}
		
		
		return null;
	}
	
	public ArrayList<Article> getSearchedArticleList(String keyword) {
		
		ArrayList<Article> searchedArticleList = new ArrayList<>();
		
		for(int i = 0; i < articles.size(); i++) {
			if(articles.get(i).getTitle().contains(keyword)) {
				searchedArticleList.add(articles.get(i));
			}
		}
		
		return searchedArticleList;
	}
	
	public ArrayList<Article> getArticles() {
		return this.articles;
	}
	
	public ArrayList<Member> getMembers() {
		return this.members;
	}
	
	// U
	public void updateArticle(Article article, String title, String body) {
		article.setTitle(title);
		article.setBody(body);
	}
	
	// D
	public void deleteArticle(Article article) {
		articles.remove(article);
	}

	public void increaseReadCnt(Article article) {
		article.setHit(article.getHit() + 1);
	}

	public void addMember(String loginId, String loginPw, String nickname) {		
		Member member = new Member(memberId, loginId, loginPw, nickname);
		members.add(member);
		memberId++;			
	}

	
	
	public LoginFlag doLogin(String loginId, String loginPw) {		
		
		Member member = getMemberByLoginId(loginId);
		
		if(member == null) {
			return flag.NOT_EXIST_LOGIN_ID;
		}
		
		if(checkMemberPassword(member, loginPw)) {
			return flag.LOGIN_SUCCESS;
		}
		
		return flag.NO_MATCH_PASS;
		
	}
	
	public Member getMemberByLoginId(String loginId) {
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			
			if(member.getLoginId().equals(loginId)) {
				return member;
			}
		}
		
		return null;
	}
	
	public boolean checkMemberPassword(Member member, String inputPw) {
		if(member.getLoginPw().equals(inputPw)) {
			return true; 
		}
		
		return false;
	}
}