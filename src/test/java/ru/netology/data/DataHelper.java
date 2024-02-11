package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    public static ValidChooseForForm validChooseForForm(){
        return new ValidChooseForForm("1111 2222 3333 4444", "5555 6666 7777 8888", "03", "25", "VASYA PUPKIN","333");
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

    public static OperationStatus getOperationStatus (String tear, String tearForId ) {
        return new OperationStatus(SQLHelper.getStatus(tear, tearForId).getStatus());
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
}
