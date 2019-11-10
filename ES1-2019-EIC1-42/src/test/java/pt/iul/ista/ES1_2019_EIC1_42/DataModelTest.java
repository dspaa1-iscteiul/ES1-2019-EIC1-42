/**
 * 
 */
package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.Frame;
import java.io.IOException;

import javax.swing.table.AbstractTableModel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.gui.ExcelData;

/**
 * @author dariop
 *
 */
public class DataModelTest {
	
	private DataModel table;
	
	/**
	 * Tests the class as a whole. 
	 * Test focused on the correct initialization of DataModelTest object
	*/
	
	@Test
	public void ConstructorTest() {
		table = new DataModel();		
		assertTrue(table.getColumnCount() != 0);
		assertTrue(table.getRowCount() != 0);
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getContent()}.
	 */
	@Test
	public void testGetContent() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getColumnCount()}.
	 */
	@Test
	public void testGetColumnCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getRowCount()}.
	 */
	@Test
	public void testGetRowCount() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getColumnName(int)}.
	 */
	@Test
	public void testGetColumnNameInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getValueAt(int, int)}.
	 */
	@Test
	public void testGetValueAt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

}
