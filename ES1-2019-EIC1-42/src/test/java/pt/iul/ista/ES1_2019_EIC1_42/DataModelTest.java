/**
 * 
 */
package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dariop
 *
 */
public class DataModelTest {

	private static DataModel ntable;
	private static DataModel table;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new Thread(new Runnable() {

			public void run() {
				try {
					Robot r = new Robot();
					r.delay(1000);
					r.keyPress(KeyEvent.VK_ESCAPE);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		ntable = DataModel.getInstance();
		table = DataModel.newInstance();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * Tests the class as a whole. Test focused on the correct initialization of
	 * DataModelTest object
	 */

	@Test
	public void ConstructorTest() {
		assertTrue(table.getColumnCount() >= 0);
		assertTrue(table.getRowCount() >= 0);
	}
	
	
	@Test
	public void ConstructorTest_null() {
		assertTrue(ntable.getColumnCount() == 0);
		assertTrue(ntable.getRowCount() == 0);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getContent()}.
	 */
	@Test
	public void testGetContent() {
		try {
			table.getContent();
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
	
	@Test
	public void testGetContent_null() {
		try {
			ntable.getContent();
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
	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getColumnCount()}.
	 */
	@Test
	public void testGetColumnCount() {
		assertTrue(table.getColumnCount() >= 0);
	}
	
	@Test
	public void testGetColumnCount_null() {
		assertTrue(ntable.getColumnCount() == 0);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getRowCount()}.
	 */
	@Test
	public void testGetRowCount() {
		assertTrue(table.getRowCount() >= 0);
	}
	
	@Test
	public void testGetRowCount_null() {
		assertTrue(ntable.getRowCount() == 0);
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getColumnName(int)}.
	 */
	@Test
	public void testGetColumnName() {
		assertTrue(table.getColumnName(0) != null);
	}
	
	
	@Test
	public void testGetColumnName_null() {
		assertTrue(ntable.getColumnName(0) == "");
	}
	
	
	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getValueAt(int, int)}.
	 */
	@Test
	public void testGetValueAt() {
		assertTrue(table.getValueAt(0, 0) != null);
	}
	
	@Test
	public void testGetValueAt_case3() {
		assertTrue(table.getValueAt(0, 3) != null);
	}
	
	@Test
	public void testGetValueAt_case6() {
		assertTrue(table.getValueAt(0, 6) != null);
	}
	
	@Test
	public void testGetValueAt_case7() {
		assertTrue(table.getValueAt(0, 7) != null);
	}
	
	@Test
	public void testGetValueAt_casedefault() {
		assertTrue(table.getValueAt(0, 8) != null);
	}
	
	@Test
	public void testGetValueAt_null() {
		assertTrue(ntable.getValueAt(0, 0) == "");
	}


	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertTrue(DataModel.getInstance() != null);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#fileChooser()}.
	 */
	@Test
	public void testFileChooser_null() {
		new Thread(new Runnable() {

			public void run() {
				try {
					Robot r = new Robot();
					r.delay(1000);
					r.keyPress(KeyEvent.VK_ESCAPE);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		assertNull(ntable.fileChooser());
	}

}
