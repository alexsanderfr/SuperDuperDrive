package com.udacity.jwdnd.course1.cloudstorage.model.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage {
    @FindBy(id = "successDiv")
    private WebElement successDiv;

    @FindBy(id = "errorDiv")
    private WebElement errorDiv;

    @FindBy(id = "homeLink")
    private WebElement homeLink;

    public boolean isSuccessShown() {
        return successDiv.isDisplayed();
    }

    public boolean isErrorShown() {
        return errorDiv.isDisplayed();
    }

    public void goToHome() {
        homeLink.click();
    }
}
