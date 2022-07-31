package dictonary;

//class WordMeaning - sets a word and it's meaning
public class WordMeaning {
	
	private String word;
	private String meaning;
	
	WordMeaning(String word, String meaning){
		this.word = word;
		this.meaning = meaning;
	}//end constructor
	
	//getter methods
	String getWord() {
		return word;
	}//end getWord
	String getMeaning() {
		return meaning;
	}//end getMeaning()
	
	//adding another meaning to a word
	void addMeaning(String meaning2) {
		this.meaning += "\n\t- "+meaning2;
	}//end addMeaning()
}//end class WordMeaning
