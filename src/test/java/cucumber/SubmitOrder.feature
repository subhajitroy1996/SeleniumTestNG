@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background: 
Given I landed on Ecommerce page 
  @Regression
 
  Scenario Outline: Positive Test of Submitting the order
  
    Given Logged in with username <name> and password <password>
    When I add product <productname> to cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | name  								 | password 			| productname  |
      | subhajit1996@gmail.com | Subhajit@1996. | ZARA				 |
      
   @ErrorValidation
  Scenario Outline: Negative Test for Login    
  
    When  Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  								 | password 		 |
      | subhajit1996@gmail.com | Subhajit@1996 |
      
      
