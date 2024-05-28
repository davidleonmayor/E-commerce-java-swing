package com.mycompany.software.administrativo.ventas.views;

import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.JLabel;

public class ContainerProductEspesification extends javax.swing.JPanel {

    public ContainerProductEspesification(String name, /*Img img, */ float unitValue, int quantity) {
        initComponents();

        // Establece los valores dinámicamente
        jLabel1.setText("nombre: " + name);
        jLabel3.setText("Cantidad: " + quantity);
        jLabel4.setText("Valor Unitario: " + unitValue);

        // Aquí también podrías establecer la imagen si es necesario y el editor lo permite
        // jLabel2.setIcon(new ImageIcon(img));
//        this.setLocationRelativeTo(this);
//        SetImageLabel(jLabelPackageIMGContainer, "src/main/java/images/PersonLoginBlueBackground.png");
// Agregar un listener para asegurar que el JLabel tenga tamaño antes de ajustar la imagen
        jLabelPackageIMGContainer.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                SetImageLabel(jLabelPackageIMGContainer, "src/main/java/images/package.png", 100, 100);
            }
        });
    }

//    private void SetImageLabel(JLabel labelName, String root) {
//        ImageIcon image = new ImageIcon(root);
//        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
//        labelName.setIcon(icon);
//        this.repaint();
//    }
    private void SetImageLabel(JLabel labelName, String root, int width, int height) {
        try {
            ImageIcon image = new ImageIcon(root);
            Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
            Icon icon = new ImageIcon(scaledImage);
            labelName.setIcon(icon);
            this.repaint();
        } catch (Exception e) {
            // Manejo de excepciones (puedes personalizarlo según tus necesidades)
            e.printStackTrace();
        }
    }

    public void setProductName(String name) {
        jLabel1.setText("nombre: " + name);
    }

    public void setQuantity(int quantity) {
        jLabel3.setText("Cantidad: " + quantity);
    }

    public void setUnitValue(float unitValue) {
        jLabel4.setText("Valor Unitario: " + unitValue);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPackageIMGContainer = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("nombre: Manzana");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Cantidad: 5");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Valor Unitario: 1500");

        jLabelPackageIMGContainer.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabelPackageIMGContainer)
                        .addGap(135, 135, 135)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addComponent(jLabel1))
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabelPackageIMGContainer)))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelPackageIMGContainer;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
