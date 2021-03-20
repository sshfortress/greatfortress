package com.sshfortress.controller.system;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheManager;



/**
 * 
 * 类路径：com.sshfortress.comm.dao.controller
 * 创建人：rp
 * 创建时间：2014-7-13
 * 类概述： 验证码生成servlet 可以根据需求定制生成的验证码规则
 * @version
 */
public class RandomServlet extends HttpServlet {
	private ByteArrayInputStream inputStream;
	
	
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
    
	/**
	 * <p class="detail">
	 * 功能：Servlet 生成验证码  -->存储在session中--->返回前台WEB 
	 * </p>
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {

		int width=60, height=20;  
		BufferedImage img = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics g = img.getGraphics();

		Random r = new Random();
		g.setColor(getRandColor(200,250));  

	    g.fillRect(0, 0, width, height);  
//      设定字体  
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));  
//      随机产生155条干扰线，使图象中的认证码不易被其它程序探测到  
        g.setColor(getRandColor(160,200));  
        for (int i=0;i<155;i++)  
        {  
         int x = r.nextInt(width);  
         int y = r.nextInt(height);  
                int xl = r.nextInt(12);  
                int yl = r.nextInt(12);  
         g.drawLine(x,y,x+xl,y+yl);  
        } 

		StringBuffer sb = new StringBuffer();

		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

		int index, len = ch.length;

		for (int i = 0; i < 4; i++) {

			index = r.nextInt(len);

			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));  
			g.drawString("" + ch[index], 13*i+6,16);

			sb.append(ch[index]);
		}
		request.getSession().setAttribute("piccode", sb.toString());
		Map<String,String> t=new HashMap<String,String>();		
		t.put(request.getParameter("sessionId").toString(), sb.toString());
		System.out.println("前端给得sessionId"+request.getParameter("sessionId").toString());		
		//BaseAction.setQjsession(t);
		
		//静止图片缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		ImageIO.write(img, "JPG", response.getOutputStream());
	}

	/*
	 * 给定范围获得随机颜色
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 随机生成字符，含大写、小写、数字
	 * 
	 * @return
	 */
	public static String getRandomChar() {
		int index = (int) Math.round(Math.random() * 2);
		String randChar = "";
		switch (index) {
		case 0:// 大写字符
			randChar = String.valueOf((char) Math
					.round(Math.random() * 25 + 65));
			break;
		case 1:// 小写字符
			randChar = String.valueOf((char) Math
					.round(Math.random() * 25 + 97));
			break;
		default:// 数字
			randChar = String.valueOf(Math.round(Math.random() * 9));
			break;
		}
		return randChar;
	}

	/**
	 * 随机生成字体、文字大小
	 * 
	 * @return
	 */
	public static Font getRandomFont() {
		String[] fonts = { "Georgia", "Verdana", "Arial", "Tahoma",
				"Time News Roman", "Courier New", "Arial Black", "Quantzite" };
		int fontIndex = (int) Math.round(Math.random() * (fonts.length - 1));
		int fontSize = (int) Math.round(Math.random() * 4 + 16);
		return new Font(fonts[fontIndex], Font.PLAIN, fontSize);
	}
}