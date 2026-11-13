package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, TransactionType type, BigDecimal amount, AccountTransactionInfo originAccount, AccountTransactionInfo destAccount, boolean isFraud, boolean isFlaggedFraud) {
    @Override
    public String toString() {
        return  "step: " + step +
                "\ntype: '" + type + '\'' +
                "\namount: " + amount +
                "\nnameOrig: '" + originAccount.name() + '\'' +
                "\noldBalanceOrg: " + originAccount.oldBalance() +
                "\nnewBalanceOrig: " + originAccount.newBalance() +
                "\nnameDest: '" + destAccount.name() + '\'' +
                "\noldBalanceDest: " + destAccount.oldBalance() +
                "\nnewBalanceDest: " + destAccount.newBalance() +
                "\nisFraud: " + (isFraud ? "1" : "0") +
                "\nisFlaggedFraud: " + (isFlaggedFraud ? "1" : "0");
    }
}
