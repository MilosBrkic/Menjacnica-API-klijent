package menjacnica.gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenjacnicaGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JButton btnKonvertuj;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JTextField textFieldIznosLevi;
	protected JTextField textFieldIznosDesni;
	protected JComboBox<String> comboBoxLevi;
	protected JComboBox<String> comboBoxDesni;
	protected JLabel poruka;
	

	/**
	 * Create the frame.
	 */
	public MenjacnicaGUI() {
		setResizable(false);
		setTitle("Menjacnica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzValuteZemlje());
		contentPane.add(getLblUValutuZemlje());
		contentPane.add(getBtnKonvertuj());
		contentPane.add(getLblIznos());
		contentPane.add(getLblIznos_1());
		contentPane.add(getTextFieldIznosLevi());
		contentPane.add(getTextFieldIznosDesni());
		contentPane.add(getComboBoxLevi());
		contentPane.add(getComboBoxDesni());
		contentPane.add(getPoruka());

	}
	private JLabel getLblIzValuteZemlje() {
		if (lblIzValuteZemlje == null) {
			lblIzValuteZemlje = new JLabel("Iz valute zemlje:");
			lblIzValuteZemlje.setBounds(40, 32, 90, 14);
		}
		return lblIzValuteZemlje;
	}
	private JLabel getLblUValutuZemlje() {
		if (lblUValutuZemlje == null) {
			lblUValutuZemlje = new JLabel("U valutu zemlje:");
			lblUValutuZemlje.setBounds(210, 32, 90, 14);
		}
		return lblUValutuZemlje;
	}
	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIKontroler.konvertuj((String)comboBoxLevi.getSelectedItem(),(String)comboBoxDesni.getSelectedItem(), textFieldIznosLevi.getText());
				}
			});
			btnKonvertuj.setBounds(138, 208, 89, 23);
		}
		return btnKonvertuj;
	}
	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos:");
			lblIznos.setBounds(40, 120, 46, 14);
		}
		return lblIznos;
	}
	private JLabel getLblIznos_1() {
		if (lblIznos_1 == null) {
			lblIznos_1 = new JLabel("Iznos:");
			lblIznos_1.setBounds(210, 120, 46, 14);
		}
		return lblIznos_1;
	}
	private JTextField getTextFieldIznosLevi() {
		if (textFieldIznosLevi == null) {
			textFieldIznosLevi = new JTextField();
			textFieldIznosLevi.setBounds(40, 145, 120, 20);
			textFieldIznosLevi.setColumns(10);
		}
		return textFieldIznosLevi;
	}
	private JTextField getTextFieldIznosDesni() {
		if (textFieldIznosDesni == null) {
			textFieldIznosDesni = new JTextField();
			textFieldIznosDesni.setBounds(210, 145, 120, 20);
			textFieldIznosDesni.setColumns(10);
		}
		return textFieldIznosDesni;
	}
	
	private JComboBox getComboBoxLevi() {
		if (comboBoxLevi == null) {
			comboBoxLevi = new JComboBox();
			comboBoxLevi.setBounds(40, 71, 120, 20);
		}
		return comboBoxLevi;
	}
	private JComboBox getComboBoxDesni() {
		if (comboBoxDesni == null) {
			comboBoxDesni = new JComboBox();
			comboBoxDesni.setBounds(210, 71, 120, 20);
		}
		return comboBoxDesni;
	}
	
	private JLabel getPoruka() {
		if (poruka == null) {
			poruka = new JLabel("");
			poruka.setForeground(Color.RED);
			poruka.setBounds(40, 176, 168, 14);
		}
		return poruka;
	}
}
