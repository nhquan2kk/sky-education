package BEAN;

import java.util.Date;

public class Result {
	private int resultId;
	private int correctAnswer;
	private int incorrectAnswer;
	private Date examCompletionTime;
	private int examinationId;
	private int memberId;

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getIncorrectAnswer() {
		return incorrectAnswer;
	}

	public void setIncorrectAnswer(int incorrectAnswer) {
		this.incorrectAnswer = incorrectAnswer;
	}

	public Date getExamCompletionTime() {
		return examCompletionTime;
	}

	public void setExamCompletionTime(Date examCompletionTime) {
		this.examCompletionTime = examCompletionTime;
	}

	public int getExaminationId() {
		return examinationId;
	}

	public void setExaminationId(int examinationId) {
		this.examinationId = examinationId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
}
