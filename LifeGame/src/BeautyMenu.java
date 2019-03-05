import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class BeautyMenu extends JFrame {
	
    private JPanel topPanel = new JPanel();
    
    private BufferedImage image = new BufferedImage(450, 500,BufferedImage.TYPE_INT_ARGB);
    
    private Algorithm gamePanel = new Algorithm(image, 30);

	public BeautyMenu() {
		initUI();
	}

	private void initUI() {
		//gamePanel.setBounds(0, 0, 300, 300);
		
		Container GAME = getContentPane();
		GAME.setLayout(new BorderLayout());
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JMenuBar menu = createMenuBar();
		setJMenuBar(menu);
		JToolBar tool = createToolbar();
		topPanel.add(tool, BorderLayout.NORTH);
		

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        GAME.add(mainPanel);
        
		setSize(500, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private JToolBar createToolbar() {

		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		toolbar.setLayout(new GridLayout(1,5));
		

		ImageIcon iconOptions = new ImageIcon(new ImageIcon("resources/optionsBut.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon iconXor = new ImageIcon(new ImageIcon("resources/xorBut.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon iconReplace = new ImageIcon(new ImageIcon("resources/replaceBut.jpg").getImage().getScaledInstance(60, 38, Image.SCALE_DEFAULT));
		ImageIcon iconClear = new ImageIcon(new ImageIcon("resources/clearBut.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		ImageIcon iconAbout = new ImageIcon(new ImageIcon("resources/aboutBut.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		
		JButton runButton = new JButton();
		runButton.setToolTipText("Run next");
		Map<Location, Pixel> centersP = new HashMap<Location, Pixel>();
		centersP.put(new Location(2,2), new Pixel (2,2));
		centersP.put(new Location(2,3), new Pixel (2,3));
		centersP.put(new Location(2,1), new Pixel (2,1));
		centersP.put(new Location(0,0), new Pixel (0,0));
		gamePanel.setCentersP(centersP);
		gamePanel.letsPlay();
		runButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.removeAll();
				
				repaint();
				/*try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				gamePanel.nextGeneration();
				gamePanel.repaint();
				getContentPane().revalidate();
			}
		});
		toolbar.add(runButton);
		JButton optionsButton = new JButton(iconOptions);
		optionsButton.setToolTipText("Options");
		toolbar.add(optionsButton);
		JButton xorButton = new JButton(iconXor);
		xorButton.setToolTipText("XOR");
		toolbar.add(xorButton);
		JButton replaceButton = new JButton(iconReplace);
		replaceButton.setToolTipText("Replace");
		toolbar.add(replaceButton);
		JButton clearButton = new JButton(iconClear);
		clearButton.setToolTipText("Clear");
		toolbar.add(clearButton);
		JButton aboutButton = new JButton(iconAbout);
		aboutButton.setToolTipText("About creator");
		aboutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame windowAboutCreator = new JFrame("About an author");
				windowAboutCreator.setSize(new Dimension(450, 350));
				windowAboutCreator.setLocationRelativeTo(null);
				windowAboutCreator.setVisible(true);
				windowAboutCreator.setLayout(new BorderLayout());
				String text = "Hey! I'm Mary and I did it for u ;)";
				ImageIcon photoAuthor = new ImageIcon(new ImageIcon("resources/photo.jpg").getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
				JLabel labelText = new JLabel(text);
				JLabel labelPhoto = new JLabel(photoAuthor);		
				windowAboutCreator.add(labelPhoto, BorderLayout.WEST);
				windowAboutCreator.add(labelText, BorderLayout.EAST);
			}
		});
		toolbar.add(aboutButton);

		return toolbar;
	}

	private JMenuBar createMenuBar() {

		JMenuBar menubar = new JMenuBar();

		ImageIcon iconNew = new ImageIcon(new ImageIcon("resources/new.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		ImageIcon iconLoad = new ImageIcon(new ImageIcon("resources/load.jpg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		ImageIcon iconSave = new ImageIcon(new ImageIcon("resources/save.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		ImageIcon iconExit = new ImageIcon(new ImageIcon("resources/exit.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));

		{ // File menu
			JMenu fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);

			JMenuItem newMenuItem = new JMenuItem("New", iconNew);
			newMenuItem.setMnemonic(KeyEvent.VK_N);
			newMenuItem.setToolTipText("New game or Restart");
			newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));

			JMenuItem loadMenuItem = new JMenuItem("Load", iconLoad);
			loadMenuItem.setMnemonic(KeyEvent.VK_L);
			loadMenuItem.setToolTipText("Load ur game");
			loadMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F6));

			JMenuItem saveMenuItem = new JMenuItem("Save", iconSave);
			saveMenuItem.setMnemonic(KeyEvent.VK_S);
			saveMenuItem.setToolTipText("Save ur game");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F5));

			JMenuItem exitMenuItem = new JMenuItem("Exit", iconExit);
			exitMenuItem.setMnemonic(KeyEvent.VK_E);
			exitMenuItem.setToolTipText("Exit");
			exitMenuItem.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_ESCAPE));

			exitMenuItem.addActionListener((event) -> System.exit(0));

			fileMenu.add(newMenuItem);
			fileMenu.add(loadMenuItem);
			fileMenu.add(saveMenuItem);
			fileMenu.addSeparator();
			fileMenu.add(exitMenuItem);

			menubar.add(fileMenu);
		}
		{ // Edit menu
			JMenu editMenu = new JMenu("Edit");
			Icon iconMode = new ImageIcon("src/resources/mode.png");
			JMenu modeMenuItem = new JMenu("Mode");
			modeMenuItem.setToolTipText("Mode ur game");
			JRadioButton modeXOR = new JRadioButton("XOR");
			modeMenuItem.add(modeXOR);
			JRadioButton modeReplace = new JRadioButton("Replace");
			modeReplace.setSelected(true);
			modeMenuItem.add(modeReplace);
			Icon iconOptions = new ImageIcon(new ImageIcon("resources/options.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
			JMenuItem optionsMenuItem = new JMenuItem("Options", iconOptions);
			optionsMenuItem.setToolTipText("Mode ur game");
			editMenu.add(modeMenuItem);
			editMenu.add(optionsMenuItem);
			menubar.add(editMenu);
		}
		{ // View Menu
			JMenu viewMenu = new JMenu("View");

			menubar.add(viewMenu);
		}
		{ // About menu
			JMenu aboutMenu = new JMenu("About");
			Icon iconMode = null;
			JMenuItem aboutGameMenuItem = new JMenuItem("About game", iconMode);
			aboutGameMenuItem.setToolTipText("Read some about game");
			aboutGameMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame windowAboutGame = new JFrame("About an author");
					windowAboutGame.setSize(new Dimension(450, 350));
					windowAboutGame.setLocationRelativeTo(null);
					windowAboutGame.setVisible(true);
					windowAboutGame.setLayout(new BorderLayout());
					String text = "Правила игры:";
					JLabel labelText = new JLabel(text);
					windowAboutGame.add(new JLabel("                                                          "), BorderLayout.WEST);  //dont watch on this
					windowAboutGame.add(labelText, BorderLayout.CENTER);
				}
			});
			Icon iconOptions = null;
			JMenuItem aboutCreatorMenuItem = new JMenuItem("About creator", iconOptions);
			aboutCreatorMenuItem.setToolTipText("Just look at her photo, lul");
			aboutCreatorMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFrame windowAboutCreator = new JFrame("About an author");
					windowAboutCreator.setSize(new Dimension(450, 350));
					windowAboutCreator.setLocationRelativeTo(null);
					windowAboutCreator.setVisible(true);
					windowAboutCreator.setLayout(new BorderLayout());
					String text = "Hey! I'm Mary and I did it for u ;)";
					ImageIcon photoAuthor = new ImageIcon(new ImageIcon("resources/photo.jpg").getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
					JLabel labelText = new JLabel(text);
					JLabel labelPhoto = new JLabel(photoAuthor);		
					windowAboutCreator.add(labelPhoto, BorderLayout.WEST);
					windowAboutCreator.add(labelText, BorderLayout.EAST);
				}
			});

			aboutMenu.add(aboutGameMenuItem);
			aboutMenu.add(aboutCreatorMenuItem);
			//menubar.add(Box.createHorizontalGlue());
			menubar.add(aboutMenu);
		}
		return menubar;
	}

	public void letsPlay() {
		gamePanel.getCenters();
		
	}
}
