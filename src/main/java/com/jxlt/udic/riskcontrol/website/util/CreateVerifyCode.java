package com.jxlt.udic.riskcontrol.website.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;


public class CreateVerifyCode{
	// 验证码图片的宽度。
	private int width = 60;
	// 验证码图片的高度。
	private int height = 30;
	private int x = 0;
	// 字体高度
	private int fontHeight;
	private int codeY;
	
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 初始化验证图片属性
	 */
	private void initImgProp(int codeCount){
		// 宽度
		String strWidth = "100";
		// 高度
		String strHeight = "40";
		// 字符个数
		String strCodeCount = "4";
		// 将配置的信息转换成数值
		try {
			if (strWidth != null && strWidth.length() != 0) {
				width = Integer.parseInt(strWidth);
			}
			if (strHeight != null && strHeight.length() != 0) {
				height = Integer.parseInt(strHeight);
			}
			if (strCodeCount != null && strCodeCount.length() != 0) {
				codeCount = Integer.parseInt(strCodeCount);
			}
		} catch (NumberFormatException e) {
		}
		x = width / (codeCount + 1) - 3;
		fontHeight = height - 2;
		codeY = height - 10;
	}

	public void createImg(OutputStream os, String randomStr){
		initImgProp(randomStr.length());
		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();
		// 创建一个随机数生成器类
		Random random = new Random();
		// 将图像填充为白色
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		// 设置字体。
		g.setFont(font);
		// 画边框。
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width - 1, height - 1);
		// 随机产生干扰线，使图象中的认证码不易被其它程序探测到。
		g.setColor(Color.BLACK);
		for (int i = 0; i < 30; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(5);
			int yl = random.nextInt(5);
			g.drawLine(x, y, x + xl, y + yl);
		}

		int red = 0, green = 0, blue = 0;
		// 随机产生codeCount数字的验证码。
		for (int i = 0; i < randomStr.length(); i++) {
			// 得到随机产生的验证码数字。
			String strRand = randomStr.substring(i, i+1);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中。
			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x, codeY);
		}

		// 将图像输出到ByteArrayOutputStream输出流中。
//		ByteArrayOutputStream bos = new ByteArrayOutputStream("gbk");
		try {
			ImageIO.write(buffImg, "png", os);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
