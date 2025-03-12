import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class Chapter3LinksTests {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebElement chapterHeader;

    @BeforeEach
    void setUp(){
    driver = new ChromeDriver();
    driver.get(BASE_URL);
    driver.manage().window().maximize();
    chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 3. WebDriver Fundamentals']"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    private WebElement getTitleElementByClass(String className) {
        return driver.findElement(By.className(className));
    }

    @DisplayName("проверки url и title для Chapter3")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @ParameterizedTest
    @CsvFileSource(resources = "/Chapter3TestData.csv", numLinesToSkip = 1)
    void openPageAndCheckTitleTest(String pageURL, String expectedTitle, String className){
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='" + pageURL + "']"));
        driver.findElement(By.linkText(expectedTitle)).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = getTitleElementByClass(className);

        Assertions.assertEquals(BASE_URL + pageURL, actualURL);
        Assertions.assertEquals(expectedTitle, title.getText());
        Assertions.assertNotNull(expectedLink, expectedTitle + "не принадлежит к Chapter 3. WebDriver Fundamentals");
    }

    @DisplayName("открытие Navigation и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openNavigationURLTitleTest(){
        String NavigationURL = "navigation1.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='navigation1.html']"));
        driver.findElement(By.linkText("Navigation")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + NavigationURL, actualURL);
        Assertions.assertEquals("Navigation example", title.getText());
        Assertions.assertNotNull(expectedLink, "Navigation не принадлежит к Chapter 3. WebDriver Fundamentals");
    }

    @DisplayName("открытие Draw in canvas и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openDraw_in_canvasURLTitleTest(){
        String Draw_in_canvasURL = "draw-in-canvas.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='draw-in-canvas.html']"));
        driver.findElement(By.linkText("Draw in canvas")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + Draw_in_canvasURL, actualURL);
        Assertions.assertEquals("Drawing in canvas", title.getText());
        Assertions.assertNotNull(expectedLink, "Draw in canvas не принадлежит к Chapter 3. WebDriver Fundamentals");
    }
}


