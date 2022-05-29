package Main;

public class Article {

	private int id;
	private String title;
	private String body;
	private String nickname;
	private String regDate;
	private int hit;
	
	
	public Article(int id, String title, String body, String nickname, String regDate, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.nickname = nickname;
		this.regDate = regDate;
		this.hit = hit;
	}


	
	
	public String getNickname() {
		return nickname;
	}




	public void setNickname(String nickname) {
		this.nickname = nickname;
	}




	public int getHit() {
		return hit;
	}



	public void setHit(int hit) {
		this.hit = hit;
	}



	public String getRegDate() {
		return regDate;
	}



	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}