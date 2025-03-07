package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Setter
@Getter
public class Payment {
    String id;
    String method;
    Order order;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.setMethod(method);
        this.order = order;
        this.setStatus(PaymentStatus.PENDING.getValue());
        this.setPaymentData(method, paymentData);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this(id, method, order, paymentData);
        this.setStatus(status);
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid status");
        }
    }
    public void setMethod(String method) {
        if (PaymentMethod.contains(method)) {
            this.method = method;
        } else {
            throw new IllegalArgumentException("Invalid method");
        }
    }

    public void setPaymentData(String method, Map<String, String> paymentData) {
        if(validatePaymentData(method, paymentData)){
            this.status = PaymentStatus.SUCCESS.getValue();
            this.paymentData = paymentData;
        }
        else {
            this.setStatus(PaymentStatus.REJECTED.getValue());
        }
        
    }

    private boolean validatePaymentData(String method, Map<String, String> paymentData) {
        if(paymentData.isEmpty()) {
            return false;
        }
        
        if(method.equals(PaymentMethod.VOUCHER.getValue())) {
            if(! validateVoucherPayment(paymentData)) {
                return false;
            }
        }

        if(method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            if(! validateBankTransferPayment(paymentData)) {
                return false;
            }
        }
        return true;
    }

    private boolean validateVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int numericCount = 0;
        for(char ch : voucherCode.toCharArray()) {
            if(Character.isDigit(ch)) numericCount++;
        }

        if(numericCount != 8) {
            return false;
        }

        return true;
    }

    private boolean validateVoucherPayment(Map<String,String> paymentData) {
        if (paymentData.get("voucherCode") == null) {
            return false;
        }

        String voucherCode = paymentData.get("voucherCode");

        if(!validateVoucherCode(voucherCode)) {
            return false;
        }

        return true;
    }

    private boolean validateBankTransferPayment(Map<String,String> paymentData) {
        if (paymentData.get("bankName") == null || paymentData.get("bankName").isBlank()) {
            return false;
        }

        if (paymentData.get("referenceCode") == null || paymentData.get("referenceCode").isBlank()) {
            return false;
        }

        return true;
    }

}
