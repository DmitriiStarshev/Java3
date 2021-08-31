package DZ3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CreateNewProjectCrm {
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        loginToCrm();

        Actions actions = new Actions(driver);
        WebElement projectMenuElement = driver.findElement(By.xpath("//a/span[text()='Контрагенты']"));
        actions.moveToElement(projectMenuElement).perform();

        driver.findElement(By.xpath("//a/span[text()='Контактные лица']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Создать контактное лицо']")));
        driver.findElement(By.xpath("//a[@title='Создать контактное лицо']")).click();

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("crm_contact[lastName]")));
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Starshev");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("crm_contact[firstName]")));
        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Dmitrii");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("1234");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("" +
                "//div[@class='select2-result-label']")));
        List<WebElement> organizationVars = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        organizationVars.get(0).click();


        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.name("crm_contact[jobTitle]")));
        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("Engineer");

        Select managerSelect = new Select(driver.findElement(By.name("crm_contact[status]")));
        managerSelect.selectByVisibleText("Активный");

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//button[contains(@class,'btn btn-success action-button')]")));
        driver.findElement(By.xpath("//button[contains(@class,'btn btn-success action-button')]")).click();

        Thread.sleep(5000);
        driver.quit();
    }

    public static void loginToCrm() {
        driver.get("https://crm.geekbrains.space/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.xpath("//button")).click();
    }
}
