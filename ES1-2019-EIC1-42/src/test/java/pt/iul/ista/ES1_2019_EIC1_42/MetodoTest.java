/**
 * 
 */
package pt.iul.ista.ES1_2019_EIC1_42;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author dariop
 *
 */
public class MetodoTest {
	
	private static Metodo metodo;
	private static final int METHOD_ID = 1;
	private static final String PACKAGE = "fat";
	private static final String CLASS = "DocumentParseFixture";
	private static final String METHOD = "Output()";
	private static final int LOC = 3;
	private static final int CYCLO = 1;
	private static final int ATFD = 0;
	private static final int LAA = 1;
	private static boolean is_long_method = false;
	private static boolean iPlasma = false;
	private static boolean pmd = false;
	private static boolean is_feature_envy = false;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		metodo = new Metodo(METHOD_ID, PACKAGE, CLASS, METHOD, LOC, CYCLO, ATFD, LAA, is_long_method, iPlasma, pmd, is_feature_envy);
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
		metodo = new Metodo(METHOD_ID, PACKAGE, CLASS, METHOD, LOC, CYCLO, ATFD, LAA, is_long_method, iPlasma, pmd, is_feature_envy);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getPacote()}.
	 */
	@Test
	public void testGetPacote() {
		assertEquals(PACKAGE, metodo.getPacote());
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setPacote(java.lang.String)}.
	 */
	@Test
	public void testSetPacote() {
		metodo.setPacote("novoPacote");
		assertEquals(metodo.getPacote(), "novoPacote");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isLongMethod()}.
	 */
	@Test
	public void testIsLongMethod() {
		assertEquals(is_long_method, metodo.isIs_long_method());
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLongMethod(boolean)}.
	 */
	@Test
	public void testSetLongMethod() {
		metodo.setIs_long_method(true);
		assertEquals(metodo.isIs_long_method(), true);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isFeatureEnvy()}.
	 */
	@Test
	public void testIsFeatureEnvy() {
		assertEquals(metodo.isIs_feature_envy(), is_feature_envy);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setFeatureEnvy(boolean)}.
	 */
	@Test
	public void testSetFeatureEnvy() {
		metodo.setIs_feature_envy(true);
		assertEquals(metodo.isIs_feature_envy(), true);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getMethodID()}.
	 */
	@Test
	public void testGetMethodID() {
		assertEquals(METHOD_ID, metodo.getMethodID());
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setMethodID(int)}.
	 */
	@Test
	public void testSetMethodID() {
		metodo.setMethodID(9999);
		assertEquals(metodo.getMethodID(), 9999);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getClasse()}.
	 */
	@Test
	public void testGetClasse() {
		assertEquals(metodo.getClasse(), CLASS);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setClasse(java.lang.String)}.
	 */
	@Test
	public void testSetClasse() {
		metodo.setClasse("TestClass");
		assertEquals(metodo.getClasse(), "TestClass");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getMethod()}.
	 */
	@Test
	public void testGetMethod() {
		assertEquals(metodo.getMethod(), METHOD);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setMethod(java.lang.String)}.
	 */
	@Test
	public void testSetMethod() {
		metodo.setMethod("Test");
		assertEquals(metodo.getMethod(), "Test");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLoc()}.
	 */
	@Test
	public void testGetLoc() {
		assertEquals(metodo.getLoc(), LOC);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLoc(int)}.
	 */
	@Test
	public void testSetLoc() {
		metodo.setLoc(9999);
		assertEquals(metodo.getLoc(), 9999);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getCyclo()}.
	 */
	@Test
	public void testGetCyclo() {
		assertEquals(metodo.getCyclo(), CYCLO);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setCyclo(int)}.
	 */
	@Test
	public void testSetCyclo() {
		metodo.setCyclo(9999);
		assertEquals(metodo.getCyclo(), 9999);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getAtfd()}.
	 */
	@Test
	public void testGetAtfd() {
		assertEquals(metodo.getAtfd(), ATFD);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setAtfd(int)}.
	 */
	@Test
	public void testSetAtfd() {
		metodo.setAtfd(9999);
		assertEquals(metodo.getAtfd(), 9999);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLaa()}.
	 */
	@Test
	public void testGetLaa() {
		assertEquals(metodo.getLaa(), LAA, 0.01);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLaa(int)}.
	 */
	@Test
	public void testSetLaa() {
		metodo.setLaa(999.9);
		assertEquals(metodo.getLaa(), 999.9, 0.01);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isIplasma()}.
	 */
	@Test
	public void testIsIplasma() {
		assertEquals(metodo.isIplasma(), iPlasma);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setIplasma(boolean)}.
	 */
	@Test
	public void testSetIplasma() {
		metodo.setIplasma(true);
		assertEquals(metodo.isIplasma(), true);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isPmd()}.
	 */
	@Test
	public void testIsPmd() {
		assertEquals(metodo.isPmd(),pmd);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setPmd(boolean)}.
	 */
	@Test
	public void testSetPmd() {
		metodo.setPmd(true);
		assertEquals(metodo.isPmd(), true);
	}

}
