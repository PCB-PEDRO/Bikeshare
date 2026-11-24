package br.ufal.ic.p2.bikeshare.controller.Data;

import br.ufal.ic.p2.bikeshare.exception.sistema.PersistenciaException;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PersistenciaXML {
    private static final String ARQUIVO_PADRAO = "BikeshareData.xml";

    public void salvarDados(DataSave dados) throws PersistenciaException {
        final Exception[] erroEncoder = new Exception[1];
        try {
            try (FileOutputStream fos = new FileOutputStream(ARQUIVO_PADRAO);
                 XMLEncoder encoder = new XMLEncoder(fos)) {
                encoder.setExceptionListener(e -> {
                    if (erroEncoder[0] == null) {
                        erroEncoder[0] = e;
                    }
                });
                encoder.writeObject(dados);
            }
            if (erroEncoder[0] != null) {
                throw new PersistenciaException("Erro ao salvar sistema", erroEncoder[0]);
            }
        } catch (PersistenciaException e) {
            throw e;
        } catch (IOException | RuntimeException e) {
            throw new PersistenciaException("Erro ao salvar sistema", e);
        }
    }

    public DataSave carregarDados() throws PersistenciaException {
        File arquivo = new File(ARQUIVO_PADRAO);
        if (!arquivo.exists()) {
            return null;
        }

        final Exception[] erroDecoder = new Exception[1];
        try {
            DataSave dadosLidos;
            try (FileInputStream fis = new FileInputStream(arquivo);
                 XMLDecoder decoder = new XMLDecoder(fis, null, e -> {
                     if (erroDecoder[0] == null) {
                         erroDecoder[0] = e;
                     }
                 })) {
                dadosLidos = (DataSave) decoder.readObject();
            }
            if (erroDecoder[0] != null) {
                throw new PersistenciaException(erroDecoder[0]);
            }
            return dadosLidos;
        } catch (PersistenciaException e) {
            throw e;
        } catch (IOException | RuntimeException e) {
            throw new PersistenciaException(e);
        }
    }
}