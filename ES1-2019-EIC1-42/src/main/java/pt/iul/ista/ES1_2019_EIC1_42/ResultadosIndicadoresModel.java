package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

/**
 * Classe para guardar as informações dos resultados e indicadores de qualidade
 * @author dariop
 *
 */
public class ResultadosIndicadoresModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7323033175889007523L;
	private ArrayList<String> columnNames;
	private ArrayList<ArrayList<Object>> rows = new ArrayList<ArrayList<Object>>();
	
	
	/**
	 * Construtor
	 * @param columnNames - array de String que contém os nomes das colunas iniciais da tabela.
	 */
	public ResultadosIndicadoresModel(String[] columnNames) {
		super();
		this.columnNames = new ArrayList<String>(Arrays.asList(columnNames));
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public String getColumnName(int column) {
		return columnNames.get(column);
	}

	@Override
	public Object getValueAt(int row, int col) {
		return rows.get(row).get(col);
	}
	
	/**
	 * Adiciona uma nova linha na tabela
	 * @param data Array que contém em cada posição uma coluna da linha que se pretende adicionar
	 * @return true se  foi adicionada a linha com sucesso
	 */
	public boolean addRow(Object[] data) {
		boolean result = rows.add(new ArrayList<Object>(Arrays.asList(data)));
		fireTableStructureChanged();
		return result;
	}
	
	/**
	 * Adiciona uma nova coluna à tabela
	 * @param columnName - header da nova coluna
	 * @param data - array que deve conter todas as linhas da tabela para essa nova coluna
	 * @return true se foi adicionada a nova coluna com sucesso, se não foi lançada nenhuma exceção
	 */
	public boolean addColumn(String columnName, Object[] data) {
		if(columnNames.contains(columnName))
			return false;
		columnNames.add(columnName);
		for(int i=0; i<rows.size(); i++) {
			rows.get(i).add(data[i]);
		}
		fireTableStructureChanged();
		return true;
	}
	
	/**
	 * Remove uma coluna da tabela, dado o seu nome
	 * @param columnName - nome da coluna que se pretende remover
	 */
	public void removeColumn(String columnName) {
		removeColumn(columnNames.indexOf(columnName));
		columnNames.remove(columnName);
		fireTableStructureChanged();
	}
	
	/**
	 * Only used as an auxiliar method for removeColumn(String columnName)
	 * @param index
	 */
	private void removeColumn(int index) {
		for(int i = 0; i<rows.size(); i++) {
			rows.get(i).remove(index);
		}
	}
	
	/**
	 * Verifica se já existe uma coluna com determinado nome
	 * @param columnName - nome da coluna que se pretende verificar se existe
	 * @return true - se já existe uma coluna com o dado nome
	 */
	public boolean existsColumn(String columnName) {
		if(columnNames.contains(columnName))
			return true;
		else
			return false;
	}
	
	/**
	 *  Muda o nome de uma coluna
	 * @param oldName - nome da coluna que se pretende alterar
	 * @param newName - novo nome que quer dar à coluna
	 * @return true - se o nome foi alterado <br>
	 * 		   false - se não existe a coluna que se quer mudar
	 */
	public boolean changeColumnName(String oldName, String newName) {
		if(!existsColumn(oldName))
				return false;
		else {
			int index = columnNames.indexOf(oldName);
			columnNames.set(index, newName);
			fireTableStructureChanged();
			return true;
		}
	}	

}
