package com.mycompany.software.administrativo.ventas.tools;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class MakePDF {

    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18);
    private Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private Document document = new Document();
    private BillSpecification billSpecification;

    public void draw(BillSpecification billSpecification) throws DocumentException, IOException {
        if (billSpecification == null) {
            throw new IllegalArgumentException("billSpecification no puede ser null");
        }
        this.billSpecification = billSpecification;

        PdfWriter.getInstance(document, new FileOutputStream("venta.pdf"));
        document.open();
        metaData();
        header();
//        addContent();
        createTable();
        document.close(); // Mover el cierre del documento aquí
    }

    private void metaData() throws DocumentException {
        document.addTitle("Factura-" + billSpecification.getId());
        document.addKeywords("Sistema de ventas, PDF");
    }

    public void header() throws DocumentException {
        // drawing ID bill
        Paragraph paragraph = new Paragraph("ID de la Factura: " + billSpecification.getId());
        paragraph.setSpacingAfter(50);
        document.add(paragraph);        
    }

    private void addContent() throws DocumentException {
        // add a table
        createTable();
    }

    private void createTable() throws DocumentException {
        float totalAmount = 0;
        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Producto"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Precio"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cantidad"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        // drawing products content in the table
        for (Product product : billSpecification.getProducts()) {
            table.addCell(product.getProductName());
            table.addCell(Float.toString(product.getUnitValue()));
            table.addCell(Integer.toString(product.getQuantity()));

            // increase amount with total product price
            totalAmount += product.getQuantity() * product.getUnitValue();
        }
        document.add(table);
        document.add(new Paragraph("Total a pager: " + totalAmount)); // move this in the table, with only  two spaces
    }
}
