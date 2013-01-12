package gui;

import java.text.SimpleDateFormat;

import javax.swing.table.AbstractTableModel;

import model.Scan;
import model.ScanList;

@SuppressWarnings("serial")
public class ScanTableModel extends AbstractTableModel {
	private ScanList sl;
	
	public void setScanList(ScanList sl) {
		this.sl = sl;
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return sl.getScan().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Scan s = sl.getScan().get(rowIndex);
		if (columnIndex == 0) {
			return s.getMac();			
		} else if (columnIndex == 1) {
			return s.getChannel();
		} else if (columnIndex == 2) {
			return s.getFreq();			
		} else if (columnIndex == 3) {
			return s.getQuality();			
		} else if (columnIndex == 4) {
			return s.getSignal();			
		} else if (columnIndex == 5) {
			return s.getEnc();			
		} else if (columnIndex == 6) {
			return s.getSsid();
		}
		
		return null;
	}
	
	public String getColumnName(int sloupec) {
		switch (sloupec) {
		// "00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL"

		case 0:
			return "MAC";			
		case 1:
			return "Kanal";
		case 2:
			return "Freq";
		case 3:
			return "Kvalita";
		case 4:
			return "Signal";		
		case 5:
			return "Sifrovani";
		case 6:
			return "SSID";
		}

		return "";				
	}


}
