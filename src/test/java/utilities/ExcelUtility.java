package utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;



public class ExcelUtility {
	

		public FileInputStream fi;
	    public FileOutputStream fo;
	    public XSSFWorkbook workbook;
	    public XSSFSheet sheet;
	    public XSSFRow row;
	    public XSSFCell cell;
	    public CellStyle style;
	    String path;

	    // Constructor to initialize the Excel file path
	    public ExcelUtility(String path) {
	        this.path = path;
	    }

	    // Get total row count in a sheet
	    public int getRowCount(String sheetName) throws IOException {
	        fi = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fi);
	        sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getLastRowNum();
	        workbook.close();
	        fi.close();
	        return rowCount;
	    }

	    // Get cell count (columns) in a specific row
	    public int getCellCount(String sheetName, int rowNum) throws IOException {
	        fi = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fi);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        int cellCount = (row == null) ? 0 : row.getLastCellNum();
	        workbook.close();
	        fi.close();
	        return cellCount;
	    }

	    // Read data from a cell as a String regardless of its data type
	    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
	        fi = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fi);
	        sheet = workbook.getSheet(sheetName);
	        row = sheet.getRow(rowNum);
	        if (row == null) return "";
	        cell = row.getCell(colNum);
	        if (cell == null) return "";

	        // DataFormatter converts any cell value format safely into a String
	        DataFormatter formatter = new DataFormatter();
	        String data;
	        try{
	        	data=formatter.formatCellValue(cell);
	        }
	        catch(Exception e){
	        	data="";
	        	
	        }
	        workbook.close();
	        fi.close();
	        return data;
	    }

	    // Write data back into a cell
	    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
	        
	    	File xFile=new File(path);
	    	if(xFile.exists()) {
	    	fo=new FileOutputStream(path);
	    	workbook.write(fo);
	    	}
	    	
	    	fi = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fi);
	        
	        if(workbook.getSheetIndex(sheetName)==-1)
	        	workbook.createSheet(sheetName);
	        sheet=workbook.getSheet(sheetName);
	        
	        if(sheet.getRow(rowNum)==null);
	        
	         sheet.createRow(rowNum);
	        row=sheet.getRow(rowNum);
	        
	        cell = row.createCell(colNum);
	        cell.setCellValue(data);

	        fo = new FileOutputStream(path);
	        workbook.write(fo);
	        
	        workbook.close();
	        fi.close();
	        fo.close();
	    }
	}


