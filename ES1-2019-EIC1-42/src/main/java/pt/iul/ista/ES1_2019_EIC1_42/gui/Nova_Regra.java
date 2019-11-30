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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import pt.iul.ista.ES1_2019_EIC1_42.Metrica;
import pt.iul.ista.ES1_2019_EIC1_42.Regra;
import pt.iul.ista.ES1_2019_EIC1_42.RegrasModel;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 * Dialogo para definir uma nova regras e os limites (thresholds)
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
	private JButton save;
	private JLabel lNome;
	private JTextField nome;
	private JComboBox<String> type;
	private JList<Regra> regrasList;
	private RegrasModel regras;


	/**
	 * Create the dialog.
	 */
	public Nova_Regra() {
		initComponents();
		initPanel();
		
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
		metrics_1 = new JComboBox<Metrica>();
		metrics_1.setEnabled(false);
		metrics_1.setBounds(10, 82, 85, 20);
		metrics_1.setModel(new DefaultComboBoxModel<Metrica>(Metrica.values()));
		metrics_1.setSelectedIndex(0);
		contentPanel.add(metrics_1);

		JLabel label_maior_que = new JLabel(">");
		label_maior_que.setBounds(95, 81, 30, 20);
		label_maior_que.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(label_maior_que);

		spinner_metric_1 = new JSpinner();
		spinner_metric_1.setBounds(125, 82, 70, 20);
		spinner_metric_1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPanel.add(spinner_metric_1);

		logic_1 = new JComboBox<Logic_And_Or>();
		logic_1.setBounds(205, 82, 75, 20);
		contentPanel.add(logic_1);
		logic_1.setModel(new DefaultComboBoxModel<Logic_And_Or>(Logic_And_Or.values()));

		metrics_2 = new JComboBox<Metrica>();
		metrics_2.setEnabled(false);
		metrics_2.setBounds(290, 82, 85, 20);
		metrics_2.setModel(new DefaultComboBoxModel<Metrica>(Metrica.values()));
		metrics_2.setSelectedIndex(1);
		contentPanel.add(metrics_2);

		final JLabel label_menor_que = new JLabel(">");
		label_menor_que.setBounds(375, 82, 30, 20);
		label_menor_que.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(label_menor_que);

		spinner_metric_2 = new JSpinner();
		spinner_metric_2.setBounds(405, 82, 70, 20);
		spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		contentPanel.add(spinner_metric_2);

		save = new JButton("Salvar");
		save.setBounds(375, 255, 100, 25);
		contentPanel.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarRegra();
			}
		});

		lNome = new JLabel("Nome:");
		lNome.setBounds(10, 10, 46, 20);
		contentPanel.add(lNome);

		nome = new JTextField();
		nome.setBounds(66, 10, 214, 20);
		contentPanel.add(nome);
		nome.setColumns(10);

		type = new JComboBox<String>();
		type.setModel(new DefaultComboBoxModel<String>(new String[] { "long method", "feature envy" }));
		type.setSelectedIndex(0);
		type.setBounds(10, 45, 150, 20);
		contentPanel.add(type);
		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		
		JLabel lblRegrasExistentes = new JLabel("Regras existentes");
		lblRegrasExistentes.setBounds(10, 115, 120, 20);
		contentPanel.add(lblRegrasExistentes);
		
		regras = RegrasModel.getInstance();
		regrasList = new JList<Regra>(regras);
		regrasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JScrollPane jscrollpane = new JScrollPane(regrasList);
		jscrollpane.setBounds(10, 140, 465, 109);
		
		contentPanel.add(jscrollpane);

	}

	/**
	 * Verifica se o campo Nome foi preenchido corretamente pelo utilizador, cria uma
	 * nova regra e adiciona à lista de Regras presente em RegrasModel, se
	 * essa regra ainda não existir lá.
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
				if (!RegrasModel.getInstance().addRegra(r))
					JOptionPane.showMessageDialog(this, "A regra já existe!", "Regra já existe",
							JOptionPane.ERROR_MESSAGE);
				else {
					String[] options = new String[] { "Sim", "Não" };
					int ok = JOptionPane.showOptionDialog(this,
							"Regra criada com sucesso! Queres testar a fiabilidade dessa regra, em comparação com as outras?",
							"Regra criada", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options,
							options[0]);
					if (ok == JOptionPane.YES_OPTION) {
						Comparador_de_Qualidade.getInstance().open();
					}
				}
			} catch (ClassCastException e) {
				JOptionPane.showMessageDialog(this, "Erro ao criar nova regra!", "ClassCastException",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public JComboBox<Metrica> getMetrics_1(){
		return metrics_1;
	}
	
	public JButton getSave() {
		return save;
	}
	
	public JTextField getNome() {
		return nome;
	}
	
	public void open() {
		setVisible(true);
	}
}
