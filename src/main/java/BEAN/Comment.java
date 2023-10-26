package BEAN;

public class Comment {
	private int commentId;
	private int memberId;
	private int grammarId;
	private String content;
	private String username;
	
	public int getGrammarId() {
		return grammarId;
	}
	public void setGrammarId(int grammarId) {
		this.grammarId = grammarId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
