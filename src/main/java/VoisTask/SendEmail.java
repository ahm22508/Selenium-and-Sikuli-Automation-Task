package VoisTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Scanner;

public class SendEmail{

    public void Send() throws FileNotFoundException{


        Scanner scanner = new Scanner(new File("C:\\TemplateMavenProject\\src\\main\\resources\\app.properties"));
        String Email = "";
        String Password = "";
        while (scanner.hasNext()){
            Email = scanner.nextLine();
        if (Email.contains("email")){
            Email = Email.replace("email= ", "");
        }
           Password = scanner.nextLine();
            if (Password.contains("password")){
                Password = Password.replace("password= ", "");
            }
        }


        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));


        By FindPassword = By.xpath("//input[@type='password']");
        By ComposeEmail = By.xpath("(//div[@role='button'])[7]");
        By UploadingConfirm = By.xpath("//div[@class = 'vI']");
        By FindTo = By.xpath("//input[@id=':bc']");

        driver.navigate().to("https://www.gmail.com");
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(Email);
        driver.findElement(By.xpath("(//span[@jsname='V67aGc'])[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(FindPassword));
        driver.findElement(FindPassword).sendKeys(Password);
        driver.findElement(By.xpath("(//span[@jsname='V67aGc'])[2]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(ComposeEmail));
        driver.findElement(ComposeEmail).click();
        wait.until(ExpectedConditions.elementToBeClickable(FindTo));
        driver.findElement(FindTo).sendKeys("islam.hassan@vodafone.com");
        driver.findElement(By.xpath("//input[@name='subjectbox']")).sendKeys("Automated Email");
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\TemplateMavenProject\\TaskData.xlsx");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(UploadingConfirm));
        driver.findElement(By.xpath("//div[@id=':7d']")).click();
    }
}
