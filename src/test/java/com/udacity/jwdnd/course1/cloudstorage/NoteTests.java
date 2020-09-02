package com.udacity.jwdnd.course1.cloudstorage;

import com.github.javafaker.Faker;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.model.page.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.model.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.model.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTests {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private WebDriverWait wait;
    private String baseURL;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
        this.driver = new ChromeDriver();
        this.wait = new WebDriverWait(driver, 30);
        signupAndLogin();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCreateNote() {
        driver.get(baseURL + "/home");
        HomePage homePage = new HomePage(driver);
        Faker faker = new Faker();
        Note note = new Note();
        note.setNoteTitle(faker.lorem().word());
        note.setNoteDescription(faker.lorem().paragraph());
        homePage.createNote(wait, note);
        ResultPage resultPage = new ResultPage(driver);
        wait.until(ExpectedConditions.titleIs("Result"));
        assertTrue(resultPage.isSuccessShown());
    }

    public void signupAndLogin() {
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        Faker faker = new Faker();
        String username = faker.name().username();
        String password = faker.lorem().word();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        signupPage.signup(firstName, lastName, username, password);
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
}
