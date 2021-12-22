package drivers;

import drivers.BrowserEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WebDriverFactory {
	
	public WebDriver createWebDriver() throws Exception {
		
		String newDriverString = System.getProperty("browser", "chrome");
		
		Map<String, Object> preferenceMap = new HashMap<String, Object>();
		
		BrowserEnum s = BrowserEnum.valueOf(newDriverString);
		System.out.println("Selected " + s);
		
		switch (s) {
		case firefox:
			preferenceMap.clear();
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/XX");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addPreference("network.cookie.cookieBehaviour", 2);
			return new FirefoxDriver(firefoxOptions);
		case chrome:

//			URL resource = getClass().getClassLoader().getResource("target/classes/drivers/chromedriver");
//			File driverFile = Paths.get(resource.toURI()).toFile();
//			URL resource = WebDriverFactory.class.getResource("chromedriver");
//			System.out.println("resource: " + resource.toString() + "\n");
//			System.out.println(File.pathSeparator);
//			System.out.println(File.separator);

			String path = "";
//			if(resource.toString().contains("!")) {


			URI uri = getClass().getResource("chromedriver").toURI();
			System.out.println(uri);
//			InputStream str = getClass().getResource("chromedriver").getIn;

//			if("jar".equals(uri.getScheme())){
//				for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
//					if (provider.getScheme().equalsIgnoreCase("jar")) {
//						try {
//							provider.getFileSystem(uri);
//						} catch (FileSystemNotFoundException e) {
//							// in this case we need to initialize it first:
//							provider.newFileSystem(uri, Collections.emptyMap());
//						}
//					}
//				}
//			}
//
//			path = Paths.get(uri).toString();

//			if (uri.contains("!")) {
//				final String[] array = uri.split("!");
//				final Map<String, String> env = new HashMap<>();
//				final FileSystem fs = FileSystems.newFileSystem(URI.create(array[0]), env);
//				path = fs.getPath(array[1]).toString();
//			} else {
//				final String[] array = uri.split(File.pathSeparator);
//				path = array[1];
//			}


//			} else {
//				path = resource.toString();
//			}


			System.setProperty("webdriver.chrome.driver", "chromedriver");

//			System.setProperty("webdriver.chrome.driver", "./src/main/resources/drivers/chromedriver");
			ChromeOptions cOptions = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
//
//			prefs.put("profile.default_content_setting_values.cookies", 2);
//			prefs.put("network.cookie.cookieBehaviour", 2);
			prefs.put("profile.block_thirds_party_cookies", true);
//			prefs.put("pageLoadStrategy", "eager");
			prefs.put("webdriver.load.strategy", "unstable");

//®
			cOptions.setExperimentalOption("prefs", prefs);
			
            cOptions.setHeadless(true);

//			cOptions.addArguments("load-extension=src/test/resources/adblock/");
//			cOptions.setExperimentalOption("useAutomationExtension", false);

			cOptions.addArguments("disable-infobars");
            cOptions.addArguments("disable-notifications");
            
			return new ChromeDriver(cOptions);
		default:
			throw new Exception("Unknown browser");
		}		
		
	}

}
