package page_object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Maruf on 8/28/2014.
 */
public class FBSigninPageObjects {
    private WebDriver WebDriver;



    @FindBy (id = "id")
    public WebElement login;

    @FindBy (id = "id")
    public WebElement password;

    @FindBy (id = "id")
    public WebElement loginButton;

    @FindBy(id = "id")
    public WebElement keepMeLoggininButton;

    @FindBy (id = "id")
    public WebElement forgotPasswordLink;

    // This constructor calls initelements method of PageFactory class. initelements method will create the WebElements declared in this class
    public FBSigninPageObjects(WebDriver driver) {
        this.WebDriver=driver;
        PageFactory.initElements(driver, this);
    }

}
