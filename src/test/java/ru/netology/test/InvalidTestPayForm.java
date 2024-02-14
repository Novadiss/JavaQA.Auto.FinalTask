package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TravelPage;

import static com.codeborne.selenide.Selenide.open;

public class InvalidTestPayForm {

    TravelPage travelPage;

    @BeforeEach
    void insertStarrValues() {
        travelPage = open("http://localhost:8080", TravelPage.class);
        travelPage.clickButton(DataHelper.buttonForInsert().getBuy());
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of one digit (1)")
    void shouldGetErrorNotificationIfMonthFormHaveOneDigit() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getOneDigitMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of impossible month (13)")
    void shouldGetErrorNotificationIfMonthFormHaveImpossibleValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getThirteenMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), DataHelper.getMistakesMessage().getNotCorrectCartDateMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of last year (23)")
    void shouldGetErrorNotificationIfYearFormHaveValueOfLastYear() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getLastYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), DataHelper.getMistakesMessage().getTimeoutCardMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect maximum value (99)")
    void shouldGetErrorNotificationIfYearFormHaveIncorrectMaximumValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getMaxValueYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), DataHelper.getMistakesMessage().getNotCorrectCartDateMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of longer then validity period (30)")
    void shouldGetErrorNotificationIfYearFormHaveValueLongerThenValidityPeriod() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getOverMaxYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), DataHelper.getMistakesMessage().getNotCorrectCartDateMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of two number (22)")
    void shouldGetErrorNotificationIfCodeFormHaveValueOfTwoNumber() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getTwoLetterCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of random unregistered card")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfUnregisteredCard() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getIncorrectCard().getNotRegisteredCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findErrorMessage(DataHelper.getInformationMessage().getDeclinedBankMessage());
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of 15 numbers (5555 5555 5555 555)")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfFifteenthNumbers() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getIncorrectCard().getNotFullCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect zero value")
    void shouldGetErrorNotificationIfMonthFormHaveZeroValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getZeroMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), DataHelper.getMistakesMessage().getNotCorrectCartDateMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of letters (aa)")
    void shouldGetErrorNotificationIfMonthFormHaveLettersValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getLetterMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'month' insert incorrect value of symbols (%!)")
    void shouldGetErrorNotificationIfMonthFormHaveSymbolsValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getSymbolMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getMonth(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of letters (aa)")
    void shouldGetErrorNotificationIfYearFormHaveLettersValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getLetterYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'year' insert incorrect value of symbols (№!)")
    void shouldGetErrorNotificationIfYearFormHaveSymbolsValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getSymbolYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getYear(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of lowercase letter")
    void shouldGetErrorNotificationIfNameFormHaveValueOfLowercaseLetter() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.getOwnerTestValue().getLowercaseOwner());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getName(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of Cyrillic (ВАСЯ ПУПКИН)")
    void shouldGetErrorNotificationIfNameFormHaveValueOfCyrillic() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.getOwnerTestValue().getCyrillicOwner());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getName(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of number")
    void shouldGetErrorNotificationIfNameFormHaveValueOfNumber() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.getOwnerTestValue().getNumberOwner());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getName(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of symbols (!!;;%))")
    void shouldGetErrorNotificationIfNameFormHaveValueOfSymbols() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.getOwnerTestValue().getSymbolsOwner());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getName(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Name' insert incorrect value of tree words (VASYA VASYA VASYA)")
    void shouldGetErrorNotificationIfNameFormHaveValueOfTreeWords() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.getOwnerTestValue().getTreeWordsOwner());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getName(), DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Code' insert incorrect zero value (000)")
    void shouldGetErrorNotificationIfCodeFormHaveZeroValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getZeroCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(),  DataHelper.getMistakesMessage().getFormatMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of letters (aaa)")
    void shouldGetErrorNotificationIfCodeFormHaveValueOfLetters() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getLetterCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(),  DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'Code' insert incorrect value of symbols (%!№)")
    void shouldGetErrorNotificationIfCodeFormHaveValueOfSymbols() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getSymbolCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCodeCSV(),  DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of letters")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfLetters() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getIncorrectCard().getLetterCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }

    @Test
    @DisplayName("Should get error notification if in form 'CardNumber' insert value of symbols")
    void shouldGetErrorNotificationIfCardNumberFormHaveValueOfSymbols() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getIncorrectCard().getSymbolCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findInvalidFormat(DataHelper.formName().getCardNumber(), DataHelper.getMistakesMessage().getFormMustToInsertMistake());
    }
}
