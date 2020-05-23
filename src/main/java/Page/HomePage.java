package Page;

import Utilities.Utilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }


    @AndroidFindBy(xpath="//android.widget.EditText[@text='Enter name here']")
    WebElement nameFieldLocator;

    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    WebElement genderFieldLocator;

    @AndroidFindBy(id = "android:id/text1")
    WebElement countryListLocator;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    WebElement letShopBtn;

    /*@AndroidFindBy(xpath = "//android.widget.TextView[@text='Argentina']")
    WebElement countryLocator;*/

    public void fillInitInfo(AndroidDriver<AndroidElement> driver,String country){

        nameFieldLocator.sendKeys("Viviana");
        driver.hideKeyboard();
        genderFieldLocator.click();
        countryListLocator.click();
        Utilities utilities=new Utilities(driver);
        utilities.scrollToText(country);
        driver.findElementByXPath("//android.widget.TextView[@text='"+country+"']").click();
        //countryLocator.click();
        letShopBtn.click();

    }








}
