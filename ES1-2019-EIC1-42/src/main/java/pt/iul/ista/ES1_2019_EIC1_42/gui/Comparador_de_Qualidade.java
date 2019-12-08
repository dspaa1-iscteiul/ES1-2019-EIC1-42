package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import pt.iul.ista.ES1_2019_EIC1_42.DataModel;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import pt.iul.ista.ES1_2019_EIC1_42.Metodo;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;

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
//	private Set<Regra> regras;
	private ArrayList<Regra> regras;
	private final JPanel contentPanel = new JPanel();
	private JPanel resultados_panel, indicadores_panel;
	private JTabbedPane tabbedPane;

	private ArrayList<Metodo> metodos = DataModel.getInstance().getMetodos();
	private ArrayList<Boolean> longMethodValues = new ArrayList<Boolean>();
	private ArrayList<Boolean> featureEnvyValues = new ArrayList<Boolean>();
	private ArrayList<Boolean> pmdValues = new ArrayList<Boolean>();
	private ArrayList<Boolean> iplasmaValues = new ArrayList<Boolean>();
	private ArrayList<Integer> locValues = new ArrayList<Integer>();
	private ArrayList<Integer> cycloValues = new ArrayList<Integer>();
	private ArrayList<Integer> atfdValues = new ArrayList<Integer>();
	private ArrayList<Double> laaValues = new ArrayList<Double>();
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	private JTable table;
	private JTable table2;

	private HashMap<Regra, ArrayList<Boolean>> longMethodRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
	private HashMap<Regra, ArrayList<Boolean>> featureEnvyRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
	private Integer[] indicadoresiPlasma = new Integer[4];
	private Integer[] indicadoresPMD = new Integer[4];
	private HashMap<Regra, ArrayList<Integer>> indicadoresRegrasUtilizador = new HashMap<Regra, ArrayList<Integer>>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Comparador_de_Qualidade dialog = new Comparador_de_Qualidade();

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(); //dis
		}
	}

	/**
	 * Create the dialog.
	 */
	private Comparador_de_Qualidade() {
		addIplasmaValues();
		addPMDValues();
		addFeatureEnvyValues();
		addLongMethodValues();
		getDataModelValues();

		calculateIndicadoresPMDiPlasma();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		regras = new HashSet<Regra>();
		regras = RegrasModel.getInstance().getRegras();
		try {
			DataModel.getInstance().getContent();
		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
		} catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		metodos = DataModel.getInstance().getMetodos();
		setBounds(100, 100, 600, 600);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		getRegrasValues();
		calculateIndicadoresLongMethod();
		calculateIndicadoresRegras();
		addIplasmaValues();
		addFeatureEnvyValues();
		addLongMethodValues();
		addPMDValues();
		getDataModelValues();
		calculateIndicadoresPMDiPlasma();

		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPanel.add(tabbedPane);

			{
				resultados_panel = new JPanel(new BorderLayout());
				tabbedPane.addTab("Resultados", null, resultados_panel, "Resultados da deteção dos defeitos");
				createResultsTable();
				{
					indicadores_panel = new JPanel(new BorderLayout());
					tabbedPane.addTab("Indicadores", null, indicadores_panel, "Indicadores de qualidade");
					createIndicatorTable();
				}
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
			}

		}
	}

	/**
	 * Creates table with MethodID, iPlasma and PMD columns and respective results
	 */
	public void createResultsTable() {
		String[] col = new String[3];
		col[0] = "MethodID";
		col[1] = "iPlasma";
		col[2] = "PMD";
		tableModel = new DefaultTableModel(col, 0) {
			/**
			 * Disables JTable cell editing
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(tableModel);
		for (int i = 0; i != metodos.size(); i++) {
			int methodID = metodos.get(i).getMethodID();
			boolean iPlasma = metodos.get(i).isIplasma();
			boolean PMD = metodos.get(i).isPmd();
			Object[] data = { methodID, iPlasma, PMD };
			tableModel.addRow(data);
		}
		JScrollPane panel = new JScrollPane(table);
		resultados_panel.add(panel);
	}

	/**
	 * Sets the dialog visible
	 */
	public void open() {
		System.out.println(regras);
		columnForEachRule();
		getRegrasValues();
		calculateIndicadoresRegras();
		columnForEachRegra();
		setVisible(true);

	}

	/**
	 * Creates a column for each rule created and fills it out with the results for
	 * each methodID
	 */
	public void columnForEachRule() {
		if (!regras.isEmpty()) {
			for (int i = tableModel.getColumnCount() - 3; i != regras.size(); i++) {
				if (regras.get(i).getMetrica_1() == Metrica.LOC)
					tableModel.addColumn(regras.get(i).getNome() + " (isLongMethod)",
							ruleResultForEachMethod(regras.get(i)));
				else
					tableModel.addColumn(regras.get(i).getNome() + " (featureEnvy)",
							ruleResultForEachMethod(regras.get(i)));
				tableModel.fireTableStructureChanged();
			}

		}
	}

//	public boolean addRegra(Regra r) {
//		return regras.add(r);
//	}

	public static Comparador_de_Qualidade getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Comparador_de_Qualidade();
		return INSTANCE;
	}

	/**
	 * Verifies the rule result for each method and stores it in an array
	 * 
	 * @param r
	 * @return resultados
	 */
	public Object[] ruleResultForEachMethod(Regra r) {
		Object[] resultados = new Object[metodos.size()];
		for (int i = 0; i != metodos.size(); i++) {
			if (r.getMetrica_1() == Metrica.LOC && r.getMetrica_2() == Metrica.CYCLO
					&& r.getLogico() == Logic_And_Or.AND)
				if ((metodos.get(i).getLoc() > r.getValor_1().intValue())
						&& (metodos.get(i).getCyclo() > r.getValor_2().intValue())) {
					resultados[i] = true;
				} else
					resultados[i] = false;
			else if (r.getMetrica_1() == Metrica.LOC && r.getMetrica_2() == Metrica.CYCLO
					&& r.getLogico() == Logic_And_Or.OR)
				if ((metodos.get(i).getLoc() > r.getValor_1().intValue())
						|| (metodos.get(i).getCyclo() > r.getValor_2().intValue())) {
					resultados[i] = true;
				} else
					resultados[i] = false;
			else if (r.getMetrica_1() == Metrica.ATFD && r.getMetrica_2() == Metrica.LAA
					&& r.getLogico() == Logic_And_Or.AND)
				if ((metodos.get(i).getAtfd() > r.getValor_1().intValue())
						&& (metodos.get(i).getLaa() < r.getValor_2().doubleValue())) {
					resultados[i] = true;
				} else
					resultados[i] = false;
			else if (r.getMetrica_1() == Metrica.ATFD && r.getMetrica_2() == Metrica.LAA
					&& r.getLogico() == Logic_And_Or.OR)
				if ((metodos.get(i).getAtfd() > r.getValor_1().intValue())
						|| (metodos.get(i).getLaa() < r.getValor_2().doubleValue())) {
					resultados[i] = true;
				} else
					resultados[i] = false;

		}
		return resultados;

	}

	/**
	 * Verifies FeatureEnvy Rule Logic Condition. Adds Boolean Results to HashMap
	 * 
	 * @param atfdValue
	 * @param laaValue
	 * @param logico
	 * @param regra
	 * 
	 */
	public void verifyFeatureEnvyRegraLogicValue(Number atfdValue, Number laaValue, Logic_And_Or logico, Regra regra) {
		ArrayList<Boolean> featureEnvyRuleValues = new ArrayList<Boolean>();
		if (logico == Logic_And_Or.AND) {
			featureEnvyRuleValues = generateFeatureEnvyRegraValuesAnd(atfdValue, laaValue, featureEnvyRuleValues);
		} else if (logico == Logic_And_Or.OR) {
			featureEnvyRuleValues = generateFeatureEnvyRegraValuesOr(atfdValue, laaValue, featureEnvyRuleValues);
		}
		this.featureEnvyRegrasValues.put(regra, featureEnvyRuleValues);
	}

	/**
	 * Starts collection of Quality Indicators
	 */

	public void calculateIndicadoresRegras() {
		this.indicadoresRegrasUtilizador = new HashMap<Regra, ArrayList<Integer>>();
		this.calculateIndicadoresFeatureEnvy();
		this.calculateIndicadoresLongMethod();
	}

	/**
	 * Verifies LongMethod Rule Logic Condition. Adds Boolean Results to HashMap
	 * 
	 * @param locValue
	 * @param cycloValue
	 * @param logico
	 * 
	 */
	public void verifyLongMethodRegraLogicValue(Number locValue, Number cycloValue, Logic_And_Or logico, Regra regra) {
		ArrayList<Boolean> longMethodRuleValues = new ArrayList<Boolean>();
		if (logico == Logic_And_Or.AND) {
			longMethodRuleValues = generateLongMethodRegraValuesAnd(locValue, cycloValue, longMethodRuleValues);
		} else if (logico == Logic_And_Or.OR) {
			longMethodRuleValues = generateLongMethodRegraValuesOr(locValue, cycloValue, longMethodRuleValues);
		}
		this.longMethodRegrasValues.put(regra, longMethodRuleValues);
	}

	/**
	 * Collects Rules results for each method - Boolean ArrayList
	 */
	public void getRegrasValues() {
		this.longMethodRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
		this.featureEnvyRegrasValues = new HashMap<Regra, ArrayList<Boolean>>();
		for (Regra regra : this.regras) {
			if ((regra.getMetrica_1() == Metrica.LOC && regra.getMetrica_2() == Metrica.CYCLO)
					|| (regra.getMetrica_1() == Metrica.CYCLO && regra.getMetrica_2() == Metrica.LOC)) {
				verifyLongMethodRuleStructure(regra);
			} else if ((regra.getMetrica_1() == Metrica.ATFD && regra.getMetrica_2() == Metrica.LAA)
					|| (regra.getMetrica_1() == Metrica.LAA && regra.getMetrica_2() == Metrica.ATFD)) {
				verifyFeatureEnvyRuleStructure(regra);

			}
		}

	}

	/**
	 * Verifies LongMethod Rule structure
	 * 
	 * @param regra
	 */
	public void verifyLongMethodRuleStructure(Regra regra) {
		if (regra.getMetrica_1() == Metrica.LOC && regra.getMetrica_2() == Metrica.CYCLO) {
			verifyLongMethodRegraLogicValue(regra.getValor_1(), regra.getValor_2(), regra.getLogico(), regra);

		} else if (regra.getMetrica_1() == Metrica.CYCLO && regra.getMetrica_2() == Metrica.LOC) {
			verifyLongMethodRegraLogicValue(regra.getValor_2(), regra.getValor_1(), regra.getLogico(), regra);

		}
	}

	/**
	 * Generates LongMethod Rule Values based on And Condition
	 * 
	 * @param locValue
	 * @param cycloValue
	 * @param longMethodRuleValues
	 * @return longMethodRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateLongMethodRegraValuesAnd(Number locValue, Number cycloValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			longMethodRuleValues.add(
					((this.locValues.get(i) > (Integer) locValue) && (this.cycloValues.get(i) > (Integer) cycloValue)));
		}
		return longMethodRuleValues;

	}

	/**
	 * Generates LongMethod Rule Values based on Or Condition
	 * 
	 * @param locValue
	 * @param cycloValue
	 * @param longMethodRuleValues
	 * @return longMethodRuleValues
	 */
	public ArrayList<Boolean> generateLongMethodRegraValuesOr(Number locValue, Number cycloValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			longMethodRuleValues.add(
					((this.locValues.get(i) > (Integer) locValue) || (this.cycloValues.get(i) > (Integer) cycloValue)));
		}
		return longMethodRuleValues;
	}

	/**
	 * Verifies FeatureEnvy Rule structure
	 * 
	 * @param regra
	 */
	public void verifyFeatureEnvyRuleStructure(Regra regra) {
		if (regra.getMetrica_1() == Metrica.ATFD && regra.getMetrica_2() == Metrica.LAA) {
			verifyFeatureEnvyRegraLogicValue(regra.getValor_1(), regra.getValor_2(), regra.getLogico(), regra);

		} else if (regra.getMetrica_1() == Metrica.LAA && regra.getMetrica_2() == Metrica.ATFD) {
			verifyFeatureEnvyRegraLogicValue(regra.getValor_2(), regra.getValor_1(), regra.getLogico(), regra);

		}
	}

	/**
	 * Generates FeatureEnvy Rule Values based on And Condition
	 * 
	 * @param atfdValue
	 * @param laaValue
	 * @param featureEnvyRuleValues
	 * @return featureEnvyRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateFeatureEnvyRegraValuesAnd(Number atfdValue, Number laaValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			featureEnvyRuleValues.add(
					((this.atfdValues.get(i) > (Integer) atfdValue) && (this.laaValues.get(i) < (Double) laaValue)));
		}
		return featureEnvyRuleValues;

	}

	/**
	 * Generates FeatureEnvy Rule Values based on Or Condition
	 * 
	 * @param atfdValue
	 * @param laaValue
	 * @param featureEnvyRuleValues
	 * @return featureEnvyRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateFeatureEnvyRegraValuesOr(Number atfdValue, Number laaValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			featureEnvyRuleValues.add(
					((this.atfdValues.get(i) > (Integer) atfdValue) || (this.laaValues.get(i) < (Double) laaValue)));
		}
		return featureEnvyRuleValues;

	}

	/**
	 * Collects Iplasma Values - Boolean Arraylist
	 */
	public void addIplasmaValues() {
		for (Metodo m : metodos)
			iplasmaValues.add(m.isIplasma());
	}

	/**
	 * Collects FeatureEnvy Values - Boolean Arraylist
	 */
	public void addFeatureEnvyValues() {
		for (Metodo m : metodos)
			featureEnvyValues.add(m.isIs_feature_envy());

	}

	/**
	 * Collects LongMethod Values - Boolean Arraylist
	 */
	public void addLongMethodValues() {
		for (Metodo m : metodos)
			longMethodValues.add(m.isIs_long_method());

	}

	/**
	 * Collects PMD Values - Boolean Arraylist
	 */
	public void addPMDValues() {
		for (Metodo m : metodos)
			pmdValues.add(m.isPmd());
	}

	/**
	 * Collects DataModel LOC, CYCLO, ATFD, LAA Values
	 */
	public void getDataModelValues() {
		ArrayList<Metodo> metodos = this.metodos;
		for (int i = 0; i < metodos.size(); i++) {
			getLocValues(metodos.get(i));
			getCycloValues(metodos.get(i));
			getAtfdValues(metodos.get(i));
			getLaaValues(metodos.get(i));
		}

	}

	/**
	 * creates Indicator Table
	 */
	public void createIndicatorTable() {
		String[] col = new String[3];
		col[0] = "";
		col[1] = "iPlasma";
		col[2] = "PMD";

		tableModel2 = new DefaultTableModel(col, 0) {
			/**
			 * Disables JTable cell editing
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table2 = new JTable(tableModel2);

		String[] indicadores = new String[] { "DCI", "DII", "ADCI", "ADII" };
		Vector<Object> data;
		for (int i = 0; i != 4; i++) {
			data = new Vector<Object>();
			data.add(indicadores[i]);
			data.add(indicadoresiPlasma[i]);
			data.add(indicadoresPMD[i]);
			tableModel2.addRow(data);
		}
		JScrollPane panel = new JScrollPane(table2);
		indicadores_panel.add(panel);

	}

	/**
	 * Generates Columns for each User Rule at Indicators Table
	 */
	public void columnForEachRegra() {
		if (!regras.isEmpty()) {
			for (int i = tableModel2.getColumnCount() - 3; i != regras.size(); i++) {
				tableModel2.addColumn(regras.get(i).getNome() + "-Regra", regraResultForEachMethod(regras.get(i)));
				
			}
			tableModel2.fireTableStructureChanged();
		

		}
	}

	public Object[] regraResultForEachMethod(Regra r) {
		Object[] resultados = new Object[4];
		for (int i = 0; i != 4; i++) {
			resultados[i] = indicadoresRegrasUtilizador.get(r).get(i);
		}
		return resultados;
	}

	/**
	 * Collects LOC Values - Integer Arraylist
	 * 
	 * @param metodo
	 */
	public void getLocValues(Metodo metodo) {
		this.locValues.add(metodo.getLoc());
	}

	/**
	 * Collects CYCLO Values - Integer ArrayList
	 * 
	 * @param metodo
	 */
	public void getCycloValues(Metodo metodo) {
		this.cycloValues.add(metodo.getCyclo());
	}

	/**
	 * Collects ATFD Values - Integer ArrayList
	 * 
	 * @param metodo
	 */
	public void getAtfdValues(Metodo metodo) {
		this.atfdValues.add(metodo.getAtfd());
	}

	/**
	 * Collects LAA Values - Double ArrayList
	 * 
	 * @param metodo
	 */
	public void getLaaValues(Metodo metodo) {
		this.laaValues.add(metodo.getLaa());
	}

	/**
	 * Starts collection of PMD and iPlasma quality Indicadores
	 */
	public void calculateIndicadoresPMDiPlasma() {
		ArrayList<Boolean> longMethodValues = this.longMethodValues;
		for (int i = 0; i < longMethodValues.size(); i++) {
			this.indicadoresiPlasma = calculateIndicadoresiPlasma(longMethodValues, 0, 0, 0, 0);
			this.indicadoresPMD = calculateIndicadoresPMD(longMethodValues, 0, 0, 0, 0);
		}
	}

	/**
	 * Calcula indicadores de qualidade iplasma
	 * 
	 * @param longMethodValues
	 * @param dci
	 * @param dii
	 * @param adci
	 * @param adii
	 * @return indicadoresiPlasma
	 */
	public Integer[] calculateIndicadoresiPlasma(ArrayList<Boolean> longMethodValues, int dci, int dii, int adci,
			int adii) {
		ArrayList<Boolean> iPlasmaValues = this.iplasmaValues;
		Integer[] indicadoresiPlasma = this.indicadoresiPlasma;
		for (int i = 0; i < longMethodValues.size(); i++) {
			if (iPlasmaValues.get(i) && longMethodValues.get(i)) {
				dci++;
			} else if (!iPlasmaValues.get(i) && longMethodValues.get(i)) {
				dii++;
			} else if (!iPlasmaValues.get(i) && !longMethodValues.get(i)) {
				adci++;
			} else if (iPlasmaValues.get(i) && !longMethodValues.get(i)) {
				adii++;
			}
		}
		indicadoresiPlasma[0] = dci;
		indicadoresiPlasma[1] = dii;
		indicadoresiPlasma[2] = adci;
		indicadoresiPlasma[3] = adii;
		return indicadoresiPlasma;
	}

	/**
	 * Calcula indicadores de qualidade PMD
	 * 
	 * @param longMethodValues
	 * @param dci
	 * @param dii
	 * @param adci
	 * @param adii
	 * @return indicadoresPMD
	 */
	public Integer[] calculateIndicadoresPMD(ArrayList<Boolean> longMethodValues, int dci, int dii, int adci,
			int adii) {
		ArrayList<Boolean> PMDValues = this.pmdValues;
		Integer[] indicadoresPMD = this.indicadoresPMD;
		for (int i = 0; i < longMethodValues.size(); i++) {
			if (PMDValues.get(i) && longMethodValues.get(i)) {
				dci++;
			} else if (!PMDValues.get(i) && longMethodValues.get(i)) {
				dii++;
			} else if (!PMDValues.get(i) && !longMethodValues.get(i)) {
				adci++;
			} else if (PMDValues.get(i) && !longMethodValues.get(i)) {
				adii++;
			}
		}
		indicadoresPMD[0] = dci;
		indicadoresPMD[1] = dii;
		indicadoresPMD[2] = adci;
		indicadoresPMD[3] = adii;
		return indicadoresPMD;
	}

	/**
	 * Starts collection of Long Method User Rules quality Indicadores
	 */
	public void calculateIndicadoresLongMethod() {
		for (Regra regra : this.longMethodRegrasValues.keySet()) {
			this.indicadoresRegrasUtilizador.put(regra,
					calculateIndicadoresLongMethodRegras(regra, 0, 0, 0, 0));
		}
	}

	/**
	 * Calcula indicadores de qualidade Long Method Regras Utilizador
	 * 
	 * @param regra
	 * @param dci
	 * @param dii
	 * @param adci
	 * @param adii
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresLongMethodRegras(Regra regra, int dci, int dii, int adci, int adii) {
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.longMethodRegrasValues.get(regra);
		for (int j = 0; j < longMethodValues.size(); j++) {
			if (regraValues.get(j) && longMethodValues.get(j)) {
				dci++;
			} else if (!regraValues.get(j) && longMethodValues.get(j)) {
				dii++;
			} else if (!regraValues.get(j) && !longMethodValues.get(j)) {
				adci++;
			} else if (regraValues.get(j) && !longMethodValues.get(j)) {
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
			this.indicadoresRegrasUtilizador.put(regra,
					calculateIndicadoresFeatureEnvyRegras(regra, 0, 0, 0, 0));
		}
	}

	/**
	 * Calcula indicadores de qualidade Feature Envy Regras Utilizador
	 * 
	 * @param regra
	 * @param D`CI
	 * @param dii
	 * @param adci
	 * @param adii
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresFeatureEnvyRegras(Regra regra, int dci, int dii, int adci, int adii) {
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.featureEnvyRegrasValues.get(regra);
		for (int j = 0; j < featureEnvyValues.size(); j++) {
			if (regraValues.get(j) && featureEnvyValues.get(j)) {
				dci++;
			} else if (!regraValues.get(j) && featureEnvyValues.get(j)) {
				dii++;
			} else if (!regraValues.get(j) && !featureEnvyValues.get(j)) {
				adci++;
			} else if (regraValues.get(j) && !featureEnvyValues.get(j)) {
				adii++;
			}
		}
		indicadores.add(dci);
		indicadores.add(dii);
		indicadores.add(adci);
		indicadores.add(adii);
		return indicadores;
	}

	public ArrayList<Boolean> getLongMethodValues() {
		return longMethodValues;
	}

	public void setLongMethodValues(ArrayList<Boolean> longMethodValues) {
		this.longMethodValues = longMethodValues;
	}

	public ArrayList<Boolean> getFeatureEnvyValues() {
		return featureEnvyValues;
	}

	public void setFeatureEnvyValues(ArrayList<Boolean> featureEnvyValues) {
		this.featureEnvyValues = featureEnvyValues;
	}

	public ArrayList<Boolean> getPmdValues() {
		return pmdValues;
	}

	public void setPmdValues(ArrayList<Boolean> pmdValues) {
		this.pmdValues = pmdValues;
	}

	public ArrayList<Boolean> getIplasmaValues() {
		return iplasmaValues;
	}

	public void setIplasmaValues(ArrayList<Boolean> iplasmaValues) {
		this.iplasmaValues = iplasmaValues;
	}

	public ArrayList<Integer> getLocValues() {
		return locValues;
	}

	public void setLocValues(ArrayList<Integer> locValues) {
		this.locValues = locValues;
	}

	public ArrayList<Integer> getCycloValues() {
		return cycloValues;
	}

	public void setCycloValues(ArrayList<Integer> cycloValues) {
		this.cycloValues = cycloValues;
	}

	public ArrayList<Integer> getAtfdValues() {
		return atfdValues;
	}

	public void setAtfdValues(ArrayList<Integer> atfdValues) {
		this.atfdValues = atfdValues;
	}

	public ArrayList<Double> getLaaValues() {
		return laaValues;
	}

	public void setLaaValues(ArrayList<Double> laaValues) {
		this.laaValues = laaValues;
	}

}