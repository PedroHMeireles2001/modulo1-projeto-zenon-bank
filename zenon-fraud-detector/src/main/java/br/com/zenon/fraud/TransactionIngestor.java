package br.com.zenon.fraud;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class TransactionIngestor {

    private final static String SEP = ",";

    public List<Transaction> getAll(String path) throws IOException {
        return Files.lines(Paths.get(path)).limit(1000)
                .map(this::parseTransaction)
                .filter(Objects::nonNull)
                .toList();
    }

    private Transaction parseTransaction(String s) {
        String[] chunks = s.split(SEP);
        try{
            return new Transaction(
                    Integer.parseInt(chunks[0]),
                    TransactionType.valueOf(chunks[1]),
                    new BigDecimal(chunks[2]),
                    new AccountTransactionInfo(
                            chunks[3],
                            new BigDecimal(chunks[4]),
                            new BigDecimal(chunks[5])
                    ),
                    new AccountTransactionInfo(
                            chunks[6],
                            new BigDecimal(chunks[7]),
                            new BigDecimal(chunks[8])
                    ),
                    Boolean.parseBoolean(chunks[9]),
                    Boolean.parseBoolean(chunks[10])
            );
        }catch (Exception e){
            return null;
        }
    }
}
