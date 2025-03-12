import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Chapter8LinkTest {

    @DisplayName("открытие Random calculator и проверка url и title")
    @Tags({@Tag("UI"), @Tag("Smoke")})
    @Test
    void openRandom_calculatorURLTitleTest(){
        WebDriver driver =new ChromeDriver();;
        final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(BASE_URL);
        driver.manage().window().maximize();

        WebElement chapterHeader = driver.findElement(By.xpath("//h5[text()='Chapter 8. Testing Framework Specifics']"));;
        String Random_calculatorURL = "random-calculator.html";
        WebElement expectedLink = chapterHeader.findElement(By.xpath("//a[@href='random-calculator.html']"));
        driver.findElement(By.linkText("Random calculator")).click();
        String actualURL = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals(BASE_URL + Random_calculatorURL, actualURL);
        Assertions.assertEquals("Random calculator", title.getText());
        Assertions.assertNotNull(expectedLink, "Random calculator не принадлежит к Chapter 8. Testing Framework Specifics");
        driver.quit();
    }
}
