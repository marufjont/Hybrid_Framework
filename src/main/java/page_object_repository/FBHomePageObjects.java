package page_object_repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Maruf on 8/28/2014.
 */
public class FBHomePageObjects {
    private WebDriver WebDriver;

    // This class does not contain actual ids for elements as it was created solely for demonstration


    @FindBy(id = "id")
    public WebElement searchField;

    @FindBy (id = "id")
    public WebElement searchButton;

    @FindBy (id = "id")
    public WebElement profile;

    @FindBy(id = "id")
    public WebElement findFriends;

    @FindBy (id = "id")
    public WebElement events;

    // This constructor calls initelements method of PageFactory class. initelements method will create the WebElements declared in this class
    public FBHomePageObjects(WebDriver driver) {
        this.WebDriver=driver;
        PageFactory.initElements(driver, this);
    }
}
