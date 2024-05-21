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
        paragraph.setSpacingAfter(10);
        document.add(paragraph);

        // drawing client information
        paragraph = new Paragraph("Cliente: " + billSpecification.getClientName() + " " + billSpecification.getClientLastName());
        paragraph.setSpacingAfter(10);
        document.add(paragraph);

        // drawing seller information
        paragraph = new Paragraph("Vendedor: " + billSpecification.getSellerName() + " " + billSpecification.getSellerLastName());
        paragraph.setSpacingAfter(10);
        document.add(paragraph);

        // drawing box number
        paragraph = new Paragraph("Número de caja: " + billSpecification.getBoxNumber());
        paragraph.setSpacingAfter(10);
        document.add(paragraph);

        // drawing payment method
        paragraph = new Paragraph("Método de pago: " + billSpecification.getPaymentMethod());
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

        // Add a cell at the end of the table with the total amount to pay
        PdfPCell totalCell = new PdfPCell(new Phrase("Total a pagar: " + totalAmount));
        totalCell.setColspan(3); // This will make the cell span 3 columns
        table.addCell(totalCell);

        document.add(table);
    }

}
