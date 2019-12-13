/**
 * 
 */
package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
		table = DataModel.getInstance();
		ntable = DataModel.newInstance();
		table.setFile(true);
		ntable.setFile(false);
	}

	/**
	 * Tests the class as a whole. Test focused on the correct initialization of
	 * DataModelTest object
	 */
	@Test
	public void ConstructorTest() {
		assertEquals(table.getColumnCount(), 12);
		assertEquals(table.getRowCount(), 420);
	}

	@Test
	public void ConstructorTest_null() {
		assertEquals(ntable.getColumnCount(), 0);
		assertEquals(ntable.getRowCount(), 0);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getContent()}.
	 */
	@Test
	public void testGetContent() {
		try {
			assertEquals(table.getContent().size(),420);
			assertEquals(ntable.getContent().size(),0);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getColumnName(int)}.
	 */
	@Test
	public void testGetColumnName() {
		assertEquals(table.getColumnName(0), "MethodID");
		assertEquals(table.getColumnName(11), "is_feature_envy");
	}

	@Test
	public void testGetColumnName_null() {
		assertEquals(ntable.getColumnName(0), "");
	}

	/**
	 * Test method for
	 * {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getValueAt(int, int)}.
	 */
	@Test
	public void testGetValueAt() {
		assertEquals(table.getValueAt(0, 0), 1);
	}

	@Test
	public void testGetValueAt_case3() {
		assertEquals(table.getValueAt(0, 3), "Output()");
	}

	@Test
	public void testGetValueAt_case6() {
		assertEquals(table.getValueAt(0, 6), 0);
	}

	@Test
	public void testGetValueAt_case7() {
		assertEquals(table.getValueAt(0, 7), 1.0);
	}

	@Test
	public void testGetValueAt_casedefault() {
		assertEquals(table.getValueAt(0, 8), false);
	}

	@Test
	public void testGetValueAt_null() {
		assertEquals(ntable.getValueAt(0, 0), "");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.DataModel#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(DataModel.getInstance());
	}

}
