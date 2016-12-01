package io.ninelives.blog.alex.graphics;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.swing.JFrame;

import io.ninelives.blog.alex.graphics.gfx.Bitmap;

public class Start {
	
	static Graphics2D graphics;

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Graphics Guide");
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		graphics = (Graphics2D) frame.getContentPane().getGraphics();
		
		Bitmap bmp = new Bitmap("C:/Users/Alex Astgen/Downloads/images.jpg");


		bmp.draw(0,0,graphics);
	}
}
