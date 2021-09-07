package src.test.java.org.HomeWork5Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewAccountIviTest {
    static WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://www.ivi.ru/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
    }

    @Test
    void chekTitleCite() {
        driver.get(BASE_URL);
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Онлайн-кинотеатр ivi - фильмы, сериалы и мультфильмы" +
                " смотреть онлайн бесплатно в хорошем качестве"));

    }

    @Test
    void chekButtonEnterOrRegistration () throws InterruptedException {
        driver.get(BASE_URL+"profile");
        driver.findElement(By.xpath("//button[@class='button button_size_shinnok" +
                " button_style_kioshi button_auth profileUserInfo__button profileUserInfo__button_auth']")).click();
        Thread.sleep(5000);

    }

    @Test
    void chekRegistrationSteps (){
        driver.get(BASE_URL);
        driver.get(BASE_URL+"profile");
        driver.findElement(By.xpath("//button[@class='button button_size_shinnok" +
                " button_style_kioshi button_auth profileUserInfo__button profileUserInfo__button_auth']")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        driver.findElement(By.name("email")).sendKeys("gbtester@mail.ru");

        driver.findElement(By.xpath("//button[@data-test='button_continue']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-test='input_password']")));
        driver.findElement(By.xpath("//input[@data-test='input_password']")).sendKeys("12314123");

        driver.findElement(By.xpath("//button[@data-test='button_continue']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("" +
                "//a[@title='Нажмите, чтобы закрыть форму']")));
        driver.findElement(By.xpath("//a[@title='Нажмите, чтобы закрыть форму']")).click();
    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
