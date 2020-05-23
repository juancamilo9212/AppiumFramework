package Page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage {

    public ProductPage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/productName")
    List<WebElement> productLocator;

    @AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
    List<WebElement> addCartLocator;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    WebElement shopBtn;


    public void chooseProduct(AndroidDriver<AndroidElement> driver,String product){

        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()." +
                "resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView(new UiSelector()." +
                "text(\""+product+"\"));");
        int position=0;
        for(int i=0;i<productLocator.size();i++){
            if(productLocator.get(i).getText().equals(product)){
                position=i;
            }
        }
        String productText=productLocator.get(position).getText();
        addCartLocator.get(position).click();
    }

    public void shop(){
        shopBtn.click();
    }


}
