import org.openqa.selenium.WebDriver;
import tests.CiteThisForMeTests;

public class RefGen {

    public static void main(String[] args) throws Exception {

        CiteThisForMeTests citation = new CiteThisForMeTests();
        WebDriver driver = CiteThisForMeTests.init();
        citation.setup(driver);
        citation.setCitationStyle(driver, "Vancouver");

        for(int i = 0; i < 3; i++) {
            citation.addJournalCitation(driver,"https://doi.org/10.1093/jn/128.11.1845");
        }

        citation.goToBibliographyFromSourceSelect(driver);

//        CiteThisForMeTests.close(driver);
    }

}


//        Journal not found alert
//        homePage.inputJournalSearch("doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335");
