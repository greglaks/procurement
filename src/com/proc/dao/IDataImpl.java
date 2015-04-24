package com.proc.dao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class IDataImpl implements IData {

	private static InputStream test;


	@Override
	public Data getData(String code) {
		InputStream inputStrem = IDataImpl.class.getResourceAsStream("IMPA5_MTMLUoM updated 1301.xls");
		HSSFWorkbook wb;
		Data data = null;
		if(code.equals(""))
			return data;
		try {
			wb = new HSSFWorkbook(inputStrem);
			HSSFSheet sheet = wb.getSheetAt(0);
			Row r = findRow(sheet, code);
			if(r != null){
				data = new Data();
				data.setPartNo(code);
				data.setDescription(r.getCell(4).getStringCellValue());
				data.setUom(r.getCell(5).getStringCellValue());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				inputStrem.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}


	private Row findRow(HSSFSheet sheet, String cellContent) {
		Row r = null;
	    for (Row row : sheet) {
	    	Cell cell = row.getCell(3);
	        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	            if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    r = row;  
	             }
	         }
	        
	    }               
	    return r;
	}
}
