package testScript;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pages.HomePage;
import pages.LoginPage;
import pages.ManageCategoryPage;
import utilities.ExcelUtility;

public class ManageCategoryTest extends Base {

	public HomePage homepage;
	public ManageCategoryPage managecategory;

	@Test(description = "To add New category", groups = { "Regression Testing" })
	public void VerifyTheUserIsAbleToAddCategory() throws IOException, AWTException {

		// Read user name and password from excel
		String username = ExcelUtility.getStringData(1, 0, "loginpage");
		String password = ExcelUtility.getStringData(1, 1, "loginpage");
		LoginPage login = new LoginPage(driver);
		login.enterTheUserName(username).enterThePassword(password);
		// once clicked on signIn the page will redirect from login page to Home page.
		homepage = login.clickOnSignIn();

		// once clicked on Manage Category More Info link the page will redirect from home page to manage category page.
		managecategory = homepage.clickOnManageCategoryMoreInfo();
		managecategory.clickOnNew();
		// reading category from excel
		String categoryInput = ExcelUtility.getStringData(3, 0, "ManageCategory");
		managecategory.addCategory(categoryInput).selectGroup().uploadImage();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
		managecategory.javaScriptScroll(driver).selectTopMenuOption().selectLeftMenuOption();

		managecategory.clickOnSave();

		// Assertion
		boolean visibilityAlert = managecategory.isAlertDisplayed();
		Assert.assertTrue(visibilityAlert, Constant.CREATECATEGORY);

	}
}
