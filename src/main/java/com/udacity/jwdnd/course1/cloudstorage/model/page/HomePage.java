package com.udacity.jwdnd.course1.cloudstorage.model.page;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "fileUploadSubmitButton")
    private WebElement fileUploadSubmitButton;

    @FindBy(id = "fileTable")
    private WebElement fileTable;

    @FindBy(id = "addNoteButton")
    private WebElement addNoteButton;

    @FindBy(id = "noteTable")
    private WebElement noteTable;

    @FindBy(id = "noteBody")
    private WebElement noteBody;

    @FindBy(id = "noteModal")
    private WebElement noteModal;

    @FindBy(id = "note-id")
    private WebElement noteIdField;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitButton;

    @FindBy(id = "addCredentialButton")
    private WebElement addCredentialButton;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    @FindBy(id = "credentialBody")
    private WebElement credentialBody;

    @FindBy(id = "credentialModal")
    private WebElement credentialModal;

    @FindBy(id = "credential-id")
    private WebElement credentialIdField;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmitButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void createNote(Note note) {
        addNoteButton.click();
        noteTitleField.sendKeys(note.getNoteTitle());
        noteDescriptionField.sendKeys(note.getNoteDescription());
        noteSubmitButton.click();
    }

    public void createCredential(Credential credential) {
        addCredentialButton.click();
        credentialUrlField.sendKeys(credential.getUrl());
        credentialUsernameField.sendKeys(credential.getUsername());
        credentialPasswordField.sendKeys(credential.getPassword());
        credentialSubmitButton.click();
    }

    public void logout() {
        logoutButton.click();
    }
}
