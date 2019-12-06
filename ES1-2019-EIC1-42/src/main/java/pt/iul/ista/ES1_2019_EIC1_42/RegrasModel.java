package pt.iul.ista.ES1_2019_EIC1_42;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Classe para guardar as regras criadas pelo utilizador
 * 
 * @author dariop
 *
 */
public class RegrasModel extends AbstractListModel<Regra> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668612315702095255L;
	private static RegrasModel INSTANCE;
	private ArrayList<Regra> regras;

	/**
	 * Construtor: inicializa o ArrayList das regras
	 */
	private RegrasModel() {
		regras = new ArrayList<Regra>();
	}

	@Override
	public Regra getElementAt(int index) {
		return regras.get(index);
	}

	@Override
	public int getSize() {
		return regras.size();
	}

	/**
	 * 
	 * @param regra regra que se pretende adicionar na lista
	 * @return <b>true</b> se ainda não existe na lista e foi adicionada com sucesso
	 *         </br>
	 *         <b>false</b> se a regra já existe na lista
	 */
	public boolean addRegra(Regra regra) {
		if (regras.contains(regra))
			return false;
		else {
			regras.add(regra);
			fireIntervalAdded(this, regras.size() - 1, regras.size() - 1);
			return true;
		}
	}

	/**
	 * @param regra regra que se pretende remvoer da lista
	 * @return <b>true</b> se a regra existia na lista e foi removida com sucesso
	 *         </br>
	 *         <b>false</b> se a regra não existe na lista
	 */
	public boolean removeRegra(Regra regra) {
		if (!regras.contains(regra))
			return false;
		else {
			int interval = regras.indexOf(regra);
			regras.remove(regra);
			fireIntervalRemoved(this, interval, interval);
			return true;
		}
	}

	/**
	 * Atualiza na tabela a regra que foi modificada.
	 * @param regra regra que se pretende atualizar
	 */
	public void atualizar(Regra regra) {
		int interval = regras.indexOf(regra);
		fireContentsChanged(this, interval, interval);
	}

	/**
	 * 
	 * @return o ArrayList com as regras criadas pelo utilizador.
	 */
	public ArrayList<Regra> getRegras() {
		return regras;
	}

	/**
	 * 
	 * @return uma instância dessa classe
	 */
	public static RegrasModel getInstance() {
		if (INSTANCE == null)
			INSTANCE = new RegrasModel();
		return INSTANCE;
	}

}
