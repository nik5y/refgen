package pages;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    public static String url = "https://www.citethisforme.com/";

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement btn_acceptCookies;

    @FindBy(xpath = "//a[contains(text(),'··· More')]")
    public WebElement btn_moreCiteOptions;

    @FindBy(xpath = "//body/div[2]/div[1]/div[2]/div[2]/div[5]/div[2]/div[1]/div[1]/div[2]/fieldset[1]/div[1]/form[1]/input[2]")
    public WebElement inp_moreCiteOptions;

    @FindBy(xpath = "//body/div[2]/div[1]/div[2]/div[2]/div[5]/div[2]/div[1]/div[1]/div[2]/div[1]/a[1]")
    public WebElement btn_moreCiteOptionsFirstResult;

    @FindBy(xpath = "//span[contains(text(),'Journal')]")
    public WebElement btn_journal;

    @FindBy(xpath = "//input[@id='jrQry']")
    public WebElement inp_journalSearch;

    @FindBy(xpath = "//button[contains(text(),'Add reference')]")
    public WebElement btn_addReference;

    @FindBy(xpath ="//a[@id='add-new-reference']")
    public WebElement btn_addNewReference;

    @FindBy(xpath = "//div[contains(@class, 'alert-error alert')]")
    public WebElement elem_journalNotFoundAlert;

    @FindBy(xpath = "//h3[contains(text(),'Select the search result that best matches your so')]")
    public WebElement elem_selectBestMatch;

    @FindBy(xpath = "//div[contains(text(), 'Please double-check the journal details below and add to them if required.')]")
    public WebElement elem_journalFoundAlert;

    public void clickAcceptCookiesButton() {
        btn_acceptCookies.click();
    }

    public void clickMoreCiteOptions() {
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btn_moreCiteOptions)).click();
    btn_moreCiteOptions.click();
    }

    public void inputMoreCiteOptions(String input) {
        inp_moreCiteOptions.sendKeys(input);
    }

    public void clickMoreCiteOptionsFirstResult(WebDriver driver, String input) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(btn_moreCiteOptionsFirstResult, input));
        btn_moreCiteOptionsFirstResult.click();
    }

    public void clickJournal() {
        btn_journal.click();
    }

    public void inputJournalSearch(String input) {
//        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(inp_journalSearch));
        inp_journalSearch.sendKeys(input);
        inp_journalSearch.submit();
    }

    public void clickAddReference() {
        btn_addReference.click();
    }

    public void clickAddNewReference() {
        btn_addNewReference.click();
    }

    public boolean isJournalNotFound() throws ElementNotVisibleException, NoSuchElementException {
        try {
            elem_journalNotFoundAlert.click();
            System.out.println("Journal Not Found!");
            return true;
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            System.out.println("Alert not found");
            return false;
        }
    }

    public boolean isJournalNotMatched() throws ElementNotVisibleException, NoSuchElementException {
        try {
            elem_selectBestMatch.click();
            System.out.println("Journal Not Matched!");
            return true;
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isJournalFound() throws ElementNotVisibleException, NoSuchElementException {
        try {
            elem_journalFoundAlert.click();
            System.out.println("Journal is found!");
            return true;
        } catch (ElementNotVisibleException | NoSuchElementException e) {
            System.out.println("Journal not found!");
            return false;
        }
    }

}
