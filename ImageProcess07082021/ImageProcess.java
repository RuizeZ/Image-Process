import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcess {
	Graphics myGraphics;
	UI myUI;
	int[][] pixelArray;
	BufferedImage myBufferedImage;

	public ImageProcess(UI myUI) {
		getImagePixel();
		this.myUI = myUI;
	}

	public void loadImage() {
		pixelArray = getImagePixel();
		BufferedImage tempBufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < pixelArray.length; i += 1) {
			for (int j = 0; j < pixelArray[0].length; j += 1) {
				myGraphics.setColor(new Color(pixelArray[i][j]));
				myGraphics.fillRect(j, i, 1, 1);
				tempBufferedImage.setRGB(j, i, myUI.picBufferedImage.getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.add(tempBufferedImage);

	}

	private int[][] getImagePixel() {
		File file = new File("pic\\pic1.jpg");
		try {
			myBufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int x = myBufferedImage.getWidth();
		int y = myBufferedImage.getHeight();
		pixelArray = new int[y][x];
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				pixelArray[i][j] = myBufferedImage.getRGB(j, i);
			}
		}
		return pixelArray;
	}

	public void mosaicPic() {
		BufferedImage tempBufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		System.out.println("mosaic");
		System.out.println(myUI.picBufferedImage);
		for (int i = 0; i < pixelArray.length; i += 10) {
			for (int j = 0; j < pixelArray[0].length; j += 10) {
				myGraphics.setColor(new Color(myUI.picBufferedImage.getRGB(j, i)));
				myGraphics.fillRect(j, i, 10, 10);
			}
		}
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length; j++) {
				tempBufferedImage.setRGB(j, i, myUI.picBufferedImage.getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.add(tempBufferedImage);
	}

	/**
	 * grey scale picture
	 */
	public void greyScalePic() {
		BufferedImage tempBufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length; j++) {
				int red = myUI.picBufferedImage.getRGB(j, i) >> 16 & 0xFF;
				int green = myUI.picBufferedImage.getRGB(j, i) >> 8 & 0xFF;
				int blue = myUI.picBufferedImage.getRGB(j, i) & 0xFF;
				int gray = (int) (red + green + blue) / 3;
				myGraphics.setColor(new Color(gray, gray, gray));
				myGraphics.fillRect(j, i, 1, 1);
				tempBufferedImage.setRGB(j, i, myUI.picBufferedImage.getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.add(tempBufferedImage);

	}

	/**
	 * hand paint
	 */
	public void handPaintPic() {
		BufferedImage tempBufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length; j++) {
				int red = myUI.picBufferedImage.getRGB(j, i) >> 16 & 0xFF;
				int green = myUI.picBufferedImage.getRGB(j, i) >> 8 & 0xFF;
				int blue = myUI.picBufferedImage.getRGB(j, i) & 0xFF;
				int gray = (int) (red + green + blue) / 3;
				if (gray < 80) {
					myGraphics.setColor(Color.DARK_GRAY);
				} else {
					myGraphics.setColor(Color.WHITE);
				}
				myGraphics.fillRect(j, i, 1, 1);
				tempBufferedImage.setRGB(j, i, myUI.picBufferedImage.getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.add(tempBufferedImage);
	}

	/**
	 * ContourPic
	 */
	public void ContourPic() {
		BufferedImage tempBufferedImage = new BufferedImage(pixelArray[0].length, pixelArray.length,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < pixelArray.length - 2; i++) {
			for (int j = 0; j < pixelArray[0].length - 2; j++) {
				int red = myUI.picBufferedImage.getRGB(j, i) >> 16 & 0xFF;
				int green = myUI.picBufferedImage.getRGB(j, i) >> 8 & 0xFF;
				int blue = myUI.picBufferedImage.getRGB(j, i) & 0xFF;
				int redNext = myUI.picBufferedImage.getRGB(j + 2, i + 2) >> 16 & 0xFF;
				int greenNext = myUI.picBufferedImage.getRGB(j + 2, i + 2) >> 8 & 0xFF;
				int blueNext = myUI.picBufferedImage.getRGB(j + 2, i + 2) & 0xFF;

				int gray = (int) (red + green + blue) / 3;
				int grayNext = (int) (redNext + greenNext + blueNext) / 3;

				if (Math.abs(grayNext - gray) > 25) {
					myGraphics.setColor(Color.BLACK);
				} else {
					myGraphics.setColor(Color.WHITE);
				}
				myGraphics.fillRect(j, i, 1, 1);
				tempBufferedImage.setRGB(j, i, myUI.picBufferedImage.getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.add(tempBufferedImage);

	}

	public void zoomInPic(int x, int y) {

		myGraphics.setColor(Color.BLACK);
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		myGraphics.fillRect(x, y, 100, 100);

	}

	public void RedoPic() {
		int removeIndex = UI.picBufferedImageArray.size() - 1;
		int targetIndex = UI.picBufferedImageArray.size() - 2;

		for (int i = 0; i < pixelArray.length; i++) {
			for (int j = 0; j < pixelArray[0].length; j++) {
				myUI.picBufferedImage.setRGB(j, i, UI.picBufferedImageArray.get(targetIndex).getRGB(j, i));
			}
		}
		UI.picBufferedImageArray.remove(removeIndex);
		if (UI.picBufferedImageArray.size() == 1) {
			myUI.redoButton.setEnabled(false);
		}
	}

}
