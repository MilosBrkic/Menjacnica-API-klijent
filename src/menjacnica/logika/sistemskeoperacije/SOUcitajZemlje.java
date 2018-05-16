package menjacnica.logika.sistemskeoperacije;

import java.io.IOException;

import menjacnica.logika.Valuta;

public class SOUcitajZemlje {
	
	public static Valuta[] izvrsi() {
		try {
			String s = URLConnectionUtil.getContent("http://free.currencyconverterapi.com/api/v3/countries");

			String[] ime = s.split("\"name\":\"");
			String[] id = s.split("\"currencyId\":\"");
			Valuta[] valute = new Valuta[ime.length-1];

			int n;
		    for(int i=0; i<ime.length;i++) {
		    		
		    	n=ime[i].indexOf("\"");
		    	ime[i]=ime[i].substring(0, n);
		    	
		    	n=id[i].indexOf("\"");
		    	id[i]=id[i].substring(0, n);
		    	
		    	
		    	if(i!=0) {
		    		valute[i-1] = new Valuta();
		    		valute[i-1].setName(ime[i]);
		    		valute[i-1].setCurrencyId(id[i]);
		    	}
		    }
		    return valute;
		   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
