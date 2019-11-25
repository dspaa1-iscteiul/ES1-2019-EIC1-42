package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pt.iul.ista.ES1_2019_EIC1_42.DataModel;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import pt.iul.ista.ES1_2019_EIC1_42.Metodo;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;

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
	private Set<Regra> regras;
	private HashMap<Regra, ArrayList<Boolean>> regrasValues;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<Metodo> metodos =  DataModel.getInstance().getMetodos();
	private ArrayList<Boolean> longMethodValues = new ArrayList<Boolean> ();
	private ArrayList<Boolean> featureEnvyValues = new ArrayList<Boolean> ();
	private ArrayList<Boolean> pmdValues = new ArrayList<Boolean> ();
	private ArrayList<Boolean> iplasmaValues = new ArrayList<Boolean> ();
	private ArrayList<Integer> locValues = new ArrayList<Integer>();
	private ArrayList<Integer> cycloValues = new ArrayList<Integer>();
	private ArrayList<Integer> atfdValues = new ArrayList<Integer>();
	private ArrayList<Double> laaValues = new ArrayList<Double>();

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
		regras = new HashSet<Regra>();
		setBounds(100, 100, 450, 300);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void open() {
		System.out.println(regras);
		setVisible(true);
	}

	public boolean addRegra(Regra r) {
		return regras.add(r);
	}

	public static Comparador_de_Qualidade getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Comparador_de_Qualidade();
		return INSTANCE;
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
		this.regrasValues.put(regra, longMethodRuleValues);
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
		this.regrasValues.put(regra, featureEnvyRuleValues);
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
		for(Metodo m: metodos)
			iplasmaValues.add(m.isIplasma());
	}

	/**
	 * Collects FeatureEnvy Values - Boolean Arraylist
	 */
	public void addFeatureEnvyValues() {
		for(Metodo m: metodos)
			featureEnvyValues.add(m.isIs_feature_envy());

	}

	/**
	 * Collects LongMethod Values - Boolean Arraylist
	 */
	public void addLongMethodValues() {
		for(Metodo m: metodos)
			longMethodValues.add(m.isIs_long_method());

	}

	/**
	 * Collects PMD Values - Boolean Arraylist
	 */
	public void addPMDValues() {
		for(Metodo m: metodos)
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
	 * Collects LOC Values - Integer Arraylist
	 * @param metodo 
	 */
	public void getLocValues(Metodo metodo) {
		this.locValues.add(metodo.getLoc());
	}

	/**
	 * Collects CYCLO Values - Integer ArrayList
	 * @param metodo 
	 */
	public void getCycloValues(Metodo metodo) {
		this.cycloValues.add(metodo.getCyclo());
	}

	/**
	 * Collects ATFD Values - Integer ArrayList
	 * @param metodo 
	 */
	public void getAtfdValues(Metodo metodo) {
		this.atfdValues.add(metodo.getAtfd());
	}

	/**
	 * Collects LAA Values - Double ArrayList
	 * @param metodo 
	 */
	public void getLaaValues(Metodo metodo) {
		this.laaValues.add(metodo.getLaa());
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
