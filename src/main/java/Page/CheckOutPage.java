package Page;

import Utilities.Utilities;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;

public class CheckOutPage {

    public CheckOutPage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
    List<WebElement> productPriceLocator;

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    WebElement totalAmountLocator;

    @AndroidFindBy(xpath="//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    WebElement checkBoxLocator;

    @AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
    WebElement termsBtn;

    @AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle")
    WebElement termsMessage;

    @AndroidFindBy(id="android:id/button1")
    WebElement undoBtn;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    WebElement proceedBtn;

    public void pricesMatches(double totalPrice,double totalAmount){
        if(totalPrice==totalAmount){
            System.out.println("******Elements matches*********");
        }
    }

    public void TapOnProceedBtn(AndroidDriver<AndroidElement> driver){
        Utilities utilities=new Utilities(driver);
        utilities.tapOnElement(proceedBtn);
    }

    public void TapOnOkBtn(AndroidDriver<AndroidElement> driver){
        Utilities utilities=new Utilities(driver);
        utilities.tapOnElement(undoBtn);
    }


    public void TapOnCheckBox(AndroidDriver<AndroidElement> driver){
        Utilities utilities=new Utilities(driver);
        utilities.tapOnElement(checkBoxLocator);
    }

    public void longPressTerms(AndroidDriver<AndroidElement> driver){
        Utilities utilities=new Utilities(driver);
        utilities.longPressElement(termsBtn);
    }

    public void termsMessageIsDisplayed(){
         if(termsMessage.isDisplayed()){
             System.out.println("*****The message was displayed successfully*****");
         }
    }

    public Double convertListToDouble(){
        String price="";
        String subPrice="";
        double totalAmount=0;
        for(int i=0;i<productPriceLocator.size();i++){
            price=productPriceLocator.get(i).getText();
            subPrice=price.substring(1);
            totalAmount=totalAmount+Double.parseDouble(subPrice);
        }
        return totalAmount;
    }

    public  Double convertElementToDouble(){
        String amount=totalAmountLocator.getText();
        String subAmount=amount.substring(1);
        double totalAmount=Double.parseDouble(subAmount);
        return totalAmount;
    }

}
