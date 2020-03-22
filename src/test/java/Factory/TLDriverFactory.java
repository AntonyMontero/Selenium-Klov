package Factory;

import Manager.SettingsManager;
import Model.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TLDriverFactory extends SettingsManager {
    protected static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    private static SettingsManager settingsManager = new SettingsManager();
    private static Settings settings;

    public synchronized static void setTLDriver (String browser, String platform) {
        settings = settingsManager.getCustomSettings("customSettings.json");

        if (browser.equals("firefox")) {
//            tlDriver = ThreadLocal.withInitial(() -> new FirefoxDriver(OptionsManager.getFirefoxOptions()));
            //For Grid Usage
            tlDriver = ThreadLocal.withInitial(() -> {

                        try {
                            return new RemoteWebDriver(new URL(settings.getRemoteDriverUrl()), OptionsManager.getFirefoxOptions(platform));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        return new RemoteWebDriver(OptionsManager.getFirefoxOptions(platform));
                    }
            );
        } else if (browser.equals("chrome")) {

            tlDriver = ThreadLocal.withInitial(() -> {

                        try {
                            return new RemoteWebDriver(new URL(settings.getRemoteDriverUrl()), OptionsManager.getChromeOptions(platform));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        return new RemoteWebDriver(OptionsManager.getChromeOptions(platform));
                    }
            );
        }
    }

    public synchronized static WebDriver getTLDriver () {
        return tlDriver.get();
    }
}
