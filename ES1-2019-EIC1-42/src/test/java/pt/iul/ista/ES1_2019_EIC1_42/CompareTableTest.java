package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

import javax.swing.table.AbstractTableModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompareTableTest {
	Robot bot;
	CompareTable ct;
	AbstractTableModel tableModel = null;
	String[] col = new String[4];
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bot = new Robot();
		bot.mouseMove(1,3);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareTable() {
		ct = new CompareTable(col,tableModel);
		assertEquals(4,col.length);
	}

	@Test
	public void testCreateDefaultTableHeader() {
		bot.mouseMove(1,3); // estava a tentar mover o rato para p.x ser > 0 e < 2 para ter o return com long method
		String result = ct.createDefaultTableHeader().getToolTipText();
		assertEquals(true, result.contains("long_method"));
	}

	@Test
	public void testGetToolTipTextMouseEvent() {
		
	}

}
