package api.utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import java.io.IOException;

@SuppressWarnings("ALL")
public class DataProviders {


    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException, InvalidFormatException {

        String path = System.getProperty("user.dir") + "\\testdata\\RestTestData.xlsx";
        int rowCount = ExcelUtils.getRowCount(path, "sheet2");
        int columnCount = ExcelUtils.getColumnCount(path, "sheet2", 1);
        String[][] appData = new String[rowCount][columnCount];
        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                appData[i - 1][j] = ExcelUtils.getData(path, "sheet2", i, j);
            }
        }
        return appData;
    }

    @DataProvider(name = "userdata")
    public String[] getUserNamesData() throws IOException, InvalidFormatException {
        String path = System.getProperty("user.dir") + "\\testdata\\RestTestData.xlsx";
        int rowCount = ExcelUtils.getRowCount(path, "sheet2");
        String[] userData = new String[rowCount];
        for (int i = 1; i <= rowCount; i++) {
            userData[i - 1] = ExcelUtils.getData(path, "sheet2", i, 1);
        }

        return userData;
    }

}
