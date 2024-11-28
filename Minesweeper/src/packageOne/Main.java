package packageOne;

public class Main {
	
	public static void main(String[] args) {
		//variables
		Window mainWindow = new Window();

		//methods
		mainWindow.createButtons();
		mainWindow.setBombs();
		mainWindow.setAdjacentBombs();
		mainWindow.setWindowAsVisible(true);
	}
}
