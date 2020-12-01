package tests.lesson7;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GooglePullRequestTests {
    @Test
    void openGoogleTest() {
        Configuration.browser = "firefox";
        Configuration.startMaximized = true;
        // Open google
        open("https://google.com");

        // Assert that title equals 'Google'
        $("title").shouldHave(attribute("text", "Google"));
        }
}
