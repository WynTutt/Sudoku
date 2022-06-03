package sudoku;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SudokuFrame extends JFrame {

	private SudokuGrid data = new SudokuGrid();
	
	private JFrame frame;
	
	private JPanel panelText = new JPanel(new GridLayout(9, 9));
	private JTextField text[][] = new JTextField[9][9];
	
	private JPanel panelButton = new JPanel();
	private JButton megoldas = new JButton("Megoldas");
	
	private JTextField feedback = new JTextField();
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnNewMenu = new JMenu("Menu");
	private final JMenuItem resetMenuItem = new JMenuItem("Reset");
	private final JMenuItem loadMenuItem = new JMenuItem("Load");
	
	int ans[][] = new int[9][9];				/**First contains the grid given by the user
												then the solved grid**/	
	
	public SudokuFrame() {
		frame = new JFrame("Sudoku");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(320,320);
		frame.setLocationRelativeTo(null);
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				text[i][j] = new JTextField("" + data.grid[i][j]);
				text[i][j].setHorizontalAlignment(JTextField.CENTER);
				
				panelText.add(text[i][j]);
				text[i][j].setBorder(BorderFactory.createLineBorder(Color.black, 1));
			}
		}
		;
		frame.getContentPane().add(panelText);
		frame.getContentPane().add(panelButton, BorderLayout.SOUTH);
		
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelButton.add(megoldas);
		megoldas.addActionListener(new ButtonActionListener());
		
		frame.getContentPane().add(feedback, BorderLayout.NORTH);
		
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mnNewMenu);
		mnNewMenu.add(resetMenuItem);
		resetMenuItem.addActionListener(new ResetActionListener());;
		mnNewMenu.add(loadMenuItem);
		loadMenuItem.addActionListener(new LoadActionListener());
		
		frame.setVisible(true);
	}
	
	/**get the given grid**/
	private int[][] gettext() {
		int[][] temp = new int[9][9];
		for(int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				temp[i][j] = 0;
			}
		}
		for(int k = 0; k < 9; k++) {
			for(int n = 0; n < 9; n++) {
				try {
					temp[n][k] = Integer.parseInt(text[n][k].getText());
					if(temp[n][k] > 9 || temp[n][k] < 0) {
						feedback.setText("Az ervenyes szamok 1 es 9 kozott vannak");
						return null;
					}
				}catch(NumberFormatException nfe) {
					 feedback.setText("Csak szamokat tartalmazhat."); 
					 return null;
				}
			}
		}
		return temp;
	}
	
	private void printToFrame(int temp[][]) {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				text[i][j].setText(String.valueOf(temp[i][j]));
			}
		}
	}
	
	private void save(int temp[][]) throws IOException {
		FileOutputStream f = new FileOutputStream("grid.txt");
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(temp);
		out.close();
	}
	
	class ButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			ans = gettext();
			if (ans != null) {
				
				/** print the grid that is solved by the algorithm **/
				if (Algorithm.solve(ans)) {
					printToFrame(ans);
					feedback.setText("A megadott palya megoldhato:");
					try {
						save(ans);
					} catch (IOException e1) {
						 e1.printStackTrace();
					}
				} else {
					feedback.setText("A megadott palyaval nem megoldhato (szam ismetles)");
				}
			}
		}
	}
	
	class ResetActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			data.init();
			printToFrame(data.grid);
		}
	}
	
	class LoadActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int[][] temp = new int[9][9];
			
			try {
				FileInputStream f = new FileInputStream("grid.txt");
				ObjectInputStream in = new ObjectInputStream(f);
				temp = (int[][]) in.readObject();
				in.close();
				printToFrame(temp);
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
