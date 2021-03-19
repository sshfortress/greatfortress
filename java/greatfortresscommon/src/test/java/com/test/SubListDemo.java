package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubListDemo {
	public static void main(String[] args) {
//		String regex = "style=\".*\"/>";
//		String s = "<img src=file/img/2016/12-28/1234-25521482893088459.jpg\" title=\"1234.jpg\" alt=\"\" width=\"396\" height=\"271\" style=\"width: 396px; height: 271px;\"/>";
//		Pattern parrten = Pattern.compile(regex);
//		Matcher m = parrten.matcher(s);
//		if(m.find()){
//			System.err.println(m.group());
//			System.out.println(s.replaceAll(regex,"style=\"width:100%\"/>"));
//			for (int i = 0; i <= m.groupCount(); i++) {
//				System.err.println(m.group(i));
//			}
//		}else{
//			System.err.println("error");
//		}
		String regex = "style=\".*\"";
		String text = "<img src=\"http://youtxweb.1035.mobi/nwjx/uedit/jsp/upload/20170731/10401501464496116.jpg\" style=\"width: 438px; height: 274px;\"/>";
		text.replaceAll(regex,"style=\"width:100%\"/>");
		System.out.println(text);

	}
}
