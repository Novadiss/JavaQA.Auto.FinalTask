package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.TravelPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidTestCreditForm {
    TravelPage travelPage;
    String pay = DataHelper.checkTear().getPaymentTear();
    String payId = DataHelper.tearColumnId().getPayment_id();
    String credit = DataHelper.checkTear().getCreditTear();

    String creditId = DataHelper.tearColumnId().getCredit_id();

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

    @AfterEach
    void tearDown() {
        SQLHelper.cleanOrderEntity();
    }

    @AfterAll
    static void tearDownAll() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with all valid values")
    void allValidValues() {
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with minimal value for month and valid other values")
    void minMonthAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getMonth());
        travelPage.setForm(DataHelper.formName().getMonth(), "01");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with maximum value for month and valid other values")
    void maxMonthAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getMonth());
        travelPage.setForm(DataHelper.formName().getMonth(), "12");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with minimal value for year and valid other values")
    void minYearAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getYear());
        travelPage.setForm(DataHelper.formName().getYear(), "24");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with maximum value for year and valid other values")
    void maxYearAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getYear());
        travelPage.setForm(DataHelper.formName().getYear(), "29");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with only one word in form 'Name' and valid other values")
    void formNameWithOnlyOneWordAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getName());
        travelPage.setForm(DataHelper.formName().getName(), "VASYA");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with minimum value for code and valid other values")
    void minCodeAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), "001");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with maximum value for code and valid other values")
    void maxCodeAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getCodeCSV());
        travelPage.setForm(DataHelper.formName().getCodeCSV(), "999");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getApproved();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful credit from the travel and find transaction in DB with card whose transaction declined from bank and valid other values")
    void cardNumberWithDeclinedTransactionAndValidOtherValue() {
        travelPage.cleanForm(DataHelper.formName().getCardNumber());
        travelPage.setForm(DataHelper.formName().getCardNumber(), "5555 6666 7777 8888");
        travelPage.clickButton(DataHelper.buttonForInsert().getNext());
        travelPage.findConfirmedMessage("Операция одобрена Банком.");
        var actual = DataHelper.getOperationStatus(credit, creditId).getStatus();
        var expected = DataHelper.statusTransaction().getDeclined();
        assertEquals(expected, actual);
    }
}
