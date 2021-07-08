import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcess {
	Graphics myGraphics;
	int[][] pixelArray;

	public ImageProcess(Graphics myGraphics) {
		this.myGraphics = myGraphics;
		loadImage();
	}

	public void loadImage() {
		pixelArray = getImagePixel();
		for (int i = 0; i < pixelArray.length; i += 1) {
			for (int j = 0; j < pixelArray[0].length; j += 1) {
				myGraphics.setColor(new Color(pixelArray[i][j]));
				myGraphics.fillRect(j, i, 1, 1);
			}
		}

	}

	private int[][] getImagePixel() {
		BufferedImage myBufferedImage = null;
		File file = new File("pic\\pic1.jpg");
		try {
			myBufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int x = myBufferedImage.getWidth();
		int y = myBufferedImage.getHeight();
		int[][] pixelArray = new int[y][x];
		for (int i = 0; i < y; i++) {
			for (int j = 0; j < x; j++) {
				pixelArray[i][j] = myBufferedImage.getRGB(j, i);
			}
		}
		return pixelArray;
	}

	public void mosaicPic() {
		for (int i = 0; i < pixelArray.length; i += 10) {
			for (int j = 0; j < pixelArray[0].length; j += 10) {
				myGraphics.setColor(new Color(pixelArray[i][j]));
				myGraphics.fillRect(j, i, 10, 10);
			}
		}
	}

}
