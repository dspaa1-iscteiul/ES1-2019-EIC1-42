package pt.iul.ista.ES1_2019_EIC1_42.gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;

/**
 * Dialogo para criar, vizualizar e editar regras do utilizador
 * 
 * @author dariop
 *
 */
public class Nova_Regra extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326177220283427980L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<Metrica> metrics_1, metrics_2;
	private JComboBox<Logic_And_Or> logic_1;
	private JSpinner spinner_metric_1, spinner_metric_2;
	private JPopupMenu popupMenu;
	private JMenuItem mEditar, mApagar;
	private JLabel label_maior_que, label_menor_que;
	private JButton save;
	private JTextField nome;
	private JComboBox<String> type;
	private JList<Regra> regrasList;
	private RegrasModel regras;
	private Regra regra;

	/**
	 * Create the dialog.
	 */
	public Nova_Regra() {

		initPanel();
		initComponents();
	}

	/**
	 * Inicializa o painel que vai guardar os componentes da GUI
	 */
	private void initPanel() {
		setResizable(false);
		setTitle("Nova Regra");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 490, 320);
		contentPanel.setBounds(0, 0, 480, 180);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	/**
	 * Inicializa os componentes da GUI
	 */
	private void initComponents() {

		initComboBoxes();
		initSpinners();
		initLabels();
		initOthers();
		addListeners();
	}

	/**
	 * Inicializa outros componentes.
	 */
	private void initOthers() {

		save = new JButton("Salvar");
		save.setBounds(375, 255, 100, 25);
		contentPanel.add(save);

		nome = new JTextField();
		nome.setBounds(66, 10, 214, 20);
		contentPanel.add(nome);
		nome.setColumns(10);

		regras = RegrasModel.getInstance();
		regrasList = new JList<Regra>(regras);
		regrasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane jscrollpane = new JScrollPane(regrasList);
		jscrollpane.setBounds(10, 140, 465, 109);
		contentPanel.add(jscrollpane);

		popupMenu = new JPopupMenu();
		mEditar = new JMenuItem("Editar");
		popupMenu.add(mEditar);
		mApagar = new JMenuItem("Apagar");
		popupMenu.add(mApagar);

	}

	/**
	 * Inicializa os componentes do tipo JLabel
	 */
	private void initLabels() {

		label_maior_que = new JLabel(">");
		label_maior_que.setBounds(95, 81, 30, 20);
		label_maior_que.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(label_maior_que);

		label_menor_que = new JLabel(">");
		label_menor_que.setBounds(375, 82, 30, 20);
		label_menor_que.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(label_menor_que);

		JLabel lNome = new JLabel("Nome:");
		lNome.setBounds(10, 10, 46, 20);
		contentPanel.add(lNome);

		JLabel lblRegrasExistentes = new JLabel("Regras existentes");
		lblRegrasExistentes.setBounds(10, 115, 120, 20);
		contentPanel.add(lblRegrasExistentes);

	}

	/**
	 * Inicializa os combonentes do tipo JComboBox
	 */
	private void initComboBoxes() {

		metrics_1 = new JComboBox<Metrica>();
		metrics_1.setEnabled(false);
		metrics_1.setBounds(10, 82, 85, 20);
		metrics_1.setModel(new DefaultComboBoxModel<Metrica>(Metrica.values()));
		metrics_1.setSelectedIndex(0);
		contentPanel.add(metrics_1);

		metrics_2 = new JComboBox<Metrica>();
		metrics_2.setEnabled(false);
		metrics_2.setBounds(290, 82, 85, 20);
		metrics_2.setModel(new DefaultComboBoxModel<Metrica>(Metrica.values()));
		metrics_2.setSelectedIndex(1);
		contentPanel.add(metrics_2);

		logic_1 = new JComboBox<Logic_And_Or>();
		logic_1.setBounds(205, 82, 75, 20);
		contentPanel.add(logic_1);
		logic_1.setModel(new DefaultComboBoxModel<Logic_And_Or>(Logic_And_Or.values()));

		type = new JComboBox<String>();
		type.setModel(new DefaultComboBoxModel<String>(new String[] { "long method", "feature envy" }));
		type.setSelectedIndex(0);
		type.setBounds(10, 45, 150, 20);
		contentPanel.add(type);

	}

	/**
	 * Inicializa os componentes do tipo JSpinner
	 */
	private void initSpinners() {

		spinner_metric_1 = new JSpinner();
		spinner_metric_1.setBounds(125, 82, 70, 20);
		spinner_metric_1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPanel.add(spinner_metric_1);

		spinner_metric_2 = new JSpinner();
		spinner_metric_2.setBounds(405, 82, 70, 20);
		spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPanel.add(spinner_metric_2);

	}

	/**
	 * Adiciona listeners para os diversos componentes que é preciso
	 */
	private void addListeners() {

		type.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (type.getSelectedIndex() == 0) {
					metrics_1.setSelectedIndex(0);
					metrics_2.setSelectedIndex(1);
					spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, null, 1));
					label_menor_que.setText(">");
				} else if (type.getSelectedIndex() == 1) {
					metrics_1.setSelectedIndex(2);
					metrics_2.setSelectedIndex(3);
					spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, 1, 0.1));
					label_menor_que.setText("<");
				}

			}
		});

		regrasList.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				if (SwingUtilities.isRightMouseButton(me)) {
					int index = regrasList.locationToIndex(me.getPoint());
					regrasList.setSelectedIndex(index);
					if (index != -1)
						popupMenu.show(regrasList, me.getX(), me.getY());
				}
				if (me.getClickCount() == 2 && !me.isConsumed()) {
					me.consume();
					int index = regrasList.locationToIndex(me.getPoint());
					preencher(regras.getElementAt(index));
				}
			}
		});

		regrasList.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (!regrasList.isSelectionEmpty()) {
					if (e.getKeyCode() == KeyEvent.VK_DELETE) {
						Comparador_de_Qualidade.getInstance().removeRegra(regrasList.getSelectedValue());
						RegrasModel.getInstance().removeRegra(regrasList.getSelectedValue());
					} else if (e.getKeyCode() == KeyEvent.VK_ENTER)
						preencher(regrasList.getSelectedValue());
				}
			}

		});

		mEditar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				preencher(regrasList.getSelectedValue());

			}
		});

		mApagar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (regra != null && regra == regrasList.getSelectedValue()) {
					reset();
				}
				Comparador_de_Qualidade.getInstance().removeRegra(regrasList.getSelectedValue());
				RegrasModel.getInstance().removeRegra(regrasList.getSelectedValue());

			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				salvarRegra();
			}
		});

	}

	/**
	 * Verifica se o campo Nome foi preenchido corretamente pelo utilizador, cria
	 * uma nova regra e adiciona à lista de Regras presente em RegrasModel, se essa
	 * regra ainda não existir lá.
	 */
	protected void salvarRegra() {
		if (nome.getText().trim().equals(""))
			JOptionPane.showMessageDialog(this, "Escreve um nome para a regra!", "Nome vazio",
					JOptionPane.ERROR_MESSAGE);
		else {
			try {
				Regra r = new Regra(nome.getText(), (Metrica) metrics_1.getSelectedItem(),
						(Metrica) metrics_2.getSelectedItem(), (Number) spinner_metric_1.getValue(),
						(Number) spinner_metric_2.getValue(), (Logic_And_Or) logic_1.getSelectedItem());
				if (Comparador_de_Qualidade.getInstance().existsRuleWithName(r.getNome())
						|| !RegrasModel.getInstance().addRegra(r))
					JOptionPane.showMessageDialog(this, "A regra já existe!", "Regra já existe",
							JOptionPane.ERROR_MESSAGE);
				else {
					String[] options = new String[] { "Sim", "Não" };
					int ok = JOptionPane.showOptionDialog(this,
							"Regra criada com sucesso! Queres testar a fiabilidade dessa regra, em comparação com as outras?",
							"Regra criada", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);
					if (ok == JOptionPane.YES_OPTION)
						Comparador_de_Qualidade.getInstance().open();
					else
						Comparador_de_Qualidade.getInstance().atualizar();
					reset();
				}
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Erro ao criar nova regra!", "ClassCastException",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Salva as alterações na regra presente.
	 */
	protected void editarRegra() {
		if (nome.getText().trim().equals(""))
			JOptionPane.showMessageDialog(this, "Escreve um nome para a regra!", "Nome vazio",
					JOptionPane.ERROR_MESSAGE);
		else {
			if (!nome.getText().equals(regra.getNome())
					&& Comparador_de_Qualidade.getInstance().existsRuleWithName(nome.getText()))
				JOptionPane.showMessageDialog(this, "Esse nome já existe! Escreve outro nome para a regra.",
						"Nome já existe", JOptionPane.ERROR_MESSAGE);
			else {
				try {
					Comparador_de_Qualidade.getInstance().mudarNomedaRegra(regra, nome.getText());
					regra.setNome(nome.getText());
					regra.setMetrica_1((Metrica) metrics_1.getSelectedItem());
					regra.setMetrica_2((Metrica) metrics_2.getSelectedItem());
					regra.setValor_1((Number) spinner_metric_1.getValue());
					regra.setValor_2((Number) spinner_metric_2.getValue());
					regra.setLogico((Logic_And_Or) logic_1.getSelectedItem());
					RegrasModel.getInstance().atualizar(regra);
					String[] options = new String[] { "Sim", "Não" };
					int ok = JOptionPane.showOptionDialog(this,
							"Regra salva com sucesso! Queres testar a fiabilidade dessa regra, em comparação com as outras?",
							"Regra salva", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);
					if (ok == JOptionPane.YES_OPTION) {
						Comparador_de_Qualidade.getInstance().open();
					}
					reset();
					regrasList.setSelectedIndex(-1);
				} catch (ClassCastException | NullPointerException e) {
					JOptionPane.showMessageDialog(this, "Erro ao salvar alterações! Nenhuma regra selecionada!",
							"Exception", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Preenche os diversos campos com os atributos da regra dada e altera o modo
	 * para edição.
	 * 
	 * @param regra regra que se pretende editar
	 */
	public void preencher(Regra regra) {
		this.regra = regra;
		setTitle(regra.getNome());
		nome.setText(regra.getNome());
		if (regra.getMetrica_1() == Metrica.LOC)
			type.setSelectedIndex(0);
		else
			type.setSelectedIndex(1);
		spinner_metric_1.setValue(regra.getValor_1());
		spinner_metric_2.setValue(regra.getValor_2());
		logic_1.setSelectedItem(regra.getLogico());

		removeActionListenersFromSaveButton();
		save.addActionListener(new SaveListenerEdit());

	}

	/**
	 * Limpa os campos a preencher e altera para o modo "Nova Regra"
	 */
	public void reset() {
		setTitle("Nova Regra");
		nome.setText("");
		type.setSelectedIndex(0);
		spinner_metric_1.setValue(0);
		spinner_metric_2.setValue(0);
		logic_1.setSelectedIndex(0);
		removeActionListenersFromSaveButton();
		save.addActionListener(new SaveListenerCreate());
	}

	/**
	 * Remove os Action Listeners do botão Save, para permitir que se adicione um
	 * novo actionListener, sem interferência de comportamento com o outro
	 */
	public void removeActionListenersFromSaveButton() {
		ActionListener[] al = save.getActionListeners();
		for (int i = 0; i < al.length; i++) {
			save.removeActionListener(al[i]);
		}
	}

	/**
	 * Torna visível esse diálogo
	 */
	public void open() {
		setVisible(true);
	}

	/**
	 * ActionListener para o modo "Nova Regra"
	 * 
	 * @author dariop
	 *
	 */
	private class SaveListenerCreate implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			salvarRegra();
		}

	}

	/**
	 * ActionListener para o modo de edição
	 * 
	 * @author dariop
	 *
	 */
	private class SaveListenerEdit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			editarRegra();
		}

	}
}
