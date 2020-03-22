package Test;

import Base.TestBase;
import Factory.TLDriverFactory;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SegundoTest extends TestBase {
    public WebDriver webDriver;
    @Test
    public void  TestTres() throws IOException {
        try {
            test=extent.createTest("TestTres","PASSED TEST CASE");
            runTest();
           // Thread.sleep(3000);
        }catch (Throwable e){
            test.log(Status.FAIL,"Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(getScreenShot(webDriver,"TestTres")));
            Assert.fail(e.getMessage());
        }
    }

    private void runTest() {
        try {
            log.info("TestTres");
            webDriver = TLDriverFactory.getTLDriver();
            String url = getURL("");
            webDriver.get(url);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            webDriver.findElement(By.name("q")).sendKeys("hola hola");

        }catch (Throwable e){
            throw e;
        }

    }
}
