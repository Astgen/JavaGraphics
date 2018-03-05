package io.astgen.graphics.gfx;

import java.awt.Graphics2D;

public class Sprite {
	Bitmap bitmap;
	int width, spriteWidth;
	int height, spriteHeight;
	int frame = 0;
	int maxFrame;
	
	/**
	 * Create a single Sprite from a Bitmap
	 * @param bmp Bitmap to create sprite from
	 */
	public Sprite(Bitmap bmp){
		bitmap = bmp;
		spriteWidth = bmp.getWidth();
		spriteHeight = bmp.getHeight();
		width = 1;
		height = 1;
		maxFrame = 0;
	}
	
	/**
	 * Creates a sprite sheet
	 * @param bmp Sheet Bitmap
	 * @param height Height of each cell
	 * @param width Width of each cell
	 */
	public Sprite(Bitmap bmp, int wid, int hei){
		bitmap = bmp;
		width = bmp.getWidth() / wid;
		height = bmp.getHeight() / hei;
		spriteWidth = wid;
		spriteHeight = hei;
		maxFrame = (height*width) - 1;
	}
	
	/**
	 * Creates a sprite sheet
	 * @param bmp Sheet Bitmap
	 * @param height Height of each cell
	 * @param width Width of each cell
	 */
	public Sprite(Bitmap bmp, int wid){
		bitmap = bmp;
		width = bmp.getWidth() / wid;
		height = bmp.getHeight();
		spriteWidth = wid;
		spriteHeight = 1;
		maxFrame = width - 1;
	}
	
	/**
	 * Draw the Sprite to the screen
	 * @param dx Starting X Pixel
	 * @param dy Starting Y Pixel
	 * @param dw Final width of image
	 * @param dh Final height of image
	 * @param rotation Rotation of image
	 * @param frame Current frame to be drawn
	 * @param gfx What the Bitmap is drawn to
	 */
	public void draw(int dx, int dy, int dw, int dh, float rotation, int frame, Graphics2D gfx){
		int xCell = frame % width;
		int yCell = frame / width;
		
		if(frame > maxFrame){
			frame = maxFrame;
			System.out.println("Frame greater than max allowed frame" + "(" + maxFrame + ").");
		}
		
		if(frame < 0){
			frame = 0;
			System.out.println("Frame cannot be less than 0.");
		}
		
		bitmap.draw(dx, dy, dw, dh, xCell * spriteWidth, yCell * spriteHeight, spriteWidth, spriteHeight, rotation, gfx);
	}
	
	/**
	 * Draw the Sprite to the screen
	 * @param x X position of top left
	 * @param y Y position of top left
	 * @param frame Current frame to be drawnO
	 * @param gfx Graphics object to draw to
	 */
	public void draw(int x, int y, int frame, Graphics2D gfx){
		draw(x,y,spriteWidth,spriteHeight,0,frame,gfx);
	}
	
	/**
	 * Draw the Sprite to the screen
	 * @param x X position of the top left
	 * @param y Y position of the top left
	 * @param wid Desired width of the image
	 * @param hei Desired height of the image
	 * @param frame Current frame to be drawn
	 * @param gfx Graphics object to draw to
	 */
	public void draw(int x, int y, int wid, int hei, int frame, Graphics2D gfx){
		draw(x,y,wid,hei,0,frame,gfx);
	}
}
