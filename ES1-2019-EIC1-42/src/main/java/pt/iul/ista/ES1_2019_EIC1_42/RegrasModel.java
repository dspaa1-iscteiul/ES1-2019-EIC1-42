package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

public class RegrasModel extends AbstractListModel<Regra> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668612315702095255L;
	private static RegrasModel INSTANCE;
	private ArrayList<Regra> regras;

	private RegrasModel() {
		regras = new ArrayList<Regra>();
	}

	public Regra getElementAt(int index) {
		return regras.get(index);
	}

	public int getSize() {
		return regras.size();
	}

	public boolean addRegra(Regra r) {
		if (regras.contains(r))
			return false;
		else {
			regras.add(r);
			fireIntervalAdded(this, regras.size() - 1, regras.size() - 1);
			return true;
		}
	}
	
	public boolean removeRegra(Regra r) {
		if (!regras.contains(r))
			return false;
		else {
			int interval = regras.indexOf(r);
			regras.remove(r);
			fireIntervalRemoved(this, interval, interval);
			return true;
		}
	}

	public ArrayList<Regra> getRegras() {
		return regras;
	}

	public static RegrasModel getInstance() {
		if (INSTANCE == null)
			INSTANCE = new RegrasModel();
		return INSTANCE;
	}

}
