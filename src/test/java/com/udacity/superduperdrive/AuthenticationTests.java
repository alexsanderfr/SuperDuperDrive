package com.udacity.superduperdrive;

import com.github.javafaker.Faker;
import com.udacity.superduperdrive.model.page.HomePage;
import com.udacity.superduperdrive.model.page.LoginPage;
import com.udacity.superduperdrive.model.page.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthenticationTests {

    @LocalServerPort
    private int port;
    private WebDriver driver;
    private String baseURL;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testUserCantAccessHomeUnauthenticated() {
        driver.get(baseURL + "/login");
        assertEquals(driver.getTitle(), "Login");
        driver.get(baseURL + "/signup");
        assertEquals(driver.getTitle(), "Sign Up");
        driver.get(baseURL + "/home");
        assertNotEquals(driver.getTitle(), "Home");
    }

    @Test
    public void testSignup() {
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        Faker faker = new Faker();
        String username = faker.name().username();
        String password = faker.lorem().word();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        signupPage.signup(firstName, lastName, username, password);
        assertTrue(signupPage.isSignupSuccessful());
    }

    @Test
    public void testLogin() {
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
        driver.get(baseURL + "/home");
        assertEquals(driver.getTitle(), "Home");
        HomePage homePage = new HomePage(driver);
        homePage.logout();
        driver.get(baseURL + "/home");
        assertNotEquals(driver.getTitle(), "Home");
    }
}