import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chapter4LinksTests {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebElement chapterHeader;

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 4. Browser-Agnostic Features']"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
    private WebElement getTitleElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    @DisplayName("проверки url и title для Chapter4")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @ParameterizedTest
    @CsvFileSource(resources = "/Chapter4TestData.csv", numLinesToSkip = 1)
    void openPageAndCheckTitleTest(String pageURL, String expectedTitle, String className){
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='" + pageURL + "']"));
        driver.findElement(By.linkText(expectedTitle)).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = getTitleElementByClass(className);

        Assertions.assertEquals(BASE_URL + pageURL, actualURL);
        Assertions.assertEquals(expectedTitle, title.getText());
        Assertions.assertNotNull(expectedLink, expectedTitle + "не принадлежит к Chapter 4. Browser-Agnostic Features");
    }

    @DisplayName("открытие Long page и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openLong_pageURLTitleTest(){
        String Long_pageURL = "long-page.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='long-page.html']"));
        driver.findElement(By.linkText("Long page")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + Long_pageURL, actualURL);
        Assertions.assertEquals("This is a long page", title.getText());
        Assertions.assertNotNull(expectedLink, "Long page не принадлежит к Chapter 4. Browser-Agnostic Features");
    }

    @DisplayName("открытие Frames и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openFramesURLTitleTest(){
        String FramesURL = "frames.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='frames.html']"));
        driver.findElement(By.linkText("Frames")).click();
        String actualURL = driver.getCurrentUrl();
        driver.switchTo().frame("frame-header");
        WebElement title = driver.findElement(By.xpath("//h1[@class='display-6']"));

        Assertions.assertEquals(BASE_URL + FramesURL, actualURL);
        Assertions.assertEquals("Frames", title.getText());
        Assertions.assertNotNull(expectedLink, "Frames не принадлежит к Chapter 4. Browser-Agnostic Features");
        driver.switchTo().defaultContent();
    }

    @DisplayName("открытие IFrames и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openIFramesURLTitleTest(){
        String IFramesURL = "iframes.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='iframes.html']"));
        driver.findElement(By.linkText("IFrames")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + IFramesURL, actualURL);
        Assertions.assertEquals("IFrame", title.getText());
        Assertions.assertNotNull(expectedLink, "IFrames не принадлежит к Chapter 4. Browser-Agnostic Features");
    }
}
