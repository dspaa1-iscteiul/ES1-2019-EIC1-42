package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import javax.swing.table.AbstractTableModel;

import org.junit.BeforeClass;
import org.junit.Test;

public class CompareTableTest {
	
	private static CompareTable ct;
	private static AbstractTableModel tableModel;
	private static String[] col;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		col = new String[]{"a", "b", "c", "d"};
		tableModel = new ResultadosIndicadoresModel(col);
		ct = new CompareTable(col, tableModel);
	}

	@Test
	public void testCompareTable() {
		assertFalse(ct.getTableHeader().getReorderingAllowed());
		assertEquals(tableModel, ct.getModel());
	}
	
	@Test
	public void testGetToolTipText() {
		MouseEvent e = new MouseEvent(ct, MouseEvent.MOUSE_MOVED,
				System.currentTimeMillis(), 0, 150, 150, 1, false);
		assertNull(ct.getToolTipText(e));
		assertEquals(ct.getTableHeader().getToolTipText(e), col[2] + " - long_method");
	}

}
