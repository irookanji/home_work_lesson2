package tests.lesson5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTests {
    private final static String REPOSITORY = "irookanji/qa_guru_home_works";
    private static final String login = PropertiesReader.getCreds("login");
    private static final String password = PropertiesReader.getCreds("password");

    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkCreationIssueTest() {
        step("Open main page", () -> {
            final String url = "https://github.com";
            open(url);
            Allure.link("Testing", url);
        });
        step("Login with email & password", () -> {
            $x("//a[@href='/login']").click();
            $("#login_field").val(login);
            $("#password").val(password);
            $x("//input[@type='submit']").click();
        });
        step("Searching for repository " + REPOSITORY, (step) -> {
            step.parameter("name", REPOSITORY);

            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Go to repository " + REPOSITORY, (step) -> {
            step.parameter("name", REPOSITORY);
            $x("//a[@href='/irookanji/qa_guru_home_works']").click();
        });
        step("Go to Issues tab", () -> $x("//span[text()='Issues']").click());
        step("Create new issue", () -> $x("//span[contains(@class,'d-none d-md-block') and contains(text(),'New issue')]").click());
        step("Fill title", () -> {
            $x("//input[contains(@aria-label,'Title')]").val("Some test Issue");
        });
        step("Assign myself", () -> $x("//*[@id='new_issue']//span/button").click());
        step("Choose label 'documentation'", () -> {
            $("#labels-select-menu").click();
            $x("//span[text()='documentation']").click();
            $("#labels-select-menu").click();
        });
        step("Submit", () -> $x("//button[contains(text(),'Submit new issue')]").click());
        step("Assert that title have expected text", () -> {
            $x("//span[@class='js-issue-title']").shouldHave(Condition.text("Some test Issue"));
        });
    }

}
