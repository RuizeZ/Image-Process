import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class PicMouse implements ActionListener, MouseListener {
	private ImageProcess myImage;
	private UI myUI;

	public PicMouse(UI myUI, ImageProcess myImage) {
		this.myUI = myUI;
		this.myImage = myImage;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		myUI.redoButton.setEnabled(true);
		if (e.getActionCommand() == "Mosaic") {
			myImage.mosaicPic();
			myUI.repaint();
		} else if (e.getActionCommand() == "Default") {
			myImage.loadImage();
			myUI.repaint();
		} else if (e.getActionCommand() == "Grey Scale") {
			myImage.greyScalePic();
			myUI.repaint();
		} else if (e.getActionCommand() == "Hand Paint") {
			myImage.handPaintPic();
			myUI.repaint();
		} else if (e.getActionCommand() == "Contour") {
			myImage.ContourPic();
			myUI.repaint();
		} else if (e.getActionCommand() == "Redo") {
			myImage.RedoPic();
			myUI.repaint();
		}else if (e.getActionCommand() == "Paint All") {
			System.out.println("Paint All");
			UI.isPaintAll = true;
			myUI.repaint();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
			int x = e.getX();
			int y = e.getY();
			myImage.zoomInPic(x, y);
			myUI.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
