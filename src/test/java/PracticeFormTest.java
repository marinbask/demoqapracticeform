import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
                Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 7000;
    }

    @Test
    void fillFormTest() {
        //открываем форму
        open("/automation-practice-form");

        //заполняем поля имя_фамилия_емейл
        $("#firstName").setValue("Marina");
        $("#lastName").setValue("Baskova");
        $("#userEmail").setValue("test_test@test.com");
        //выбираем пол
        $("[for='gender-radio-2']").click();
        //вводим телефон
        $("#userNumber").setValue("1234567890");
        //вводим ДР
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1989");
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--004").click();
        //выбираем предметы
        $("#subjectsInput").val("English").pressEnter();
        $("#subjectsInput").val("History").pressEnter();
        $("#subjectsInput").val("Social Studies").pressEnter();
        //выбираем хобби
        $("[for='hobbies-checkbox-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();
        //добавляем картинку
        $("#uploadPicture").uploadFile(new File("src/test/picture/1.png"));
        //вводим адрес
        $("#currentAddress").setValue("Somewhere street 33");
        $("#react-select-3-input").val("Haryana").pressEnter();
        $("#react-select-4-input").val("Panipat").pressEnter();
        //нажимаем отправить
        $("#submit").pressEnter();

        //проверяем введенные данные
        $(".table-responsive").shouldHave(
                text("Marina Baskova"),
                text("test_test@test.com"),
                text("Female"),
                text("1234567890"),
                text("04 October,1989"),
                text("English, History, Social Studies"),
                text("Sports, Reading, Music"),
                text("1.png"),
                text("Somewhere street 33"),
                text("Haryana Panipat"));

    }
}