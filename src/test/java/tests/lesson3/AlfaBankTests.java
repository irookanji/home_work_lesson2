package tests.lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AlfaBankTests {
    @BeforeAll
    static public void config() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkDepositTest() {
        open("https://alfabank.ru/make-money/");

        // Go to Deposits tab
        $$(byText("Депозиты")).find(visible).parent().click();

        // Go to Archived debts & deposits tab
        $$(byText("Архивные счета и депозиты")).find(visible).parent().click();

        // Choose Deposits tab
        $$(byText("Депозиты")).find(visible).parent().click();

        // Check that Deposits = 5
        $("#filter").$$("[data-widget-name=CatalogCard]").shouldHaveSize(5);

    }

    @Test
    void siblingLocatorTest() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=tabs-list-tabTitle-0]").sibling(0).scrollIntoView(true).click();

        // Assert
        $("[data-test-id=accordion-header-0]").shouldHave(text("Альфа-Банк является участником системы обязательного страхования вкладов"));

    }

    @Test
    void precedingAndParentLocatorsTest() {
        open("https://alfabank.ru/make-money/");
        $("[data-test-id=tabs-list-tabTitle-2]").preceding(0).scrollIntoView(true).click();

        // Assert
        $("[data-test-id=accordion-item-0]").parent().shouldHave(text("Страхованию подлежат"));
    }


    @Test
    void closestLocatorTest() {
        open("https://alfabank.ru");

        $$(byText("Вклады")).find(visible).click();
        $x("//*[contains(text(),'Страхование вкладов')]").closest("button").click();

        // Assert
        $$x("//div[@class='a1Etq03']").shouldHaveSize(8);
    }

}
