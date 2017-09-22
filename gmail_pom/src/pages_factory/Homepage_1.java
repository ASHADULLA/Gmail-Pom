package pages_factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage_1 {

public WebDriver driver;

//Dom for user id
@FindBy(name="identifier")
public WebElement uid;

//Dom for Error message
@FindBy(xpath="(//*[contains(text(),'Enter an email') or contains(text(),'find your Google Account')])[2]")
public WebElement uiderr;

//Constructor method
public Homepage_1(WebDriver driver)
{
this.driver=driver;
PageFactory.initElements(driver, this);
}

//operational method
public void filluid(String u)
{
uid.sendKeys(u,Keys.ENTER);
}
}
