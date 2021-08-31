package src.test.java.org.LessonTest4.Lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static Lesson5.ColorMatcher.hasColor;
import static Lesson5.CustomsWaiters.elementFirstInCollection;
import static Lesson5.JsUtils.clickWithJs;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class ActionsExamplesTest {
    static WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://crm.geekbrains.space/";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        loginToCrm();
    }

    @Test
    void checkInstrumentsPanelColumnsChanging() throws InterruptedException {
        driver.get(BASE_URL + "dashboard");
        driver.findElement(By.xpath("//a[@title='Настройки представления']")).click();

        ////label[text()='Наименование']/ancestor::tr//span
        Actions actions = new Actions(driver);

        actions.clickAndHold(driver.findElement(By.xpath("//label[text()='Наименование']/ancestor::tr//span")))
                .dragAndDrop(driver.findElement(By.xpath("//label[text()='Наименование']/ancestor::tr")),
                        driver.findElement(By.xpath("//label[text()='Владелец']/ancestor::tr")))
                .build()
                .perform();

        webDriverWait.until(elementFirstInCollection(By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']"),
                "ВЛАДЕЛЕЦ"));// метод для постоянного использовани одидания, создается в Utils

        webDriverWait.until(driver -> driver.findElements(
                By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']"))).get(0).getText()
                .equals("ВЛАДЕЛЕЦ");//метод ожидания для однократного спользования

        List<WebElement> headersList = driver.findElements(By.xpath("//thead[@class='grid-header']//span[@class='grid-header-cell__label']"));
        Assertions.assertEquals("ВЛАДЕЛЕЦ", headersList.get(0).getText());

        assertThat(headersList.get(0), hasText("ВЛАДЕЛЕЦ"));

        Thread.sleep(10000);
    }

    @Test
    void checkCellsColorChangingTest() {
        driver.get(BASE_URL + "dashboard");
        driver.findElement(By.xpath("//thead[@class='grid-header']//input")).click();
        String color = driver.findElement(By.xpath("//tbody[@class='grid-body']/tr[1]")).getCssValue("background-color");
        assertThat(driver.findElement(By.xpath("//tbody[@class='grid-body']/tr[1]")).getCssValue("background-color")
                , is("rgba(254, 250, 237, 1)"));
        assertThat(driver.findElement(By.xpath("//tbody[@class='grid-body']/tr[1]")), hasColor("rgba(254, 250, 237, 10)"));
    }

    @Test
    void jsExecutorTest() throws InterruptedException {
        driver.get("https://ya.ru");

        clickWithJs(driver, driver.findElement(By.xpath("//button")));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.alert('test')");
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);

        javascriptExecutor.executeScript("window.open()");
        Thread.sleep(5000);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(5000);
        driver.close();
    }

    @Test
    void loginToDiaryWithCookie() throws InterruptedException {
        driver.get("https://diary.ru");
        Cookie authCookie = new Cookie("_identity_", "83668234c30812b14c46bac1deda1a240770255504032650b424a75038c00b3aa%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3545507%2C%22E_QJqRaNdBrepyeVN7uNXi5Dz9tjGpfX%22%2C2592000%5D%22%3B%7D");
        driver.manage().addCookie(authCookie);
        driver.navigate().refresh();
        Thread.sleep(1000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    public static void loginToCrm() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.xpath("//button")).click();
    }


}

