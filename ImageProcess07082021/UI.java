import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UI extends JFrame {
	Graphics myGraphics;
	BufferedImage picBufferedImage;
	JButton redoButton;
	static ArrayList<BufferedImage> picBufferedImageArray = new ArrayList<BufferedImage>();
	static boolean isRedo = false;
	static boolean isPaintAll = false;

	public UI() {
		// set the jFrame
		this.setSize(1280, 720);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		// set two panels
		JPanel optionPanel = new JPanel();
		JPanel picPanel = new JPanel();
		optionPanel.setBackground(Color.LIGHT_GRAY);
		this.add(optionPanel, BorderLayout.EAST);
		Dimension optionPanelDIm = new Dimension(200, 0);
		optionPanel.setPreferredSize(optionPanelDIm);
		this.add(picPanel);

		// load image to picPanel
		ImageProcess myImage = new ImageProcess(this);
		picBufferedImage = new BufferedImage(myImage.pixelArray[0].length, myImage.pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		myGraphics = picBufferedImage.getGraphics();
		myImage.myGraphics = myGraphics;
		myImage.loadImage();

		// set optionPanel
		PicMouse mouse = new PicMouse(this, myImage);
		picPanel.addMouseListener(mouse);

		String[] buttonString = { "Default", "Mosaic", "Grey Scale", "Hand Paint", "Contour", "Redo", "Zoom In" };
		Dimension buttonDIm = new Dimension(120, 32);
		for (int i = 0; i < buttonString.length; i++) {
			JButton button = new JButton(buttonString[i]);
			optionPanel.add(button);
			button.setPreferredSize(buttonDIm);
			if (i == 5) {
				redoButton = button;
				redoButton.setEnabled(false);
			}
			if (buttonString[i].equals("Zoom In")) {
				button.setEnabled(false);
			}
			button.addActionListener(mouse);

		}

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(picBufferedImage, 0, 0, 1080, this.getHeight(), null);

	}

	public static void main(String[] args) {
		UI myUI = new UI();
	}
}
