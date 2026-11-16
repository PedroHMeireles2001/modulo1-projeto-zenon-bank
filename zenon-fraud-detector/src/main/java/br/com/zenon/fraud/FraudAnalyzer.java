package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FraudAnalyzer {
    private final List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions){
        this.transactions = transactions;
    }

    public long getFraudulentTransactionsSize() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .count();
    }

    public List<Transaction> getTopFraudulentTransactions() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .sorted(Comparator.comparing(Transaction::amount))
                .limit(3)
                .toList();
    }

    //A Tarefa não especificou o que seriam clientes suspeitos, então eu deduzi que seria aquele com a maior quantidade de transações fraudulentas
    public Set<String> getTopSuspiciousClients(){
        Map<String, Long> fraudsByClient = transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(
                        t -> t.originAccount().name(),
                        Collectors.counting()
                ));
        return fraudsByClient.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public BigDecimal getTotalInjuryAmount() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<TransactionType, Long> groupFraudulentTransactionsByType() {
        return transactions.stream()
                .filter(Transaction::isFraud)
                .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
    }
}
