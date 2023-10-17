package api.testcases;

import api.utilities.DataProviders;
import api.utilities.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Test {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        DataProviders dataProviders=new DataProviders();
       System.out.println(dataProviders.getUserNamesData());
    }
}
