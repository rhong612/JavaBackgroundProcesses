import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class PatternPanel extends JPanel
{
	private int[][] pattern;
	private int[][] winPattern;
	private int boardSize;
	
	private static final int BLACK = 0;
	private static final int BLUE = 1;
	private static final int YELLOW = 2;
	private static final int RED = 3;
	
	private static int SQUARE_LENGTH = 200;
	
	public PatternPanel(int size) {
		pattern = new int[size][size];
		winPattern = new int[][]{ {0, 3, 2}, {1, 0, 3}, {2, 3, 2} }; //Default pattern
		boardSize = size;
		
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
			}

			@Override
			public void mouseExited(MouseEvent arg0)
			{
			}

			@Override
			public void mousePressed(MouseEvent arg0)
			{
				int x = arg0.getX();
				int y = arg0.getY();
				boolean done = false;
				for (int row = 0; row < boardSize && !done; row++) {
					for (int col = 0; col < boardSize && !done; col++) {
						if (x >= 0 && x <= (col + 1) * SQUARE_LENGTH && y >= 0 && y <= (row + 1) * SQUARE_LENGTH) {
							touchSquare(row, col);
							done = true;
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0)
			{
			}
		});
		
		this.setPreferredSize(new Dimension(600, 600));
	}

	public void touchSquare(int x, int y) {
		if (pattern[x][y] == RED)
			pattern[x][y] = BLACK;
		else
			pattern[x][y]++;
		repaint();
	}
	
	public void resetModel() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				pattern[i][j] = 0;
			}
		}
		repaint();
	}
	
	public boolean checkWin() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (pattern[i][j] != winPattern[i][j])
					return false;
			}
		}
		return true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				Color squareClr = Color.BLACK; //Default
				if (pattern[row][col] == BLUE) {
					squareClr = Color.BLUE;
				}
				else if (pattern[row][col] == YELLOW){
					squareClr = Color.YELLOW;
				}
				else if (pattern[row][col] == RED) {
					squareClr = Color.RED;
				}
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(squareClr);
				g2d.fillRect(col * SQUARE_LENGTH, row * SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH);
				g2d.setColor(Color.WHITE);
				g2d.drawRect(col * SQUARE_LENGTH, row * SQUARE_LENGTH, SQUARE_LENGTH, SQUARE_LENGTH);
			}
		}
	}
	
	public void setCurrentAsWin() {
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				winPattern[i][j] = pattern[i][j];
			}
		}
	}
}
