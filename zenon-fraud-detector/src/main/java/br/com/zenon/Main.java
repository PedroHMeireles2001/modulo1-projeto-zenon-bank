package br.com.zenon;


import br.com.zenon.fraud.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Transaction> transactions = new TransactionIngestor().getAll("C:\\Users\\user\\Documents\\Estudo\\modulo1-projeto-zenon-bank\\data\\PS_20174392719_1491204439457_log.csv");
        System.out.println("Success: " + transactions.size());
        System.out.println("Errors: " + TransactionIngestor.getErros());

        FraudAnalyzer analyzer = new FraudAnalyzer(transactions);

        System.out.println("Total de fraudes: " + analyzer.getFraudulentTransactionsSize());
        System.out.println("Top 3 fraudes por valor: ");
        System.out.println(analyzer.getTopFraudulentTransactions().stream().map(Transaction::amount).map(Objects::toString).collect(Collectors.joining(System.lineSeparator())));
        System.out.println("Top clientes suspeitos: ");
        System.out.println(String.join(System.lineSeparator(),analyzer.getTopSuspiciousClients()));
        System.out.println("Prejuízo total: " + analyzer.getTotalInjuryAmount());
        System.out.println("Fraudes por tipo: ");

        Map<TransactionType,Long> groupedByType = analyzer.groupFraudulentTransactionsByType();
        System.out.println(groupedByType.entrySet().stream().map(entry -> " - %s: %s".formatted(entry.getKey(),entry.getValue())).collect(Collectors.joining(System.lineSeparator())));
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