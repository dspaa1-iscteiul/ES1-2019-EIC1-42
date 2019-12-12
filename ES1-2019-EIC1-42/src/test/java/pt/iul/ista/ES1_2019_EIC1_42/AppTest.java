package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.Rectangle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.gui.ExcelData;

public class AppTest {
	
	ExcelData ed;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ed = new ExcelData();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMain() {
		ed.openFile(); //cria fazer o openFile dps testar o file mas isso tem de abrir a gui;
	}

}
