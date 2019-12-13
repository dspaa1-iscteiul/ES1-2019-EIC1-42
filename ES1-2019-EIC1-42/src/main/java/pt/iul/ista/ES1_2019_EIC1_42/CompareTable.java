package pt.iul.ista.ES1_2019_EIC1_42;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

/**
 * Classe usada para a tabela de Resultados e Indicadores de Qualidade
 * @author dariop
 *
 */
public class CompareTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4565688033152341695L;
	private String[] col;

	/**
	 * Construtor da classe
	 * @param col - colunas iniciais que vão ser usadas
	 * @param tableModel - O tableModel que vai ser usado para inicializar a tabela
	 */
	public CompareTable(String[] col, AbstractTableModel tableModel) {
		super(tableModel);
		this.col = col;
		getTableHeader().setReorderingAllowed(false);
	}

	@Override
	protected JTableHeader createDefaultTableHeader() {
		return new JTableHeader(getColumnModel()) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getToolTipText(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int index = columnModel.getColumnIndexAtX(p.x);
				int realIndex = columnModel.getColumn(index).getModelIndex();
				if (realIndex > 2)
					return RegrasModel.getInstance().getRegras().get(realIndex - 3).toString();
				else if (realIndex > 0)
					return col[realIndex] + " - long_method";
				else
					return super.getToolTipText();
			}
		};
	}

	@Override
	public String getToolTipText(MouseEvent event) {
		java.awt.Point p = event.getPoint();
		int row = rowAtPoint(p);
		int column = columnAtPoint(p);
		column = convertColumnIndexToModel(column);
		row = convertRowIndexToModel(row);
		if (column == 0) {
			if (getModel().getValueAt(0, 0).equals("DCI")) {
				switch (row) {
				case 0:
					return "Defeitos Corretamente Identificados (Regra: TRUE & Valor certo: TRUE)";
				case 1:
					return "Defeitos Incorretamente Identificados (Regra: TRUE & Valor certo: FALSE)";
				case 2:
					return "Ausência de Defeitos Corretamente Identificados (Regra: FALSE & Valor certo: FALSE)";
				case 3:
					return "Ausência de Defeitos Incorretamente Identificados (Regra: FALSE & Valor certo: TRUE)";
				default:
					break;
				}
			}
		}
		return super.getToolTipText(event);
	}

}
