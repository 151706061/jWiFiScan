package gui;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import sluzby.SeznamWifiKaret;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JPanel control;
	private JComboBox cmbInterface;
	private JButton btnReloadInterface;
	private SeznamWifiKaret sw;
	private ArrayList<String> wifiInterface;
	private DefaultComboBoxModel dcbmWifiInt = new DefaultComboBoxModel<>();


	public MainWindow() {
		setTitle("jWiFiScan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 608, 435);
		
		control = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(control, GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(control, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(239, Short.MAX_VALUE))
		);
		
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
		
		GroupLayout gl_control = new GroupLayout(control);
		gl_control.setHorizontalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cmbInterface, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnReloadInterface)
					.addContainerGap(277, Short.MAX_VALUE))
		);
		gl_control.setVerticalGroup(
			gl_control.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_control.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_control.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(cmbInterface, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReloadInterface))
					.addContainerGap(122, Short.MAX_VALUE))
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
}
