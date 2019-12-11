package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import pt.iul.ista.ES1_2019_EIC1_42.CompareTable;
import pt.iul.ista.ES1_2019_EIC1_42.DataModel;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import pt.iul.ista.ES1_2019_EIC1_42.Metodo;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;
import pt.iul.ista.ES1_2019_EIC1_42.ResultadosIndicadoresModel;

/**
 * Classe para visualizar graficamente as regras comparativamente
 * 
 * @author dariop
 * @author fmpts
 * @author rmdca
 * @author aaspo
 */
public class Comparador_de_Qualidade extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 489999421652308781L;
	private static Comparador_de_Qualidade INSTANCE;
	private ArrayList<Regra> regras;
	private ArrayList<Metodo> metodos;
	private final JPanel contentPanel = new JPanel();
	private JPanel resultados_panel, indicadores_panel;
	private JTabbedPane tabbedPane;

	private ResultadosIndicadoresModel tableModelResultados, tableModelIndicadores;
	private JTable resultTable, indicadoresTable;

	private HashMap<Regra, ArrayList<Boolean>> longMethodRegrasValues;
	private HashMap<Regra, ArrayList<Boolean>> featureEnvyRegrasValues;
	private HashMap<Regra, ArrayList<Integer>> indicadoresRegrasUtilizador;
	private Integer[] indicadoresiPlasma = new Integer[4];
	private Integer[] indicadoresPMD = new Integer[4];

	/**
	 * Create the dialog.
	 */
	private Comparador_de_Qualidade() {

		setBounds(100, 100, 600, 600);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane);

		resultados_panel = new JPanel(new BorderLayout());
		tabbedPane.addTab("Resultados", null, resultados_panel, "Resultados da deteção dos defeitos");

		indicadores_panel = new JPanel(new BorderLayout());
		tabbedPane.addTab("Indicadores", null, indicadores_panel, "Indicadores de qualidade");

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(okButton);

		try {
			metodos = DataModel.getInstance().getContent();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
			metodos = new ArrayList<Metodo>();
		}

		inicializarRegrasHashMaps();
		regras = RegrasModel.getInstance().getRegras();

		createResultsTable();
		calculateIndicadoresPMDiPlasma();
		createIndicatorTable();

	}

	public void inicializarRegrasHashMaps() {
		longMethodRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
		featureEnvyRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
		indicadoresRegrasUtilizador = new HashMap<Regra, ArrayList<Integer>>();
	}

	/**
	 * Creates table with MethodID, iPlasma and PMD columns and respective results
	 */
	public void createResultsTable() {
		String[] col = { "MethodID", "iPlasma", "PMD" };

		tableModelResultados = new ResultadosIndicadoresModel(col);
		resultTable = new CompareTable(col, tableModelResultados);

		for (int i = 0; i != metodos.size(); i++) {
			int methodID = metodos.get(i).getMethodID();
			boolean iPlasma = metodos.get(i).isIplasma();
			boolean PMD = metodos.get(i).isPmd();
			Object[] data = { methodID, iPlasma, PMD };
			tableModelResultados.addRow(data);
		}
		JScrollPane panel = new JScrollPane(resultTable);
		resultados_panel.add(panel);
	}

	/**
	 * Creates a column for each rule created and fills it out with the results for
	 * each methodID
	 */
	public void columnForEachRule() {
		if (!regras.isEmpty()) {
			for (int i = 0; i != regras.size(); i++) {
				if (tableModelResultados.existsColumn(regras.get(i).getNome()))
					tableModelResultados.removeColumn(regras.get(i).getNome());
				tableModelResultados.addColumn(regras.get(i).getNome(), ruleResultForEachMethod(regras.get(i)));
			}
		}
	}

	/**
	 * Verifies the rule result for each method and stores it in an array
	 * 
	 * @param r rule that should be verified
	 * @return an array that contains the full column with the results for the rule
	 *         in the parameter
	 */
	public Object[] ruleResultForEachMethod(Regra r) {
		Object[] resultados = null;
		if (r.isLongMethodRule()) {
			resultados = longMethodRegrasValues.get(r).toArray();
		} else if (r.isFeautureEnvyRule()) {
			resultados = featureEnvyRegrasValues.get(r).toArray();
		}
		return resultados;
	}

	/**
	 * creates Indicator Table
	 */
	public void createIndicatorTable() {
		String[] col = { "", "iPlasma", "PMD" };

		tableModelIndicadores = new ResultadosIndicadoresModel(col);
		indicadoresTable = new CompareTable(col, tableModelIndicadores);

		String[] indicadores = { "DCI", "DII", "ADCI", "ADII" };
		for (int i = 0; i != 4; i++) {
			Object[] data = { indicadores[i], indicadoresiPlasma[i], indicadoresPMD[i] };
			tableModelIndicadores.addRow(data);
		}
		JScrollPane panel1 = new JScrollPane(indicadoresTable);
		indicadores_panel.add(panel1);
	}

	/**
	 * Generates Columns for each User Rule at Indicators Table
	 */
	public void columnForEachRegra() {
		if (!regras.isEmpty()) {
			for (int i = 0; i != regras.size(); i++) {
				if (tableModelIndicadores.existsColumn(regras.get(i).getNome()))
					tableModelIndicadores.removeColumn(regras.get(i).getNome());
				tableModelIndicadores.addColumn(regras.get(i).getNome(), regraResultForEachMethod(regras.get(i)));
			}
		}
	}

	/**
	 * Verifies the indicators for the rule and stores it in an array
	 * 
	 * @param r rule that should be verified
	 * @return an array that contains the indicators for the rule
	 */
	public Object[] regraResultForEachMethod(Regra r) {
		return indicadoresRegrasUtilizador.get(r).toArray();
	}

	/**
	 * Collects Rules results for each method - Boolean ArrayList
	 */
	public void getRegrasValues() {
		for (Regra regra : this.regras) {
			if (regra.isLongMethodRule()) {
				verifyLongMethodRegraLogicValue(regra);
			} else if (regra.isFeautureEnvyRule()) {
				verifyFeatureEnvyRegraLogicValue(regra);
			}
		}
	}

	/**
	 * Verifies LongMethod Rule Logic Condition. Adds Boolean Results to HashMap
	 * 
	 * @param regra - rule that should be verified
	 * 
	 */
	public void verifyLongMethodRegraLogicValue(Regra regra) {
		ArrayList<Boolean> longMethodRuleValues = new ArrayList<Boolean>();
		if (regra.getLogico() == Logic_And_Or.AND) {
			generateLongMethodRegraValuesAnd(regra.getValor_1(), regra.getValor_2(), longMethodRuleValues);
		} else if (regra.getLogico() == Logic_And_Or.OR) {
			generateLongMethodRegraValuesOr(regra.getValor_1(), regra.getValor_2(), longMethodRuleValues);
		}
		this.longMethodRegrasValues.put(regra, longMethodRuleValues);
	}

	/**
	 * Verifies FeatureEnvy Rule Logic Condition. Adds Boolean Results to HashMap
	 * 
	 * @param regra - rule that should be verified
	 * 
	 */
	public void verifyFeatureEnvyRegraLogicValue(Regra regra) {
		ArrayList<Boolean> featureEnvyRuleValues = new ArrayList<Boolean>();
		if (regra.getLogico() == Logic_And_Or.AND) {
			generateFeatureEnvyRegraValuesAnd(regra.getValor_1(), regra.getValor_2(), featureEnvyRuleValues);
		} else if (regra.getLogico() == Logic_And_Or.OR) {
			generateFeatureEnvyRegraValuesOr(regra.getValor_1(), regra.getValor_2(), featureEnvyRuleValues);
		}
		this.featureEnvyRegrasValues.put(regra, featureEnvyRuleValues);
	}

	/**
	 * Generates LongMethod Rule Values based on And Condition
	 * 
	 * @param locValue
	 * @param cycloValue
	 * @param longMethodRuleValues- - ArrayList that contains the actual longMethod
	 *                              values for the rules
	 * 
	 */
	public void generateLongMethodRegraValuesAnd(Number locValue, Number cycloValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < metodos.size(); i++) {
			longMethodRuleValues.add(((metodos.get(i).getLoc() > (Integer) locValue)
					&& (metodos.get(i).getCyclo() > (Integer) cycloValue)));
		}
	}

	/**
	 * Generates LongMethod Rule Values based on Or Condition
	 * 
	 * @param locValue
	 * @param cycloValue
	 * @param longMethodRuleValues- ArrayList that contains the actual longMethod
	 *                              values for the rules
	 */
	public void generateLongMethodRegraValuesOr(Number locValue, Number cycloValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < metodos.size(); i++) {
			longMethodRuleValues.add(((metodos.get(i).getLoc() > (Integer) locValue)
					|| (metodos.get(i).getCyclo() > (Integer) cycloValue)));
		}
	}

	/**
	 * Generates FeatureEnvy Rule Values based on And Condition
	 * 
	 * @param atfdValue
	 * @param laaValue
	 * @param featureEnvyRuleValues - ArrayList that contains the actual feature
	 *                              envy values for the rules
	 * 
	 */
	public void generateFeatureEnvyRegraValuesAnd(Number atfdValue, Number laaValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < metodos.size(); i++) {
			featureEnvyRuleValues.add(((metodos.get(i).getAtfd() > (Integer) atfdValue)
					&& (metodos.get(i).getLaa() < (Double) laaValue)));
		}
	}

	/**
	 * Generates FeatureEnvy Rule Values based on Or Condition
	 * 
	 * @param atfdValue
	 * @param laaValue
	 * @param featureEnvyRuleValues - ArrayList that contains the actual feature
	 *                              envy values for the rules
	 * 
	 */
	public void generateFeatureEnvyRegraValuesOr(Number atfdValue, Number laaValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < metodos.size(); i++) {
			featureEnvyRuleValues.add(((metodos.get(i).getAtfd() > (Integer) atfdValue)
					|| (metodos.get(i).getLaa() < (Double) laaValue)));
		}
	}

	/**
	 * Starts collection of PMD and iPlasma quality Indicadores
	 */
	public void calculateIndicadoresPMDiPlasma() {
//		for (int i = 0; i < metodos.size(); i++) {
		calculateIndicadoresiPlasma();
		calculateIndicadoresPMD();
//		}
	}

	/**
	 * Calcula indicadores de qualidade iPlasma
	 * 
	 */
	public void calculateIndicadoresiPlasma() {
		int dci = 0, dii = 0, adci = 0, adii = 0;
		for (int i = 0; i < metodos.size(); i++) {
			if (metodos.get(i).isIplasma() && metodos.get(i).isIs_long_method()) {
				dci++;
			} else if (metodos.get(i).isIplasma() && !metodos.get(i).isIs_long_method()) {
				dii++;
			} else if (!metodos.get(i).isIplasma() && !metodos.get(i).isIs_long_method()) {
				adci++;
			} else if (!metodos.get(i).isIplasma() && metodos.get(i).isIs_long_method()) {
				adii++;
			}
		}
		indicadoresiPlasma[0] = dci;
		indicadoresiPlasma[1] = dii;
		indicadoresiPlasma[2] = adci;
		indicadoresiPlasma[3] = adii;
	}

	/**
	 * Calcula indicadores de qualidade PMD
	 * 
	 */
	public void calculateIndicadoresPMD() {
		int dci = 0, dii = 0, adci = 0, adii = 0;
		for (int i = 0; i < metodos.size(); i++) {
			if (metodos.get(i).isPmd() && metodos.get(i).isIs_long_method()) {
				dci++;
			} else if (metodos.get(i).isPmd() && !metodos.get(i).isIs_long_method()) {
				dii++;
			} else if (!metodos.get(i).isPmd() && !metodos.get(i).isIs_long_method()) {
				adci++;
			} else if (!metodos.get(i).isPmd() && metodos.get(i).isIs_long_method()) {
				adii++;
			}
		}
		indicadoresPMD[0] = dci;
		indicadoresPMD[1] = dii;
		indicadoresPMD[2] = adci;
		indicadoresPMD[3] = adii;
	}

	/**
	 * Starts collection of Quality Indicators
	 */

	public void calculateIndicadoresRegras() {
		indicadoresRegrasUtilizador = new HashMap<Regra, ArrayList<Integer>>();
		calculateIndicadoresFeatureEnvy();
		calculateIndicadoresLongMethod();
	}

	/**
	 * Starts collection of Long Method User Rules quality Indicadores
	 */
	public void calculateIndicadoresLongMethod() {
		for (Regra regra : this.longMethodRegrasValues.keySet()) {
			this.indicadoresRegrasUtilizador.put(regra, calculateIndicadoresLongMethodRegras(regra));
		}
	}

	/**
	 * Calcula indicadores de qualidade Long Method Regras Utilizador
	 * 
	 * @param regra
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresLongMethodRegras(Regra regra) {
		int dci = 0, dii = 0, adci = 0, adii = 0;
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.longMethodRegrasValues.get(regra);
		for (int i = 0; i < metodos.size(); i++) {
			if (regraValues.get(i) && metodos.get(i).isIs_long_method()) {
				dci++;
			} else if (regraValues.get(i) && !metodos.get(i).isIs_long_method()) {
				dii++;
			} else if (!regraValues.get(i) && !metodos.get(i).isIs_long_method()) {
				adci++;
			} else if (!regraValues.get(i) && metodos.get(i).isIs_long_method()) {
				adii++;
			}
		}
		indicadores.add(dci);
		indicadores.add(dii);
		indicadores.add(adci);
		indicadores.add(adii);
		return indicadores;
	}

	/**
	 * Starts collection of Feature Envy Rules quality Indicadores
	 */
	public void calculateIndicadoresFeatureEnvy() {
		for (Regra regra : this.featureEnvyRegrasValues.keySet()) {
			this.indicadoresRegrasUtilizador.put(regra, calculateIndicadoresFeatureEnvyRegras(regra));
		}
	}

	/**
	 * Calcula indicadores de qualidade Feature Envy Regras Utilizador
	 * 
	 * @param regra
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresFeatureEnvyRegras(Regra regra) {
		int dci = 0, dii = 0, adci = 0, adii = 0;
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.featureEnvyRegrasValues.get(regra);
		for (int j = 0; j < metodos.size(); j++) {
			if (regraValues.get(j) && metodos.get(j).isIs_feature_envy()) {
				dci++;
			} else if (regraValues.get(j) && !metodos.get(j).isIs_feature_envy()) {
				dii++;
			} else if (!regraValues.get(j) && !metodos.get(j).isIs_feature_envy()) {
				adci++;
			} else if (!regraValues.get(j) && metodos.get(j).isIs_feature_envy()) {
				adii++;
			}
		}
		indicadores.add(dci);
		indicadores.add(dii);
		indicadores.add(adci);
		indicadores.add(adii);
		return indicadores;
	}

	/**
	 * Remove uma regra de ambas as tabelas
	 * 
	 * @param regra - regra que deve ser removida
	 */
	public void removeRegra(Regra regra) {
		if (regra != null) {
			tableModelResultados.removeColumn(regra.getNome());
			tableModelIndicadores.removeColumn(regra.getNome());
		}
	}

	/**
	 * Altera o nome da coluna correspondente à regra dada
	 * 
	 * @param regra    - regra que se pretende alterar o nome
	 * @param novoNome - nome que se quer atribuir
	 */
	public void mudarNomedaRegra(Regra regra, String novoNome) {
		tableModelResultados.changeColumnName(regra.getNome(), novoNome);
		tableModelIndicadores.changeColumnName(regra.getNome(), novoNome);
	}

	/**
	 * Verifica se existe nas tabelas uma regra com o nome dado
	 * 
	 * @param name - nome da regra que se pretende verificar se existe
	 * @return true - se foi encontrada uma coluna com o nome dado como parametro
	 */
	public boolean existsRuleWithName(String name) {
		return tableModelResultados.existsColumn(name);
	}

	/**
	 * Sets the dialog visible
	 */
	public void open() {
		atualizar();
		setVisible(true);
	}

	/**
	 * Atualiza a informação presente nas tabelas.
	 */
	public void atualizar() {
		inicializarRegrasHashMaps();
		getRegrasValues();
		columnForEachRule();
		calculateIndicadoresRegras();
		columnForEachRegra();
	}

	/**
	 * 
	 * @return uma instância dessa classe
	 */
	public static Comparador_de_Qualidade getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Comparador_de_Qualidade();
		return INSTANCE;
	}

	public ArrayList<Regra> getRegras() {
		return regras;
	}

	public HashMap<Regra, ArrayList<Integer>> getIndicadoresRegrasUtilizador() {
		return indicadoresRegrasUtilizador;
	}

}