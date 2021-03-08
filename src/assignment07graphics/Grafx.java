package assignment07graphics;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Grafx {

	public static void main(String... args) throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		//fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"));
		fileChooser.setCurrentDirectory(new File("D:\\Photos\\Java"));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			String filename = selectedFile.getName();
			BufferedImage image = ImageIO.read(selectedFile);
			BufferedImage imageCopy = image;
			//invertColors(image, filename);
			pixelateImage(imageCopy, filename);
		}
	}

	public static void invertColors(BufferedImage image, String filename) throws Exception {
		BufferedImage invertedImage = image;
		int w = image.getWidth();
		int h = image.getHeight();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color c = new Color(image.getRGB(i, j));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				Color newColor = new Color(255 - red, 255 - green, 255 - blue);
				invertedImage.setRGB(i, j, newColor.getRGB());
			}
		}
		saveImage(invertedImage, filename, "Inverted");
	}

	public static void pixelateImage(BufferedImage image, String filename) throws Exception {
		Scanner kbd = new Scanner(System.in);
		BufferedImage PixelatedImage = image;
		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("Image width: " + w);
		System.out.println("Image height: " + h);
		Color c = new Color(0,0,0);

		System.out.print("Select which pixelation size you want: ");
		int pix = kbd.nextInt();
		for (int i = 0; i <= w - pix ; i+=pix) {
			for (int j = 0; j <= h - pix; j+=pix) {
				c = new Color((image.getRGB(i, j)));
			
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				Color newColor = new Color(red, green, blue);
				
				for (int k = 0; k < pix; k++) {
					for (int m = 0; m < pix; m++) {
						PixelatedImage.setRGB(i+k, j+m, newColor.getRGB());
					}
				}
			}
		}
		
		if (!(w % pix == 0)) {
			System.out.println("Pixelated square ("+ pix +"x"+pix+") does not match width of image ("+w+" pixels)");
			System.out.println("\tBlurring the edges of image..");
			int unchangedLength = w % pix;
			int unchangedLengthPixelNumber = w - unchangedLength;
			for (int i = unchangedLengthPixelNumber; i < w ; i+=unchangedLength) {
				for (int j = 0; j <= h - pix; j+=pix) {
					c = new Color((image.getRGB(i, j)));
					int red = c.getRed();
					int green = c.getGreen();
					int blue = c.getBlue();
					Color newColor = new Color(red, green, blue);
					
					for (int k = 0; k < unchangedLength; k++) {
						for (int m = 0; m < pix; m++) {
							PixelatedImage.setRGB(i+k, j+m, newColor.getRGB());
						}
					}		
				}
			}
		}
		else {
			System.out.println("Pixelated square ("+ pix +"x"+pix+") does match width of image ("+w+" pixels)");
		}
		
		if (!(h % pix == 0)) {
			System.out.println("Pixelated square ("+ pix +"x"+pix+") does not match height of image ("+h+" pixels)");
			System.out.println("\tBlurring the edges of image..");
			int unchangedLength = h % pix; 
			int unchangedLengthPixelNumber = h - unchangedLength;
			for (int i = 0; i < w - pix; i+=pix) {
				for (int j = unchangedLengthPixelNumber; j < h; j+=unchangedLength) {
					c = new Color((image.getRGB(i, j)));
					int red = c.getRed();
					int green = c.getGreen();
					int blue = c.getBlue();
					Color newColor = new Color(red, green, blue);
					
					for (int k = 0; k < pix; k++) {
						for (int m = 0; m < unchangedLength; m++) {
							PixelatedImage.setRGB(i+k, j+m, newColor.getRGB());
						}
					}
						
				}
			}
		} else {
			System.out.println("Pixelated square ("+ pix +"x"+pix+") does match height of image ("+h+" pixels)");
		}
		String pixelatedSize = pix + "pixels";
		saveImage(PixelatedImage, filename, "Pixelated",pixelatedSize);
		kbd.close();
	}
	
	public static void saveImage(BufferedImage image, String filename, String FilenameModifier) throws Exception {
		String basename = filename.split("\\.")[0];
		
		String fileOutput = "D:\\Photos\\Java\\"+basename+FilenameModifier+".jpg";
		ImageIO.write(image, "jpg", new File(fileOutput));
		System.out.println("File saved: " + fileOutput);
	}
	
	public static void saveImage(BufferedImage image, String filename, String FilenameModifier, String SizeModifier) throws Exception {
		String basename = filename.split("\\.")[0];
		
		String fileOutput = "D:\\Photos\\Java\\"+basename+FilenameModifier+"-size"+SizeModifier+".jpg";
		ImageIO.write(image, "jpg", new File(fileOutput));
		System.out.println("File saved: " + fileOutput);
	}
	
	public static void saveImage(BufferedImage image) throws Exception {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File("D:\\Photos"));
		int result = fileChooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			String location = f.getAbsolutePath() + ".jpg";
			ImageIO.write(image, "jpg", new File(location));
			System.out.println("Saved file to: " + location);
		}
	}
	
}
