package BEAN;

public class Answer {
	private int number;
	private String answer;

	private boolean isCorrect;
	private String correct;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}
}
