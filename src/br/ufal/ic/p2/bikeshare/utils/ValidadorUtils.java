package br.ufal.ic.p2.bikeshare.utils;

import br.ufal.ic.p2.bikeshare.exception.validacao.numericos.ValidadeInvalidaException;
import br.ufal.ic.p2.bikeshare.exception.validacao.tipos.EmailInvalidoException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.function.Supplier;

public class ValidadorUtils {

    private static final Locale LOCALE_BR = Locale.of("pt", "BR");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/uuuu H:mm").withResolverStyle(ResolverStyle.STRICT);

    public static <T extends Exception> void validaString(String valor, Supplier<T> exceptionSupplier) throws T {
        if (valor == null || valor.isBlank()) {
            throw exceptionSupplier.get();
        }
    }

    public static <T1 extends Exception, T2 extends Exception, T3 extends Exception> double conversaoDouble(
            String valor, Supplier<T1> numeroException, Supplier<T2> negativoException, Supplier<T3> nuloException) throws T1, T2, T3 {
        validaString(valor, nuloException);
        double num;
        try {
            num = Double.parseDouble(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw numeroException.get();
        }
        if (num < 0) {
            throw negativoException.get();
        }
        return num;
    }

    public static <T1 extends Exception, T2 extends Exception, T3 extends Exception> double conversaoDoublePositivo(
            String valor, Supplier<T1> numeroException, Supplier<T2> positivoException, Supplier<T3> nuloException) throws T1, T2, T3 {
        double num = conversaoDouble(valor, numeroException, positivoException, nuloException);
        if (num == 0) {
            throw positivoException.get();
        }
        return num;
    }

    public static String formatarMoeda(double valor) {
        return String.format(LOCALE_BR, "%.2f", valor);
    }

    public static String validaEmail(String email) throws EmailInvalidoException {
        String emailNormalizado = email.trim().toLowerCase();
        if (!emailNormalizado.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new EmailInvalidoException();
        }
        return emailNormalizado;
    }

    public static void validaValidadeCartao(String validade) throws ValidadeInvalidaException {
        if (!validade.trim().matches("^(0[1-9]|1[0-2])/\\d{4}$")) {
            throw new ValidadeInvalidaException();
        }
    }

    public static <T1 extends Exception, T2 extends Exception> int conversaoInt(
            String valor, Supplier<T1> numeroException, Supplier<T2> nuloException) throws T1, T2 {
        validaString(valor, nuloException);
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            throw numeroException.get();
        }
    }

    public static <T1 extends Exception, T2 extends Exception, T3 extends Exception> int conversaoIntPositivo(
            String valor, Supplier<T1> numeroException, Supplier<T2> positivoException, Supplier<T3> nuloException) throws T1, T2, T3 {
        int num = conversaoInt(valor, numeroException, nuloException);
        if (num <= 0) {
            throw positivoException.get();
        }
        return num;
    }

    public static <T1 extends Exception, T2 extends Exception> String validarData(
            String data, Supplier<T1> nuloException, Supplier<T2> invalidaException) throws T1, T2 {
        validaString(data, nuloException);
        try {
            LocalDateTime dt = LocalDateTime.parse(data.trim(), FORMATTER);
            return dt.format(FORMATTER);
        } catch (DateTimeParseException e) {
            throw invalidaException.get();
        }
    }

    public static <T extends Exception> long calcularMinutos(
            String dataInicio, String dataFim, Supplier<T> dataAnteriorException) throws T {
        LocalDateTime inicio = LocalDateTime.parse(dataInicio, FORMATTER);
        LocalDateTime fim = LocalDateTime.parse(dataFim, FORMATTER);

        long minutos = Duration.between(inicio, fim).toMinutes();
        if (minutos < 0) {
            throw dataAnteriorException.get();
        }
        return minutos;
    }
}
