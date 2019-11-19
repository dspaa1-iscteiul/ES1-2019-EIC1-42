package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import pt.iul.ista.ES1_2019_EIC1_42.DataModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Classe para visualizar um ficheiro Excel
 * 
 * @author dariop
 *
 */
public class ExcelData extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5646410931104674210L;
	private JPanel contentPane, excel_panel;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void start() {
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
		initContentPanel();
		initTable();

		JPanel info_panel = new JPanel();
		contentPane.add(info_panel, BorderLayout.SOUTH);

	}

	/**
	 * Inicializa o painel onde vão ficar os componentes.
	 */
	private void initContentPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		excel_panel = new JPanel();
		excel_panel.setLayout(new GridLayout(1, 1));
		contentPane.add(excel_panel, BorderLayout.CENTER);
	}

	/**
	 * Inicializa a tabela.
	 */
	private void initTable() {
		table = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1528885477774668184L;

			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);
				int colIndex = columnAtPoint(p);

				try {
					tip = getValueAt(rowIndex, colIndex).toString();
				} catch (RuntimeException e1) {
					// catch null pointer exception if mouse is over an empty line
				}

				return tip;
			}
		};
		excel_panel.add(table);

		table.setModel(DataModel.getInstance());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPane = new JScrollPane();
		excel_panel.add(scrollPane);
		scrollPane.getViewport().add(table);
	}

	/**
	 * 
	 * @return a JTable que guarda os dados do Excel
	 */
	public JTable getTable() {
		return table;

	}

}
