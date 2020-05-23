package Page;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    AppiumDriverLocalService service;
    AndroidDriver<AndroidElement> driver;

    public void startEmulator() throws IOException {

        Runtime.getRuntime().exec(System.getProperty("user.dir")+"/src/main/java/App/device.bat");
        waitUntilElementsLoad(6000);

    }

    public void killAllNodes() throws IOException, InterruptedException {
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
        Thread.sleep(3000);
    }

    public boolean checkIfServerIsRunning(int port){

        boolean isServerRunning=false;
        ServerSocket serverSocket;
        try {
            serverSocket=new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning=true;
        } finally {
            serverSocket=null;

        }
        return isServerRunning;
    }

    public AppiumDriverLocalService startServer(){
        boolean isServerRunning=checkIfServerIsRunning(4723);
        if(!isServerRunning){
            service=AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;
    }

    public void stopServer(){
        service=AppiumDriverLocalService.buildDefaultService();
        service.stop();
    }

    public void waitUntilElementsLoad(long wait){
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AndroidDriver<AndroidElement> capabilities(String appName ) throws IOException {
        FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/Properties/GlobalProperties.properties");
        Properties properties=new Properties();
        properties.load(fileInputStream);
        String app=properties.getProperty(appName);
       // String device=properties.getProperty("Device");
        String device=System.getProperty("deviceName");//on cmd mvn test -DdeviceName=emulator

        /*if(device.contains("Emulator")){
            startEmulator();
        }*/
        File sourceFolder=new File(System.getProperty("user.dir")+"/src/main/java/App");
        File filePath=new File(sourceFolder,app);
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,"15");
        capabilities.setCapability(MobileCapabilityType.APP,filePath.getAbsolutePath());
        capabilities.setCapability("chromedriverExecutable", System.getProperty("user.dir")+"/src/main/java/Driver/chromedriver.exe");
        driver=new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;

    }

    public void getScreenShot(String testName) throws IOException {
        driver=capabilities("GeneralStoreApp");
        File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("D:\\Ejercicios\\CursoAppium\\com.cursoAppium\\ScreenShots"+"\\"+testName+".png"));
    }


}
