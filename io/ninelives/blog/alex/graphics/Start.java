package io.ninelives.blog.alex.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import io.ninelives.blog.alex.graphics.gfx.Bitmap;

public class Start {
	
	static Graphics2D graphics;

	static Bitmap bmp = new Bitmap("C:/Users/Alex Astgen/Downloads/images.jpeg");

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Graphics Guide"){
			@Override
			public void paint(Graphics g){
				BufferedImage bi = new BufferedImage(getContentPane().getWidth() + 50, getContentPane().getHeight() + 50, BufferedImage.TYPE_INT_ARGB);
				Start.graphics = (Graphics2D) bi.getGraphics();
				graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
				Start.bmp.draw(0, 0, graphics);
				g.drawImage(bi, 0, 0, null);
				//bmp.gaussianBlur().draw(bmp.getWidth(), 0, graphics);
				//bmp.edgeDetect().draw(0, bmp.getHeight(), graphics);
				//bmp.emboss().draw(bmp.getWidth(), bmp.getHeight(), graphics);
			}};
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		graphics = (Graphics2D) frame.getContentPane().getGraphics();

		while(true){
			frame.repaint();
		}
	}
}
