package br.com.zenon.fraud;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class TransactionIngestor {

    private final static String SEP = ",";
    private static int erros;

    public List<Transaction> getAll(String path) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(path)).skip(1)) {
            return lines
                    .map(this::parseTransaction)
                    .flatMap(Optional::stream)
                    .toList();
        }
    }

    public static int getErros() {
        return erros;
    }

    private Optional<Transaction> parseTransaction(String s) {
        String[] chunks = s.split(SEP);
        try{
            Transaction transaction = new Transaction(
                    Integer.parseInt(chunks[0]),
                    TransactionType.valueOf(chunks[1]), //Como o type é um enum, se for um valor diferente vai dar erro no parse
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
                    Boolean.parseBoolean(chunks[10]));

            verifyFields(transaction);
            return Optional.of(transaction);

        }catch (Exception e){
            String transactionRaw = String.join(SEP, chunks);
            erros++;
            System.err.printf("Erro: %s  | %s: %s%n", transactionRaw, e.getClass().getSimpleName(), e.getMessage());
            return Optional.empty();
        }
    }

    private void verifyFields(Object obj) throws IllegalAccessException, IllegalArgumentException {
        for(Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object value = f.get(obj);
            if (Objects.isNull(value)) {
                throw new IllegalArgumentException("%s should not be empty".formatted(f.getName()));
            }

            if(value instanceof BigDecimal bigDecimal && bigDecimal.compareTo(BigDecimal.ZERO) == -1) {
                throw new IllegalArgumentException("%s should be positive: %s".formatted(f.getName(), value));
            }

            if(value instanceof Integer integer && integer <= 0) {
                throw new IllegalArgumentException("%s should be positive: %s".formatted(f.getName(), value));
            }

            if(value instanceof String s && s.isEmpty())
                throw new IllegalArgumentException("%s should not be empty".formatted(f.getName()));

            if(value instanceof AccountTransactionInfo accountTransactionInfo) {
                verifyFields(accountTransactionInfo);
            }
        }
    }
}
