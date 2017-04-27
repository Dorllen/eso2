package com.zhidian.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// 没有放入容器中
public class YZMCreator {
	public static final String YZM_CODE = "YZM_CODE";
	public static final String YZM_IMAG = "YZM_IMAG";
	public static Map<String,Object> create(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			BufferedImage bi = new BufferedImage(90,50,BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			Color c = new Color(252,252,252);
			g.setColor(c);
			g.fillRect(0, 0, 90, 50);
			char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
			Random r = new Random();
			int len=ch.length,index;
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<4; i++){
				index = r.nextInt(len);
				g.setColor(new Color(r.nextInt(88),r.nextInt(188),r.nextInt(255)));
				g.setFont(new Font("SansSerif",Font.BOLD, 28));
				g.drawString(ch[index]+"", (i*20)+8, 35);//(i*20)+8  20指字间距，8指据左侧8px
				sb.append(ch[index]);
			}
			map.put(YZM_CODE, sb.toString().toUpperCase());
			map.put(YZM_IMAG, bi);
			g.dispose();
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
}
