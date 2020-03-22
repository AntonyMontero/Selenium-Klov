package Factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptionsManager {
    public static final Logger log = LoggerFactory.getLogger(OptionsManager.class);

    //Get Chrome Options
    public static DesiredCapabilities getChromeOptions(String platform) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //Set Platform
        capabilities.setCapability("platform", platform);
        capabilities.setCapability("browserName", "chrome");

        log.info("Platform name: "+ capabilities.getPlatform());
        log.info("Browser name: " + capabilities.getBrowserName());

        return capabilities;
    }

    //Get Firefox Options
    public static DesiredCapabilities getFirefoxOptions (String platform) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        //options.addArguments("--headless");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        //Set Platform
        capabilities.setCapability("platform", platform);
        //Set BrowserName
        capabilities.setCapability("browserName", "firefox");

        log.info("Platform name: " + capabilities.getPlatform());
        log.info("Browser name: " + capabilities.getBrowserName());

        return capabilities;
    }

}
