package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Classe para gerir os dados que são importados do ficheiro Excel e são
 * passados para uma JTable
 * 
 * @author elsas
 *
 */
public class DataModel extends AbstractTableModel {

	private ArrayList<Metodo> metodos;
	private static DataModel INSTANCE;
	private Sheet sheet;
	private DataModel() {
		INSTANCE = this;
	}

	public void getContent() throws EncryptedDocumentException, InvalidFormatException, IOException {
		metodos = new ArrayList<Metodo>();
		Workbook workbook = WorkbookFactory.create(new File("Long-Method.xlsx"));
		sheet = workbook.getSheetAt(0);
		DataFormatter dataFormatter = new DataFormatter();
		int j = 1;
		while (j <= sheet.getLastRowNum()) {
			Row row = sheet.getRow(j);
			int methodID = (int) row.getCell(0).getNumericCellValue();
			String pacote = row.getCell(1).getStringCellValue();
			String classe = row.getCell(2).getStringCellValue();
			String method = row.getCell(3).getStringCellValue();
			int loc = (int) row.getCell(4).getNumericCellValue();
			int cyclo = (int) row.getCell(5).getNumericCellValue();
			int atfd = (int) row.getCell(6).getNumericCellValue();
			double laa = Double.parseDouble(dataFormatter.formatCellValue(row.getCell(7)));
			boolean is_long_method = row.getCell(8).getBooleanCellValue();
			boolean iplasma = row.getCell(9).getBooleanCellValue();
			boolean pmd = row.getCell(10).getBooleanCellValue();
			boolean is_feature_envy = row.getCell(11).getBooleanCellValue();
			Metodo met = new Metodo(methodID,pacote,classe,method,loc,cyclo,atfd,laa,is_long_method,
					iplasma,pmd,is_feature_envy);
			metodos.add(met);
			j++;

		}
	}

	public int getColumnCount() {
		int last_cell = sheet.getRow(0).getLastCellNum();
		return last_cell;
	}

	public int getRowCount() {
		return sheet.getLastRowNum();
	}

	public Object getValueAt(int arg0, int arg1) {
		Cell cell = sheet.getRow(arg0).getCell(arg1);
		switch(cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		
			
		}
		return null;
	}



}
