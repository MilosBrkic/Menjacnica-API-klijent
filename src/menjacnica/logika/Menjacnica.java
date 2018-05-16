package menjacnica.logika;

import menjacnica.logika.sistemskeoperacije.SOKonvertuj;
import menjacnica.logika.sistemskeoperacije.SOKreirajIzvestaj;
import menjacnica.logika.sistemskeoperacije.SOUcitajZemlje;

public class Menjacnica {
	
	private Valuta[] valute;
	
	public Valuta[] ucitajZemlje() {
		return valute = SOUcitajZemlje.izvrsi();
	}
	
	public double konvertuj(String levi,String desni , double iznos) throws Exception{
		return SOKonvertuj.izvrsi(levi,desni , iznos, valute);
	}

	
	public void izvestaj(String levi,String desni , double odnos, boolean postoji) {
		SOKreirajIzvestaj.izvrsi(levi, desni, odnos, postoji);
	}
}
