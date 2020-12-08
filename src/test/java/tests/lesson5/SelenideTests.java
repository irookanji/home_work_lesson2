package tests.lesson5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.PropertiesReader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    private static final String login = PropertiesReader.getCreds("login");
    private static final String password = PropertiesReader.getCreds("password");

    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkCreationIssueTest() {
        open("https://github.com");

        // Login
        $x("//a[@href='/login']").click();
        $("#login_field").val(login);
        $("#password").val(password);
        $x("//input[@type='submit']").click();

        // Search for repository
        $(".header-search-input").setValue("irookanji/qa_guru_home_works").pressEnter();
        $x("//a[@href='/irookanji/qa_guru_home_works']").click();

        // Go to "Issues" tab
        $x("//span[text()='Issues']").click();

        // Create new issue
        $x("//span[contains(@class,'d-none d-md-block') and contains(text(),'New issue')]").click();
        // Fill title
        $x("//input[contains(@aria-label,'Title')]").val("Some test Issue");
        // Assign myself
        $x("//*[@id='new_issue']//span/button").click();
        // Choose label 'documentation'
        $("#labels-select-menu").click();
        $x("//span[text()='documentation']").click();
        $("#labels-select-menu").click();
        // Submit
        $x("//button[contains(text(),'Submit new issue')]").click();

        // Assert
        $x("//span[@class='js-issue-title']").shouldHave(text("Some test Issue"));

    }

}
