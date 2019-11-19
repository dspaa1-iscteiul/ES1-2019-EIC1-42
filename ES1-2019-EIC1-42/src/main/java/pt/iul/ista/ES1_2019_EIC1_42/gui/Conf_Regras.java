package pt.iul.ista.ES1_2019_EIC1_42.gui;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import pt.iul.ista.ES1_2019_EIC1_42.Metricas;
import pt.iul.ista.ES1_2019_EIC1_42.Logic_And_Or;
import javax.swing.SpinnerNumberModel;

/**
 * Dialogo para definir as regras e limites (thresholds)
 * 
 * @author dariop
 *
 */
public class Conf_Regras extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2326177220283427980L;
	private final JPanel contentPanel = new JPanel();
	private JPanel longMethodPanel;
	private JPanel featureEnvyPanel;
	private JComboBox<Metricas> metrics_1, metrics_2, metrics_3, metrics_4;
	private JComboBox<Logic_And_Or> logic_1, logic_2, logic_3;
	private final JSpinner spinner_metric_1, spinner_metric_2, spinner_metric_3, spinner_metric_4;
	private JButton addCondition, removeCondition, save;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Conf_Regras dialog = new Conf_Regras();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Conf_Regras() {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 500, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		longMethodPanel = new JPanel();
		featureEnvyPanel = new JPanel();
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane);

		tabbedPane.add(longMethodPanel, "long method");
		longMethodPanel.setLayout(null);

		metrics_1 = new JComboBox<Metricas>();
		metrics_1.setModel(new DefaultComboBoxModel<Metricas>(Metricas.values()));
		metrics_1.setSelectedIndex(0);
		metrics_1.setEditable(false);
		metrics_1.setBounds(10, 35, 86, 20);
		longMethodPanel.add(metrics_1);

		JLabel label_maior_que = new JLabel(">");
		label_maior_que.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_maior_que.setHorizontalAlignment(SwingConstants.CENTER);
		label_maior_que.setBounds(103, 35, 22, 20);
		longMethodPanel.add(label_maior_que);

		spinner_metric_1 = new JSpinner();
		spinner_metric_1.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner_metric_1.setBounds(129, 35, 57, 20);
		longMethodPanel.add(spinner_metric_1);

		logic_1 = new JComboBox<Logic_And_Or>();
		logic_1.setModel(new DefaultComboBoxModel<Logic_And_Or>(Logic_And_Or.values()));
		logic_1.setBounds(211, 35, 50, 20);
		longMethodPanel.add(logic_1);

		metrics_2 = new JComboBox<Metricas>();
		metrics_2.setModel(new DefaultComboBoxModel<Metricas>(Metricas.values()));
		metrics_2.setSelectedIndex(1);
		metrics_2.setEditable(false);
		metrics_2.setBounds(283, 35, 86, 20);
		longMethodPanel.add(metrics_2);

		JLabel label_maior_que_1 = new JLabel(">");
		label_maior_que_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_maior_que_1.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_maior_que_1.setBounds(376, 35, 22, 20);
		longMethodPanel.add(label_maior_que_1);

		spinner_metric_2 = new JSpinner();
		spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinner_metric_2.setBounds(402, 35, 57, 20);
		longMethodPanel.add(spinner_metric_2);
		
		{
		logic_2 = new JComboBox<Logic_And_Or>();
		logic_2.setModel(new DefaultComboBoxModel<Logic_And_Or>(Logic_And_Or.values()));
		logic_2.setBounds(10, 76, 50, 20);
		longMethodPanel.add(logic_2);

		metrics_3 = new JComboBox<Metricas>();
		metrics_3.setModel(new DefaultComboBoxModel<Metricas>(Metricas.values()));
		metrics_3.setSelectedIndex(2);
		metrics_3.setEditable(false);
		metrics_3.setBounds(10, 117, 86, 20);
		longMethodPanel.add(metrics_3);

		spinner_metric_3 = new JSpinner();
		spinner_metric_3.setBounds(129, 117, 57, 20);
		longMethodPanel.add(spinner_metric_3);
		
		}

		JLabel label_maior_que_2 = new JLabel(">");
		label_maior_que_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_maior_que_2.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_maior_que_2.setBounds(103, 117, 22, 20);
		longMethodPanel.add(label_maior_que_2);

		logic_3 = new JComboBox<Logic_And_Or>();
		logic_3.setModel(new DefaultComboBoxModel<Logic_And_Or>(Logic_And_Or.values()));
		logic_3.setBounds(211, 117, 50, 20);
		longMethodPanel.add(logic_3);

		metrics_4 = new JComboBox<Metricas>();
		metrics_4.setModel(new DefaultComboBoxModel<Metricas>(Metricas.values()));
		metrics_4.setSelectedIndex(3);
		metrics_4.setEditable(false);
		metrics_4.setBounds(283, 117, 86, 20);
		longMethodPanel.add(metrics_4);

		JLabel label_maior_que_3 = new JLabel(">");
		label_maior_que_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_maior_que_3.setFont(new Font("Arial Black", Font.PLAIN, 11));
		label_maior_que_3.setBounds(376, 116, 22, 20);
		longMethodPanel.add(label_maior_que_3);

		spinner_metric_4 = new JSpinner();
		spinner_metric_4.setBounds(402, 117, 57, 20);
		longMethodPanel.add(spinner_metric_4);
		
		addCondition = new JButton("Adicionar condição");
		addCondition.setBounds(10, 189, 155, 23);
		longMethodPanel.add(addCondition);


		removeCondition = new JButton("Remover condição");
		removeCondition.setBounds(175, 189, 148, 23);
		longMethodPanel.add(removeCondition);

		save = new JButton("Salvar");
		save.setBounds(333, 189, 136, 23);
		longMethodPanel.add(save);
		tabbedPane.add(featureEnvyPanel, "feature envy");

		initLongMethodPanel();
		initFeatureEnvyPanel();

		metrics_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (metrics_1.getSelectedItem().equals(Metricas.LAA))
					spinner_metric_1.setModel(new SpinnerNumberModel(0, 0, 1, 0.1));
				else
					spinner_metric_1.setModel(new SpinnerNumberModel(0, 0, null, 1));
			}
		});

		metrics_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (metrics_2.getSelectedItem().equals(Metricas.LAA))
					spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, 1, 0.1));
				else
					spinner_metric_2.setModel(new SpinnerNumberModel(0, 0, null, 1));
			}
		});

		metrics_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (metrics_2.getSelectedItem().equals(Metricas.LAA))
					spinner_metric_3.setModel(new SpinnerNumberModel(0, 0, 1, 0.1));
				else
					spinner_metric_3.setModel(new SpinnerNumberModel(0, 0, null, 1));
			}
		});

		metrics_4.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (metrics_4.getSelectedItem().equals(Metricas.LAA))
					spinner_metric_4.setModel(new SpinnerNumberModel(0, 0, 1, 0.1));
				else
					spinner_metric_4.setModel(new SpinnerNumberModel(0, 0, null, 1));
			}
		});
	}

	private void initFeatureEnvyPanel() {
		// TODO Auto-generated method stub

	}

	private void initLongMethodPanel() {
		// TODO Auto-generated method stub

	}
}
