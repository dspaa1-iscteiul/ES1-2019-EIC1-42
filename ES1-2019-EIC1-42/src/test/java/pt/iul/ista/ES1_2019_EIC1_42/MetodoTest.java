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
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isLongMethod()}.
	 */
	@Test
	public void testIsLongMethod() {
		assertEquals(is_long_method, metodo.isIs_long_method());
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isFeatureEnvy()}.
	 */
	@Test
	public void testIsFeatureEnvy() {
		assertEquals(metodo.isIs_feature_envy(), is_feature_envy);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getMethodID()}.
	 */
	@Test
	public void testGetMethodID() {
		assertEquals(METHOD_ID, metodo.getMethodID());
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLoc()}.
	 */
	@Test
	public void testGetLoc() {
		assertEquals(metodo.getLoc(), LOC);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getCyclo()}.
	 */
	@Test
	public void testGetCyclo() {
		assertEquals(metodo.getCyclo(), CYCLO);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getAtfd()}.
	 */
	@Test
	public void testGetAtfd() {
		assertEquals(metodo.getAtfd(), ATFD);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#getLaa()}.
	 */
	@Test
	public void testGetLaa() {
		assertEquals(metodo.getLaa(), LAA, 0.01);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isIplasma()}.
	 */
	@Test
	public void testIsIplasma() {
		assertEquals(metodo.isIplasma(), iPlasma);
	}

	/**
	 * Test method for {@link pt.iul.ista.ES1_2019_EIC1_42.Metodo#isPmd()}.
	 */
	@Test
	public void testIsPmd() {
		assertEquals(metodo.isPmd(),pmd);
	}

}
