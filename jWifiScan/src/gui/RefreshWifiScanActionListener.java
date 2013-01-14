package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sluzby.WifiTools;

public class RefreshWifiScanActionListener implements ActionListener {
	//private WifiTools wt;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//   "00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL"
		String prikaz = " /bin/bash ./cmd/wifiScan.sh " + MainWindow.getSelectedWifiInterface() + " " + MainWindow.getSudoPassword();
		System.out.println(prikaz);
		WifiTools.getWifiScan(prikaz);
		MainWindow.getScanTblModel().setScanList(MainWindow.getScanlist());
		TableColumnAdjuster tca = new TableColumnAdjuster(MainWindow.getTblScan());
		tca.adjustColumns();	
		
	}

}
