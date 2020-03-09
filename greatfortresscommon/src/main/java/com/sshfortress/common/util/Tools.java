package com.sshfortress.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools
{
  public static int getRandomNum()
  {
    Random r = new Random();
    return r.nextInt(900000) + 100000;
  }
  
  public static boolean notEmpty(String s)
  {
    return (s != null) && (!"".equals(s)) && (!"null".equals(s));
  }
  
  public static boolean isEmpty(String s)
  {
    return (s == null) || ("".equals(s)) || ("null".equals(s));
  }
  
  public static String[] str2StrArray(String str, String splitRegex)
  {
    if (isEmpty(str)) {
      return null;
    }
    return str.split(splitRegex);
  }
  
  public static String[] str2StrArray(String str)
  {
    return str2StrArray(str, ",\\s*");
  }
  
  public static String date2Str(Date date)
  {
    return date2Str(date, "yyyy-MM-dd HH:mm:ss");
  }
  
  public static Date str2Date(String date)
  {
    if (notEmpty(date))
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try
      {
        return sdf.parse(date);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
        
        return new Date();
      }
    }
    return null;
  }
  
  public static String date2Str(Date date, String format)
  {
    if (date != null)
    {
      SimpleDateFormat sdf = new SimpleDateFormat(format);
      return sdf.format(date);
    }
    return "";
  }
  
  public static String getTimes(String StrDate)
  {
    String resultTimes = "";
    
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date now = new Date();
      Date date = df.parse(StrDate);
      long times = now.getTime() - date.getTime();
      long day = times / 86400000L;
      long hour = times / 3600000L - day * 24L;
      long min = times / 60000L - day * 24L * 60L - hour * 60L;
      long sec = times / 1000L - day * 24L * 60L * 60L - hour * 60L * 60L - min * 60L;
      
      StringBuffer sb = new StringBuffer();
      if (hour > 0L) {
        sb.append(hour + "������");
      } else if (min > 0L) {
        sb.append(min + "������");
      } else {
        sb.append(sec + "����");
      }
      resultTimes = sb.toString();
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return resultTimes;
  }
  
  public static void writeFile(String fileP, String content)
  {
    String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
    filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
    if (filePath.indexOf(":") != 1) {
      filePath = File.separator + filePath;
    }
    try
    {
      OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
      BufferedWriter writer = new BufferedWriter(write);
      writer.write(content);
      writer.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  
  public static boolean checkEmail(String email)
  {
    boolean flag = false;
    try
    {
      String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
      Pattern regex = Pattern.compile(check);
      Matcher matcher = regex.matcher(email);
      flag = matcher.matches();
    }
    catch (Exception e)
    {
      flag = false;
    }
    return flag;
  }
  
  public static boolean checkMobileNumber(String mobileNumber)
  {
    boolean flag = false;
    try
    {
      Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
      Matcher matcher = regex.matcher(mobileNumber);
      flag = matcher.matches();
    }
    catch (Exception e)
    {
      flag = false;
    }
    return flag;
  }
  
  public static boolean checkKey(String paraname, String FKEY)
  {
    paraname = paraname == null ? "" : paraname;
    return MD5.md5(paraname + DateUtil.getDays() + ",fh,").equals(FKEY);
  }
  
  public static String readTxtFile(String fileP)
  {
    try
    {
      String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
      filePath = filePath.replaceAll("file:/", "");
      filePath = filePath.replaceAll("%20", " ");
      filePath = filePath.trim() + fileP.trim();
      if (filePath.indexOf(":") != 1) {
        filePath = File.separator + filePath;
      }
      String encoding = "utf-8";
      File file = new File(filePath);
      if ((file.isFile()) && (file.exists()))
      {
        InputStreamReader read = new InputStreamReader(
          new FileInputStream(file), encoding);
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        if ((lineTxt = bufferedReader.readLine()) != null) {
          return lineTxt;
        }
        read.close();
      }
      else
      {
        System.out.println("����������������,������������������:" + filePath);
      }
    }
    catch (Exception e)
    {
      System.out.println("����������������");
    }
    return "";
  }
  
  public static void main(String[] args)
  {
    System.out.println(getRandomNum());
  }
}
