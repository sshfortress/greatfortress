package com.test;

import java.io.IOException;

import com.maxmind.geoip.*;

public class Test {


	public static void main(String[] args) {  
        try {  
//          String sep = System.getProperty("file.separator");  
//  
//          // Uncomment for windows  
//           String dir = System.getProperty("user.dir");  
//  
//          // Uncomment for Linux  
//          //String dir = "/usr/local/share/GeoIP";  
//            
//          String dbfile = dir + sep + "GeoIP.dat";  
            // You should only call LookupService once, especially if you use  
            // GEOIP_MEMORY_CACHE mode, since the LookupService constructor  
            // takes up  
            // resources to load the GeoIP.dat file into memory  
            // LookupService cl = new  
            // LookupService(dbfile,LookupService.GEOIP_STANDARD);  
              
              
              
//            LookupService cl = new LookupService("D:\\GeoIP.dat\\GeoIP.dat",  
//                    LookupService.GEOIP_MEMORY_CACHE);  
            String classPath="E:\\svn\\greatfortress\\greatfortressparent\\greatfortressframework"+"\\src\\main\\webapp\\GeoIP\\GeoIP.dat";
            LookupService cl = new LookupService(classPath,  
            		LookupService.GEOIP_MEMORY_CACHE);  
            String classPath2="E:\\svn\\greatfortress\\greatfortressparent\\greatfortressframework"+"\\src\\main\\webapp\\GeoIP\\GeoLiteCity.dat";
            LookupService cl2 = new LookupService(classPath2,  
            		LookupService.GEOIP_MEMORY_CACHE);  
            
            Location l2 = cl2.getLocation("144.0.9.29");
            System.out.println(
            		"countryCode: " + l2.countryCode +"\n"+
            		"countryName: " + l2.countryName +"\n"+
            		"region: " + l2.region +"\n"+
            		"city: " + l2.city +"\n"+
            		"latitude: " + l2.latitude +"\n"+
            		"longitude: " + l2.longitude);
  
//            System.out.println(cl.getCountry("159.226.115.22").getCode());  
//            System.out.println(cl.getCountry("159.226.115.22").getName());  
//            System.out.println(cl.getCountry("183.16.200.127").getName());  
//            System.out.println(cl.getCountry("213.52.50.8").getName());  
//            System.out.println(cl.getCountry("200.21.225.82").getName());  
//            cl.getCountry("183.16.200.127").
            cl.close();  
        } catch (IOException e) {  
        	e.printStackTrace();
            System.out.println("IO Exception");  
        }  
    }  

}
