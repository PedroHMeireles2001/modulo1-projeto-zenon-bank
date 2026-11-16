package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, AccountTransactionInfo originAccount, AccountTransactionInfo destAccount, boolean isFraud, boolean isFlaggedFraud) {

}
