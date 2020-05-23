package Test;

import Page.*;
import Utilities.TestData;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HybridAppTest extends Base {

    HomePage home;
    AndroidDriver<AndroidElement> driver;
    ProductPage product;
    CheckOutPage checkOut;
    BrowserPage browser;
    double totalAmount;
    double productTotalAmount;

    @BeforeTest
    public void setUp() throws IOException {
        //startServer();
        driver=capabilities("GeneralStoreApp");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        home=new HomePage(driver);
        product=new ProductPage(driver);
        checkOut=new CheckOutPage(driver);
        browser=new BrowserPage(driver);
    }

    @Test(dataProvider = "InputData",dataProviderClass = TestData.class)
    public void appValidation(String input)  throws IOException {
        home.fillInitInfo(driver,input);
        //------------------------------------Scroll and purchase a product-----------------------------
        product.chooseProduct(driver,"Air Jordan 4 Retro");
        product.chooseProduct(driver,"Jordan 6 Rings");
        product.shop();
        waitUntilElementsLoad(4000);
        totalAmount=checkOut.convertListToDouble();
        productTotalAmount=checkOut.convertElementToDouble();
        checkOut.pricesMatches(totalAmount,productTotalAmount);
        checkOut.TapOnCheckBox(driver);
        checkOut.longPressTerms(driver);
        checkOut.termsMessageIsDisplayed();
        checkOut.TapOnOkBtn(driver);
        checkOut.TapOnProceedBtn(driver);
        //--------------------------------------------------Web handles-----------------------------------------------
        waitUntilElementsLoad(7000);
        browser.contextAvailable(driver);
        String browserContext=browser.getBrowserContext();
        String appContext=browser.getAppContext();
        browser.changeContext(driver,browserContext);
        //browser.searchOnBrowser("Hello");
        browser.goBackToApp(driver);
        browser.changeContext(driver,appContext);
    }

    @AfterTest
    public void tearDown(){
        driver.closeApp();
    }

}
