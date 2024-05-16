package com.mycompany.software.administrativo.ventas.tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;

public class MakePDF {
//    public void crearPDF(Venta venta) {
    public static void crearPDF(String nombre, String fecha) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("venta.pdf"));
            documento.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Detalles de la Venta:\n", font);

            documento.add(chunk);
//            documento.add(new Paragraph("ID de la Venta: " + venta.getId()));
//            documento.add(new Paragraph("Nombre del Cliente: " + venta.getNombreCliente()));
//            documento.add(new Paragraph("Fecha de la Venta: " + venta.getFecha()));
//            documento.add(new Paragraph("ID de la Venta: " + venta.getId()));
            documento.add(new Paragraph("Nombre del Cliente: " + nombre));
            documento.add(new Paragraph("Fecha de la Venta: " + fecha));
            // Agrega más detalles de la venta aquí...

            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void crearBillPDF(BillSpecification billSpecification) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("venta.pdf"));
            documento.open();

            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Detalles de la Venta:\n", font);

            documento.add(chunk);
//            documento.add(new Paragraph("ID de la Venta: " + venta.getId()));
//            documento.add(new Paragraph("Nombre del Cliente: " + venta.getNombreCliente()));
//            documento.add(new Paragraph("Fecha de la Venta: " + venta.getFecha()));
//            documento.add(new Paragraph("ID de la Venta: " + venta.getId()));
            documento.add(new Paragraph("Nombre del Cliente: " + billSpecification.getClientName()));
            documento.add(new Paragraph("Apellido del Cliente: " + billSpecification.getClientLastName()));
            documento.add(new Paragraph("Fecha de la Venta: " + billSpecification.getFecha()));
            // Agrega más detalles de la venta aquí...

            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
