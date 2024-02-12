package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TravelPage;

import static com.codeborne.selenide.Selenide.open;

public class InvalidTestCreditForm {

    TravelPage travelPage;

    @BeforeEach
    void insertStarrValues() {
        travelPage = open("http://localhost:8080", TravelPage.class);
        travelPage.clickButton(DataHelper.buttonForInsert().getBuyForCredit());
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.validChooseForForm().getApprovedCard());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.validChooseForForm().getMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.validChooseForForm().getYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.validChooseForForm().getName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.validChooseForForm().getCodeCSV());
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of one digit (01)")
    void shouldGetErrorNotificationIfMonthFormHaveOneDigit() {
        travelPage.cleanForm(DataHelper.formName().getMonth());
        travelPage.setForm(DataHelper.formName().getMonth(), "1");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), "Неверный формат");
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of impossible month (13)")
    void shouldGetErrorNotificationIfMonthFormHaveImpossibleValue() {
        travelPage.cleanForm(DataHelper.formName().getMonth());
        travelPage.setForm(DataHelper.formName().getMonth(), "13");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of last year (23)")
    void shouldGetErrorNotificationIfYearFormHaveValueOfLastYear() {
        travelPage.cleanForm(DataHelper.formName().getYear());
        travelPage.setForm(DataHelper.formName().getYear(), "23");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), "Истёк срок действия карты");
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect maximum value (99)")
    void shouldGetErrorNotificationIfYearFormHaveIncorrectMaximumValue() {
        travelPage.cleanForm(DataHelper.formName().getYear());
        travelPage.setForm(DataHelper.formName().getYear(), "99");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of longer then validity period (30)")
    void shouldGetErrorNotificationIfYearFormHaveValueLongerThenValidityPeriod() {
        travelPage.cleanForm(DataHelper.formName().getYear());
        travelPage.setForm(DataHelper.formName().getYear(), "30");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of two number (00)")
    void shouldGetErrorNotificationIfCodeFormHaveValueOfTwoNumber() {
        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), "22");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(), "Неверный формат");
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of unregistered card (5555 6666 7777 8823)")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfUnregisteredCard() {
        travelPage.cleanForm(DataHelper.formName().getCardNumber());
        travelPage.setForm(DataHelper.formName().getCardNumber(), "5555 6666 7777 8823");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findErrorMessage("Ошибка! Банк отказал в проведении операции");
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of 15 numbers (5555 5555 5555 555)")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfFifteenthNumbers() {
        travelPage.cleanForm(DataHelper.formName().getCardNumber());
        travelPage.setForm(DataHelper.formName().getCardNumber(), "5555 5555 5555 555");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), "Неверный формат");
    }

//    @Test
//    @DisplayName("Should get error notification if in form 'month' insert incorrect zero value")
//    void shouldGetErrorNotificationIfMonthFormHaveZeroValue() {
//        travelPage.cleanForm(DataHelper.formName().getMonth());
//        travelPage.setForm(DataHelper.formName().getMonth(), "00");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), "Неверно указан срок действия карты");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'month' insert incorrect value of letters (aa)")
//    void shouldGetErrorNotificationIfMonthFormHaveLettersValue() {
//        travelPage.cleanForm(DataHelper.formName().getMonth());
//        travelPage.setForm(DataHelper.formName().getMonth(), "aa");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'month' insert incorrect value of symbols (%!)")
//    void shouldGetErrorNotificationIfMonthFormHaveSymbolsValue() {
//        travelPage.cleanForm(DataHelper.formName().getMonth());
//        travelPage.setForm(DataHelper.formName().getMonth(), "aa");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'year' insert incorrect value of letters (aa)")
//    void shouldGetErrorNotificationIfYearFormHaveLettersValue() {
//        travelPage.cleanForm(DataHelper.formName().getYear());
//        travelPage.setForm(DataHelper.formName().getYear(), "aa");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getYear(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'year' insert incorrect value of symbols (№!)")
//    void shouldGetErrorNotificationIfYearFormHaveSymbolsValue() {
//        travelPage.cleanForm(DataHelper.formName().getYear());
//        travelPage.setForm(DataHelper.formName().getYear(), "№!");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getYear(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of lowercase letter (vasya)")
//    void shouldGetErrorNotificationIfNameFormHaveValueOfLowercaseLetter() {
//        travelPage.cleanForm(DataHelper.formName().getName());
//        travelPage.setForm(DataHelper.formName().getName(), "vasya");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getName(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of Cyrillic (vasya)")
//    void shouldGetErrorNotificationIfNameFormHaveValueOfCyrillic() {
//        travelPage.cleanForm(DataHelper.formName().getName());
//        travelPage.setForm(DataHelper.formName().getName(), "ВАСЯ");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getName(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of number (8439)")
//    void shouldGetErrorNotificationIfNameFormHaveValueOfNumber() {
//        travelPage.cleanForm(DataHelper.formName().getName());
//        travelPage.setForm(DataHelper.formName().getName(), "8439");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getName(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of symbols (!!;;%))")
//    void shouldGetErrorNotificationIfNameFormHaveValueOfSymbols() {
//        travelPage.cleanForm(DataHelper.formName().getName());
//        travelPage.setForm(DataHelper.formName().getName(), "!!;;%)");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getName(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of tree words (VASYA VASYA VASYA)")
//    void shouldGetErrorNotificationIfNameFormHaveValueOfTreeWords() {
//        travelPage.cleanForm(DataHelper.formName().getName());
//        travelPage.setForm(DataHelper.formName().getName(), "VASYA VASYA VASYA");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getName(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Code' insert incorrect zero value (000)")
//    void shouldGetErrorNotificationIfCodeFormHaveZeroValue() {
//        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
//        travelPage.setForm(DataHelper.formName().getCodeCSV(), "000");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(), "Неверный формат");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of letters (aaa)")
//    void shouldGetErrorNotificationIfCodeFormHaveValueOfLetters() {
//        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
//        travelPage.setForm(DataHelper.formName().getCodeCSV(), "aaa");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of symbols (%!№)")
//    void shouldGetErrorNotificationIfCodeFormHaveValueOfSymbols() {
//        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
//        travelPage.setForm(DataHelper.formName().getCodeCSV(), "%!№");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'CardNumber' insert value of letters (aaaa aaaa aaaa aaaa)")
//    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfLetters() {
//        travelPage.cleanForm(DataHelper.formName().getCardNumber());
//        travelPage.setForm(DataHelper.formName().getCardNumber(), "aaaa aaaa aaaa aaaa");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), "Поле обязательно для заполнения");
//    }
//
//    @Test
//    @DisplayName("Should get error notification if in form 'CardNumber' insert value of symbols (aaaa aaaa aaaa aaaa)")
//    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfSymbols() {
//        travelPage.cleanForm(DataHelper.formName().getCardNumber());
//        travelPage.setForm(DataHelper.formName().getCardNumber(), "Поле обязательно для заполнения");
//        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
//        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), "Поле обязательно для заполнения");
//    }
}
