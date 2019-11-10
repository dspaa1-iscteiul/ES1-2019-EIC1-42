package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pt.iul.ista.ES1_2019_EIC1_42.DataModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Classe para visualizar um ficheiro Excel
 * @author dariop
 *
 */
public class ExcelData extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel excel_panel = new JPanel();
		contentPane.add(excel_panel, BorderLayout.CENTER);
		
		table = new JTable();
		excel_panel.add(table);
		table.setModel(DataModel.getInstance());
		
		JScrollPane scrollPane = new JScrollPane();
		excel_panel.add(scrollPane);
		scrollPane.getViewport().add(table);
		
		

		JPanel info_panel = new JPanel();
		contentPane.add(info_panel, BorderLayout.SOUTH);
		
		
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void setContentPane(JPanel contentPane) {
		this.contentPane = contentPane;
	}

	public JTable getTable() {
		return table;
	}
}
