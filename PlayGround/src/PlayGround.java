public class PlayGround {

	public static void main(String[] args) {
	    System.out.println(fibNumPos(32));
	    System.out.println(fibNumPosRecursive(32));
	}
	
	private static int fibNumPosRecursive(int index) 
	{ 
	   if (index < 3) 
	      return index; 
	   return fibNumPosRecursive(index-1) + fibNumPosRecursive(index-2); 
	} 
	
	private static int fibNumPos(int index) {
		int length = index  + 1;
		int[] fibArr = new int[length];
		fibArr[0] = 1;
		fibArr[1] = 1;
		for (int i = 2; i < length; ++i) {
			fibArr[i] = fibArr[i - 2] + fibArr[i - 1];
		}
		return fibArr[index];
	}
}
