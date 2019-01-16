package static_classes;

import java.util.Random;

public class StaticHelper {
	private static final Random RAND = new Random();
	
	private StaticHelper() {}

	public static int randomInt(int min, int max) {
		if (max < min) {
			throw new IllegalArgumentException("Max must be not be less than min");
		}
		
		return RAND.nextInt(max - min + 1) + min;
	}

	public static <T extends Object> T randomElement(T[] arr) {
		if (arr.length == 0) {
			throw new IllegalArgumentException("Array must not be empty");
		}
		return arr[RAND.nextInt(arr.length)];
	}
}
