package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step, String type, BigDecimal amount, String nameOrig, BigDecimal oldBalanceOrg, BigDecimal newBalanceOrig, String nameDest, BigDecimal oldBalanceDest, BigDecimal newBalanceDest, boolean isFraud, boolean isFlaggedFraud) {
    @Override
    public String toString() {
        return  "step: " + step +
                "\ntype: '" + type + '\'' +
                "\namount: " + amount +
                "\nnameOrig: '" + nameOrig + '\'' +
                "\noldBalanceOrg: " + oldBalanceOrg +
                "\nnewBalanceOrig: " + newBalanceOrig +
                "\nnameDest: '" + nameDest + '\'' +
                "\noldBalanceDest: " + oldBalanceDest +
                "\nnewBalanceDest: " + newBalanceDest +
                "\nisFraud: " + (isFraud ? "1" : "0") +
                "\nisFlaggedFraud: " + (isFlaggedFraud ? "1" : "0");
    }
}
