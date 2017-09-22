package tests;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.Homepage;
import pages.Loginpage;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Test1 {

	public static void main(String[] args)throws Exception {
	//open excel file for reading
	File f=new File("testdata.xls");
	Workbook rwb=Workbook.getWorkbook(f);
	Sheet rsh=rwb.getSheet(0);
	int nour=rsh.getRows();
	int nouc=rsh.getColumns();
	
	//open excel file for writing
	WritableWorkbook wwb=Workbook.createWorkbook(f,rwb);
	WritableSheet wsh=wwb.getSheet(0);
	
	//Data driven testing from 2nd row(index is1)
	//1st row index is 0 have names of columns
	for(int i=1;i<nour;i++){
	String u=rsh.getCell(0, i).getContents();
	String uc=rsh.getCell(1,i).getContents();
	String p=rsh.getCell(2, i).getContents();
	String pc=rsh.getCell(3,i).getContents();
	
	//Launch site
	System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.gmail.com");
	Thread.sleep(5000);
	
	//create objects to page classes
	Homepage hp=new Homepage(driver);
	Loginpage lp=new Loginpage(driver);
	
	//enter user id & validations
	hp.filluid(u);
	Thread.sleep(5000);
	
	
	try {
		if(uc.equals("valid")&&driver.findElement(lp.pwd).isDisplayed())
		{
			lp.fillpwd(p);
			Thread.sleep(5000);
			
			if(pc.equals("valid")&&driver.findElement(By.xpath("(//*[contains(text(),'Inbox')])[2]")).isDisplayed())
					{
				Label l=new Label(nouc,i,"Pwd Test Passed");
				wsh.addCell(l);
					}
			else if(pc.equals("invalid")&&driver.findElement(lp.pwderr).isDisplayed())
			{
				Label l=new Label(nouc,i,"Pwd Test Passed");
				wsh.addCell(l);
			}
			else
			{
				Label l=new Label(nouc,i,"Pwd Test Failed");
				wsh.addCell(l);
			}
		}
		else if(uc.equals("invalid")&&driver.findElement(hp.uiderr).isDisplayed())
				{
			Label l=new Label(nouc,i,"Uid Test Passed");
			wsh.addCell(l);
				}
		else
		{
			Label l=new Label(nouc,i,"Uid Test Failed");
			wsh.addCell(l);
		}
	} 
	
	catch (Exception e) {	
		Label l=new Label(nouc,i,"Login Test Interrupted");
		wsh.addCell(l);
	}
	driver.close();
	}
	wwb.write();
	rwb.close();
	wwb.close();
}
}