package testScript;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import constant.Constant;
import pages.HomePage;
import pages.LoginPage;
import pages.ManageContactPage;
import utilities.ExcelUtility;

public class ManageContactTest extends Base {

	public HomePage homepage;
	public ManageContactPage managecontact;

	@Test(description = " To update contact details", groups = { "Regression Testing" })
	public void VerifyTheUserIsAbleToUpdateContactDetails() throws IOException {

		// Reading user name and password from excel
		String username = ExcelUtility.getStringData(1, 0, "loginpage");
		String password = ExcelUtility.getStringData(1, 1, "loginpage");
		LoginPage login = new LoginPage(driver);
		login.enterTheUserName(username).enterThePassword(password);
		// once clicked on signIn the page will redirect from login page to Home page.
		homepage = login.clickOnSignIn();

		// once clicked on Manage Category More Info link the page will redirect from home page to manage contact page.
		managecontact = homepage.manageContactMoreInfo();
		managecontact.clickOnAction();
		// Reading phone number from excel
		String phone = ExcelUtility.getStringData(1, 0, "ManageContact");
		// Reading email from excel
		String email = ExcelUtility.getStringData(1, 1, "ManageContact");
		// Reading address from excel
		String address = ExcelUtility.getStringData(1, 2, "ManageContact");
		// Reading delivery time from excel
		String deliveryTime = ExcelUtility.getIntegerData(1, 3, "ManageContact");
		// Reading delivery charge from excel
		String deliveryCharge = ExcelUtility.getIntegerData(1, 4, "ManageContact");

		managecontact.updatePhone(phone).updateEmail(email).updateAddress(address).updateDeliveryTime(deliveryTime)
				.updateDeliveryChargeLimit(deliveryCharge);
		managecontact.clickOnUpdate();

		// Assertion
		boolean visibilityAlert = managecontact.isAlertDisplayed();
		Assert.assertTrue(visibilityAlert, Constant.UPDATECONTACT);

	}
}
