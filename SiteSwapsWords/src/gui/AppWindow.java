package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;

import engine.FileUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;

public class AppWindow {

	private JFrame frame;
	private JTextField inputFile;
	private JTextField outputFile;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel inputLabel = new JLabel("Input data file");
		inputLabel.setBounds(49, 9, 68, 14);
		panel.add(inputLabel);
		
		inputFile = new JTextField();
		inputFile.setBounds(189, 6, 86, 20);
		panel.add(inputFile);
		inputFile.setColumns(10);
		
		JButton inputButton = new JButton("Load File");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser("C:\\Users\\anuvin\\Documents\\GitHub\\JugglableWords\\SiteSwapsWords\\src");
				chooser.showOpenDialog(panel);
				chooser.setVisible(true);
				inputFile.setText(chooser.getSelectedFile().getPath());
			}
		});
		inputButton.setBounds(280, 5, 75, 23);
		panel.add(inputButton);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBounds(269, 111, 148, 133);
		panel.add(textPane);
		
		
		JLabel outputLabel = new JLabel("Output data file");
		outputLabel.setBounds(49, 34, 86, 14);
		panel.add(outputLabel);
		
		outputFile = new JTextField();
		outputFile.setColumns(10);
		outputFile.setBounds(189, 37, 86, 20);
		panel.add(outputFile);
		
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"SiteSwap"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 0, 0), null));
		table.setBackground(Color.ORANGE);
		table.setBounds(220, 111, -171, 190);
		panel.add(table);
		
		JButton outputButton = new JButton("Select File");
		outputButton.setBounds(280, 36, 75, 23);
		panel.add(outputButton);
		
		JButton btnExecute = new JButton("Execute!");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> siteswaps = FileUtils.getLines(inputFile.getText());
				int i = 0;
				
				for (String str: siteswaps){
					String[] pole = {str};
					
					 if ( i+1 < table.getRowCount()-1 )
						    ( (DefaultTableModel) table.getModel() ).insertRow(i+1, pole);
						  else
						    ( (DefaultTableModel) table.getModel() ).addRow(pole);
				}
				table.repaint();
				table.setVisible(true);
				panel.repaint();
				table.updateUI();
				System.out.println(table.getColumnCount());
				System.out.println(table.getModel().getColumnCount());
				System.out.println(table.getModel().getRowCount());
				
				System.out.println(table.getModel().getColumnCount());
				StringBuilder builder = new StringBuilder();
				for (String str : siteswaps){
					builder.append(str+System.getProperty("line.separator"));
				}
				textPane.setText(builder.toString());
			}
		});
		btnExecute.setBounds(46, 59, 89, 23);
		panel.add(btnExecute);
		


	}
}
