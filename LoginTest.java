import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    public static void main(String[] args) {
        // Set up WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the login page
            driver.get("https://practicetestautomation.com/practice-test-login/");
            driver.manage().window().maximize();

            // Locate username and password fields
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("submit"));

            // Test valid login
            usernameField.sendKeys("student");
            passwordField.sendKeys("Password123");
            loginButton.click();

            // Verify success
            String expectedUrl = "https://practicetestautomation.com/logged-in-successfully/";
            if (driver.getCurrentUrl().equals(expectedUrl)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed.");
            }
            // Verify expected text on the new page
            WebElement messageElement = driver.findElement(By.tagName("h1")); // Adjust locator if needed
            String pageText = messageElement.getText();
            if (pageText.contains("Congratulations") || pageText.contains("Logged In Successfully")) {
                System.out.println("Text verification passed!");
            } else {
                System.out.println("Text verification failed. Found text: " + pageText);
            }
            
            Thread.sleep(5000);

            //  Verify Log out button is displayed
            WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Log out']"));
            if (logoutButton.isDisplayed()) {
                System.out.println("Log out button is displayed. Verification passed!");
            } else {
                System.out.println("Log out button is not displayed. Verification failed!");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit the driver
            driver.quit();
        }
    }
}
