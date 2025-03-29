package br.edu.ifms.controller.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataManipulation {
	public Date convertStringData(String dataString) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = format.parse(dataString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
