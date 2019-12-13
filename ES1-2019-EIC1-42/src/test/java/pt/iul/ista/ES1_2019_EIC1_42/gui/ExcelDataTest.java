package pt.iul.ista.ES1_2019_EIC1_42.gui;

import static org.junit.Assert.*;

import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelDataTest {

	private static ExcelData excel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		excel = new ExcelData();
	}

	@Test
	public void testExcelData() {
		assertEquals(excel.getSize().getWidth(), 1300, 0.1);
		assertEquals(excel.getSize().getHeight(), 600, 0.1);
		assertEquals(excel.getDefaultCloseOperation(), JFrame.EXIT_ON_CLOSE);
		MouseEvent e = new MouseEvent(excel.getTable(), MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 150, 150,
				1, false);
		assertEquals(excel.getTable().getToolTipText(e), "");
	}
}
