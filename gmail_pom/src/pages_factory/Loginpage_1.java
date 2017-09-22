package pages_factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage_1 {
public WebDriver driver;
//Dom for pwd
@FindBy(name="password")
public WebElement pwd;

@FindBy(xpath="(//*[contains(text(),'Wrong password') or contains(text(),'Enter a password')])[2]")
public WebElement pwderr;
public Loginpage_1(WebDriver driver)
{	
this.driver=driver;	
PageFactory.initElements(driver, this);
}
public void fillpwd(String p)
{
pwd.sendKeys(p,Keys.ENTER);
}
}