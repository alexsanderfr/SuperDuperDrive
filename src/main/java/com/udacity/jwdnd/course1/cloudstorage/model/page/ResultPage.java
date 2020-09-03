package com.udacity.jwdnd.course1.cloudstorage.model.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "successDiv")
    private WebElement successDiv;

    @FindBy(id = "errorDiv")
    private WebElement errorDiv;

    @FindBy(id = "successHomeLink")
    private WebElement successHomeLink;

    @FindBy(id = "errorHomeLink")
    private WebElement errorHomeLink;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public boolean isSuccessShown() {
        return successDiv.isDisplayed();
    }

    public boolean isErrorShown() {
        return errorDiv.isDisplayed();
    }
}
