package com.mycompany.software.administrativo.ventas.tools;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class LeerPDF {
    public void leerPDF() {
        PDDocument documento = null;
        try {
            File file = new File("venta.pdf"); // Ruta al archivo PDF
            documento = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String texto = pdfStripper.getText(documento);
            System.out.println("Texto en el PDF: " + texto);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (documento != null) {
                try {
                    documento.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
