package br.com.zenon;


import br.com.zenon.fraud.AccountTransactionInfo;
import br.com.zenon.fraud.Transaction;
import br.com.zenon.fraud.TransactionIngestor;
import br.com.zenon.fraud.TransactionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Transaction> transactions = new TransactionIngestor().getAll("C:\\Users\\user\\Documents\\Estudo\\modulo1-projeto-zenon-bank\\data\\paysim.csv");
        System.out.println("Success: " + transactions.size());
        System.out.println("Errors: " + TransactionIngestor.getErros());

        System.out.println(transactions);
    }

    private static void printTransaction() {
        Transaction t1 = new Transaction(1, TransactionType.PAYMENT, BigDecimal.valueOf(9839.64),new AccountTransactionInfo("C1231006815", BigDecimal.valueOf(170136.0), BigDecimal.valueOf(160296.36)), new AccountTransactionInfo("M1979787155", BigDecimal.ZERO,BigDecimal.ZERO),false,false);
        Transaction t2 = new Transaction(743,TransactionType.CASH_OUT, BigDecimal.valueOf(850002.52),new AccountTransactionInfo("C1280323807", BigDecimal.valueOf(850002.52), BigDecimal.ZERO), new AccountTransactionInfo("C873221189", BigDecimal.valueOf(6510099.11),BigDecimal.valueOf(7360101.63)),true,false);

        System.out.println("Transação 1");
        System.out.println(t1);
        System.out.println("Transação 2");
        System.out.println(t2);
    }
}