import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardSeleniumTest {
    private WebDriver driver;
    @AfterAll
     static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }
    @BeforeEach
     public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }
    @AfterEach
     public void afterEach() {
        driver.quit();
        driver = null;
    }
    @Test
     void shouldTest() {
        driver.get("http://localhost:9999");

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Самойлов Сергей");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79048888888");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.tagName("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id= order-success])")).getText().trim();
        assertEquals(expected, actual);
    }

}
