package sluzby;

import gui.MainWindow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.Scan;
import model.ScanList;

public class WifiTools {
	private static ArrayList<String> wifiIntList;
	private static ArrayList<String> wifiScan;
	
	// 127 "command not found"    -   http://stackoverflow.com/a/1535733
	
	
	public static ArrayList<String> getWifiScan(String cmd) {
		wifiScan = new ArrayList<>(0);
		try {
	        Runtime rt = Runtime.getRuntime();
	        Process pr = rt.exec(cmd);

	        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

	        String line=null;
	        MainWindow.getScanlist().clearList();
	        while((line=input.readLine()) != null) {
	           // System.out.println(line);
	        	wifiScan.add(line);
	        	
	        	StringTokenizer st = new StringTokenizer(line, ",");
	        	int tokenNumber = 0;
	        	ArrayList<String> scanResult = new ArrayList<>(5);
	        	while(st.hasMoreTokens()) {
                    tokenNumber++;
                    String nacteno = st.nextToken();
                    scanResult.add(nacteno);
                    System.out.println(nacteno);
                }
	        	Scan s = new Scan();
	        	//"00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL"
	        	s.setMac(scanResult.get(0));
	        	s.setChannel(scanResult.get(1));
	        	s.setFreq(scanResult.get(2));
	        	s.setQuality(scanResult.get(3));
	        	s.setSignal(scanResult.get(4));
	        	s.setEnc(scanResult.get(5));
	        	s.setSsid(scanResult.get(6));
	        	
	        	MainWindow.getScanlist().addScan(s);
               
                //reset token number
                tokenNumber = 0;
	        }

	        int exitVal = pr.waitFor();
	        System.out.println("Exited with error code "+exitVal);

	    } catch(Exception e) {
	        System.out.println(e.toString());
	        e.printStackTrace();
	    }
		
		return wifiScan;			
	}
	
	public static ArrayList<String> getWifiInterfaceList(String cmd) {	
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
