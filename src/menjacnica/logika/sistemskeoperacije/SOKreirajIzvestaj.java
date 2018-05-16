package menjacnica.logika.sistemskeoperacije;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class SOKreirajIzvestaj {
	
	public static void izvrsi(String levi, String desni, double odnos, boolean postoji) {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		GregorianCalendar danas = new GregorianCalendar();
		JsonObject kon = new JsonObject();
		JsonArray niz;
		String datumVreme = danas.get(GregorianCalendar.YEAR)+"-"+danas.get(GregorianCalendar.MONTH)+"-"+danas.get(GregorianCalendar.DAY_OF_MONTH)
		+" "+danas.get(GregorianCalendar.HOUR_OF_DAY)+":"+danas.get(GregorianCalendar.MINUTE)+":"+danas.get(GregorianCalendar.SECOND)+"."+danas.get(GregorianCalendar.MILLISECOND);
		
		kon.addProperty("datumVreme", datumVreme);
		kon.addProperty("izValuta", levi);
		kon.addProperty("uValuta", desni);
		if(postoji)
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
