import java.util.Random;

public class Translator {
	private String word;
	private String vowels = "aeiou";
	private String[] endWords = {"yay", "way", "ay"};

	private boolean isVowelAt(int charIndex) {
		return vowels.indexOf(Character.toLowerCase(this.word.charAt(charIndex))) >= 0;
	}
	
	private int indexOfVowel() {
		char[] wordArray = this.word.toCharArray();

		if (wordArray.length > 1) {
			for (int i = 1; i < wordArray.length; ++i) 
				if (this.isVowelAt(i)) return i;
		}
		
		return -1;
	}

	public String translate(String word) {
		this.word = word;

		Random rand = new Random();
		int i = rand.nextInt(2);
		String endWord = endWords[i];
		if (this.isVowelAt(0)) return word + endWord;
		
		int vowelIndex = this.indexOfVowel();
		if (vowelIndex >= 0) 
			word = word.substring(vowelIndex) + word.substring(0, vowelIndex);
		
		return word + endWords[2];
	}
}
