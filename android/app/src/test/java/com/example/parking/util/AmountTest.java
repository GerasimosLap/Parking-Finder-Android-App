package com.example.parking.util;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AmountTest {
    private Amount amount;
    private BigDecimal number;
    private Currency currency;

    @Test
    public void FullConTest() {
        Amount a = new Amount(new Currency(),new BigDecimal(10));
        assertNotNull(a);
    }

    @Before
    public void setup(){
        number = new BigDecimal(100);
        CurrencyEnum currencyEnum = CurrencyEnum.EUR;
        currency = new Currency(number, currencyEnum);
        amount = new Amount();
        amount.setAmountToConvert(number);
        amount.setCurrency(currency);
    }
    @Test
    public void getAmountTest(){
        assertEquals(number,amount.getAmountToConvert());
    }

    @Test
    public void getCurrencyTest(){
        assertEquals(currency,amount.getCurrency());
    }

}
