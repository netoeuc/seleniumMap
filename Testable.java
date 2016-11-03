package automatedTest;
 
import org.openqa.selenium.WebDriver;
 
public interface Testable {
    void close(WebDriver driver);
    WebDriver getWebDriver(String browser);
    void testScenario();
   
   
}