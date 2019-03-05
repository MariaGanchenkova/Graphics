import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Algorithm extends JPanel {
	
	private BufferedImage image;

	private int size;
	
	private Map<Location, Pixel> centersP; //храним позицию гекса в сетке и его центр пиксель
	private Map<Location, Square> centersS;
	private Field field;
	
	private Graphics g;
	
	public Algorithm(BufferedImage img, int size) {
		this.image = img;
		this.size = size;
		this.centersP = new HashMap<Location, Pixel>();
	}
	
	private int sign (double x) {
		return (x > 0) ? 1 : (x < 0) ? -1 : 0;
	}
	
	public void Bresenham(double x0, double x1, double y0, double y1) {
		double deltaX = x1 - x0;
		double deltaY = y1 - y0;
		double error = 0;
		int signX = sign(deltaX);
		int signY = sign(deltaY);
		int lorX = 0; 
		int lorY = 0;
		double uod = 0;
		double dou = 0;
		deltaX = Math.abs(deltaX);
		deltaY = Math.abs(deltaY);
		if (deltaX > deltaY) {
			lorX = signX;
			lorY = 0;
			uod = deltaX;
			dou = deltaY;
		}else {
			lorY = signY;
			lorX = 0;
			uod = deltaY;
			dou = deltaX;
		}
		double i = 0;
		error = dou/2;
		Pixel pixel = new Pixel((int) Math.ceil(x0), (int) Math.ceil(y0), Color.BLACK.getRGB()); // 0 = black
		dot(pixel);
		for (i = 0; i < uod; i++) {
			error -= dou;
			if (error < 0)
			{
				error += uod;
				x0 += signX;
				y0 += signY;
			}
			else
			{
				x0 += lorX;
				y0 += lorY;
			}
			pixel = new Pixel((int) Math.ceil(x0), (int) Math.ceil(y0), Color.BLACK.getRGB());  // 0 = black
			dot(pixel);
		}
	}
	
	private void dot(Pixel pix) {
		if (isInBorder(pix)) {
			image.setRGB((int) Math.ceil(pix.getPexelX()), (int) Math.ceil(pix.getPexelY()), Color.BLACK.getRGB());
		} else {
		}
	}
	
	private double countX(double size, double angle, double centerX) {
		double x = size*Math.cos(angle + Math.PI/6) + centerX;
		return x;
	}
	
	private double countY(double size, double angle, double centerY) {
		double y = size*Math.sin(angle + Math.PI/6) + centerY;
		return y;
	}

	public void drawHex(double centerX, double centerY, int size) {
		Map<Double, Double> peaks = new LinkedHashMap<Double, Double>();
		double angle = 0;
		double prevX = countX(size, angle, centerX);
		double prevY = countY(size, angle, centerY);
		double x = 0;
		double y = 0;
		double firstX = prevX;
		double firstY = prevY; 
		Pixel pixel = new Pixel((int) Math.ceil(prevX), (int) Math.ceil(prevY), Color.BLACK.getRGB()); //0 = black
		dot(pixel);
		for (int i = 1; i <= 5; i++) {
			angle = 2*Math.PI*i/6;
			x = countX(size, angle, centerX);
			y = countY(size, angle, centerY);
			Bresenham(prevX, x, prevY, y);
			prevX = x;
			prevY = y;
		}
		Bresenham(firstX, prevX, firstY, prevY);
	}
	
	public void drawNet(int size) {
		int width = (int) Math.ceil(Math.sqrt(3) / 2 * size);  //ширина
		int height = size * 2;                //высота
		int counterX = 0;
		for (int i = width; i < 10 * width; i = i + width * 2) {
			int counterY = 0;
			for (int j = height; j < 6 * height; j = j +(height * 3/4)) {
				if (counterY % 2 == 0) {
					drawHex(i + (Math.sqrt(3)/2*size), j, size);
					Location loc = new Location(counterX, counterY);
					Pixel thisCenter = new Pixel((int) Math.ceil(i + (Math.sqrt(3)/2*size)), j);
					g.drawString(loc.getCoordX() + " " + loc.getCoordY(), thisCenter.getPexelX(), thisCenter.getPexelY());
					centersP.put(loc, thisCenter);
				} else {
					if (counterX == 0) {
						
					} else {
						drawHex(i, j, size);
						//counterY++;
						Location loc = new Location(counterX-1, counterY);
						Pixel thisCenter = new Pixel(i, j);
						g.drawString(loc.getCoordX() + " " + loc.getCoordY(), thisCenter.getPexelX(),
								thisCenter.getPexelY());
						centersP.put(loc, thisCenter);
					}
				}
				counterY++;
			}
			counterX++;
		}
	}
	
	public void fill(Location loca, Color color) {
		Pixel ceed = null;
		ArrayDeque<Pixel> stack = new ArrayDeque<Pixel>();
		for (Map.Entry<Location, Pixel> entry : centersP.entrySet()) {
			if ((entry.getKey().getCoordX() == loca.getCoordX()) && (entry.getKey().getCoordY() == loca.getCoordY())) {
				ceed = entry.getValue();
			}
		}
		stack.push(ceed);
		while (!stack.isEmpty()) {
			Pixel pix = stack.pollFirst();
			int x = pix.getPexelX();
			int y = pix.getPexelY();
			if ((x >= 0) && (x < image.getWidth() && (y >= 0) && (y < image.getHeight()))) {
				if ((image.getRGB(x, y) != Color.GREEN.getRGB()) && (image.getRGB(x, y) != Color.BLACK.getRGB())) {

					image.setRGB(x, y, color.getRGB());
					stack.add(new Pixel(x + 1, y));
					stack.add(new Pixel(x - 1, y));
					stack.add(new Pixel(x, y + 1));
					stack.add(new Pixel(x, y - 1));
				}
			}
		}
	}

	private boolean isInBorder(Pixel current) {
		if ((current.getPexelX() < 350) && (current.getPexelX() > 0) && (current.getPexelY() < 450) && (current.getPexelY() > 0)) {
			return true;
		}
		return false;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(field);
		//g.clearRect(0, 0, image.getWidth(), image.getHeight());
		//this.removeAll();
		this.g = g;
		drawNet(size);
		if (image != null) {
			g.drawImage(image, 2, 0, null);
		}
		if ((field != null) && !(centersS.isEmpty())) {
			for (Entry<Location, Square> entry : centersS.entrySet()) {
				System.out.println(entry.getKey().getCoordX() + " " + entry.getKey().getCoordY() + " " + field.getCenters().get(entry.getKey()).getValue());
				if (entry.getValue().getValue()) {
					fill(entry.getKey(), Color.GREEN);
				} else {
					fill(entry.getKey(), Color.GRAY);
				}
			}
			g.drawImage(image, 2, 0, null);
		}
	}
	
	public Map<Location, Pixel> getCenters() {
		return centersP;
	}

	public void setCentersP(Map<Location, Pixel> centers) {
		this.centersP = centers;
	}
	
	public void setCentersS(Map<Location, Square> centers) {
		this.centersS = centers;
	}
	
	public void letsPlay() {
		this.field = new Field(5, 10, centersP);
		centersS = field.getCenters();	
	}
	
	public void nextGeneration() {
		centersS = field.nextGeneration();
	}
}
