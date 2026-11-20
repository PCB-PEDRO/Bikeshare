package br.ufal.ic.p2.bikeshare.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.function.Supplier;

public class DataUtils {
    public static final DateTimeFormatter FORMATADOR_STRICT = DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter FORMATADOR_PADRAO = DateTimeFormatter.ofPattern("d/M/yyyy");

    public static <T extends Exception> LocalDate conversaoData(String dataStr, Supplier<T> exceptionDataSupplier) throws T {
        if (dataStr == null || dataStr.isBlank()) {
            throw exceptionDataSupplier.get();
        }
        try {
            return LocalDate.parse(dataStr, FORMATADOR_STRICT);
        } catch (DateTimeParseException e) {
            throw exceptionDataSupplier.get();
        }
    }

    public static LocalDate parseLocalDate(String dataStr) {
        if (dataStr == null || dataStr.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(dataStr, FORMATADOR_PADRAO);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String formatLocalDate(LocalDate data) {
        if (data == null) {
            return null;
        }
        return data.format(FORMATADOR_PADRAO);
    }
}
