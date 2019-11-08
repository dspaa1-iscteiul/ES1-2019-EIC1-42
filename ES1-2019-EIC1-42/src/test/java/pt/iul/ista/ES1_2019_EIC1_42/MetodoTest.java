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
	private static boolean pdm = false;
	private static boolean is_feature_envy = false;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		metodo = new Metodo(METHOD_ID, PACKAGE, CLASS, METHOD, LOC, CYCLO, ATFD, LAA, is_long_method, iPlasma, pdm, is_feature_envy);
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
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getPacote()}.
	 */
	@Test
	public void testGetPacote() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setPacote(java.lang.String)}.
	 */
	@Test
	public void testSetPacote() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isLongMethod()}.
	 */
	@Test
	public void testIsLongMethod() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLongMethod(boolean)}.
	 */
	@Test
	public void testSetLongMethod() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isFeatureEnvy()}.
	 */
	@Test
	public void testIsFeatureEnvy() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setFeatureEnvy(boolean)}.
	 */
	@Test
	public void testSetFeatureEnvy() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getMethodID()}.
	 */
	@Test
	public void testGetMethodID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setMethodID(int)}.
	 */
	@Test
	public void testSetMethodID() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getClasse()}.
	 */
	@Test
	public void testGetClasse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setClasse(java.lang.String)}.
	 */
	@Test
	public void testSetClasse() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getMethod()}.
	 */
	@Test
	public void testGetMethod() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setMethod(java.lang.String)}.
	 */
	@Test
	public void testSetMethod() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLoc()}.
	 */
	@Test
	public void testGetLoc() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLoc(int)}.
	 */
	@Test
	public void testSetLoc() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getCyclo()}.
	 */
	@Test
	public void testGetCyclo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setCyclo(int)}.
	 */
	@Test
	public void testSetCyclo() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getAtfd()}.
	 */
	@Test
	public void testGetAtfd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setAtfd(int)}.
	 */
	@Test
	public void testSetAtfd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLaa()}.
	 */
	@Test
	public void testGetLaa() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setLaa(int)}.
	 */
	@Test
	public void testSetLaa() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isIplasma()}.
	 */
	@Test
	public void testIsIplasma() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setIplasma(boolean)}.
	 */
	@Test
	public void testSetIplasma() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isPmd()}.
	 */
	@Test
	public void testIsPmd() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#setPmd(boolean)}.
	 */
	@Test
	public void testSetPmd() {
		fail("Not yet implemented");
	}

}
