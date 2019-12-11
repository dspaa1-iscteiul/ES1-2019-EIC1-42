package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
	private static DataModel INSTANCE;
	private Sheet sheet;

	private DataModel() {
	}

	/**
	 * Abre o filechooser para escolher o ficheiro Excel
	 */

	public void addFile() {
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(fileChooser());
			sheet = workbook.getSheetAt(0);
		} catch (NullPointerException | InvalidFormatException | IOException e) {
			System.out.println("Ficheiro não aberto!");
			System.exit(0);
		}
	}

	public ArrayList<Metodo> getContent() throws EncryptedDocumentException, InvalidFormatException, IOException {
		ArrayList<Metodo> metodos = new ArrayList<Metodo>();
		if (sheet != null) {
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
				Metodo met = new Metodo(methodID, pacote, classe, method, loc, cyclo, atfd, laa, is_long_method,
						iplasma, pmd, is_feature_envy);
				metodos.add(met);
				j++;
			}
		}
		return metodos;
	}

	@Override
	public int getColumnCount() {
		if (sheet != null) {
			int last_cell = sheet.getRow(0).getLastCellNum();
			return last_cell;
		} else {
			return 0;
		}
	}

	@Override
	public int getRowCount() {
		if (sheet != null) {
			return sheet.getLastRowNum();
		} else {
			return 0;
		}
	}

	@Override
	public String getColumnName(int arg0) {
		if (sheet != null) {
			Cell cell = sheet.getRow(0).getCell(arg0);
			return cell.getStringCellValue();
		} else {
			return "";
		}
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		if (sheet != null) {
			DataFormatter dataFormatter = new DataFormatter();
			arg0++;
			Cell cell = sheet.getRow(arg0).getCell(arg1);

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
		} else {
			return "";
		}

	}

	/**
	 * 
	 * @return a Instance of this class
	 */
	public static DataModel getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DataModel();
		return INSTANCE;
	}

	/**
	 * 
	 * @return a new Instance of this class
	 */
	public static DataModel newInstance() {
		INSTANCE = new DataModel();
		return INSTANCE;
	}

	/**
	 * Abre um seletor de ficheiros para o utilizador poder abrir um ficheiro do tipo Excel
	 * @return o ficheiro selecionado pelo utilizador (null se não foi selecionado nenhum)
	 */
	public File fileChooser() {
		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File", "xlsx", "excel");
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setFileFilter(filter);
		filechooser.showOpenDialog(null);
		File excelfile = filechooser.getSelectedFile();
		return excelfile;
	}
}
