package com.prevent.senior.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.prevent.senior.model.LogDetail;

public class LogDetailUtil {
	private static DateTimeFormatter formater =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static List<LogDetail> fileToList(InputStream is) {
		
		List<LogDetail> details = new ArrayList<>();
		
		try {
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			
			while(bufferedreader.ready()) {
				String line = bufferedreader.readLine();
				String val[]  = line.split("[|]");
				LogDetail detail = new LogDetail();
				detail.setData(LocalDateTime.parse(val[0],formater));
				detail.setIp(val[1]);
				detail.setRequest(val[2]);
				detail.setStatus(val[3]);
				detail.setUserAgent(val[4]);
				details.add(detail);
			}
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Problema no encoding do arquivo: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Problema de leitura e escrita do arquivo (IO): " + e.getMessage());
		}
		
		return details;
	}

}
