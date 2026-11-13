package br.com.zenon.fraud;

import java.math.BigDecimal;

public record AccountTransactionInfo(String name, BigDecimal newBalance, BigDecimal oldBalance) {
}
