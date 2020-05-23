package Utilities;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "InputData")
    public Object [][] getDataForEditField(){
         Object[][] objects=new Object[][]{{"Argentina"},{"Brazil"}};
        return objects;
    }
}
