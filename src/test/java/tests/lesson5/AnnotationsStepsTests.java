package tests.lesson5;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.*;

public class AnnotationsStepsTests {
    private final static String REPOSITORY = "irookanji/qa_guru_home_works";
    private final static String login = PropertiesReader.getCreds("login");
    private final static String password = PropertiesReader.getCreds("password");

    @BeforeAll
    static public void config() {

        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.startMaximized = true;
    }

    @Test
    @DisplayName("Наш любимый тест с аннотациями")
    @Feature("Issues")
    @Story("User should create issue in existing repository")
    @Link(url = "https://github.com", name = "Testing")
    @Owner("irookanji")
    @Severity(SeverityLevel.CRITICAL)
    public void creationIssueTest() {
        final BaseSteps steps = new BaseSteps();

        steps.openMainPage();
        steps.loginWithCreds();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepository();
        steps.goToIssues();
        steps.createNewIssue();
        steps.fillTitle();
        steps.assignMyself();
        steps.chooseLabel();
        steps.submitLabel();
        steps.shouldSeeCreatedIssueWithExpectedText();
    }

    public static class BaseSteps {

        @Step("Open main page")
        public void openMainPage() {
            open("https://github.com");
        }

        @Step("Login with email & password")
        public void loginWithCreds() {
            $x("//a[@href='/login']").click();
            $("#login_field").val(login);
            $("#password").val(password);
            $x("//input[@type='submit']").click();
        }

        @Step("Searching for repository {name}")
        public void searchForRepository(final String name) {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(name);
            $(".header-search-input").submit();
        }

        @Step("Go to repository")
        public void goToRepository() {
            $x("//a[@href='/irookanji/qa_guru_home_works']").click();
        }

        @Step("Go to Issues tab")
        public void goToIssues() {
            $x("//span[text()='Issues']").click();
        }

        @Step("Create new issue")
        public void createNewIssue() {
            $x("//span[contains(@class,'d-none d-md-block') and contains(text(),'New issue')]").click();
        }

        @Step("Fill title")
        public void fillTitle() {
            $x("//input[contains(@aria-label,'Title')]").val("Some test Issue");
        }

        @Step("Assign myself")
        public void assignMyself() {
            $x("//*[@id='new_issue']//span/button").click();
        }

        @Step("Choose label 'documentation'")
        public void chooseLabel() {
            $("#labels-select-menu").click();
            $x("//span[text()='documentation']").click();
            $("#labels-select-menu").click();
        }

        @Step("Submit")
        public void submitLabel() {
            $x("//button[contains(text(),'Submit new issue')]").click();
        }

        @Step("Assert that title have expected text")
        public void shouldSeeCreatedIssueWithExpectedText() {
            $x("//span[@class='js-issue-title']").shouldHave(Condition.text("Some test Issue"));
        }

    }
}
