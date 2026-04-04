package com.automation.tests;

//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
import com.automation.utilities.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
//    extends TestCase
{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
    public static WebDriver driver;
    @Test
    public void test(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://opencart_demo.test/");
        driver.manage().window().fullscreen();
        WebElement dv = driver.findElement(new By.ByCssSelector("input[placeholder='Search']"));
        dv.sendKeys("hihi");
        driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']")).click();
        driver.close();
    }
    public static void main(String[] args) {
        ConfigReader config = new ConfigReader();
        System.out.println(config.getBrowser());
    }


}
