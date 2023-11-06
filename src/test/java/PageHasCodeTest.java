import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class PageHasCodeTest {
    public static String jUnit5Code = "@ExtendWith({SoftAssertsExtension.class})\n" +
            "class Tests {\n" +
            "@Test\n" +
            "void test() {\n" +
            "Configuration.assertionMode = SOFT;\n" +
            "open(\"page.html\");\n" +
            "$(\"#first\").should(visible).click();\n" +
            "$(\"#second\").should(visible).click();\n" +
            "}\n" +
            "}";
   @BeforeAll public static void configureTests() {
       Configuration.pageLoadStrategy = "eager";
       //Configuration.holdBrowserOpen = true;
       Configuration.timeout = 4000;
       Configuration.baseUrl = "https://github.com";
   }
    @Test
    public void pageHasText() {
        //Перейдите в раздел Wiki проекта
        open("/selenide/selenide/wiki");

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".js-wiki-more-pages-link").click();
        $(".wiki-rightbar").shouldHave(text("SoftAssertions"));

        //Откройте страницу SoftAssertions и проверьте, что внутри есть пример кода для JUnit5
        $(byText("SoftAssertions")).click();
        $("#user-content-3-using-junit5-extend-test-class").sibling(0)
                .shouldHave(text(jUnit5Code));
    }
        @AfterAll
        public static void clearAll() {
            clearBrowserCookies();
            clearBrowserLocalStorage();
            closeWindow();
            closeWebDriver();
            }
        }

