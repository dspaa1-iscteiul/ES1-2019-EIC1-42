package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import java.io.File;  
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

/**
 * Classe para gerir os dados que são importados do ficheiro Excel e são passados para uma JTable
 * @author elsas
 *
 */
public class DataModel extends AbstractTableModel {
	
	private ArrayList<Metodo> metodos;
	private static DataModel INSTANCE;
	private String[] colunas = {"MethodID","package","class","method","LOC","CYCLO",
			"ATFD","LAA","is_long_method","iPlasma","PMD","is_future_envy"};
	private DataModel() {
		INSTANCE = this;
	}
	public void readFile() {
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
