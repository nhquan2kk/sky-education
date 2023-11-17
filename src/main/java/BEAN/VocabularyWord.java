package BEAN;

public class VocabularyWord {
	private int vocabularyWordId;
	private int vocabularyId;
	private String name;
	private String transcribe;
	private String audioMp3;
	private String image;
	private String mean;
	private int ordinalNum;

	public int getVocabularyWordId() {
		return vocabularyWordId;
	}

	public void setVocabularyWordId(int vocabularyWordId) {
		this.vocabularyWordId = vocabularyWordId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTranscribe() {
		return transcribe;
	}

	public void setTranscribe(String transcribe) {
		this.transcribe = transcribe;
	}

	public String getAudioMp3() {
		return audioMp3;
	}

	public void setAudioMp3(String audioMp3) {
		this.audioMp3 = audioMp3;
	}


	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public int getVocabularyId() {
		return vocabularyId;
	}

	public void setVocabularyId(int vocabularyId) {
		this.vocabularyId = vocabularyId;
	}

	public int getOrdinalNum() {
		return ordinalNum;
	}

	public void setOrdinalNum(int ordinalNum) {
		this.ordinalNum = ordinalNum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
