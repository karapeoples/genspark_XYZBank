package test.utils;


import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExecUtils {
    public static  Map<String, Map<String, String>> readExcel()throws IOException{
        Map<String, Map<String, String>> fileData = new HashMap<>();
        String dir = System.getProperty("user.dir");
        File file = new File( dir + File.separator +"TestInfoBook.xlsx");
        FileInputStream inputStream = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        for(int sheetNum = 0; sheetNum < wb.getNumberOfSheets(); sheetNum++){
            XSSFSheet sheet = wb.getSheetAt(sheetNum);
            XSSFRow headers = sheet.getRow(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                XSSFRow row = sheet.getRow(i);
                Map<String, String> colMap = new HashMap<>();
                for (int j = 1; j < row.getLastCellNum(); j++){
                    DataFormatter dataFormatter = new DataFormatter();
                    String value = dataFormatter.formatCellValue(row.getCell(j));
                    colMap.put(String.valueOf(headers.getCell(j)), value);

                }
                fileData.put(String.valueOf(row.getCell(0)), colMap);

            }
        }

        return fileData;
    }
    public static void main(String[] args) throws IOException {
        readExcel();

    }
}
