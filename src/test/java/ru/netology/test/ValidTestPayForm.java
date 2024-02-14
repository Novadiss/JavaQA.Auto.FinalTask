package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TravelPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidTestPayForm {

    TravelPage travelPage;

    @BeforeEach
    void insertStarrValues() {
        travelPage = open("http://localhost:8080", TravelPage.class);
        travelPage.clickButton(DataHelper.buttonForInsert().getBuy());
    }

    @AfterEach
    void tearDown() {
        SQLHelper.cleanOrderEntity();
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with all valid values")
    void allValidValues() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with minimal value for month and valid other values")
    void minMonthAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getFirstMonth());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with maximum value for month and valid other values")
    void maxMonthAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.getMonthTestValue().getLastMonth());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with minimal value for year and valid other values")
    void minYearAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getNowYear());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with maximum value for year and valid other values")
    void maxYearAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.getYearTestValue().getMaxYear());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with only one word in form 'Name' and valid other values")
    void formNameWithOnlyOneWordAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with minimum value for code and valid other values")
    void minCodeAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getFirsCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with maximum value for code and valid other values")
    void maxCodeAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getApprovedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.getCVCTestValue().getLastCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.getApprovedCard().getStatus();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful pay from the travel and find transaction in DB with card whose transaction declined from bank and valid other values")
    void cardNumberWithDeclinedTransactionAndValidOtherValue() {
        travelPage.setForm(DataHelper.formName().getCardNumber(), DataHelper.getDeclinedCard().getCardNumber());
        travelPage.setForm(DataHelper.formName().getMonth(), DataHelper.generateMonth());
        travelPage.setForm(DataHelper.formName().getYear(), DataHelper.generateYear());
        travelPage.setForm(DataHelper.formName().getName(), DataHelper.generateValidOwner().getFirstNameAndLastName());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), DataHelper.generateRandomCVCCode().getCode());
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage(DataHelper.getInformationMessage().getApprovedBankMessage());
        var actual = DataHelper.getPaymentStatus().getStatus();
        var expected = DataHelper.statusTransaction().getDeclined();
        assertEquals(expected, actual);
    }
}
