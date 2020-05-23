package Utilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class Utilities {

    private AndroidDriver driver;
    TouchAction action;

    public Utilities(AndroidDriver<AndroidElement> driver){
    this.driver=driver;
    }

    public void scrollToText(String text){
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView" +
                "(text(\""+text+"\"));");
    }

    public void tapOnElement(WebElement element){
       action=new TouchAction(driver);
       action.tap(TapOptions.tapOptions().withElement(ElementOption.element(element))).perform();
    }

    public void longPressElement(WebElement element){
        action=new TouchAction(driver);
        action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)).withDuration(Duration.ofSeconds(3))).release().perform();
    }

}
