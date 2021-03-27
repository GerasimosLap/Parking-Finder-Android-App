package com.example.parking.util;
import java.math.BigDecimal;

public class Currency {

    private BigDecimal amount;
    private CurrencyEnum currency;

    public Currency(BigDecimal amount, CurrencyEnum currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Currency() {
        this.amount = new BigDecimal(0);
        this.currency = CurrencyEnum.EUR;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
