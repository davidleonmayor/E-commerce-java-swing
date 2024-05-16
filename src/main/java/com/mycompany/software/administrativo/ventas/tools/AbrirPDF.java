package com.mycompany.software.administrativo.ventas.tools;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AbrirPDF {
    public static void abrirPDF() {
        try {
            File file = new File("venta.pdf");
            if(file.exists()) {
                if(Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("No se puede abrir el archivo, no hay una aplicación registrada para manejar PDFs.");
                }
            } else {
                System.out.println("El archivo no existe!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
