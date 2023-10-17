package api.utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {


    public static String getData(String path, String sheetName, int rc, int cc) {
        String v = "";
        try {
            FileInputStream fis = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(fis);
            v = wb.getSheet(sheetName).getRow(rc).getCell(cc).toString();
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        return v;
    }

    public static int getRowCount(String path, String sheetName) throws IOException, InvalidFormatException {
        int rc = 0;
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        rc = wb.getSheet(sheetName).getLastRowNum();
        return rc;
    }


    public static int getColumnCount(String path, String sheetName, int rc) throws IOException, InvalidFormatException {
        int cc = 0;
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        cc = wb.getSheet(sheetName).getRow(rc).getLastCellNum();
        return cc;
    }
}
