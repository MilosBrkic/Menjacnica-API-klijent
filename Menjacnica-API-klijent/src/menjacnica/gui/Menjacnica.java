package menjacnica.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import menjacnica.URLConnectionUtil;
import menjacnica.Valuta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menjacnica extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzValuteZemlje;
	private JLabel lblUValutuZemlje;
	private JButton btnKonvertuj;
	private JLabel lblIznos;
	private JLabel lblIznos_1;
	private JTextField textFieldIznosLevi;
	private JTextField textFieldIznosDesni;
	private JComboBox comboBoxLevi;
	private JComboBox comboBoxDesni;

	private Valuta[] valute;
	private JLabel poruka;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					Menjacnica frame = new Menjacnica();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public Menjacnica() {
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

		ucitajZemlje();
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
					konvertuj();
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
	
	
	private void ucitajZemlje() {
		try {
			String s = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");

			String[] ime = s.split("\"name\":\"");
			String[] id = s.split("\"currencyId\":\"");
			valute = new Valuta[ime.length-1];

			int n;
		    for(int i=0; i<ime.length;i++) {
		    		
		    	n=ime[i].indexOf("\"");
		    	ime[i]=ime[i].substring(0, n);
		    	
		    	n=id[i].indexOf("\"");
		    	id[i]=id[i].substring(0, n);
		    	
		    	
		    	if(i!=0) {
		    		comboBoxDesni.addItem(ime[i]);
		    		comboBoxLevi.addItem(ime[i]);
		    		valute[i-1] = new Valuta();
		    		valute[i-1].setName(ime[i]);
		    		valute[i-1].setCurrencyId(id[i]);
		    	}
		    }
		   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void konvertuj() {
		poruka.setText("");
		
		double val=0;
		String desni = (String)comboBoxDesni.getSelectedItem();
		String levi = (String)comboBoxLevi.getSelectedItem();
		
		for(int i=0;i<valute.length;i++)
			if(valute[i].getName().equals(desni))
				desni=valute[i].getCurrencyId();
		
		for(int i=0;i<valute.length;i++)
			if(valute[i].getName().equals(levi))
				levi=valute[i].getCurrencyId();

		String q = levi+"_"+desni;
		try {
			String s = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/convert?q="+q);
			Gson gson = new GsonBuilder().create();
			JsonObject json = (gson.fromJson(s, JsonObject.class));
			JsonObject query = (JsonObject) json.get("query");
			int count = query.get("count").getAsInt();
			
			if(count==1) {
				JsonObject results = (JsonObject) json.get("results");
				JsonObject odnos = (JsonObject) results.get(q);
				val = odnos.get("val").getAsDouble();
				double leviIznos = Double.parseDouble(textFieldIznosLevi.getText());
				double desniIznos = val*leviIznos;
				textFieldIznosDesni.setText(Double.toString(desniIznos));	
			}
			else JOptionPane.showMessageDialog(null, "Ne postoje podaci o odnosu ove dve valute.");

			izvestaj(levi, desni, val, count);	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			poruka.setText("Neispravan iznos!");
		}
	}
	private JLabel getPoruka() {
		if (poruka == null) {
			poruka = new JLabel("");
			poruka.setForeground(Color.RED);
			poruka.setBounds(40, 176, 168, 14);
		}
		return poruka;
	}
	
	private void izvestaj(String levi, String desni, double odnos, int count) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		GregorianCalendar danas = new GregorianCalendar();
		JsonObject kon = new JsonObject();
		JsonArray niz;
		String datumVreme = danas.get(GregorianCalendar.YEAR)+"-"+danas.get(GregorianCalendar.MONTH)+"-"+danas.get(GregorianCalendar.DAY_OF_MONTH)
		+" "+danas.get(GregorianCalendar.HOUR_OF_DAY)+":"+danas.get(GregorianCalendar.MINUTE)+":"+danas.get(GregorianCalendar.SECOND)+"."+danas.get(GregorianCalendar.MILLISECOND);
		
		kon.addProperty("datumVreme", datumVreme);
		kon.addProperty("izValuta", levi);
		kon.addProperty("uValuta", desni);
		if(count==1)
			kon.addProperty("kurs", odnos);
		else
			kon.add("kurs", null);

		
		try (FileReader reader = new FileReader("data/log.json")) {
			niz = gson.fromJson(reader, JsonArray.class);
		} catch (Exception e) {
			niz = new JsonArray();
		}
		
		niz.add(kon);
			
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/log.json")))) {
			out.println(gson.toJson(niz));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
