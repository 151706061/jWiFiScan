package model;

import java.util.ArrayList;
import java.util.List;

public class ScanList {
	private List<Scan> scan = new ArrayList<>();
	
	public void addScan(Scan s) {
		scan.add(s);		
	}
	
	public List<Scan> getScan() {
		return scan;
	}
	
	public int numberOfScan() {
		return scan.size();
	}
	
	public void clearList() {
		scan.clear();		
	}
	

}
