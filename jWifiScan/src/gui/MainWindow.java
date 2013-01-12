package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Scan;
import model.ScanList;
import sluzby.SeznamWifiKaret;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ListSelectionListener {
	private int scanRefresh = 2; // jak casto skenovat WiFi site
	private ScanList sl = new ScanList();
	
	private JPanel control;
	private JComboBox cmbInterface;
	private JButton btnReloadInterface;
	private SeznamWifiKaret sw;
	private DefaultComboBoxModel dcbmWifiInt = new DefaultComboBoxModel<>();
	private JLabel lblScanTime = new JLabel(Integer.toString(scanRefresh));
	
	private JSlider sldScanTime;
	private JToggleButton tglbtnScan;
	private JTable tblScan;
	private ScanTableModel stm = new ScanTableModel();


	public MainWindow() {
		
		Scan s1 = new Scan("00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL");
		sl.addScan(s1);
		
		setTitle("jWiFiScan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		
		control = new JPanel();
		control.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel scan = new JPanel();
		scan.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addComponent(control, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(control, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scan, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(114, Short.MAX_VALUE))
		);
		scan.setLayout(new BorderLayout());

		stm.setScanList(sl);
		tblScan = new JTable();
		tblScan.setModel(stm);
		JScrollPane tblScroll = new JScrollPane(tblScan);
		tblScan.getSelectionModel().addListSelectionListener(this);
		tblScan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnAdjuster tca = new TableColumnAdjuster(tblScan);
		tca.adjustColumns();	
		scan.add(tblScroll);
		

		
		
		JLabel lblNewLabel = new JLabel("WiFi karta");
		
		cmbInterface = new JComboBox();
		cmbInterface.setModel(dcbmWifiInt);
		loadWifiIntList();
		
		btnReloadInterface = new JButton("Nacist");
		btnReloadInterface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadWifiIntList();
			}			
		});
		
		tglbtnScan = new JToggleButton("Start");
		tglbtnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tglbtnScan.isSelected()) {
					tglbtnScan.setText("Stop");					
				} else {
					tglbtnScan.setText("Start");
				}
			}
		});
		
		sldScanTime = new JSlider();
		sldScanTime.setMaximum(10);
		sldScanTime.setMinimum(1);
		sldScanTime.setValue(scanRefresh);
		sldScanTime.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				scanRefresh = sldScanTime.getValue();
				lblScanTime.setText(Integer.toString(scanRefresh));
			}
		});		
		
		JLabel lblNewLabel_1 = new JLabel("Obnoveni [s]");
				
		GroupLayout gl_control = new GroupLayout(control);
		gl_control.setHorizontalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addGroup(gl_control.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_control.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnScan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_control.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_control.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_control.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(cmbInterface, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnReloadInterface))
								.addGroup(gl_control.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sldScanTime, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblScanTime)))))
					.addContainerGap(334, Short.MAX_VALUE))
		);
		gl_control.setVerticalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_control.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cmbInterface, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReloadInterface))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_control.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addComponent(sldScanTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblScanTime))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tglbtnScan)
					.addGap(57))
		);
		control.setLayout(gl_control);
		getContentPane().setLayout(groupLayout);
		
	}
	
	/**
	 * Nacte seznam vsech WiFi karet v PC a ulozi je do ComboBoxu
	 */
	private void loadWifiIntList() {
		sw.runCmd(" /bin/bash cmd/wifiIntList.sh  ");
		dcbmWifiInt.removeAllElements();
		for (String s : sw.getWifiIntList()) {
			dcbmWifiInt.addElement(s);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
