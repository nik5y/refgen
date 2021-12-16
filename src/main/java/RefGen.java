import org.openqa.selenium.WebDriver;
import tests.CiteThisForMeTests;

public class RefGen {

    public static void main(String[] args) throws Exception {

        CiteThisForMeTests citation = new CiteThisForMeTests();

        WebDriver driver = CiteThisForMeTests.init();

        citation.setup(driver);

        citation.navigate(driver);

    }

}
