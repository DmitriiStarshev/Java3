package DZ3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewAccountIvi {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        driver.get("https://www.ivi.ru/");

        driver.findElement(By.xpath("//a[@class='headerAvatar__link']")).click();

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

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("" +
                "//div[@class='profile-viewing-guide__close']")));
        driver.findElement(By.xpath("//div[@class='profile-viewing-guide__close']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a//div[contains(text(),'Выйти')]")));
        driver.findElement(By.xpath("//a//div[contains(text(),'Выйти')]")).click();

        Thread.sleep(5000);
        driver.quit();
    }
}
