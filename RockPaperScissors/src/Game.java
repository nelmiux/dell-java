import java.util.Random;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

public class Game {
	private static final char[] options = new char[] {'r', 'p', 's'}; 
	private static final Set<String> winningMoves = new HashSet<String>(Arrays.asList(new String[] {"pr","sp","rs"}));
	private char pcOption;
	private char userOption;
	
	Game(char userOption) {
		this.setUserOption(userOption);
		this.setPcOption();
	}

	private void setPcOption() {
		Random randNumGenerator = new Random();
	    int randomNum = randNumGenerator.nextInt(3);
	    pcOption = options[randomNum];
	}
	
	public char getPcOption() {
		return this.pcOption;
	}
	
	public void setUserOption(char userOption) {
		this.userOption = userOption;
	}
	
	public char getUserOption() {
		return this.userOption;
	}
	
	public String finalResult() {
		if (this.pcOption == this.userOption) return "It is Draw";
		String winnerResult = "The winner is ";
		if (winningMoves.contains(String.format("%c%c", this.pcOption, this.userOption))) return winnerResult + "PC";
		return winnerResult + "User";
	}
}
