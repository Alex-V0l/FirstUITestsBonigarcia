import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chapter5LinksTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebElement chapterHeader;

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 5. Browser-Specific Manipulation']"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private WebElement getTitleElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    @DisplayName("проверки url и title для Chapter5")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @ParameterizedTest
    @CsvFileSource(resources = "/Chapter5TestData.csv", numLinesToSkip = 1)
    void openPageAndCheckTitleTest(String pageURL, String expectedTitle, String className){
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='" + pageURL + "']"));
        driver.findElement(By.linkText(expectedTitle)).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = getTitleElementByClass(className);

        Assertions.assertEquals(BASE_URL + pageURL, actualURL);
        Assertions.assertEquals(expectedTitle, title.getText());
        Assertions.assertNotNull(expectedLink, expectedTitle + "не принадлежит к Chapter 5. Browser-Specific Manipulation");
    }

    @DisplayName("открытие Multilanguage и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openMultilanguageURLTitleTest(){
        String MultilanguageURL = "multilanguage.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='multilanguage.html']"));
        driver.findElement(By.linkText("Multilanguage")).click();
        String actualURL = driver.getCurrentUrl();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String pageTitle = (String) js.executeScript("return arrLang['en']['_title'];");

        Assertions.assertEquals(BASE_URL + MultilanguageURL, actualURL);
        Assertions.assertEquals("Multilanguage page", pageTitle, "Заголовок не соответствует");
        Assertions.assertNotNull(expectedLink, "Multilanguage не принадлежит к Chapter 5. Browser-Specific Manipulation");
    }
}
