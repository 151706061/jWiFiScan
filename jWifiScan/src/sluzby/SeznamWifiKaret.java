package sluzby;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SeznamWifiKaret {
	static ArrayList<String> wifiIntList;
	
	public static ArrayList<String> runCmd(String cmd) {	
		wifiIntList = new ArrayList<>();
		try {
	        Runtime rt = Runtime.getRuntime();
	        Process pr = rt.exec(cmd);

	        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

	        String line=null;

	        while((line=input.readLine()) != null) {
	            //System.out.println(line);
	            wifiIntList.add(line);
	        }

	        int exitVal = pr.waitFor();
	        System.out.println("Exited with error code "+exitVal);

	    } catch(Exception e) {
	        System.out.println(e.toString());
	        e.printStackTrace();
	    }
		return wifiIntList;				
	}

	public static ArrayList<String> getWifiIntList() {
		return wifiIntList;
	}
	

}
