package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import java.io.File;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

/**
 * Classe para gerir os dados que são importados do ficheiro Excel e são
 * passados para uma JTable
 * 
 * @author elsas
 *
 */
public class DataModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4220550076295049347L;
	private ArrayList<Metodo> metodos;
	private static DataModel INSTANCE;
	private Sheet sheet;
	
	private DataModel() {
//		INSTANCE = this;
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File("Long-Method.xlsx"));
			sheet = workbook.getSheetAt(0);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void getContent() throws EncryptedDocumentException, InvalidFormatException, IOException {
		metodos = new ArrayList<Metodo>();
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

	@Override
	public String getColumnName(int arg0) {
		Cell cell = sheet.getRow(0).getCell(arg0);
		
		return cell.getStringCellValue();
	}

	public Object getValueAt(int arg0, int arg1) {
		DataFormatter dataFormatter = new DataFormatter();
		arg0++;
		Cell cell = sheet.getRow(arg0).getCell(arg1);
//		if(arg0 == 0) {
//			return cell.getStringCellValue();
//		}
		switch (arg1) {
		case 0:
			return (int) cell.getNumericCellValue();
		case 1:
		case 2:
		case 3:
			return cell.getStringCellValue();
		case 4:
		case 5:
		case 6:
			return (int) cell.getNumericCellValue();
		case 7:
			return Double.parseDouble(dataFormatter.formatCellValue(cell));
		default:
			return cell.getBooleanCellValue();
		}
	
	}
	
	public static DataModel getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DataModel();
		return INSTANCE;
	}

}
