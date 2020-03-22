package Base;

import Model.Settings;
import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Factory.TLDriverFactory;
import Manager.SettingsManager;
import Model.Settings;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class TestBase  {
    protected FluentWait wait;
    protected Settings settingsModel;
    protected static SettingsManager settingsManager = new SettingsManager();
    protected static String baseURL;
    protected ExtentHtmlReporter htmlReporter;
    protected ExtentReports extent;
    //protected KlovReporter klovReporter;
    protected ExtentTest test;
    //public WebDriver web;

    public static final Logger log = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setupReport() {
        log.info("-----------Open Report----------------");
        ExtentKlovReporter klov = new ExtentKlovReporter("Testing-Selenium-V2","Report3");
        klov.initMongoDbConnection("localhost",27017);
        klov.initKlovServerConnection("http://localhost:81");
        //String workingDir = System.getProperty("user.dir");
        //htmlReporter = new ExtentHtmlReporter(workingDir + "\\ExtentReports\\ExtentReportResults.html", true);
        htmlReporter = new ExtentHtmlReporter("C:\\Users\\User\\Documents\\Reportes\\Report.html");
        extent = new ExtentReports();

// attach all reporters
        extent.attachReporter(htmlReporter,klov);
        extent.setSystemInfo("Platform", "Windows");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "Prueba Final");
        extent.setAnalysisStrategy(AnalysisStrategy.TEST);
        htmlReporter.config().setDocumentTitle("Extent Report Selenium");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTheme(Theme.DARK);

    }

    @BeforeMethod
    @Parameters(value = {"browser", "platform"})
    public void setupTest(@Optional String browser, @Optional String platform) {
        initializeCustomSetting();
        baseURL = settingsModel.getDefaultTestUrl();
        TLDriverFactory.setTLDriver(browser, platform);
        initializeFluentWait(TLDriverFactory.getTLDriver());
    }


    @AfterMethod
    @Parameters(value = {"browser", "platform"})
    public synchronized void tearDown(@Optional String browser,@Optional String platform,ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //test=extent.createTest(result.getName());
            test.log(Status.ERROR, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.log(Status.ERROR, MarkupHelper.createLabel(" - Test Case Failed:" +result.getThrowable() , ExtentColor.RED));
        }else if (result.getStatus()==ITestResult.SUCCESS){
            //test=extent.createTest(result.getName());
            test.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"PASSED ",ExtentColor.GREEN));
        }else{
          //  test=extent.createTest(result.getTestName());
            test.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"SKIPPED ",ExtentColor.BLUE));
            test.skip(result.getThrowable());
        }
        log.info("------------------Finish---------------");
        TLDriverFactory.getTLDriver().quit();
    }
    @AfterSuite
    public synchronized void CloseReport() throws InterruptedException {
        log.info("------------------Closed Report---------------");
        extent.flush();
        // TLDriverFactory.getTLDriver().quit();
    }
    public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        //String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //TakesScreenshot ts = (TakesScreenshot) driver;
        // File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots" under src folder
        // String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
        // File finalDestination = new File(destination);
        // FileUtils.copyFile(scrFile, finalDestination);
        return " ";
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expection = new ExpectedCondition<Boolean>() {

            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) webDriver).executeAsyncScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(settingsModel.getDefaultSleepTime());
            wait.until(expection);
        } catch (Throwable error) {
            Assert.fail("TimeOut waiting for page load request to complete");
        }
    }

    protected String getURL(String dest) {
        return baseURL + dest;
    }

    private void initializeFluentWait(WebDriver webDriver) {
        Duration timeOut = Duration.ofSeconds(settingsModel.getDefaultTimeout());
        Duration pollingRate = Duration.ofSeconds(settingsModel.getDefaultPollingRate());

        wait = new FluentWait(webDriver).withTimeout(timeOut).pollingEvery(pollingRate).ignoring(NoSuchElementException.class);
    }

    private void initializeCustomSetting() {
        settingsModel = settingsManager.getCustomSettings("customSettings.json");
    }


    /*public WebDriver getDriver() {

        return this.web;
    }*/
}
