package com.ezen.word.dto;

public class WordSet {
	
	private String wordKey;
	private String wordValue;
	
	//constructor using field
	public WordSet(String wordKey, String wordValue) {
		super();
		this.wordKey = wordKey;
		this.wordValue = wordValue;
	}
	
	//getter and setter
	public String getWordKey() {
		return wordKey;
	}
	public void setWordKey(String wordKey) {
		this.wordKey = wordKey;
	}
	public String getWordValue() {
		return wordValue;
	}
	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}
	
}
