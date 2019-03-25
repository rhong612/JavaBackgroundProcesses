import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainWindow
{
	private PatternPanel panel;
	private JFrame mainFrame;
	private final String ADMIN_PASS = "PASS";
	private boolean adminMode;
	
	public MainWindow(int size) {
		adminMode = false;
		mainFrame = new JFrame();
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new PatternPanel(size);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				if (adminMode) {
					panel.setCurrentAsWin();
					adminMode = false;
				}
				else {
					if (panel.checkWin()) {
						JOptionPane.showMessageDialog(null, "Success! Letter: R", "OK", JOptionPane.DEFAULT_OPTION);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect Pattern!", "Error", JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});
		JButton adminConfig = new JButton("AdminConfig");
		adminConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String password = JOptionPane.showInputDialog("Enter the administrator password", null);
				if (password.equals(ADMIN_PASS)) {
					adminMode = true;
				}
				else {
					JOptionPane.showMessageDialog(null, "Only the admin can access this. This option is not part of the game.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton reset = new JButton("Reset");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				panel.resetModel();
			}
		});
		
		Box hBox = Box.createHorizontalBox();
		hBox.add(submit);
		hBox.add(adminConfig);
		hBox.add(reset);
		
		Box panelBox = Box.createHorizontalBox();
		panelBox.add(Box.createHorizontalStrut(360));
		panelBox.add(panel);
		
		Box vBox = Box.createVerticalBox();
		vBox.add(panelBox);
		vBox.add(hBox);
		
		mainFrame.add(vBox);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args) {
		final int SIZE = 3;
		MainWindow view = new MainWindow(SIZE);
	}
}
