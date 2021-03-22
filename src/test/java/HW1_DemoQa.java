import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HW1_DemoQa {
    WebDriver wd;

    @BeforeMethod
    public void precondition(){
        wd = new ChromeDriver();

        wd.navigate().to("https://demoqa.com/text-box");
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void fillForm(){
        // initialisation of the textbox fields variables
        WebElement fullName = wd.findElement(By.id("userName"));
        WebElement email = wd.findElement(By.id("userEmail"));
        WebElement currentAddress = wd.findElement(By.id("currentAddress"));
        WebElement submitBtn = wd.findElement(By.id("submit"));

        // String variables data for textboxes
        String name = "Tom Yam";
        String emailStr = "tomyam@yahoo.com";
        String address = " Jobnik 13, dira 14";

        // auxiliary methods(helpers) for filling in data to a textboxes
        fillField(fullName, name);
        fillField(email, emailStr);
        fillField(currentAddress, address);
        submitBtn.click();
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        // initialisation of output table in list
        List<WebElement> items = wd.findElements(By.tagName("p"));
        System.out.println("number of items in output: " + items.size());
        WebElement textBoxName = items.get(0);
        WebElement textBoxEmail = items.get(1);
        WebElement textBoxAddress = items.get(2);
//        for (WebElement e : items){
//            System.out.println(e.getText());
//        }

        // initialisation of the output elements into String variables
        String resultName = textBoxName.getText();
        String resultEmail = textBoxEmail.getText();
        String resultAddress = textBoxAddress.getText();

        // Test on assertion of entered data with output fields
        Assert.assertTrue(resultName.contains(name));
        Assert.assertTrue(resultEmail.contains(emailStr));
        Assert.assertTrue(resultAddress.contains(address));



    }

    public void fillField(WebElement fullName, String name) {
        fullName.click();
        fullName.clear();
        fullName.sendKeys(name);
    }

    @AfterMethod
    public void tearDown(){
        wd.quit();
    }
}
