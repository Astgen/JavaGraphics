package io.ninelives.blog.alex.graphics.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bitmap {
	private int[] pixels;
	
	private int width;
	private int height;
	
	public Bitmap(){
		width = 1;
		height = 1;
		pixels = new int[1];
	}
	
	public Bitmap(String path){
		File f = new File(path);
		try {
			BufferedImage img = ImageIO.read(f);
			width = img.getWidth();
			height = img.getHeight();
			pixels = img.getRGB(0, 0, width, height, null, 0, width);
		} catch (IOException e) {
			System.out.println("Failed to read file: " + path);
			e.printStackTrace();
		}
	}
	
	private boolean withinBounds(int x, int y){
		if(x < width && x > -1){
			if(y < height && y > -1){
				return true;
			}
		}
		return false;
	}
	
	public int getRGB(int x, int y){
		if(withinBounds(x,y)){
			//This will strip the Alpha channel from the pixel
			return (pixels[y * width + x] & 0xFFFFFF);
		}
		return -1;
	}
	
	public int getARGB(int x, int y){
		if(withinBounds(x,y)){
			return pixels[y * width + x];
		}
		return -1;
	}
	
	public int getAlpha(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 24 & 0xFF);
		}
		return -1;
	}
	
	public int getRed(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 16 & 0xFF);
		}
		return -1;
	}
	
	public int getGreen(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 8 & 0xFF);
		}
		return -1;
	}
	
	public int getBlue(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] & 0xFF);
		}
		return -1;
	}
	
	/**
	 * Set the pixels Alpha channel to 'alpha'
	 * @param x x Coord of pixel
	 * @param y y Coord of pixel
	 * @param alpha New alpha value [0 - 255]
	 * @return New pixel ARGB value
	 */
	public int setAlpha(int x, int y, int alpha){
		if(alpha < 0 || alpha > 255){
			System.out.println("New red value [" + alpha + "] out of bounds.");
			return -1;
		}
		if(withinBounds(x,y)){
			int col = pixels[y * width + x] & 0x00FFFFFF;
			if(alpha < 128){
				col += alpha << 24;
			}else{
				col -= (128 - (alpha - 128)) << 24;
			}
			pixels[y * width + x] = col;
			return col;
		}
		System.out.println("X,Y coordinate [" + x + "," + y + "] out of bounds.");
		return -1;
	}
	
	/**
	 * Set the pixels Red channel to 'red'
	 * @param x x Coord of pixel
	 * @param y y Coord of pixel
	 * @param red New red value [0 - 255]
	 * @return New pixel ARGB value
	 */
	public int setRed(int x, int y, int red){
		if(red < 0 || red > 255){
			System.out.println("New red value [" + red + "] out of bounds.");
			return -1;
		}
		if(withinBounds(x,y)){
			int col = pixels[y * width + x];
			col -= getRed(x,y) * Math.pow(256, 2);
			col += red << 16;
			pixels[y * width + x] = col;
			return col;
		}
		System.out.println("X,Y coordinate [" + x + "," + y + "] out of bounds.");
		return -1;
	}
	
	/**
	 * Set the pixels Green channel to 'green'
	 * @param x x Coord of pixel
	 * @param y y Coord of pixel
	 * @param green New green value [0 - 255]
	 * @return New pixel ARGB value
	 */
	public int setGreen(int x, int y, int green){

		if(green < 0 || green > 255){
			System.out.println("New green value [" + green + "] out of bounds.");
			return -1;
		}
		if(withinBounds(x,y)){
			int col = pixels[y * width + x];
			col -= getGreen(x,y) * 256;
			col += green << 8;
			pixels[y * width + x] = col;
			return col;
		}
		System.out.println("X,Y coordinate [" + x + "," + y + "] out of bounds.");
		return -1;
	}
	
	/**
	 * Set the pixels Blue channel to 'blue'
	 * @param x x Coord of pixel
	 * @param y y Coord of pixel
	 * @param blue New blue value [0 - 255]
	 * @return New pixel ARGB value
	 */
	public int setBlue(int x, int y, int blue){

		if(blue < 0 || blue > 255){
			System.out.println("New blue value [" + blue + "] out of bounds.");
			return -1;
		}
		if(withinBounds(x,y)){
			int col = pixels[y * width + x];
			col -= getBlue(x,y);
			col += blue;
			pixels[y * width + x] = col;
			return col;
		}
		System.out.println("X,Y coordinate [" + x + "," + y + "] out of bounds.");
		return -1;
	}
	
	/**
	 * Set the 3 RGB channels of the pixel at once
	 * @param x
	 * @param y
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public int setRGB(int x, int y, int red, int green, int blue){
		return setARGB(x,y,255,red,blue,green);
	}
	
	/**
	 * Set all 4 channels of the pixel at once
	 * @param x
	 * @param y
	 * @param alpha
	 * @param red
	 * @param green
	 * @param blue
	 * @return
	 */
	public int setARGB(int x, int y, int alpha, int red, int green, int blue){
		if(alpha < 0 || alpha > 255){
			System.out.println("New red value [" + alpha + "] out of bounds.");
			return -1;
		}
		if(red < 0 || red > 255){
			System.out.println("New red value [" + red + "] out of bounds.");
			return -1;
		}
		if(green < 0 || green > 255){
			System.out.println("New green value [" + green + "] out of bounds.");
			return -1;
		}
		if(blue < 0 || blue > 255){
			System.out.println("New blue value [" + blue + "] out of bounds.");
			return -1;
		}
		if(withinBounds(x,y)){
			int col = (red << 16) + (green << 8) + (blue);
			if(alpha < 128){
				col += alpha << 24;
			}else{
				col -= (128 - (alpha - 128)) << 24;
			}
			pixels[y * width + x] = col;
			return col;
		}
		System.out.println("X,Y coordinate [" + x + "," + y + "] out of bounds.");
		return -1;
	}
	
}