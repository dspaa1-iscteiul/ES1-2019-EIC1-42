package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.gui.ExcelData;


/**
 * @author fmpts
 *
 */

public class ExcelDataTest {
	
	private ExcelData frame;
	
	/**
	 * Tests the class as a whole. 
	 * Test focused on the correct initialization of ExcelData object
	*/
	@Test
	public void ConstructorTest() {
		frame = new ExcelData();
		assertTrue(frame.getContentPane() != null);
		assertTrue(frame.getTable() != null);
	}
}
