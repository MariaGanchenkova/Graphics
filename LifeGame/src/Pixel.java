import java.awt.Color;

public class Pixel {
		private int x;
		private int y;
		private Color color;
		
		public Pixel(int x, int y) {
			this.x = x;
			this.y = y;
			this.color = Color.WHITE;
		}
		
		public Pixel(int x, int y, int color) {
			this.x = x;
			this.y = y;
			this.color = new Color(color, true);
		}

		void setPixel(int x, int y, int color) {
			this.setPexelX(x);
			this.setPexelY(y);
			this.setPexelColor(color);
		}

		public int getPexelX() {
			return x;
		}

		public void setPexelX(int x) {
			this.x = x;
		}

		public int getPexelY() {
			return y;
		}

		public void setPexelY(int y) {
			this.y = y;
		}

		public Color getPexelColor() {
			return color;
		}

		public void setPexelColor(int color) {
			this.color = new Color(color, true);
		}
	}