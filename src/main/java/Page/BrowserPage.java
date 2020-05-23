package Page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class BrowserPage {

    public BrowserPage(AndroidDriver<AndroidElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath= "//input[@name='q']")
    WebElement searchBoxLocator;

    public String getBrowserContext() { return "WEBVIEW_com.androidsample.generalstore"; }

    public String getAppContext() { return "NATIVE_APP"; }

    public void contextAvailable(AndroidDriver<AndroidElement> driver){
        Set<String> contexts=driver.getContextHandles();
        for(String contextNames:contexts){
            System.out.println(contextNames);
        }
    }

    public void changeContext(AndroidDriver<AndroidElement> driver,String context){
        driver.context(context);
    }

    public void searchOnBrowser(String search){
        searchBoxLocator.sendKeys(search);
        searchBoxLocator.sendKeys(Keys.ENTER);
    }

    public void goBackToApp(AndroidDriver<AndroidElement> driver){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }


}
