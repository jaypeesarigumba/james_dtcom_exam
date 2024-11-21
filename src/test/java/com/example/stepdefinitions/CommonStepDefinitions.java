package com.example.stepdefinitions;

import com.example.testbase.webUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Objects;

public class CommonStepDefinitions {

    webUtils webLibrary = webUtils.getInstance();


    @Given("I navigate to the {string} URL")
    public void iNavigateToTheURL(String url) {
        webLibrary.launchWebPage(url);
    }

    @When("the page is loaded")
    public void thePageIsLoaded() {
        webLibrary.waitforPagetoLoad();
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        String actualTitle=webLibrary.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
    }

    @And("the {string} section should be visible with xpath {string}")
    public void theSectionShouldBeVisibleWithXpath(String section, String xpath) {
        Assert.assertTrue(webLibrary.elementIsVisible(xpath),  section+" section is visible on the page.");

    }

    @When("I click the country {string}")
    public void iClickTheCountry(String country) {
        webLibrary.elementClick("//li[@data-tab-section-id='.item0']");
    }

    @And("I click the city {string} with {string}")
    public void iClickTheCityWith(String city, String xpath) {
        if(!Objects.equals(city, "Auckland")){
            webLibrary.elementClick(xpath);
        }
    }

//    @Then("the Addreess should be {string} with {}")
//    public void theAddreessShouldBeWith(String expectedTxt, String xpath) {
//        String actualText = webLibrary.getElementTextByXpath(xpath);
//        Assert.assertEquals(actualText, expectedTxt, "Address text does not match!");
//
//    }


    @Then("the {string} should be {string} with {}")
    public void theShouldBeWith(String element, String expectedText, String xpath) {
        String actualText = webLibrary.getElementTextByXpath(xpath);
        Assert.assertEquals(actualText, expectedText, element+" text does not match!");
    }

    @Then("the Address {string} should be {string}")
    public void theAddressShouldBe(String section, String expectedText) {
       // String xpath ="//div[@class='cmp-location__location__details']//p[@class='cmp-location__location__address'][1]";
        String xpath="//div[@id='section-"+section+"']//p[contains(@class, 'cmp-location__location__address')]";
        String actualText = webLibrary.getElementTextByXpath(xpath);
        Assert.assertEquals(actualText, expectedText, "Address text does not match!");
    }

    @And("the Phone number {string} should be {string}")
    public void thePhoneNumberShouldBe(String section, String expectedText) {
        String xpath="//div[@id='section-"+section+"']//p[contains(@class, 'cmp-location__location__phone')]";
        String actualText = webLibrary.getElementTextByXpath(xpath);
        Assert.assertEquals(actualText, expectedText, "Phone Number text does not match!");
    }


    @And("the Email {string} should be {string}")
    public void theEmailShouldBe(String section, String expectedText) {
        String xpath="//div[@id='section-"+section+"']//p[contains(@class, 'cmp-location__location__email')]";
        String actualText = webLibrary.getElementTextByXpath(xpath);
        Assert.assertEquals(actualText, expectedText, "Email text does not match!");
    }
}
