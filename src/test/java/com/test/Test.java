package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class Test {

    private WebDriver driver;

    public static void main(String[] args) {
        new Test().searchAndAddToCart();
    }

    public void searchAndAddToCart() {

        WebDriverManager.chromedriver().setup();


        ChromeOptions options = new ChromeOptions();
        WebDriver delegate = new ChromeDriver(options);
        driver = SelfHealingDriver.create(delegate);
        driver.manage().window().maximize();

        try {
            driver.get("https://www.amazon.in/");
            driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nike Shoes for men");
            driver.findElement(By.id("nav-search-submit-button")).click();

            Thread.sleep(2000);

            WebElement firstResult = driver.findElements(By.cssSelector("div.s-main-slot div[data-component-type='s-search-result']")).get(0);
            firstResult.findElement(By.cssSelector("a.a-link-normal")).click();

            Thread.sleep(2000);

            driver.findElement(By.id("add-to-cart-button")).click();


        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}



//package com.test;
//
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.WebDriver;
//import com.epam.healenium.SelfHealingDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//public class Test {
//    Webdriver driver;
//    public static void main(String[] args) {
//        // Assuming the path to chromedriver is already set in system properties or environment variables
//        // System.setProperty("webdriver.chrome.driver", "path/to/your/chromedriver");
//
//        ChromeOptions options = new ChromeOptions();
//        WebDriver delegate = new ChromeDriver(options);
//        SelfHealingDriver driver = SelfHealingDriver.create(delegate);
//
//        try {
//            driver.get("https://www.google.com");
//
//            // Option 1: Using contains() with aria-label to account for partial match
//            driver.findElement(By.xpath("//a[contains(@aria-label, 'Gmail')]")).click();
//
//            // Option 2: Directly using the text "Gmail"
//            // This option is straightforward and likely to remain stable
//            // driver.findElement(By.xpath("//a[text()='Gmail']")).click();
//
//            // Option 3: Targeting by class name (less recommended due to potential dynamic nature of class names)
//            // driver.findElement(By.className("gb_H")).click();
//
//            // Ensure there's enough time to observe the action before quitting the driver (for demonstration purposes)
//            Thread.sleep(3000); // Pause for 3 seconds
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            // Ensure the driver is quit at the end of the session
//            driver.quit();
//        }
//    }
//}
