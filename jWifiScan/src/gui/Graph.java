package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graph extends JPanel {
	private static BufferedImage img;
    private int imgX;
	private int imgY;
	
	public Graph(int imgX, int imgY) {
		this.imgX = imgX;
		this.imgY = imgY;
	}
	
	public static BufferedImage getImg() {
		return img;
	}
	
	public BufferedImage GetImage() {
        if (img == null) {
        	img = new BufferedImage(imgX, imgY, BufferedImage.TYPE_INT_RGB);
        }
        return img;
    }
	
	public Graphics2D getGraphics2D() {
		Graphics2D g = img.createGraphics();
		return g;
	}
	
	public void clearGraph() {
    	Graphics2D g = (Graphics2D) GetImage().createGraphics();
    	g.clearRect(0, 0, imgX, imgY);
	}
	
	/**
	 * @deprecated
	 * @param x
	 * @param y
	 * @param barva
	 */
	public static void drawPixel(int x, int y, Color barva) {
		img.setRGB(x, y, barva.getRGB());				 
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, null);
    }
	
	public void drawGrid() {
		Graphics2D g = img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // vyhlazovani
		
		int pocetRadku = 11;
		int pocetSloupcu = 14;
		int mezeraNahore = 20;
		int mezeraVlevo = 50;
		int mezeraMezyRadky = 22;
		int mezeraMezySloupci = 50;
		
		//g.drawLine(0, 0, 100, 100);

		// RADKY
		for (int i = 0; i < pocetRadku; i++) {
			g.drawLine(mezeraVlevo, mezeraNahore + (i*mezeraMezyRadky), 700, mezeraNahore + (i*mezeraMezyRadky));			
		}
		// RADKY POPIS
		for (int i = 0; i < pocetRadku; i++) {
			g.drawString(Integer.toString(-i*10), mezeraVlevo - 35, mezeraNahore + (i*mezeraMezyRadky) + 5);		
		}
		// TODO - zarovnani textu vpravo
		
		// SLOUPCE
		for (int i = 0; i < pocetSloupcu; i++) {
			g.drawLine(mezeraVlevo + (i*mezeraMezySloupci), mezeraNahore, mezeraVlevo + (i*mezeraMezySloupci), 220+mezeraNahore);
		}
		
		for (int i = 0; i < pocetSloupcu; i++) {
			g.drawString(Integer.toString(i+1), mezeraVlevo + (i*mezeraMezySloupci), 240+mezeraNahore);			
		}
		
	}

}
