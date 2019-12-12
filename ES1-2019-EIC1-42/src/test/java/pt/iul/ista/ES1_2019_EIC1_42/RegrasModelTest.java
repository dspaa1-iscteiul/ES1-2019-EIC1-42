package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RegrasModelTest {
	
	RegrasModel regras;
	Regra r = new Regra("regra", Metrica.ATFD, Metrica.LAA, 20, 30, Logic_And_Or.AND);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		regras = new RegrasModel();
		regras.addRegra(r);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetElementAt() {
		assertEquals(r,regras.getElementAt(0));
	}

	@Test
	public void testGetSize() {
		assertEquals(1,regras.getSize());
	}

	@Test
	public void testAddRegra() {
		assertFalse(regras.addRegra(r));
	}

	@Test
	public void testRemoveRegra() {
		assertTrue(regras.removeRegra(r));
		assertFalse(regras.removeRegra(r));
	}

	@Test
	public void testAtualizar() {
		r.setValor_1(40);
		regras.atualizar(r);
		assertEquals(40,regras.getElementAt(0).getValor_1());
		
	}

	@Test
	public void testGetRegras() {
		assertNotNull(regras.getRegras());
	}

	
	@Test
	public void testGetInstance() {
		assertNotNull(RegrasModel.getInstance());
	}

}
