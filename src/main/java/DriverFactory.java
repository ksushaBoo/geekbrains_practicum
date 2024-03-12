import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.NO_RESET;


public class DriverFactory {
    AndroidDriver<?> driver;
    public AndroidDriver<?> setUpDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android" );
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
        desiredCapabilities.setCapability(APP_PACKAGE, "ru.mail.mailapp");
        desiredCapabilities.setCapability(APP_ACTIVITY, "ru.mail.ui.portal.MailPortalActivity");
        desiredCapabilities.setCapability(NO_RESET, true);

        URL remouteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver<>(remouteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
