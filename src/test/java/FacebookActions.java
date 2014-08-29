import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import page_object_repository.FBHomePageObjects;
import page_object_repository.FBSigninPageObjects;

import static org.testng.Assert.assertTrue;

/**
 * Created by Maruf on 8/28/2014.
 */
public class FacebookActions {

    public static void main(String[] args) {

        // Methods below will not run as they are only here to show how to access WebElements from Page Objects Repositories
       WebDriver driver = new FirefoxDriver();

        // create an object instance of HomePageObjects
        FBSigninPageObjects signinPageObjects = new FBSigninPageObjects(driver);

        // perform actions using WebElement variables made available by that object
        signinPageObjects.login.sendKeys("maymail@mail.com");
        signinPageObjects.password.sendKeys("mypassword");
        signinPageObjects.loginButton.click();

        // create an object instance of HomePageObjects
        FBHomePageObjects fbHomePageObjects = new FBHomePageObjects(driver);

        // perform actions using WebElement variables made available by that object
        assertTrue(fbHomePageObjects.profile.isDisplayed());
        fbHomePageObjects.searchButton.sendKeys("friend");
        fbHomePageObjects.searchButton.click();
    }
}
