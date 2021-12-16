package tests;

import drivers.WebDriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.Home;

import java.util.concurrent.TimeUnit;

public class CiteThisForMeTests {

//    public static WebDriver driver;

    public static WebDriver init() throws Exception {
        WebDriver driver = WebDriverFactory.createWebDriver();
        return driver;
    }

    public void setup(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try {

            driver.get(Home.url);
//            String[] tabs = driver.getWindowHandles().toArray(new String[2]);
//            driver.switchTo().window(tabs[1]);
//            driver.close();
//            driver.switchTo().window(tabs[0]);

//            Set<Cookie> cookies = driver.manage().getCookies();
            Cookie cookie = new Cookie("OptanonAlertBoxClosed","2021-12-16T08:22:14.311Z");
            driver.manage().addCookie(cookie);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Did not load page\n");
        }
    }

    public void navigate(WebDriver driver) {

        Home homePage = PageFactory.initElements(driver, Home.class);
//        showCookies();
        homePage.clickAcceptCookiesButton();
//        showCookies();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        homePage.clickMoreCiteOptions();
        homePage.inputMoreCiteOptions("Vancouver");
        homePage.clickMoreCiteOptionsFirstResult(driver, "Vancouver");

        homePage.clickJournal();
//        Journal not found alert
//        homePage.inputJournalSearch("doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335");

        homePage.inputJournalSearch("https://doi.org/10.1093/jn/128.11.1845");

//        if (!(homePage.isJournalNotMatched() | homePage.isJournalNotFound())) {
//            System.out.println("Journal found");
////            js.executeScript("window.scrollBy(0,9999)", "");
//            homePage.clickAddReference();
//        };

        if (homePage.isJournalFound()) {
            homePage.clickAddReference();
            homePage.clickAddNewReference();
        }

    }

    public static void close(WebDriver driver) throws InterruptedException {

        Thread.sleep(4000);
        driver.quit();
    }

    public void showCookies(WebDriver driver) {
        System.out.println("Cookies!");
        System.out.println(driver.manage().getCookies().toString());
    }

}
