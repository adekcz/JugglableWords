package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import engine.FileUtils;

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

		final JLabel jLabel_Status = new JLabel("");
		jLabel_Status.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel_Status.setBounds(0, 312, 472, 14);
		panel.add(jLabel_Status);

		inputFile = new JTextField();
		inputFile.setBounds(189, 6, 86, 20);
		panel.add(inputFile);
		inputFile.setColumns(10);

		JButton inputButton = new JButton("Load File");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(
						"C:\\Users\\anuvin\\Documents\\GitHub\\JugglableWords\\SiteSwapsWords\\src");
				chooser.showOpenDialog(panel);
				chooser.setVisible(true);
				inputFile.setText(chooser.getSelectedFile().getPath());
			}
		});
		inputButton.setBounds(280, 5, 75, 23);
		panel.add(inputButton);

		JLabel outputLabel = new JLabel("Output data file");
		outputLabel.setBounds(49, 34, 86, 14);
		panel.add(outputLabel);

		outputFile = new JTextField();
		outputFile.setColumns(10);
		outputFile.setBounds(189, 37, 86, 20);
		panel.add(outputFile);

		table = new JTable();
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		JScrollPane pane = new JScrollPane(table,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setLocation(22, 93);
		pane.setSize(95, 220);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// frame.add(panel);
		// frame.setSize(500,150);
		// frame.setUndecorated(true);
		// frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "SiteSwap" }) {
			Class[] columnTypes = new Class[] { String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBorder(new CompoundBorder(new EmptyBorder(2, 0, 0, 0), null));
		table.setBackground(Color.ORANGE);
		table.setBounds(49, 111, 176, 190);
		// panel.add(table);
		panel.add(pane);

		JButton outputButton = new JButton("Select File");
		outputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(
						"C:\\Users\\anuvin\\Documents\\GitHub\\JugglableWords\\SiteSwapsWords\\src");
				chooser.showSaveDialog(panel);
				outputFile.setText(chooser.getSelectedFile().getPath());
			}
		});
		outputButton.setBounds(280, 36, 75, 23);
		panel.add(outputButton);

		JButton btnExecute = new JButton("Execute!");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<String> siteswaps = FileUtils.getLines(inputFile.getText());
				int i = 0;

				for (String str : siteswaps) {
					String[] pole = { str };

					if (i + 1 < table.getRowCount() - 1)
						((DefaultTableModel) table.getModel()).insertRow(++i,
								pole);
					else
						((DefaultTableModel) table.getModel()).addRow(pole);
				}
				StringBuilder builder = new StringBuilder();
				for (String str : siteswaps) {
					builder.append(str + System.getProperty("line.separator"));
				}

				try {
					FileUtils.writeLines(siteswaps, outputFile.getText());
					jLabel_Status.setText("Soubor ulozen");
				} catch (IOException e) {
					jLabel_Status.setText("Soubor se nepovedlo ulozit");
				}
			}
		});
		btnExecute.setBounds(46, 59, 89, 23);
		panel.add(btnExecute);

		
		frame.getContentPane().add(panel);
	}
}
