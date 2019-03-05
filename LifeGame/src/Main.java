import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			BeautyMenu bm = new BeautyMenu();
			bm.setVisible(true);
		});
		
		//bm.getGamePanel().get
	}

}
