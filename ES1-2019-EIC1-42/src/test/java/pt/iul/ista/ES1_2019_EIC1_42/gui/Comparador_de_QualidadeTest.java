package pt.iul.ista.ES1_2019_EIC1_42.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;

public class Comparador_de_QualidadeTest {

	private static Comparador_de_Qualidade comparador;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		comparador = Comparador_de_Qualidade.getInstance();
	}

	@Test
	public void testInicializarRegrasHashMaps() {
		comparador.inicializarRegrasHashMaps();
	}

	@Test
	public void testCreateResultsTable() {
		comparador.createResultsTable();
		assertNotNull(comparador.getResultTable());
	}

	@Test
	public void testColumnForEachRule() {
		assertEquals(comparador.getTableModelResultados().getColumnCount(),3);
		RegrasModel.getInstance()
				.addRegra(new Regra("nome", Metrica.LOC, Metrica.CYCLO, (Number) 12, (Number) 13, Logic_And_Or.AND));
		comparador.columnForEachRule();
		assertEquals(comparador.getTableModelResultados().getColumnCount(),4);
	}

	@Test
	public void testRuleResultForEachMethod() {
		assertEquals(comparador.ruleResultForEachMethod(
				new Regra("nome", Metrica.LOC, Metrica.CYCLO, (Number) 12, (Number) 13, Logic_And_Or.AND)).length, 0);
	}

	@Test
	public void testCreateIndicatorTable() {
		comparador.createIndicatorTable();
		assertNotNull(comparador.getIndicadoresTable());
	}

	@Test
	public void testColumnForEachRegra() {
		assertEquals(comparador.getTableModelIndicadores().getColumnCount(),4);
		RegrasModel.getInstance()
				.addRegra(new Regra("nome", Metrica.LOC, Metrica.CYCLO, (Number) 12, (Number) 13, Logic_And_Or.AND));
		comparador.columnForEachRegra();
		assertEquals(comparador.getTableModelIndicadores().getColumnCount(),4);
	}

	@Test
	public void testGetRegrasValues() {
		comparador.getRegrasValues();
	}

	@Test
	public void testVerifyLongMethodRegraLogicValue() {
		Regra regra = new Regra("nome", Metrica.LOC, Metrica.CYCLO, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.verifyLongMethodRegraLogicValue(regra);
	}

	@Test
	public void testVerifyFeatureEnvyRegraLogicValue() {
		Regra regra = new Regra("nome", Metrica.ATFD, Metrica.LAA, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.verifyFeatureEnvyRegraLogicValue(regra);
	}

	@Test
	public void testGenerateLongMethodRegraValuesAnd() {
		comparador.generateLongMethodRegraValuesAnd(22, 22, new ArrayList<Boolean>());
	}

	@Test
	public void testGenerateLongMethodRegraValuesOr() {
		comparador.generateLongMethodRegraValuesOr(22, 22, new ArrayList<Boolean>());
	}

	@Test
	public void testGenerateFeatureEnvyRegraValuesAnd() {
		comparador.generateFeatureEnvyRegraValuesAnd(22, 22, new ArrayList<Boolean>());
	}

	@Test
	public void testGenerateFeatureEnvyRegraValuesOr() {
		comparador.generateFeatureEnvyRegraValuesOr(22, 22, new ArrayList<Boolean>());
	}

	@Test
	public void testRegraResultForEachMethodTest() {
		Regra regra = new Regra("nome", Metrica.ATFD, Metrica.LAA, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.getIndicadoresRegrasUtilizador().put(regra, new ArrayList<Integer>());
	}

	@Test
	public void testCalculateIndicadoresPMDiPlasma() {
		comparador.calculateIndicadoresPMDiPlasma();
	}

	@Test
	public void testCalculateIndicadoresiPlasma() {
		comparador.calculateIndicadoresiPlasma();
	}

	@Test
	public void testCalculateIndicadoresPMD() {
		comparador.calculateIndicadoresPMD();
	}

	@Test
	public void testCalculateIndicadoresRegras() {
		comparador.calculateIndicadoresRegras();
	}

	@Test
	public void testCalculateIndicadoresLongMethod() {
		comparador.calculateIndicadoresLongMethod();
	}

	@Test
	public void testCalculateIndicadoresLongMethodRegras() {
		Regra regra = new Regra("nome", Metrica.LOC, Metrica.CYCLO, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.calculateIndicadoresLongMethodRegras(regra);
	}

	@Test
	public void testCalculateIndicadoresFeatureEnvy() {
		comparador.calculateIndicadoresFeatureEnvy();
	}

	@Test
	public void testCalculateIndicadoresFeatureEnvyRegras() {
		Regra regra = new Regra("nome", Metrica.ATFD, Metrica.LAA, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.calculateIndicadoresFeatureEnvyRegras(regra);
	}

	@Test
	public void testRemoveRegra() {
		comparador.removeRegra(null);
	}

	@Test
	public void testMudarNomedaRegra() {
		Regra regra = new Regra("nome", Metrica.ATFD, Metrica.LAA, (Number) 12, (Number) 13, Logic_And_Or.AND);
		RegrasModel.getInstance().addRegra(regra);
		comparador.mudarNomedaRegra(regra, "null");
	}

	@Test
	public void testExistsRuleWithName() {
		Regra regra = new Regra("nome", Metrica.ATFD, Metrica.LAA, (Number) 12, (Number) 13, Logic_And_Or.AND);
		comparador.getTableModelResultados().addColumn(regra.getNome(), null);
		assertTrue(comparador.existsRuleWithName("nome"));
		assertFalse(comparador.existsRuleWithName("nada"));
	}

	@Test
	public void testAtualizar() {
		comparador.atualizar();
	}

	@Test
	public void testGetInstance() {
		assertEquals(comparador, Comparador_de_Qualidade.getInstance());
	}

}
