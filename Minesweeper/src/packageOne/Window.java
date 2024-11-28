package packageOne;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Window {
	protected JFrame frame;
	protected final int FRAME_DIMENSION = 500;
	protected final int BUTTON_SIDE_LENGTH = 50;
	protected final int NUM_OF_BUTTONS_PER_ROW_COLUMN = FRAME_DIMENSION/BUTTON_SIDE_LENGTH;
	protected int numOfButtonsRequired = (FRAME_DIMENSION/BUTTON_SIDE_LENGTH)*(FRAME_DIMENSION/BUTTON_SIDE_LENGTH);
	protected Button[][] buttonList;
	protected GridLayout layout;
	
	protected JPanel buttonPanel;
	protected int numOfBombs;
	protected int bombList[];
	
	//constructor
	public Window() {
		//frame
		this.frame = new JFrame();
		frame.setSize(FRAME_DIMENSION, FRAME_DIMENSION);
		
		//buttonlist
		this.buttonList = new Button[NUM_OF_BUTTONS_PER_ROW_COLUMN][NUM_OF_BUTTONS_PER_ROW_COLUMN];
		
		//button panel
		this.buttonPanel = new JPanel();
		frame.add(buttonPanel);
		
		//layout
		this.layout = new GridLayout(NUM_OF_BUTTONS_PER_ROW_COLUMN, NUM_OF_BUTTONS_PER_ROW_COLUMN);
		buttonPanel.setLayout(layout);
		
		this.numOfBombs = 13;
		this.bombList = new int[numOfBombs];
		
		
	}
	
	//method to set window as visible
	public void setWindowAsVisible(boolean visible) {
		this.frame.setVisible(visible);
	}
	
	public void setBombs() {
		
		boolean inList = true;
		Random rand = new Random();
		for (int i = 0; i < numOfBombs; i++) {
			inList = true;
			//the while loop runs while inlist is true. first in the loop inlist is set to false. then a 
			//random num is generated. there is then a for loop which goes through all the numbers already in the list.
			//if it finds that a the num is a duplicate, it sets inlist equal to true which runs the loop again.
			//if it is not a duplicate, inlist remains false and the num is put in the bomblist.
			while (inList == true) {
				inList = false;
				int randInt = rand.nextInt(numOfButtonsRequired);
			
				//for loop to check whether the new random number is already in the bomblist
				for (int j = 0; j < i; j++) {
					if (bombList[j] == randInt) {
						inList = true;
					}
				}
				if (inList == false) {
					bombList[i] = randInt;
				}
			}
		}
		
		//make a loop using the bomblist to set the respective bombs to true
		int row = 0;
		int column = 0;
		for (int b: bombList) {
			column = (b % 10);
			row = ((b - column)/10);		
			buttonList[row][column].setBomb();
			}
	}
	
	public void setActualAdjacentBombs(int row, int column) {
		
		for (int[] adjacentList: buttonList[row][column].possibleAdjacentMineList) {
			if ((buttonList[row+adjacentList[0]][column + adjacentList[1]]).bomb==true) {
				buttonList[row][column].incrementNumOfAdjacentBombs();
			}
		}
	}
	public void setAdjacentBombs() {
		for (int i = 0; i < NUM_OF_BUTTONS_PER_ROW_COLUMN; i++) {
			for (int j = 0; j < NUM_OF_BUTTONS_PER_ROW_COLUMN; j++) {
				setActualAdjacentBombs(i, j);
			}
		}
	}
	
	//creates buttons and shows them in the window
	public void createButtons() {
		for (int i = 0; i < NUM_OF_BUTTONS_PER_ROW_COLUMN; i ++) {
			for (int j = 0; j < NUM_OF_BUTTONS_PER_ROW_COLUMN; j++) {
				Button button = new Button(i, j);
				button.setBackground(Color.blue);
				buttonPanel.add(button);
				buttonList[i][j] = button;
			}
			
		}
	}
	
	
	
}
