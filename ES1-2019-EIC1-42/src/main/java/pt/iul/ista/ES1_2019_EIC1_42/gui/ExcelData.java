package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe para visualizar um ficheiro Excel
 * @author dariop
 *
 */
public class ExcelData extends JFrame {
	private File excelfile;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExcelData frame = new ExcelData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public ExcelData() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		fileChooser();
	}
	
	//create JFileChooser
	
	public void fileChooser() {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".xlsx", "xlsx", "excel");
		filechooser.setFileFilter(filter);
		filechooser.showOpenDialog(null);	
		excelfile = filechooser.getSelectedFile();
	}

}
