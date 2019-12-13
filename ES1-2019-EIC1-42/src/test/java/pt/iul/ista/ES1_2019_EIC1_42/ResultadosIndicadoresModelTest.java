package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import org.junit.Test;

public class ResultadosIndicadoresModelTest {
	
	private String[] columnNames= {"col1","col2","col3","col4","col5", "col6", "col7"};
	private ResultadosIndicadoresModel m=new ResultadosIndicadoresModel(columnNames);
	
	@Test
	public void testIsCellEditable() {
		assertFalse(m.isCellEditable(1,1));
	}

	@Test
	public void testResultadosIndicadoresModel() {
		assertNotNull(m);
		
	}

	@Test
	public void testGetColumnCount() {
		assertEquals(7,m.getColumnCount());
		
	}

	@Test
	public void testGetRowCount() {
		assertEquals(0,m.getRowCount());
	}

	@Test
	public void testGetColumnNameInt() {
		assertEquals("col1",m.getColumnName(0));
	}

	@Test
	public void testGetValueAt() {
		Object[] data= {1};
		m.addRow(data);
		assertSame(1,m.getValueAt(0, 0));
	}

	@Test
	public void testAddRow() {
		Object[] data= {1};
		assertTrue(m.addRow(data));
	}

	@Test
	public void testAddColumn() {
		Object[] data= {1};
		m.addRow(data);
		Object[] data1= {1,2,3,4,5};
		assertFalse(m.addColumn("col1", data1));
		assertTrue(m.addColumn("col8", data1));
		
		
	}

	@Test
	public void testRemoveColumn() {
		Object[] data= {1};
		m.addRow(data);
		m.removeColumn("col1");
		assertEquals(6,m.getColumnCount());
	}

	@Test
	public void testExistsColumn() {
		assertTrue(m.existsColumn("col1"));
		assertFalse(m.existsColumn("col8"));
	}

	@Test
	public void testChangeColumnName() {
		assertFalse(m.changeColumnName("col8", "col9"));
		assertTrue(m.changeColumnName("col7", "col7b"));
	}

}
