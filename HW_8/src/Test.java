
public class Test {
	public static void main(String[] args) {
		int[] array = {21,10,23,8,6,-5};
		System.out.println(min(array));

	}
	
	public static int min(int[] array) {
		int ans = array[0];
		if (array.length<=4) {
			for (int i = 0; i <array.length;i++) {
				if(array[i] < ans) ans = array[i];
			}
			return ans;
		}
		else
			return min(subarray(array,0, array.length-1));
	}
	public static int[] subarray(int[] array, int start, int end) {
		int[] newArray = new int[end];
		
		for (int i =start; i<end; i++) {
			newArray[i] = array[start];
		}
		return newArray;
	}
	
}
