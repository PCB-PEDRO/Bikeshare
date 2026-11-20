package br.ufal.ic.p2.bikeshare.utils;

import br.ufal.ic.p2.bikeshare.exeption.validacao.tipos.EmailIvalidoException;

import java.util.function.Supplier;

public class ValidadorUtils {

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
        if (num <= 0) {
            throw negativoException.get();
        }
        return num;
    }

     public static String formatarMoeda(double valor) {
        return String.format("%.2f", valor).replace(".", ",");
    }

    public static void validaEmail(String email) throws EmailIvalidoException {
        if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            throw new EmailIvalidoException();
        }
    }

}
