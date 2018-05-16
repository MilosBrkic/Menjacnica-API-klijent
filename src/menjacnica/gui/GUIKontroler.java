package menjacnica.gui;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import menjacnica.logika.Menjacnica;
import menjacnica.logika.Valuta;

public class GUIKontroler {
	
	private static Menjacnica m;
	private static MenjacnicaGUI prozor;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					m = new Menjacnica();
					prozor = new MenjacnicaGUI();
					ucitajZemlje();
					prozor.setVisible(true);			
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private static void ucitajZemlje() {
		Valuta[] valute = m.ucitajZemlje();
		for(int i=0;i<valute.length;i++) {
		    prozor.comboBoxDesni.addItem(valute[i].getName());
		    prozor.comboBoxLevi.addItem(valute[i].getName());
		}
	}
	
	public static void konvertuj(String levi, String desni, String iznosText) {
		double odnos=0;
		try {
			prozor.poruka.setText("");
			double leviIznos = Double.parseDouble(iznosText);
			odnos = m.konvertuj(levi,desni,leviIznos);
			m.izvestaj(levi, desni, odnos, true);
			prozor.textFieldIznosDesni.setText(Double.toString(leviIznos*odnos));
		}
		catch (NumberFormatException ex) {
			prozor.poruka.setText("Neispravan iznos!");
			return;
		}
		catch (Exception greska) {
			JOptionPane.showMessageDialog(null, greska.getMessage());
			m.izvestaj(levi, desni, odnos, false);
		}
	}
	

}
