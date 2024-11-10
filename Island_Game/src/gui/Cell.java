package gui;

import java.awt.*;

@SuppressWarnings("serial")
public abstract class Cell extends Canvas {
	protected int height;
	private Color color;
	private static int width = 30;
	
	private Color colorAfterClick;
	
	protected Cell(int height) throws HeightException {
		if(height < 0 || height > 1000) throw new HeightException();
		this.height = height;
		if(height == 0) this.color = Color.BLUE;
		else {
			if(height <= 400) {
				int number = (int) (height / 400.0 * 255);
				this.color = new Color(0, (int)(number / 255.0 * 180), 255 - number);
			} else if (height <= 500) {
				int number = (int) ((height - 400) / 100.0 * 255);
				this.color = new Color(number, 180 + (int)(number / 255.0 * 75), (int)((height - 400) / 100.0 * 150));
			}
			else if (height <= 800) {
				int number = (int) ((height - 500) / 300.0 * 255);
				this.color = new Color(255 - (int)(number / 255.0 * 155), 255 - (int)(number / 255.0 * 205), 150 - (int)(number / 255.0 * 150));
			} else {
				int number = (int) ((height - 800) / 200.0 * 255);
				this.color = new Color(100 + (int)(number / 255.0 * 155), 50 + (int)(number / 255.0 * 205), number);
			}
		}
		this.setPreferredSize(new Dimension(width, width));
	}
	
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		
		if(colorAfterClick != null) g.setColor(colorAfterClick);
		else g.setColor(color);
		g.fillRect(0, 0, width, width);
		
		g.setColor(oldColor);
	}
	
	public void mark(Color colorAfterClick) {
		this.colorAfterClick = colorAfterClick;
	}
}
