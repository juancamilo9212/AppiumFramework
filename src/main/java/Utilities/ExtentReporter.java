package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;
import org.testng.*;
import org.testng.xml.XmlSuite;
import java.util.List;
import java.util.Map;

public class ExtentReporter implements IReporter {

    private ExtentReports extent;
    ExtentHtmlReporter htmlReporter;


    public void generateReport(List<XmlSuite> xmlSuite, List<ISuite> suites, String outputDirectory){

        htmlReporter=new ExtentHtmlReporter("D:\\Ejercicios\\CursoAppium\\com.cursoAppium\\ExtentReport\\ExtentReportsTestNG.html");
        extent= new ExtentReports();
        extent.attachReporter(htmlReporter);

        for(ISuite suite:suites){
            Map<String, ISuiteResult> result=suite.getResults();
            for(ISuiteResult r: result.values()){
                ITestContext context=r.getTestContext();
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.PASS);
                buildTestNodes(context.getSkippedTests(), Status.PASS);
            }
        }
        extent.flush();
    }

    private void buildTestNodes(IResultMap tests,Status status){
        ExtentTest test;
        if(tests.size()>0){
            for(ITestResult result:tests.getAllResults()){
                test =extent.createTest(result.getMethod().getMethodName());
                for(String group:result.getMethod().getGroups()){
                    test.assignCategory(group);
                    String message="test "+ status.toString().toLowerCase() + "ed";
                    if(result.getThrowable() != null){
                        message=result.getThrowable().getMessage();
                        test.log(status,message);
                    }
                }
            }
        }
    }

}
