import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PicMouse implements ActionListener {
	private ImageProcess myImage;
	private JButton mosaicButton;
	private JButton defaultButton;
	private UI myUI;

	public PicMouse(UI myUI, ImageProcess myImage, JButton mosaicButton, JButton defaultButton) {
		this.myUI = myUI;
		this.myImage = myImage;
		this.defaultButton = defaultButton;
		this.mosaicButton = mosaicButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mosaicButton) {
			System.out.print("in mosaicButton");
			myImage.mosaicPic();
			myUI.repaint();
		} else if (e.getSource() == defaultButton) {
			myImage.loadImage();
			myUI.repaint();
		}

	}
}
