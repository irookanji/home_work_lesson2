package tests.lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class LambdaStepsTests {
    @BeforeAll
    static public void config() {

        Configuration.startMaximized = true;
    }

    @Test
    void checkCreationIssueTest() {
        open("https://github.com");
    }

}
