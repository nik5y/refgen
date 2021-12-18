package tests;

import drivers.WebDriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    public void setCitationStyle(WebDriver driver, String citationStyle) {

        Home homePage = PageFactory.initElements(driver, Home.class);
//        homePage.removeAds(driver);
        homePage.clickAcceptCookiesButton();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        homePage.clickMoreCiteOptions();
        homePage.inputMoreCiteOptions(citationStyle);
        homePage.removeAds(driver);
        homePage.clickMoreCiteOptionsFirstResult(driver, citationStyle);

    }

    public void addJournalCitation(WebDriver driver, String journalIdentifier) throws InterruptedException {


        Home homePage = PageFactory.initElements(driver, Home.class);
//        Thread.sleep(3000);
        homePage.waitForElementToBeClickable(driver, homePage.btn_journal, "//span[contains(text(),'Journal')]");
        homePage.removeAds(driver);
        homePage.clickJournal(driver);

        homePage.waitForElementToBeClickable(driver, homePage.inp_journalSearch, "//input[@id='jrQry']");
        homePage.inputJournalSearch(journalIdentifier);
//        homePage.removeAds(driver);
//        if (!(homePage.isJournalNotMatched() | homePage.isJournalNotFound())) {
//            System.out.println("Journal found");
////            js.executeScript("window.scrollBy(0,9999)", "");
//            homePage.clickAddReference();
//        };

        if (homePage.isJournalFound()) {
            homePage.waitForElementToBeClickable(driver, homePage.btn_addReference, "//button[contains(text(),'Add reference')]");
            homePage.removeAds(driver);
            homePage.clickAddReference();
            homePage.clickAddNewReference();
        }
    }

    public void goToBibliographyFromSourceSelect(WebDriver driver) {
        Home homePage = PageFactory.initElements(driver, Home.class);
        homePage.clickBackToBibliographyButton(driver);
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
