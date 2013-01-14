package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

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

}
