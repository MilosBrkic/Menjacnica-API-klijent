package menjacnica.logika.sistemskeoperacije;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import menjacnica.logika.Valuta;

public class SOKonvertuj {
	
	public static double izvrsi(String levi, String desni , double leviIznos, Valuta[] valute) throws Exception {
					
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
				return  odnos.get("val").getAsDouble();
			}
			else throw new Exception("Ne postoje podaci o odnosu ove dve valute.");
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
