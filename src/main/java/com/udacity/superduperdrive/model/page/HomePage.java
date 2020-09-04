package com.udacity.superduperdrive.model.page;

import com.udacity.superduperdrive.model.Credential;
import com.udacity.superduperdrive.model.Note;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "fileUpload")
    private WebElement fileUpload;

    @FindBy(id = "fileUploadSubmitButton")
    private WebElement fileUploadSubmitButton;

    @FindBy(id = "fileTable")
    private WebElement fileTable;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

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

    @FindBy(id = "note-save")
    private WebElement noteSaveButton;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;

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

    @FindBy(id = "credential-save")
    private WebElement credentialSaveButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void createNote(WebDriverWait wait, Note note) {
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleField)).sendKeys(note.getNoteTitle());
        noteDescriptionField.sendKeys(note.getNoteDescription());
        wait.until(ExpectedConditions.elementToBeClickable(noteSaveButton)).click();
    }

    public void editNote(WebDriverWait wait, Note note, String oldTitle) {
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab)).click();
        String noteEditElementId = String.format("%s-edit", oldTitle);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(noteEditElementId))).click();
        wait.until(ExpectedConditions.elementToBeClickable(noteTitleField)).clear();
        noteTitleField.sendKeys(note.getNoteTitle());
        noteDescriptionField.sendKeys(note.getNoteDescription());
        wait.until(ExpectedConditions.elementToBeClickable(noteSaveButton)).click();
    }

    public void deleteNote(WebDriverWait wait, String title) {
        wait.until(ExpectedConditions.elementToBeClickable(navNotesTab)).click();
        String noteDeleteElementId = String.format("%s-delete", title);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(noteDeleteElementId))).click();
    }

    public void createCredential(WebDriverWait wait, Credential credential) {
        wait.until(ExpectedConditions.elementToBeClickable(navCredentialsTab)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUrlField)).sendKeys(credential.getUrl());
        credentialUsernameField.sendKeys(credential.getUsername());
        credentialPasswordField.sendKeys(credential.getPassword());
        wait.until(ExpectedConditions.elementToBeClickable(credentialSaveButton)).click();
    }

    public void editCredential(WebDriverWait wait, Credential credential, String oldUsername) {
        wait.until(ExpectedConditions.elementToBeClickable(navCredentialsTab)).click();
        String credentialEditElementId = String.format("%s-edit", oldUsername);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(credentialEditElementId))).click();
        wait.until(ExpectedConditions.elementToBeClickable(credentialUsernameField)).clear();
        credentialUsernameField.sendKeys(credential.getUsername());
        credentialPasswordField.sendKeys(credential.getPassword());
        credentialUrlField.sendKeys(credential.getUrl());
        wait.until(ExpectedConditions.elementToBeClickable(credentialSaveButton)).click();
    }

    public void deleteCredential(WebDriverWait wait, String username) {
        wait.until(ExpectedConditions.elementToBeClickable(navCredentialsTab)).click();
        String credentialDeleteElementId = String.format("%s-delete", username);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(credentialDeleteElementId))).click();
    }

    public void logout() {
        logoutButton.click();
    }
}
