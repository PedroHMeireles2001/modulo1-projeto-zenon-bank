package br.com.zenon;


import br.com.zenon.fraud.Transaction;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Transaction t1 = new Transaction(1,"PAYMENT", BigDecimal.valueOf(9839.64),"C1231006815", BigDecimal.valueOf(170136.0), BigDecimal.valueOf(160296.36), "M1979787155", BigDecimal.ZERO,BigDecimal.ZERO,false,false);
        Transaction t2 = new Transaction(743,"CASH_OUT", BigDecimal.valueOf(850002.52),"C1280323807", BigDecimal.valueOf(850002.52), BigDecimal.ZERO, "C873221189", BigDecimal.valueOf(6510099.11),BigDecimal.valueOf(7360101.63),true,false);

        System.out.println("Transação 1");
        System.out.println(t1);
        System.out.println("Transação 2");
        System.out.println(t2);
    }
}