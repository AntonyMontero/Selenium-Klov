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

public class PrimerTest extends TestBase {
    public WebDriver webDriver;
    @Test
    public void  TestUno() throws IOException {
        try {

            test=extent.createTest("TestUno","PASSED TEST CASE");

            runTest();
        }catch (Throwable e) {

            test.log(Status.FAIL,"Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(getScreenShot(webDriver, "TestUno")));
            Assert.fail(e.getMessage());
        }
    }
    @Test
    public void  TestDos() throws IOException {
        try {
            test=extent.createTest("TestDos","PASSED TEST CASE");
            runTest2();
        }catch (Throwable e) {
             test.log(Status.FAIL,"Test Case Failed Snapshot is below " + test.addScreenCaptureFromPath(getScreenShot(webDriver, "TestDos")));
            Assert.fail(e.getMessage());
        }
    }


    private void runTest() {
        try {
            log.info("TestUno");
            webDriver = TLDriverFactory.getTLDriver();
            String url = getURL("");
            webDriver.get(url);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
            webDriver.findElement(By.name("q")).sendKeys("hola hola");

        }catch (Throwable e){
            throw e;
        }


    }
    private void runTest2() {
        try {
            log.info("TestDos");
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
