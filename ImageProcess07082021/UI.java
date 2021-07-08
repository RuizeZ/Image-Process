import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import BoardGame605.BoardPanel;

public class UI extends JFrame {
	Graphics myGraphics;
	static BufferedImage picBufferedImage;

	public UI() {
		this.setSize(1280, 720);
		picBufferedImage = new BufferedImage(1070, this.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout border = new BorderLayout();
		this.setLayout(border);
		JPanel optionPanel = new JPanel();
		JPanel picPanel = new JPanel();
		optionPanel.setBackground(Color.LIGHT_GRAY);
		this.add(optionPanel, border.EAST);
		this.add(picPanel);
		Dimension optionPanelDIm = new Dimension(200, 0);
		optionPanel.setPreferredSize(optionPanelDIm);

		JButton mosaicButton = new JButton("Mosaic");
		JButton defaultButton = new JButton("Default");
		optionPanel.add(defaultButton);
		optionPanel.add(mosaicButton);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setVisible(true);

		myGraphics = picBufferedImage.getGraphics();
		ImageProcess myImage = new ImageProcess(myGraphics);
		
		
		myGraphics = picPanel.getGraphics();
		myGraphics.drawImage(picBufferedImage, 0, 0, 1080, this.getHeight(), null);
		PicMouse mouse = new PicMouse(this, myImage, mosaicButton, defaultButton);
		mosaicButton.addActionListener(mouse);
		defaultButton.addActionListener(mouse);
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
