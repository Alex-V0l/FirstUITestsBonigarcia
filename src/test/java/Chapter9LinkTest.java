import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chapter9LinkTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebElement chapterHeader;

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 9. Third-Party Integrations']"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private WebElement getTitleElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    @DisplayName("проверки url и title для Chapter9")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @ParameterizedTest
    @CsvFileSource(resources = "/Chapter9TestData.csv", numLinesToSkip = 1)
    void openPageAndCheckTitleTest(String pageURL, String expectedTitle, String className){
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='" + pageURL + "']"));
        driver.findElement(By.linkText(expectedTitle)).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = getTitleElementByClass(className);

        Assertions.assertEquals(BASE_URL + pageURL, actualURL);
        Assertions.assertEquals(expectedTitle, title.getText());
        Assertions.assertNotNull(expectedLink, expectedTitle + "не принадлежит к Chapter 9. Third-Party Integrations");
    }
}
