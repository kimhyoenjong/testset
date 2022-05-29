package Main;

import java.util.ArrayList;

public class ArticleView {

//	public void printArticles(ArrayList<Article> articles) {
//		// list 전체 출력
//		for (int i = 0; i < articles.size(); i++) {
//			Article article = articles.get(i);
//
//			System.out.printf("번호: %d\n", article.getId());
//			System.out.printf("제목: %s\n", article.getTitle());
//			System.out.printf("등록일: %s\n", article.getRegDate());
//			System.out.printf("작성자 : %s\n", article.getNickname());
//			System.out.printf("조회수: %d\n", article.getHit());
//			System.out.println("==========================");
//		}
//	}

	public void printArticleDetail(Article article) {
		System.out.printf("========= %d번 게시물 =========\n", article.getId());
		System.out.printf("번호 : %d\n", article.getId());
		System.out.printf("제목 : %s\n", article.getTitle());
		System.out.printf("등록일: %s\n", article.getRegDate());
		System.out.printf("작성자 : %s\n", article.getNickname());
		System.out.printf("조회수: %d\n", article.getHit());
		System.out.println("------------------------------");
		System.out.printf("내용 : %s\n", article.getBody());
		System.out.println("------------------------------");
		System.out.println("===============================");
	}

	public void printHelp() {
		System.out.println("add    : 게시물 등록");
		System.out.println("list   : 게시물 목록");
		System.out.println("update : 게시물 수정");
		System.out.println("eixt   : 프로그램 종료");
	}

	public String add_padding(String str, int width) {

		int cnt = 0;

		for (int i = 0; i < str.length(); i++) {
			int ch = (int) str.charAt(i);
			if (ch >= 0 && ch <= 127) {
				cnt++;
			} else {
				cnt += 2;
			}
		}
		int pdLen = width - cnt;

		for (int i = 0; i < pdLen; i++) {
			str += " ";
		}

		return str;

	}

	public void f2(String s1, String s2, String s3, String s4, String s5) {
		
		
		if (s2.length() >= 10) {
			s2 = s2.substring(0, 7) + "...";
		}
		
		if (s3.length() >= 10) {
			s3 = s3.substring(0, 7) + "...";
		}

		System.out.print(add_padding(s1, 8));
		System.out.print(add_padding(s2, 20));
		System.out.print(add_padding(s3, 20));
		System.out.print(add_padding(s4, 13));
		System.out.print(add_padding(s5, 0));
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
	}

	public void printArticles(ArrayList<Article> articles) {
		f2("번호", "제목", "작성자", "등록날짜", "조회수");
		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			f2(String.valueOf(article.getId()), article.getTitle(), article.getNickname(), article.getRegDate(),
					String.valueOf(article.getHit()));
		}
	}
	
	public void printMembers(ArrayList<Member> members) {
		for(int i = 0; i < members.size(); i++) {
			System.out.printf("%s , %s \n", members.get(i).getLoginId(), members.get(i).getNickname());
		}
	}
	
}