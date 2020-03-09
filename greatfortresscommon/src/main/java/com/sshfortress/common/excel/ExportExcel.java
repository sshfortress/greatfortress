package com.sshfortress.common.excel;

import java.io.FileOutputStream;  
import java.util.List;  


import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;  
import org.apache.poi.hssf.usermodel.HSSFCellStyle;  
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

	/**
	 * <p class="detail">
	 * 功能：简单导出excel
	 * </p>
	 * @param sheetName sheet名称
	 * @param head excel第一行单元格名称
	 * @param list 实体类数据
	 * @param path 下载路径
	 */
	public static void outExcel(String sheetName,String[] head,List<Map<String,Object>> list,String path) throws Exception{
		// 第一步，创建一个webbook，对应一个Excel文件  
		HSSFWorkbook wb = new HSSFWorkbook();  
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		HSSFSheet sheet = wb.createSheet(sheetName);  
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		HSSFRow row = sheet.createRow((int) 0);  
		// 第四步，创建单元格，并设置值表头 设置表头居中  
		HSSFCellStyle style = wb.createCellStyle();  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  

		//HSSFCell cell = row.createCell(0); 
		String[] datakey=new String[head.length];
		for(int i=0,m=1;i<head.length;i++,m++){
			HSSFCell  cell = row.createCell(i); 
			cell.setCellValue(head[i]);  
			cell.setCellStyle(style);             
			datakey[i]=m+"";
		}
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，   
		for (int i = 0; i < list.size(); i++){  
			HSSFRow datarow = sheet.createRow((int)i+1);  
			// row = sheet.createRow((int) i + 1);  
			// 第四步，创建单元格，并设置值           

			for(int j=0;j<head.length;j++){
				//System.out.println(list.get(i).get("1"));
				//System.out.print(list.get(i).get(datakey[j]).toString());
				datarow.createCell(j).setCellValue(list.get(i).get(datakey[j]).toString());
			}
		}  
		// 第六步，将文件存到指定位置  
			FileOutputStream fout = new FileOutputStream(path);  
			wb.write(fout);  
			fout.close();  
	}

}
