package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class ConsoleUI {

	private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Generates a console-based menu using the values in options as the menu items. Reserves the
	 * number 0 for the "Quit" option.
	 * 
	 * @param options - Strings representing the menu options
	 * @return null if 0, or the enum value chosen
	 */
	public static <T extends MenuOption> T promptForMenuSelection(T[] options) {
		return promptForMenuSelection(options, true);
	}

	/**
	 * Generates a console-based menu using the values in options as the menu items. Reserves the
	 * number 0 for the "Quit" option.
	 * 
	 * @param options - Strings representing the menu options
	 * @param withQuit - adds "Quit" as a zeroOption when true
	 * @return null if 0, or the enum value chosen
	 */
	public static <T extends MenuOption> T promptForMenuSelection(T[] options, boolean withQuit) {
		return promptForMenuSelection(options, withQuit ? "Quit" : "");
	}

	/**
	 * Generates a console-based menu using the values in options as the menu items. Reserves the
	 * number 0 for the zeroOption, if provided.
	 * 
	 * @param options - Strings representing the menu options
	 * @param zeroOption - A string representing what the number 0 should say
	 * @return null if 0, or the enum value chosen
	 */
	public static <T extends MenuOption> T promptForMenuSelection(T[] options, String zeroOption) {
		int choice;
		for (int i = 1; i <= options.length; i++) {
			System.out.println(i + ")\t" + options[i - 1].getDesc());
		}
		if (!zeroOption.isEmpty()) {
			System.out.println("0)\t" + zeroOption);
		}
		choice = promptForInt("Select a menu option", zeroOption.isEmpty() ? 1 : 0, options.length);
		return choice == 0 ? null : options[choice - 1];
	}

	/**
	 * Generates a console-based menu using the Strings in options as the menu items. Reserves the
	 * number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options - Strings representing the menu options
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options) {
		return promptForMenuSelection(options, true);
	}

	/**
	 * Generates a console-based menu using the Strings in options as the menu items. Reserves the
	 * number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options - Strings representing the menu options
	 * @param withQuit - adds option 0 for "quit" when true
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options, boolean withQuit) {
		return promptForMenuSelection(options, withQuit ? "Quit" : "");
	}

	/**
	 * Generates a console-based menu using the Strings in options as the menu items. Reserves the
	 * number 0 for the "quit" option when withQuit is true.
	 * 
	 * @param options - Strings representing the menu options
	 * @param zeroOption - A string representing what the number 0 should say
	 * @return the int of the selection made by the user
	 */
	public static int promptForMenuSelection(String[] options, String zeroOption) {
		for (int i = 1; i <= options.length; i++) {
			System.out.println(i + ")\t" + options[i - 1]);
		}

		if (!zeroOption.isEmpty()) {
			System.out.println("0)\t" + zeroOption);
		}
		return promptForInt("Select a menu option", zeroOption.isEmpty() ? 1 : 0, options.length);
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that will equate to a
	 * boolean value. This calls {@link #promptForBool(String, String, String) promptForBool()} with
	 * a {@code trueString} of "yes" and a {@code falseString} of "no". Example: If the enters
	 * "YES", the method returns true. If the user enters "no", the method returns false. All other
	 * inputs are considered invalid, the user will be informed, and the prompt will repeat.
	 * 
	 * @return the boolean value
	 */
	public static boolean promptForBool() {
		return promptForBool("Enter \"yes\" or \"no\".", "Yes", "No");
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that will equate to a
	 * boolean value. The trueString represents the case insensitive response that will equate to
	 * true. The falseString acts similarly, but for a false boolean value. Example: Assume this
	 * method is called with a trueString argument of "yes" and a falseString argument of "no". If
	 * the enters "YES", the method returns true. If the user enters "no", the method returns false.
	 * All other inputs are considered invalid, the user will be informed, and the prompt will
	 * repeat.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param trueString - the case insensitive value that will evaluate to true
	 * @param falseString - the case insensitive value that will evaluate to false
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString) {
		return promptForBool(prompt, trueString, falseString, true);
	}

	/**
	 * Generates a prompt that expects the user to enter one of two responses that will equate to a
	 * boolean value. The trueString represents the case insensitive response that will equate to
	 * true. The falseString acts similarly, but for a false boolean value. Example: Assume this
	 * method is called with a trueString argument of "yes" and a falseString argument of "no". If
	 * the enters "YES", the method returns true. If the user enters "no", the method returns false.
	 * All other inputs are considered invalid, the user will be informed, and the prompt will
	 * repeat.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param trueString - the case insensitive value that will evaluate to true
	 * @param falseString - the case insensitive value that will evaluate to false
	 * @param appendColon - if true, appends ": " to the end of the prompt
	 * @return the boolean value
	 */
	public static boolean promptForBool(String prompt, String trueString, String falseString,
			boolean appendColon) {
		String input;

		if (trueString.equals(falseString)) {
			throw new IllegalArgumentException("trueString cannot equal falseString!");
		}

		do {
			input = promptForInput(prompt, true, appendColon);

			if (input.equalsIgnoreCase(trueString)) {
				return true;
			} else if (input.equalsIgnoreCase(falseString)) {
				return false;
			} else {
				System.out.println("Input must be " + trueString + " or " + falseString + "!");
				continue;
			}
		} while (true);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value. This method loops
	 * until valid input is given.
	 * 
	 * @return the byte value
	 */
	public static byte promptForByte() {
		return promptForByte("Enter a byte");
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt) {
		return promptForByte(prompt, Byte.MIN_VALUE, Byte.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a byte value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the byte value
	 */
	public static byte promptForByte(String prompt, byte min, byte max) {
		double input;
		byte result = 0;

		do {
			input = promptForNumber(prompt, min, max);

			if (Math.floor(input) == input) {
				result = (byte) input;
				break;
			} else {
				System.out.println("Input must not be a floating point number!");
				continue;
			}
		} while (true);

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value. This method loops
	 * until valid input is given.
	 * 
	 * @return the short value
	 */
	public static short promptForShort() {
		return promptForShort("Enter a short");
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the short value
	 */
	public static short promptForShort(String prompt) {
		return promptForShort(prompt, Short.MIN_VALUE, Short.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a short value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the short value
	 */
	public static short promptForShort(String prompt, short min, short max) {
		double input;
		short result = 0;

		do {
			input = promptForNumber(prompt, min, max);

			if (Math.floor(input) == input) {
				result = (short) input;
				break;
			} else {
				System.out.println("Input must not be a floating point number!");
				continue;
			}
		} while (true);

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value. This method loops
	 * until valid input is given.
	 * 
	 * @return the int value
	 */
	public static int promptForInt() {
		return promptForInt("Enter an integer");
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the int value
	 */
	public static int promptForInt(String prompt) {
		return promptForInt(prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing an int value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the int value
	 */
	public static int promptForInt(String prompt, int min, int max) {
		double input;
		int result = 0;

		do {
			input = promptForNumber(prompt, min, max);

			if (Math.floor(input) == input) {
				result = (int) input;
				break;
			} else {
				System.out.println("Input must not be a floating point number!");
				continue;
			}
		} while (true);

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value. This method loops
	 * until valid input is given.
	 * 
	 * @return the long value
	 */
	public static long promptForLong() {
		return promptForLong("Enter a long");
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the long value
	 */
	public static long promptForLong(String prompt) {
		return promptForLong(prompt, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a long value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the long value
	 */
	public static long promptForLong(String prompt, long min, long max) {
		double input;
		long result = 0;

		do {
			input = promptForNumber(prompt, min, max);

			if (Math.floor(input) == input) {
				result = (long) input;
				break;
			} else {
				System.out.println("Input must not be a floating point number!");
				continue;
			}
		} while (true);

		return result;
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value. This method loops
	 * until valid input is given.
	 * 
	 * @return the float value
	 */
	public static float fromptForFloat() {
		return promptForFloat("Enter a floating point number");
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the float value
	 */
	public static float promptForFloat(String prompt) {
		return promptForFloat(prompt, Float.NEGATIVE_INFINITY, Float.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a float value. This method loops
	 * until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the float value
	 */
	public static float promptForFloat(String prompt, float min, float max) {
		return (float) promptForNumber(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value. This method
	 * loops until valid input is given.
	 * 
	 * @return the double value
	 */
	public static double promptForDouble() {
		return promptForDouble("Enter a floating point number");
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value. This method
	 * loops until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the double value
	 */
	public static double promptForDouble(String prompt) {
		return promptForDouble(prompt, Double.NEGATIVE_INFINITY, Double.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a numeric input representing a double value. This method
	 * loops until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the double value
	 */
	public static double promptForDouble(String prompt, double min, double max) {
		return promptForNumber(prompt, min, max);
	}

	/**
	 * Generates a prompt that expects a single letter input representing a char value. This method
	 * loops until valid input is given.
	 * 
	 * @return the char value
	 */
	public static char promptForLetter() {
		return promptForChar("Enter a letter", 'A', 'z');
	}

	/**
	 * Generates a prompt that expects a single character input representing a char value. This
	 * method loops until valid input is given.
	 * 
	 * @return the char value
	 */
	public static char promptForChar() {
		return promptForChar("Enter a character");
	}

	/**
	 * Generates a prompt that expects a single character input representing a char value. This
	 * method loops until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @return the char value
	 */
	public static char promptForChar(String prompt) {
		return promptForChar(prompt, Character.MIN_VALUE, Character.MAX_VALUE);
	}

	/**
	 * Generates a prompt that expects a single character input representing a char value. This
	 * method loops until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the char value
	 */
	public static char promptForChar(String prompt, char min, char max) {
		String toParse;
		char result;

		if (min >= max) {
			throw new IllegalArgumentException("Min must be less than max!");
		}

		do {
			toParse = promptForInput(prompt, false);
			result = 0;

			if (toParse.length() > 1) {
				System.out.println("Input must be a single character!");
				continue;
			}

			result = toParse.charAt(0);

			if (result < min || result > max) {
				System.out.println("Input must be from " + Character.valueOf(min) + " to "
						+ Character.valueOf(max) + "!");
				continue;
			} else {
				break;
			}
		} while (true);

		return result;
	}

	// TODO: JavaDoc
	public static int[] promptForIntArray(String prompt, String delimiter) {
		String[] unparsedResults;
		int[] results;
		boolean isValidArray = false;
		do {
			unparsedResults = promptForArray(prompt, delimiter);
			results = new int[unparsedResults.length];
			try {
				for (int i = 0; i < results.length; i++) {
					results[i] = Integer.parseInt(unparsedResults[i]);
				}
				isValidArray = true;
			} catch (NumberFormatException nfe) {
				System.out.println("All entries must be of type int, separated by " + delimiter);
			}
		} while (!isValidArray);
		return results;
	}
	
	/**
	 * Generates a prompt that allows the user to enter an array of Strings, which are denoted as
	 * separate by the given delimiter.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param delimiter - the delimiter to separate array elements in the string
	 * @return an array populated by elements from a given string
	 */
	public static String[] promptForArray(String prompt, String delimiter) {
		return promptForInput(prompt, true).split(delimiter);
	}
	
	public static String promptForIP(IPType form) {
		return promptForIP("Enter an IP in " + String.valueOf(form).toLowerCase() + " form", form);
	}

	// TODO: JavaDoc
	public static String promptForIP(String prompt, IPType form) {
		boolean isValidIP = false;
		String IP;
		do {
			IP = ConsoleUI.promptForInput(prompt).trim();
			switch (form) {
			case BINARY:
				isValidIP = isValidBinaryIP(IP);
				break;
			case DECIMAL:
				isValidIP = isValidDecimalIP(IP);
				break;
			case DOT_DECIMAL:
				isValidIP = isValidDotDecimalIP(IP);
				break;
			case HEX:
				isValidIP = isValidHexIP(IP);
				break;
			}
		} while (!isValidIP);
		
		return IP;
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the String.
	 * 
	 * @return the input from the user as a String
	 */
	public static String promptForInput() {
		return promptForInput("Enter a string");
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the String.
	 * 
	 * @param prompt - the prompt to be displayed to the user.
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt) {
		return promptForInput(prompt, false);
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the String. When
	 * allowEmpty is true, empty responses are valid. When false, responses must contain at least
	 * one character (including whitespace).
	 * 
	 * @param prompt - the prompt to be displayed to the user.
	 * @param allowEmpty - when true, makes empty responses valid
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt, boolean allowEmpty) {
		return promptForInput(prompt, allowEmpty, true);
	}

	/**
	 * Generates a prompt that allows the user to enter any response and returns the String. When
	 * allowEmpty is true, empty responses are valid. When false, responses must contain at least
	 * one character (including whitespace).
	 * 
	 * @param prompt - the prompt to be displayed to the user.
	 * @param allowEmpty - when true, makes empty responses valid
	 * @param appendColon - when true, adds ": " to the end of the prompt
	 * @return the input from the user as a String
	 */
	public static String promptForInput(String prompt, boolean allowEmpty, boolean appendColon) {
		String result;

		if (prompt == null) {
			throw new IllegalArgumentException("Prompt must not be null!");
		}
		
		if (prompt.length() < 1) {
			throw new IllegalArgumentException("Prompt must not be blank!");
		}

		do {
			result = "";
			System.out.print(prompt + (appendColon ? ": " : ""));
			try {
				result = in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!allowEmpty && result.isEmpty()) {
				System.out.println("Input must not be empty!");
				continue;
			} else {
				break;
			}
		} while (true);

		return result;
	}

	// TODO: JavaDoc
	public static String printableArray(Object[] arr) {
		return printableArray(arr, "{}");
	}

	// TODO: JavaDoc
	public static String printableArray(Object[] arr, String container) {
		if (arr.length == 0) {
			return container;
		}


		return Arrays.toString(arr).replace('[', container.charAt(0))
				.replace(']', container.charAt(1)).replaceAll("null(, )*", "")
				.replaceAll(", }", "}");
	}

	// TODO: JavaDoc
	public static String printableDoubleArray(Object[][] arr) {
		return printableDoubleArray(arr, "{}", "()");
	}

	// TODO: JavaDoc
	public static String printableDoubleArray(Object[][] arr, String container,
			String subcontainer) {
		String result = String.valueOf(container.charAt(0));

		for (int i = 0; i < arr.length; i++) {
			result += printableArray(arr[i], subcontainer);
			if (i < arr.length - 1) {
				result += ", ";
			}
		}

		if (arr.length == 0) {
			result = container;
		}

		return result + container.charAt(1);
	}
	
	/**
	 * Generates a prompt that expects a numeric input representing a double value. This method
	 * loops until valid input is given.
	 * 
	 * @param prompt - the prompt to be displayed to the user
	 * @param min - the inclusive minimum boundary
	 * @param max - the inclusive maximum boundary
	 * @return the double value
	 */
	private static double promptForNumber(String prompt, double min, double max) {
		String toParse;
		double result;

		if (min > max) {
			throw new IllegalArgumentException("Min must be less than max!");
		}

		do {
			toParse = promptForInput(prompt, false);
			result = 0;

			try {
				result = Double.parseDouble(toParse);
			} catch (NumberFormatException nfe) {
				System.out.println("Input must be a number!");
				continue;
			}

			if (result < min || result > max) {
				NumberFormat df = new DecimalFormat("#.##");
				System.out.println(
						"Input must be from " + df.format(min) + " to " + df.format(max) + "!");
				continue;
			} else {
				break;
			}
		} while (true);

		return result;
	}

	private static boolean isValidDecimalIP(String IP) {
		// TODO: Make this not suck.
		return true;
	}
	
	// TODO: JavaDoc
	private static boolean isValidBinaryIP(String IP) {
		if (IP.length() != 32) {
			System.out.println("IP must consist of exactly 32 digits long");
			return false;
		}
		
		for (char c : IP.toCharArray()) {
			if (c != '0' && c != '1') {
				System.out.println("IP must consist of only 1s and 0s");
				return false;
			}
		}
		
		return true;
	}
	
	// TODO: JavaDoc
	private static boolean isValidDotDecimalIP(String IP) {
		String[] octets = IP.split("\\.");
		 
		if (octets.length != 4) {
			System.out.println("IP must consist of 4 octets, separated by a \".\"");
			return false;
		}
		for (String octet : octets) {
			if (octet.length() > 3) {
				System.out.println("Each octet must consist of no more than 3 numbers");
				return false;
			}
		}
		for (String octet : octets) {
			try {
				if (Integer.parseInt(octet) > 255 | Integer.parseInt(octet) < 0) {
					System.out.println("Each octet must be in the range 0-255");
					return false;
				}
			} catch (NumberFormatException e) {
				System.out.println("Each octet must be a number");
				return false;
			}
		}
		
		return true;
	}
	
	// TODO: JavaDoc
	private static boolean isValidHexIP(String IP) {
		if (IP.length() != 8) {
			System.out.println("IP must consist of exactly 8 digits");
			return false; // TODO: Better hex IP checking
		}
		
		return true;
	}
}
