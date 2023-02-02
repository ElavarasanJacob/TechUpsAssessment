package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import com.base.BaseClassForAPI;

public class ReadDatFromExcel {
public static Logger log;
	
	public static void readDataFromExcel(int columnCount) throws IOException {
		File f = new File(System.getProperty("user.dir") + "\\convertcsv.xlsx");
		FileInputStream stream = new FileInputStream(f);
		Workbook w = new XSSFWorkbook(stream);
		Sheet createSheet = w.getSheet("Sheet 1");
		for (int i = 0; i < createSheet.getPhysicalNumberOfRows(); i++) {
			Row row = createSheet.getRow(i);
			System.out.println(" ");
			for (int j = 0; j < columnCount; j++) {
				Cell cell = row.getCell(j);
				System.out.print(cell + " ");
			}

		}
	}

	public static void main(String[] args) throws IOException {
		readDataFromExcel(5);
		
	}
}
