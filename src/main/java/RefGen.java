import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import tests.CiteThisForMeTests;

import java.io.FileReader;

public class RefGen {

    public static void main(String[] args) throws Exception {

        String fileLocation = args[0];

        JSONParser parser = new JSONParser();
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        JSONObject a = (JSONObject) parser.parse(new FileReader(fileLocation));
//        System.out.println(a.get("referenceStyle"));
//        System.out.println(a.get("referencesByDOI").toString() + a.get("referencesByDOI").getClass().getName());


        CiteThisForMeTests citation = new CiteThisForMeTests();
        WebDriver driver = CiteThisForMeTests.init();
        citation.setup(driver);
        citation.setCitationStyle(driver, "Vancouver");

        JSONArray referencesByDOI = (JSONArray) a.get("referencesByDOI");

        for(Object doi : referencesByDOI) {
            System.out.println("Working with " + doi.toString());
            citation.addJournalCitation(driver, doi.toString());
        }

        citation.goToBibliographyFromSourceSelect(driver);

        CiteThisForMeTests.close(driver);

        CiteThisForMeTests.close(driver);
    }

}


//        Journal not found alert
//        homePage.inputJournalSearch("doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335doi.org/10.1038/nm.4335");
