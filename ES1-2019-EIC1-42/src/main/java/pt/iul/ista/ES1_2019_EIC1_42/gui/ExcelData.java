package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import pt.iul.ista.ES1_2019_EIC1_42.DataModel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
	private JPanel contentPane, excel_panel, info_panel;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnFicheiro;
	private JMenuItem mImportarExcel, mNovaRegra, mComparador;

	/**
	 * Create the frame.
	 */
	public ExcelData() {
		initContentPanel();
		initTable();
	}

	/**
	 * Inicializa o painel onde vão ficar os componentes.
	 */
	private void initContentPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 100, 1300, 600);
		initMenus();
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		excel_panel = new JPanel(new GridLayout(1, 1));
		contentPane.add(excel_panel, BorderLayout.CENTER);
		info_panel = new JPanel();
		contentPane.add(info_panel, BorderLayout.SOUTH);
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
					tip = "";
				}
				return tip;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scrollPane = new JScrollPane();
		excel_panel.add(scrollPane);
		scrollPane.setViewportView(table);
	}

	/**
	 * Inicializa a MenuBar e os menus
	 */
	private void initMenus() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFicheiro = new JMenu("Ficheiro");
		menuBar.add(mnFicheiro);

		mImportarExcel = new JMenuItem("Importar Excel");
		mnFicheiro.add(mImportarExcel);

		mImportarExcel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				table.setModel(DataModel.newInstance());
			}
		});

		mNovaRegra = new JMenuItem("Nova Regra / Gerir Regras");
		mnFicheiro.add(mNovaRegra);

		mNovaRegra.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Nova_Regra().open();
			}
		});

		mComparador = new JMenuItem("Comparador");
		mnFicheiro.add(mComparador);

		mComparador.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Comparador_de_Qualidade.getInstance().open();
			}
		});
	}
	
	public void openFile() {
		DataModel.getInstance().addFile();
		table.setModel(DataModel.getInstance());
		info_panel.add(new JLabel(DataModel.getInstance().getRowCount() + " métodos"));
		setVisible(true);
		}

	/**
	 * 
	 * @return a JTable que guarda os dados do Excel
	 */
	public JTable getTable() {
		return table;

	}

}
