package app;

import gui.MainWindow;

import java.awt.EventQueue;
import java.io.IOException;

@SuppressWarnings("unused")
public class Spustit {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
