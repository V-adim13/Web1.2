package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class webTest {
    private static WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.getInstance().setup();

    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;

    }

    @Test
    void shouldWebTest() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Хмелев Олег");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79666666666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldWed() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева Ким");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79666666666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
    }

    @Test
    void shouldWedIdPName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ева-Оливия Каренина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79666666666");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();


    }

    @Test
    void shouldNotEmptyName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79644000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[class=input__sub]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotEmptyPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Погодаев Вадим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[class=input__sub]")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotPhone() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Погодаев Вадим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79333333");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[class=input__sub]")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Piter Parker");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79644000000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.cssSelector("div button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[class=input__sub]")).getText().trim();
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotPassedByCheckBox() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Рамиль Мусаваров");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79644000000");
        driver.findElement(By.cssSelector("div button")).click();

        Boolean actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id='agreement']")).isEnabled();

        assertEquals(true, actual);
    }
}
