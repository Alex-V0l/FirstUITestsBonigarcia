import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chapter7LinksTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    WebElement chapterHeader;

    @BeforeEach
    void setUp(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 7. The Page Object Model (POM)']"));
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }

    @DisplayName("открытие Login form и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openLogin_formURLTitleTest(){
        String Login_formURL = "login-form.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='login-form.html']"));
        driver.findElement(By.linkText("Login form")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + Login_formURL, actualURL);
        Assertions.assertEquals("Login form", title.getText());
        Assertions.assertNotNull(expectedLink, "Login form не принадлежит к Chapter 7. The Page Object Model (POM)");
    }

    @DisplayName("открытие Slow login и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openSlow_loginURLTitleTest(){
        String Slow_loginURL = "login-slow.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='login-slow.html']"));
        driver.findElement(By.linkText("Slow login")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + Slow_loginURL, actualURL);
        Assertions.assertEquals("Slow login form", title.getText());
        Assertions.assertNotNull(expectedLink, "Slow login не принадлежит к Chapter 7. The Page Object Model (POM)");
    }
}
