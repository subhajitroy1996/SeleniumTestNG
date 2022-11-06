package RahulShettyAcademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import RahulShettyAcademy.TestComponents.BaseTest;
import RahulShettyAcademy.pageobjects.Cart;
import RahulShettyAcademy.pageobjects.Confirmation;
import RahulShettyAcademy.pageobjects.FillDetails;

import RahulShettyAcademy.pageobjects.ProductCatelogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImplementation extends BaseTest{

public ProductCatelogue p;
public Confirmation co;
public String msg;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		launch();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username,String password) 
	{
		p=l.login(username,password);
		msg=l.getErrorMsg();
	}
	
	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String prod)
	{
		p.addToCart(prod);
	}
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String prod)
	{
		Cart c=p.Cart();
		Assert.assertTrue(c.check(prod));
		
		FillDetails f=c.checkout();
		f.setCountry("ind");
		 co=f.Confirm();
		 msg=co.getmsg();
	}
	
	@Then("{string} message is displayed")
	public void  message_is_displayed(String message)
	{
		Assert.assertEquals(msg,message);
		driver.close();
	}

}
