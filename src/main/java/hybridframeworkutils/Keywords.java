package hybridframeworkutils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by Maruf on 8/26/2014.
 */
public class Keywords {

    private static int millis;


    //	navigate
    public static void navigate(WebDriver driver, String url){
        driver.get(url);
    }

    //	Click
    public static void click(WebDriver driver, String xPath){
        driver.findElement(By.xpath(xPath)).click();
    }

    // clear field
    public static void clearfield(WebDriver driver,String xPath) {
        driver.findElement(By.xpath(xPath)).clear();
    }

    //	enter field
    public static void enterField(WebDriver driver, String xPath, String input){
        driver.findElement(By.xpath(xPath)).sendKeys(input);
    }

    //	Select from menu
    public static void selectDropDown(WebDriver driver,  String xPath, String input){
        Select item = new Select(driver.findElement(By.xpath(xPath)));
        item.selectByVisibleText(input);
        //new Select(driver.findElement(By.id("xPath"))).selectByVisibleText(bMonth);
    }

    //	wait for page to load
    public static void waitFor(long millis) throws InterruptedException{
        Thread.sleep(millis);
    }

    //	verify element present
    public static Boolean verifyElementPresent(WebDriver driver, String xPath){
        if(driver.findElement(By.xpath(xPath)).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }
    // 	verify text
    public static boolean verifyText(WebDriver driver,  String xPath, String verData){
        return driver.findElement(By.xpath(xPath)).getText().equals(verData);
    }
    //	verify contains
    public static boolean verifyContains(WebDriver driver,  String xPath, String verData){
        return driver.findElement(By.xpath(xPath)).getText().contains(verData);
    }
    //	verify starts with
    public static boolean verifyStartsWith(WebDriver driver, String xPath, String verData){
        return driver.findElement(By.xpath(xPath)).getText().startsWith(verData);
    }
    //DOB to month, day, year
    public static String dobToData(String driver, String DOB, String month, String day, String Year){

        return Year;

    }
}

