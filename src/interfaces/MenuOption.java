package interfaces;

/**
 * A simple interface to allow enumerations (or, theoretically, any class) to pass to my
 * {@link ConsoleUI#promptForMenuSelection(MenuOption[], String) promptForMenuSelection} method.
 * 
 * @author Ryder James
 */
public interface MenuOption {

	public String getDesc();

}
