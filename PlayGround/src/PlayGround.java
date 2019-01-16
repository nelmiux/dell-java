import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PlayGround {

	public static void main(String[] args) {
		String[] colors = {"Red", "Blue", "Yellow"};
		System.out.println(Arrays.toString(colors));
		
		List<String> colorsWrapper = new ArrayList<>(Arrays.asList("Red", "Blue", "Yellow"));
		System.out.println(colorsWrapper);
	}
}