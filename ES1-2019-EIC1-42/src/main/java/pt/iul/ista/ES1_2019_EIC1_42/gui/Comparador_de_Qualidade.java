package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
 *
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
	private JTable table;

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
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private Comparador_de_Qualidade() {
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
			createTable();
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
		table = new JTable(tableModel) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected JTableHeader createDefaultTableHeader() {
				return new JTableHeader(getColumnModel()) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String getToolTipText(MouseEvent e) {
//						String tip = null;
						java.awt.Point p = e.getPoint();
						int index = columnModel.getColumnIndexAtX(p.x);
						int realIndex = columnModel.getColumn(index).getModelIndex();
						if (realIndex > 2)
							return regras.get(realIndex - 3).toString();
						else if (realIndex > 0)
							return col[realIndex] + " - long_method";
						else
							return super.getToolTipText();
					}

				};
			}

		};
		table.getTableHeader().setReorderingAllowed(false);
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
		setVisible(true);
	}

	/**
	 * Creates a column for each rule created and fills it out with the results for
	 * each methodID
	 */
	public void columnForEachRule() {
		if (!regras.isEmpty()) {
			for (int i = tableModel.getColumnCount() - 3; i != regras.size(); i++) {
				tableModel.addColumn(regras.get(i).getNome(), ruleResultForEachMethod(regras.get(i)));
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
	 * @param ATFDValue
	 * @param LAAValue
	 * @param logico
	 * @param regra
	 * 
	 */
	public void verifyFeatureEnvyRegraLogicValue(Number ATFDValue, Number LAAValue, Logic_And_Or logico, Regra regra) {
		ArrayList<Boolean> featureEnvyRuleValues = new ArrayList<Boolean>();
		if (logico == Logic_And_Or.AND) {
			featureEnvyRuleValues = generateFeatureEnvyRegraValuesAnd(ATFDValue, LAAValue, featureEnvyRuleValues);
		} else if (logico == Logic_And_Or.OR) {
			featureEnvyRuleValues = generateFeatureEnvyRegraValuesOr(ATFDValue, LAAValue, featureEnvyRuleValues);
		}
		this.featureEnvyRegrasValues.put(regra, featureEnvyRuleValues);
	}

	/**
	 * Verifies LongMethod Rule Logic Condition. Adds Boolean Results to HashMap
	 * 
	 * @param LOCValue
	 * @param CYCLOValue
	 * @param logico
	 * 
	 */
	public void verifyLongMethodRegraLogicValue(Number LOCValue, Number CYCLOValue, Logic_And_Or logico, Regra regra) {
		ArrayList<Boolean> longMethodRuleValues = new ArrayList<Boolean>();
		if (logico == Logic_And_Or.AND) {
			longMethodRuleValues = generateLongMethodRegraValuesAnd(LOCValue, CYCLOValue, longMethodRuleValues);
		} else if (logico == Logic_And_Or.OR) {
			longMethodRuleValues = generateLongMethodRegraValuesOr(LOCValue, CYCLOValue, longMethodRuleValues);
		}
		this.longMethodRegrasValues.put(regra, longMethodRuleValues);
	}

	/**
	 * Collects Rules results for each method - Boolean ArrayList
	 */
	public void getRegrasValues() {
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
	 * @param LOCValue
	 * @param CYCLOValue
	 * @param longMethodRuleValues
	 * @return longMethodRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateLongMethodRegraValuesAnd(Number LOCValue, Number CYCLOValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			longMethodRuleValues.add(
					((this.locValues.get(i) > (Integer) LOCValue) && (this.cycloValues.get(i) > (Integer) CYCLOValue)));
		}
		return longMethodRuleValues;

	}

	/**
	 * Generates LongMethod Rule Values based on Or Condition
	 * 
	 * @param LOCValue
	 * @param CYCLOValue
	 * @param longMethodRuleValues
	 * @return longMethodRuleValues
	 */
	public ArrayList<Boolean> generateLongMethodRegraValuesOr(Number LOCValue, Number CYCLOValue,
			ArrayList<Boolean> longMethodRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			longMethodRuleValues.add(
					((this.locValues.get(i) > (Integer) LOCValue) || (this.cycloValues.get(i) > (Integer) CYCLOValue)));
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
	 * @param ATFDValue
	 * @param LAAValue
	 * @param featureEnvyRuleValues
	 * @return featureEnvyRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateFeatureEnvyRegraValuesAnd(Number ATFDValue, Number LAAValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			featureEnvyRuleValues.add(
					((this.atfdValues.get(i) > (Integer) ATFDValue) && (this.laaValues.get(i) < (Double) LAAValue)));
		}
		return featureEnvyRuleValues;

	}

	/**
	 * Generates FeatureEnvy Rule Values based on Or Condition
	 * 
	 * @param ATFDValue
	 * @param LAAValue
	 * @param featureEnvyRuleValues
	 * @return featureEnvyRuleValues
	 * 
	 */
	public ArrayList<Boolean> generateFeatureEnvyRegraValuesOr(Number ATFDValue, Number LAAValue,
			ArrayList<Boolean> featureEnvyRuleValues) {
		for (int i = 0; i < this.locValues.size(); i++) {
			featureEnvyRuleValues.add(
					((this.atfdValues.get(i) > (Integer) ATFDValue) || (this.laaValues.get(i) < (Double) LAAValue)));
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

	public void createTable() {
		String data[][] = { { "101", "200", "200" }, { "102", "200", "222" }, { "101", "200", "222" } };
		String column[] = { "iPlasma", "PMD", "Número de Regras" };
		JTable t = new JTable(data, column);
		System.out.println(iplasmaValues.size());
		for (int i = 0; i != iplasmaValues.size(); i++) {
			System.out.println(iplasmaValues.get(i));
		}
		JScrollPane sp = new JScrollPane(t);
		indicadores_panel.add(sp);
		metodos = DataModel.getInstance().getMetodos();
		System.out.println(metodos != null); // metodos null...

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
		int DCI = 0;
		int DII = 0;
		int ADCI = 0;
		int ADII = 0;
		for (int i = 0; i < longMethodValues.size(); i++) {
			this.indicadoresiPlasma = calculateIndicadoresiPlasma(longMethodValues, DCI, DII, ADCI, ADII);
			this.indicadoresPMD = calculateIndicadoresPMD(longMethodValues, DCI, DII, ADCI, ADII);
		}
	}

	/**
	 * Calcula indicadores de qualidade iplasma
	 * 
	 * @param longMethodValues
	 * @param DCI
	 * @param DII
	 * @param ADCI
	 * @param ADII
	 * @return indicadoresiPlasma
	 */
	public Integer[] calculateIndicadoresiPlasma(ArrayList<Boolean> longMethodValues, int DCI, int DII, int ADCI,
			int ADII) {
		ArrayList<Boolean> iPlasmaValues = this.iplasmaValues;
		Integer[] indicadoresiPlasma = this.indicadoresiPlasma;
		for (int i = 0; i < longMethodValues.size(); i++) {
			if (iPlasmaValues.get(i) && longMethodValues.get(i)) {
				DCI++;
			} else if (!iPlasmaValues.get(i) && longMethodValues.get(i)) {
				DII++;
			} else if (!iPlasmaValues.get(i) && !longMethodValues.get(i)) {
				ADCI++;
			} else if (iPlasmaValues.get(i) && !longMethodValues.get(i)) {
				ADII++;
			}
		}
		indicadoresiPlasma[0] = DCI;
		indicadoresiPlasma[1] = DII;
		indicadoresiPlasma[2] = ADCI;
		indicadoresiPlasma[3] = ADII;
		return indicadoresiPlasma;
	}

	/**
	 * Calcula indicadores de qualidade PMD
	 * 
	 * @param longMethodValues
	 * @param DCI
	 * @param DII
	 * @param ADCI
	 * @param ADII
	 * @return indicadoresPMD
	 */
	public Integer[] calculateIndicadoresPMD(ArrayList<Boolean> longMethodValues, int DCI, int DII, int ADCI,
			int ADII) {
		ArrayList<Boolean> PMDValues = this.pmdValues;
		Integer[] indicadoresPMD = this.indicadoresPMD;
		for (int i = 0; i < longMethodValues.size(); i++) {
			if (PMDValues.get(i) && longMethodValues.get(i)) {
				DCI++;
			} else if (!PMDValues.get(i) && longMethodValues.get(i)) {
				DII++;
			} else if (!PMDValues.get(i) && !longMethodValues.get(i)) {
				ADCI++;
			} else if (PMDValues.get(i) && !longMethodValues.get(i)) {
				ADII++;
			}
		}
		indicadoresPMD[0] = DCI;
		indicadoresPMD[1] = DII;
		indicadoresPMD[2] = ADCI;
		indicadoresPMD[3] = ADII;
		return indicadoresPMD;
	}

	/**
	 * Starts collection of Long Method User Rules quality Indicadores
	 */
	public void calculateIndicadoresLongMethod() {
		int DCI = 0;
		int DII = 0;
		int ADCI = 0;
		int ADII = 0;
		for (Regra regra : this.longMethodRegrasValues.keySet()) {
			this.indicadoresRegrasUtilizador.put(regra,
					calculateIndicadoresLongMethodRegras(regra, DCI, DII, ADCI, ADII));
		}
	}

	/**
	 * Calcula indicadores de qualidade Long Method Regras Utilizador
	 * 
	 * @param regra
	 * @param DCI
	 * @param DII
	 * @param ADCI
	 * @param ADII
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresLongMethodRegras(Regra regra, int DCI, int DII, int ADCI, int ADII) {
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.longMethodRegrasValues.get(regra);
		for (int j = 0; j < longMethodValues.size(); j++) {
			if (regraValues.get(j) && longMethodValues.get(j)) {
				DCI++;
			} else if (!regraValues.get(j) && longMethodValues.get(j)) {
				DII++;
			} else if (!regraValues.get(j) && !longMethodValues.get(j)) {
				ADCI++;
			} else if (regraValues.get(j) && !longMethodValues.get(j)) {
				ADII++;
			}
		}
		indicadores.add(DCI);
		indicadores.add(DII);
		indicadores.add(ADCI);
		indicadores.add(ADII);
		return indicadores;
	}

	/**
	 * Starts collection of Feature Envy Rules quality Indicadores
	 */
	public void calculateIndicadoresFeatureEnvy() {
		int DCI = 0;
		int DII = 0;
		int ADCI = 0;
		int ADII = 0;
		for (Regra regra : this.featureEnvyRegrasValues.keySet()) {
			this.indicadoresRegrasUtilizador.put(regra,
					calculateIndicadoresFeatureEnvyRegras(regra, DCI, DII, ADCI, ADII));
		}
	}

	/**
	 * Calcula indicadores de qualidade Feature Envy Regras Utilizador
	 * 
	 * @param regra
	 * @param DCI
	 * @param DII
	 * @param ADCI
	 * @param ADII
	 * @return indicadores
	 */
	public ArrayList<Integer> calculateIndicadoresFeatureEnvyRegras(Regra regra, int DCI, int DII, int ADCI, int ADII) {
		ArrayList<Integer> indicadores = new ArrayList<Integer>();
		ArrayList<Boolean> regraValues = this.featureEnvyRegrasValues.get(regra);
		for (int j = 0; j < featureEnvyValues.size(); j++) {
			if (regraValues.get(j) && featureEnvyValues.get(j)) {
				DCI++;
			} else if (!regraValues.get(j) && featureEnvyValues.get(j)) {
				DII++;
			} else if (!regraValues.get(j) && !featureEnvyValues.get(j)) {
				ADCI++;
			} else if (regraValues.get(j) && !featureEnvyValues.get(j)) {
				ADII++;
			}
		}
		indicadores.add(DCI);
		indicadores.add(DII);
		indicadores.add(ADCI);
		indicadores.add(ADII);
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