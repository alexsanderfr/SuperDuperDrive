package com.udacity.jwdnd.course1.cloudstorage.model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "signupLink")
    private WebElement signupLink;

    @FindBy(id = "invalidUsernameAlert")
    private WebElement invalidUsernameAlert;

    @FindBy(id = "logoutAlert")
    private WebElement logoutAlert;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }

    public void goToSignup() {
        signupLink.click();
    }

}
