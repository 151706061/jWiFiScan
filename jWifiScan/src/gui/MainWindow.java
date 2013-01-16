package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ScanList;
import sluzby.WifiTools;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ListSelectionListener {
	private int scanRefresh = 5; // jak casto skenovat WiFi site
	private static ScanList scanlist = new ScanList();
	
	private JPanel control;
	private static JComboBox cmbInterface;
	private JButton btnReloadInterface;
	private static WifiTools wt;
	private DefaultComboBoxModel dcbmWifiInt = new DefaultComboBoxModel<>();
	private JLabel lblScanTime = new JLabel(Integer.toString(scanRefresh));
	
	private JSlider sldScanTime;
	private JToggleButton tglbtnScan;
	private static JTable tblScan;
	private static ScanTableModel scanTblModel = new ScanTableModel();
	private JPanel graph;
	private static Graph img;
	private JLabel lblNewLabel_2;
	private JPasswordField passwdSudo;
	private static String sudoPassword;

	Timer timer;

	public MainWindow() {
			
		// testovaci data:
		//Scan s1 = new Scan("00:22:43:1E:FC:59","11","2.462","70/70","-39","on","VANCL");
		//scanlist.addScan(s1);
		
		setTitle("jWiFiScan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		control = new JPanel();
		control.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel scan = new JPanel();
		scan.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		graph = new JPanel();
		graph.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(graph, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
						.addComponent(scan, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
						.addComponent(control, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(control, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scan, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(graph, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addContainerGap())
		);
		graph.setLayout(new BorderLayout());
		scan.setLayout(new BorderLayout());
		//img = new Graph(graph.getWidth(), graph.getHeight());
		img = new Graph(767, 278);
		img.clearGraph();
		graph.add(img, BorderLayout.CENTER);
		//img.drawPixel(20, 20, Color.RED);
		img.drawGrid();

		scanTblModel.setScanList(scanlist);
		tblScan = new JTable();
		tblScan.setModel(scanTblModel);		// NEFUNGUJE WINDOW BUILDER --- ZAKOMENTOVAT PRED EDITACI
		JScrollPane tblScroll = new JScrollPane(tblScan);
		tblScan.getSelectionModel().addListSelectionListener(this);
		tblScan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//TableColumnAdjuster tca = new TableColumnAdjuster(tblScan);
		//tca.adjustColumns();	
		tblScroll.setViewportView(tblScan);
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
					sudoPassword = passwdSudo.getText();
					timer = new Timer(scanRefresh*1000, new RefreshWifiScanActionListener());
					timer.start();
					
				} else {
					tglbtnScan.setText("Start");
					timer.stop();
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
		
		lblNewLabel_2 = new JLabel("Sudo heslo");
		
		passwdSudo = new JPasswordField();
				
		GroupLayout gl_control = new GroupLayout(control);
		gl_control.setHorizontalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_control.createParallelGroup(Alignment.LEADING, false)
						.addComponent(tglbtnScan, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
							.addComponent(lblScanTime)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(passwdSudo, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		gl_control.setVerticalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_control.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_control.createSequentialGroup()
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
							.addComponent(tglbtnScan))
						.addGroup(gl_control.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(passwdSudo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		control.setLayout(gl_control);
		getContentPane().setLayout(groupLayout);

		
		
		
	}
	
	/**
	 * Nacte seznam vsech WiFi karet v PC a ulozi je do ComboBoxu
	 */
	private void loadWifiIntList() {
		wt.getWifiInterfaceList();
		dcbmWifiInt.removeAllElements();
		for (String s : wt.getWifiIntList()) {
			dcbmWifiInt.addElement(s);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public static String getSudoPassword() {
		return sudoPassword;
	}
	
	public static String getSelectedWifiInterface() {
		return cmbInterface.getSelectedItem().toString();		
	}

	public static ScanList getScanlist() {
		return scanlist;
	}

	public static ScanTableModel getScanTblModel() {
		return scanTblModel;
	}

	public static JTable getTblScan() {
		return tblScan;
	}

	

	
	
	
}
