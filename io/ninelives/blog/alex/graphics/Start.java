package io.ninelives.blog.alex.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

import io.ninelives.blog.alex.graphics.gfx.Bitmap;

public class Start {
	
	static Graphics2D graphics;

	static Bitmap bmp = new Bitmap("Path/To/Image.jpg");

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("Graphics Guide");
		frame.setSize(640,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		graphics = (Graphics2D) frame.getContentPane().getGraphics();

		bmp.emboss().draw(0,0, graphics);
	}
}
