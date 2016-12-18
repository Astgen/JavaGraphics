package io.ninelives.blog.alex.graphics.gfx;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
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
	
	/**
	 * Creates a Bitmap from an Image at the given path
	 * @param path Path to Image
	 */
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
	
	private Bitmap(int wid, int hei, int[] pix){
		width = wid;
		height = hei;
		pixels = pix.clone();
	}
	
	public Bitmap clone(){
		return new Bitmap(width,height,pixels);
	}
	
	/**
	 * Checks whether the x,y coordinate is within width,height.
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean withinBounds(int x, int y){
		if(x < width && x > -1){
			if(y < height && y > -1){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the INT_RGB of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getRGB(int x, int y){
		if(withinBounds(x,y)){
			//This will strip the Alpha channel from the pixel
			return (pixels[y * width + x] & 0xFFFFFF);
		}
		return -1;
	}
	
	/**
	 * Returns the INT_ARGB of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getARGB(int x, int y){
		if(withinBounds(x,y)){
			return pixels[y * width + x];
		}
		return -1;
	}
	
	/**
	 * Returns the Alpha channel 0xFF000000 of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getAlpha(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 24 & 0xFF);
		}
		return -1;
	}

	/**
	 * Returns the Red channel 0x00FF0000 of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getRed(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 16 & 0xFF);
		}
		return -1;
	}

	/**
	 * Returns the Green channel 0x0000FF00 of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
	public int getGreen(int x, int y){
		if(withinBounds(x,y)){
			return (pixels[y * width + x] >> 8 & 0xFF);
		}
		return -1;
	}

	/**
	 * Returns the Blue channel 0x000000FF of the pixel at x,y
	 * @param x
	 * @param y
	 * @return
	 */
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
		return setARGB(x,y,getAlpha(x,y),red,green,blue);
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
		if(alpha < 0){
			alpha = 0;
		}else if(alpha > 255){
			alpha = 255;
		}
		if(red < 0){
			red = 0;
		}else if(red > 255){
			red = 255;
		}
		if(green < 0){
			green = 0;
		}else if(green > 255){
			green = 255;
		}
		if(blue < 0){
			blue = 0;
		}else if(blue > 255){
			blue = 255;
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
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	/**
	 * Draw the Bitmap to the screen
	 * @param dx Starting X Pixel
	 * @param dy Starting Y Pixel
	 * @param dw Final width of image
	 * @param dh Final height of image
	 * @param sx Source XPixel
	 * @param sy Source YPixel
	 * @param sw Source Width
	 * @param sh Source Height
	 * @param rotation Rotation of image
	 * @param gfx What the Bitmap is drawn to
	 */
	public void draw(int dx, int dy, int dw, int dh, int sx, int sy, int sw, int sh, float rotation, Graphics2D gfx){
		if(withinBounds(sx,sy) &&
		   withinBounds(sx+sw-1,sy+sh-1)){
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(-rotation), dx + (dh/2), dy + (dh/2));
			gfx.setTransform(at);
			
			int[] newPixels = new int[dw*dh];

			float widthScale = (float)sw/dw;
			float heightScale = (float)sh/dh;
			
			for(int j = 0; j < dh - 1; j++){
				for(int k = 0; k < dw - 1; k++){					
					int pixX = (int)Math.round(k * widthScale) + sx;
					int pixY = (int)Math.round(j * heightScale) + sy;
					
					newPixels[(j * dw) + k] = pixels[(pixY * width) + pixX];
				}
			}
			BufferedImage img = new BufferedImage(dw,dh,BufferedImage.TYPE_INT_ARGB);
			img.setRGB(0, 0, dw, dh, newPixels, 0, dw);
			gfx.drawImage(img, dx, dy, dw, dh, null);
			
			gfx.setTransform(new AffineTransform());
		}else{
			System.out.println("Source image not within bounds");
		}
	}
	
	public void draw(int x, int y, Graphics2D gfx){
		draw(x,y,width,height,0,0,width,height,0,gfx);
	}
	
	public void draw(int x, int y, int wid, int hei, Graphics2D gfx){
		
	}

	public Bitmap toGreyscale(){
		Bitmap bmp = clone();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int r = bmp.getRed(x,y);
				int g = bmp.getGreen(x,y);
				int b = bmp.getBlue(x,y);
				
				int lum = (int)((r * 0.21) + (g * 0.72) + (b * 0.07));
				
				bmp.setRGB(x, y, lum, lum, lum);
			}
		}
		return bmp;
	}
	
	public Bitmap toSepia(){
		Bitmap bmp = clone();
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				int r = bmp.getRed(x,y);
				int g = bmp.getGreen(x,y);
				int b = bmp.getBlue(x,y);

				int newRed = (int)((r*0.393) + (g * 0.769) + (b * 0.189));
				int newGreen = (int)((r*0.349) + (g * 0.686) + (b * 0.168));
				int newBlue = (int)((r*0.272) + (g * 0.534) + (b * 0.131));
				
				bmp.setRGB(x, y, newRed, newGreen, newBlue);
			}
		}
		return bmp;
	}
	
	public Bitmap gaussianBlur(){
		double[][] blur = 
		{{0.00000067,0.00002292,0.00019117,0.00038771,0.00019117,0.00002292,0.00000067},
		{0.00002292,0.00078634,0.00655965,0.01330373,0.00655965,0.00078633,0.00002292},
		{0.00019117,0.00655965,0.05472157,0.11098164,0.05472157,0.00655965,0.00019117},
		{0.00038771,0.01330373,0.11098164,0.22508352,0.11098164,0.01330373,0.00038771},
		{0.00019117,0.00655965,0.05472157,0.11098164,0.05472157,0.00655965,0.00019117},
		{0.00002292,0.00078633,0.00655965,0.01330373,0.00655965,0.00078633,0.00002292},
		{0.00000067,0.00002292,0.00019117,0.00038771,0.00019117,0.00002292,0.00000067}};
		
		return applyKernel(blur);
	}
	
	public Bitmap blur(){
		double[][] sharp = 
				{{0.111,0.111,0.111},
				{0.111,0.111,0.111},
				{0.111,0.111,0.111}};
		
		return applyKernel(sharp);
	}
	
	public Bitmap sharpen(){
		double[][] sharp = 
				{{0,-1,0},
				{-1,5,-1},
				{0,-1,0}};
		
		return applyKernel(sharp);
	}
	
	public Bitmap edgeDetect(){
		double[][] edge = 
				{{0,1,0},
				{1,-4,1},
				{0,1,0}};
		
		return applyKernelNoAlpha(edge);
	}
	
	public Bitmap emboss(){
		double[][] edge = 
				{{-2,-1,0},
				{-1,1,1},
				{0,1,2}};
		
		return applyKernel(edge);
	}
	
	public Bitmap applyKernelNoAlpha(double[][] kernel){
		Bitmap bmp = clone();
		int kernelHeight = kernel.length;
		int kernelWidth = kernel[0].length;
		
		if(kernelHeight % 2 == 1 && kernelWidth % 2 == 1){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					double r = 0;
					double g = 0;
					double b = 0;
					
					for(int yy = -(kernelHeight / 2); yy <= (kernelHeight / 2); yy++){
						for(int xx = -(kernelWidth / 2); xx <= (kernelWidth / 2); xx++){
							if(kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)] != 0){
								if(bmp.withinBounds(x + xx, y + yy)){
									r += getRed(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									g += getGreen(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									b += getBlue(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
								}else{
									//In edge cases just use the original pixel, instead of resizing image
									r += getRed(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									g += getGreen(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									b += getBlue(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
								}
							}
						}
					}
					
					bmp.setRGB(x, y, (int)r, (int)g, (int)b);
				}
			}
			return bmp;
		}else{
			System.out.println("kernel matrix must have odd width and height!");
			return null;
		}
	}
	
	public Bitmap applyKernel(double[][] kernel){
		Bitmap bmp = clone();
		int kernelHeight = kernel.length;
		int kernelWidth = kernel[0].length;
		
		if(kernelHeight % 2 == 1 && kernelWidth % 2 == 1){
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					double a = 0;
					double r = 0;
					double g = 0;
					double b = 0;
					
					for(int yy = -(kernelHeight / 2); yy <= (kernelHeight / 2); yy++){
						for(int xx = -(kernelWidth / 2); xx <= (kernelWidth / 2); xx++){
							if(kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)] != 0){
								if(bmp.withinBounds(x + xx, y + yy)){
									a += getAlpha(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									r += getRed(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									g += getGreen(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									b += getBlue(x + xx, y + yy) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
								}else{
									//In edge cases just use the original pixel, instead of resizing image
									a += getAlpha(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									r += getRed(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									g += getGreen(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
									b += getBlue(x, y) * kernel[yy+(kernelHeight/2)][xx+(kernelWidth/2)];
								}
							}
						}
					}
					
					bmp.setARGB(x, y, (int)a, (int)r, (int)g, (int)b);
				}
			}
			return bmp;
		}else{
			System.out.println("kernel matrix must have odd width and height!");
			return null;
		}
	}
	
	public void save(String path) throws IOException{
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, width, height, pixels, 0, width);
		ImageIO.write(img, "png", new File(path));
	}
}
