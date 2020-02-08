package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("getDriver()");
        }
        return driver;
    }

    public static WebDriverWait getDriverWait() {
        if (wait == null) {
            wait = new WebDriverWait(Driver.getDriver(), 10);
            System.out.println("getDriverWait()");
        }
        return wait;
    }

    public static void kill() {
        System.out.println("kill() = " + driver);
        if (driver != null) {
            driver.quit();
            driver = null;
            wait = null;
            System.out.println("killed = " + driver);
        } else
            System.out.println("null error at kill()");
    }

}