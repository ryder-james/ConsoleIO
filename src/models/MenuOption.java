package models;

import interfaces.ConsoleUI;

/**
 * A simple interface to allow enumerations (or, theoretically, any class) to pass to my
 * {@link ConsoleUI#promptForMenuSelection(MenuOption[], String) promptForMenuSelection} method.
 * 
 * @author Ryder James
 */
@FunctionalInterface
public interface MenuOption {
	
	/**
	 * @return a simple description of this option to print to the user
	 */
	public abstract String getDesc();
}
