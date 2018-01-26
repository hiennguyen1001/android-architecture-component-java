package hiennguyen.me.architecture.example.data.models;

import android.support.annotation.Nullable;

public class PromotionCode {

    private double discountRate;

    private Status status;

    @Nullable
    private String value;

    public PromotionCode(double discountRate, Status status, @Nullable String value) {
        this.discountRate = discountRate;
        this.status = status;
        this.value = value;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Nullable
    public String getValue() {
        return value;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status{
        VALID,
        INVALID,
        OUT_OF_DATE
    }
}