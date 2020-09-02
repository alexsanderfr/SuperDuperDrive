package com.udacity.jwdnd.course1.cloudstorage.model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "loginLink")
    private WebElement loginLink;

    @FindBy(id = "successMsg")
    private WebElement successMsg;

    @FindBy(id = "errorMsg")
    private WebElement errorMsg;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public boolean isSignupSuccessful() {
        return successMsg.isDisplayed();
    }

    public boolean isSignupUnsuccessful() {
        return errorMsg.isDisplayed();
    }
}