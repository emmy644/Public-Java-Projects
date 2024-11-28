package packageOne;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Button extends JButton implements MouseListener {
	protected boolean bomb;
	protected int numOfAdjacentBombs;
	protected int[][] possibleAdjacentMineList;
	protected int numOnList;
	protected int rowIndex;
	protected int columnIndex;
	protected boolean flag;
	protected boolean alreadyClicked;
	
	static boolean firstClick = false;
	static int[][] cannotBeMines;
	
	public Button(int rowIndex, int columnIndex) {
		this.bomb = false;
		this.numOfAdjacentBombs = 0;
		//this.addActionListener(this);
		this.addMouseListener(this);
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.flag = false;
		this.alreadyClicked = false;
		
		//these lists correspond to which indexes could possibly be adjacent mines for all possible spots on the board.
		//The number is added to the spots actual index.
		
		int[][] surroundingMineCenterList = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
		int[][] surroundingMineLeftList = { {-1, 0}, {-1, 1}, {0, 1}, {1, 0}, {1, 1} };
		int[][] surroundingMineRightList = { {-1, -1}, {-1, 0}, {0, -1}, {1, -1}, {1, 0} };
		int[][] surroundingMineTopList = { {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1} };
		int[][] surroundingMineBottomList = { {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1} };
		int[][] surroundingMineTopLeftCornerList = { {0, 1}, {1, 0}, {1, 1} };
		int[][] surroundingMineTopRightCornerList = { {0, -1}, {1, -1}, {1, 0} };
		int[][] surroundingMineBottomLeftCornerList = {{-1, 0}, {-1, 1}, {0, 1} };
		int[][] surroundingMineBottomRightCornerList = { {-1, -1}, {-1, 0}, {0, -1} };
		
		//checking where it is on the board to get the adjacent mine list
		if ((rowIndex == 0) && (columnIndex == 0)) {
			this.possibleAdjacentMineList = surroundingMineTopLeftCornerList;
		}
		else if ((rowIndex == 0) && (columnIndex == 9)) {
			this.possibleAdjacentMineList = surroundingMineTopRightCornerList;
		}
		else if ((rowIndex == 9) && (columnIndex == 0)) {
			this.possibleAdjacentMineList = surroundingMineBottomLeftCornerList;
		}
		else if ((rowIndex == 9) && (columnIndex == 9)) {
			this.possibleAdjacentMineList = surroundingMineBottomRightCornerList;
		}
		else if (columnIndex == 0) {
			this.possibleAdjacentMineList = surroundingMineLeftList;
		}
		else if (columnIndex == 9) {
			this.possibleAdjacentMineList = surroundingMineRightList;
		}
		else if (rowIndex == 0) {
			this.possibleAdjacentMineList = surroundingMineTopList;
		}
		else if (rowIndex == 9) {
			this.possibleAdjacentMineList = surroundingMineBottomList;
		}
		else {
			this.possibleAdjacentMineList = surroundingMineCenterList;
		}
		
	}
	
	public void setBomb() {
		this.bomb = true;
	}
	
	public void incrementNumOfAdjacentBombs() {
		this.numOfAdjacentBombs++;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//if left button is clicked
		if (e.getButton() == MouseEvent.BUTTON1) {
			//gets the coordinates of what is not allowed to be a bomb
//			if (firstClick == false) {
//				firstClick = true;
//				cannotBeMines = this.possibleAdjacentMineList;
//				
//			}
			
				if (this.bomb) {
					this.setBackground(Color.red);
				}
				else {
					this.setBackground(Color.green);
					this.setText("" + numOfAdjacentBombs);
					this.alreadyClicked = true;
				}
			}
		
		//if right button is clicked
		if ((e.getButton() == MouseEvent.BUTTON3) && (this.alreadyClicked == false)) {
			if (this.flag == false) {
				this.setBackground(Color.yellow);
				this.flag = true;
			}
			else if (this.flag == true) {
				this.flag = false;
				this.setBackground(Color.blue);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
