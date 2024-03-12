import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;

public class MailTest {
    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUpDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "ru.mail.mailapp:id/button_hamburger")
    MobileElement foldersBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.mail.mailapp:id/text\" and @text=\"Inbox\"]")
    MobileElement inboxBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.mail.mailapp:id/subject\" and @text=\"Письмо для тестов\"]")
    MobileElement mailForTest;

    @AndroidFindBy(id = "ru.mail.mailapp:id/toolbar_mailview_action_move")
    MobileElement moveToBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.mail.mailapp:id/text\" and @text=\"News\"]")
    MobileElement newsFolderBtn;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Go back\"]")
    MobileElement backBtn;

    @AndroidFindBy(id = "ru.mail.mailapp:id/fab")
    MobileElement createBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.mail.mailapp:id/action_name\" and @text=\"Compose email\"]")
    MobileElement composeEmailBtn;

    @AndroidFindBy(xpath = "(//android.widget.AutoCompleteTextView[@resource-id=\"ru.mail.mailapp:id/edit\"])[1]")
    MobileElement toInpt;

    @AndroidFindBy(id = "ru.mail.mailapp:id/subject")
    MobileElement subjectInpt;

    @AndroidFindBy(id = "ru.mail.mailapp:id/toolbar_action_send")
    MobileElement sendBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"ru.mail.mailapp:id/subject\" and @text=\"Ваше сообщение не доставлено. Mail failure.\"]")
    MobileElement errMail;

    @AndroidFindBy(id = "ru.mail.mailapp:id/toolbar_mailview_action_delete")
    MobileElement deleteBtn;

    @Test
    public void mailPositiveTest() {
        mailForTest.click();
        moveToBtn.click();
        newsFolderBtn.click();
        foldersBtn.click();
        newsFolderBtn.click();
        boolean actualMailForTestIsDisplayed = mailForTest.isDisplayed();

        Assert.assertTrue(actualMailForTestIsDisplayed);

        returnTestMailToInbox();
    }

    @Test
    public void mailNegativeTest() {
        createBtn.click();
        composeEmailBtn.click();
        toInpt.sendKeys("test@test.test");
        subjectInpt.sendKeys("test");
        sendBtn.click();

        Assert.assertTrue(errMail.isDisplayed());

        deleteErrMail();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void returnTestMailToInbox() {
        mailForTest.click();
        moveToBtn.click();
        inboxBtn.click();
        foldersBtn.click();
        inboxBtn.click();
    }

    public void deleteErrMail() {
        errMail.click();
        deleteBtn.click();
        backBtn.click();
    }
}
