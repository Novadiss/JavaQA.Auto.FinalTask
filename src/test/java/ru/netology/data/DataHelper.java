package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static ValidChooseForForm validChooseForForm() {
        return new ValidChooseForForm("1111 2222 3333 4444", "5555 6666 7777 8888", "03", "25", "VASYA PUPKIN", "333");
    }

    public static Owner generateValidOwner() {
        return new Owner(
                (faker.name().firstName().toUpperCase(Locale.ROOT) + " " + faker.name().lastName().toUpperCase(Locale.ROOT)),
                faker.name().firstName().toUpperCase(Locale.ROOT))
                ;
    }

    public static OwnerTestValue getOwnerTestValue() {
        return new OwnerTestValue(
                (faker.name().firstName().toLowerCase(Locale.ROOT) + " " + faker.name().lastName().toLowerCase(Locale.ROOT)),
                "ВАСЯ ПУПКИН",
                faker.numerify("#######"),
                "!#@$!$#!@#",
                (faker.name().firstName().toUpperCase(Locale.ROOT) + " " + faker.name().lastName().toUpperCase(Locale.ROOT) + " " + faker.name().lastName().toUpperCase(Locale.ROOT))
        );
    }

    public static CardNumber getApprovedCard() {
        return new CardNumber("1111 2222 3333 4444", "APPROVED");
    }

    public static CardNumber getDeclinedCard() {
        return new CardNumber("5555 6666 7777 8888", "DECLINED");
    }

    public static CardNumberTestValue getIncorrectCard() {
        return new CardNumberTestValue(
                faker.numerify("#### #### #### ####"),
                faker.numerify("#### #### #### ###"),
                "aaaa aaaa aaaa aaaa",
                "!@#$ (*&% !@#$ ^$%#")
                ;
    }

    public static CVCCode generateRandomCVCCode() {
        return new CVCCode(faker.numerify("###"));
    }

    public static String generateMonth() {
        return LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }


    public static ButtonForInsert buttonForInsert() {
        return new ButtonForInsert("Купить", "Купить в кредит", "Продолжить");
    }

    public static FormName formName() {
        return new FormName("Номер карты", "Месяц", "Год", "Владелец", "CVC/CVV");
    }

    public static StatusTransaction statusTransaction() {
        return new StatusTransaction("APPROVED", "DECLINED");
    }

    public static CheckTear checkTear() {
        return new CheckTear("payment_entity", "credit_request_entity");
    }

    public static TearColumnId tearColumnId() {
        return new TearColumnId("payment_id", "credit_id");
    }

    public static TransactionId getTransactionId(String tear) {
        return new TransactionId(SQLHelper.getTransactionId(tear).getId());
    }

    public static OperationStatus getPaymentStatus() {
        return new OperationStatus(SQLHelper.getStatus(checkTear().getPaymentTear(), tearColumnId().getPayment_id()).getStatus());
    }

    public static OperationStatus getCreditStatus() {
        return new OperationStatus(SQLHelper.getStatus(checkTear().getCreditTear(), tearColumnId().getCredit_id()).getStatus());
    }

    public static InformationMessage getInformationMessage() {
        return new InformationMessage("Операция одобрена Банком.", "Ошибка! Банк отказал в проведении операции");
    }

    public static MistakesMessage getMistakesMessage() {
        return new MistakesMessage("Неверный формат", "Истёк срок действия карты", "Неверно указан срок действия карты", "Поле обязательно для заполнения");
    }

    public static MonthTestValue getMonthTestValue() {
        return new MonthTestValue("00", "01", "1", "13", "12", "aa", "%!");
    }

    public static YearTestValue getYearTestValue() {
        return new YearTestValue(
                LocalDate.now().format(DateTimeFormatter.ofPattern("yy")),
                LocalDate.now().plusYears(5).format(DateTimeFormatter.ofPattern("yy")),
                LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy")),
                LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy")),
                "1",
                "99",
                "aa",
                "%!")
                ;
    }

    public static CVCTestValue getCVCTestValue() {
        return new CVCTestValue("001", "999", "000", "22", "aaa", "%!$");
    }


    @Value
    public static class CardNumber {
        String cardNumber;
        String status;
    }

    @Value
    public static class CVCCode {
        String code;
    }

    @Value
    public static class Owner {
        String firstNameAndLastName;
        String firstName;
    }

    @Value
    public static class ValidChooseForForm {
        String approvedCard;
        String declineCard;
        String month;
        String year;
        String name;
        String codeCSV;
    }

    @Value
    public static class ButtonForInsert {
        String buy;
        String buyForCredit;
        String next;
    }

    @Value
    public static class FormName {
        String cardNumber;
        String month;
        String year;
        String name;
        String codeCSV;
    }

    @Value
    public static class StatusTransaction {
        String approved;
        String declined;
    }

    @Value
    public static class OperationStatus {
        String status;
    }

    @Value
    public static class TransactionId {
        String id;
    }

    @Value
    public static class CheckTear {
        String paymentTear;
        String creditTear;
    }

    @Value
    public static class TearColumnId {
        String payment_id;
        String credit_id;
    }

    @Value
    public static class InformationMessage {
        String approvedBankMessage;
        String declinedBankMessage;
    }

    @Value
    public static class MistakesMessage {
        String formatMistake;
        String timeoutCardMistake;
        String notCorrectCartDateMistake;
        String formMustToInsertMistake;
    }

    @Value
    public static class MonthTestValue {
        String zeroMonth;
        String firstMonth;
        String oneDigitMonth;
        String thirteenMonth;
        String lastMonth;
        String letterMonth;
        String symbolMonth;
    }

    @Value
    public static class YearTestValue {
        String nowYear;
        String maxYear;
        String overMaxYear;
        String lastYear;
        String oneDigitYear;
        String maxValueYear;
        String letterYear;
        String symbolYear;
    }

    @Value
    public static class OwnerTestValue {
        String lowercaseOwner;
        String cyrillicOwner;
        String numberOwner;
        String symbolsOwner;
        String treeWordsOwner;
    }

    @Value
    public static class CVCTestValue {
        String firsCode;
        String lastCode;
        String zeroCode;
        String twoLetterCode;
        String letterCode;
        String symbolCode;
    }

    @Value
    public static class CardNumberTestValue {
        String notRegisteredCardNumber;
        String notFullCardNumber;
        String letterCardNumber;
        String symbolCardNumber;
    }

}
