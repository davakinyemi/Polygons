/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.HeadlessException;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author dav
 */
public class TriangleDialog extends javax.swing.JDialog {
    private Triangle tri; // triangle
    private IsoscelesTriangle iso; // isosceles triangle
    private EquiTriangle equi; // equilateral triangle
    private Vertex A, B, C; // vertices
    private Double base, rightSide, leftSide, area, perimeter; // lengths
    /**
     * Creates new form TriangleDialog
     */
    public TriangleDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        checkTriangle();
    }
     
    // returns name of selected button in button group
    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    
    // prevents user from using vertices to construct shape
    private void disableVertices(){
        jTextPane_A_X.setEditable(false);
        jTextPane_A_Y.setEditable(false);
        jTextPane_B_X.setEditable(false);
        jTextPane_B_Y.setEditable(false);
        jTextPane_C_X.setEditable(false);
        jTextPane_C_Y.setEditable(false);
    }
    
    // enables user to use vertices to construct shape
    private void enableVertices(){
        jTextPane_A_X.setEditable(true);
        jTextPane_A_Y.setEditable(true);
        jTextPane_B_X.setEditable(true);
        jTextPane_B_Y.setEditable(true);
        jTextPane_C_X.setEditable(true);
        jTextPane_C_Y.setEditable(true);
    }
    
    // prevents user from using length to construct shape
    private void disableLengths(){
        jTextPane_Base.setEditable(false);
        jTextPane_Left.setEditable(false);
        jTextPane_Right.setEditable(false);
    }
    
    // enables user to use length to construct shape
    private void enableLengths(){
        jTextPane_Base.setEditable(true);
        jTextPane_Left.setEditable(true);
        jTextPane_Right.setEditable(true);
    }
    
    // resets all variables to null and clears all text panes;
    private void reset(){
        tri = null;
        iso = null;
        equi = null;
        A = null;
        B = null;
        C = null;
        base = null;
        rightSide = null;
        leftSide = null;
        area = null; 
        perimeter = null;
        
        jTextPane_A_X.setText("");
        jTextPane_A_Y.setText("");
        jTextPane_B_X.setText("");
        jTextPane_B_Y.setText("");
        jTextPane_C_X.setText("");
        jTextPane_C_Y.setText("");
        
        jTextPane_Base.setText("");
        jTextPane_Left.setText("");
        jTextPane_Right.setText("");
        
        jTextPane_Area.setText("");
        jTextPane_Perimeter.setText("");
    }
    
    // restores components to their original states
    private void restore(){
        jLabel_base.setText("Base:");
        jLabel_right.setText("Right:");
        jLabel_left.setText("Left:");
        
        jLabel_base.setVisible(true);
        jLabel_right.setVisible(true);
        jLabel_left.setVisible(true);
        
        jTextPane_Base.setVisible(true);
        jTextPane_Right.setVisible(true);
        jTextPane_Left.setVisible(true);
    }
    
    // check whether triangle is equilateral or isosceles and disable text panes accordingly
    private void checkTriangle(){
        String selected = jComboBox_Triangle.getSelectedItem().toString();
        switch(selected){
            case "Equilateral":
                jTextPane_Left.setEditable(false);
                jTextPane_Right.setEditable(false);
                jTextPane_Left.setVisible(false);
                jTextPane_Right.setVisible(false);
                
                jLabel_base.setText("Length:");
                jLabel_right.setVisible(false);
                jLabel_left.setVisible(false);
                break;
            case "Isosceles":                
                jTextPane_Left.setEditable(false);
                jTextPane_Left.setVisible(false);
                jLabel_left.setVisible(false);
                
                jLabel_base.setText("Width:");
                jLabel_right.setText("Length (sides):");
                break;
        }
    }
        
    // get vertices and create triangles based on the inputs
    private void getVertices(String shape){
        try{
            A = new Vertex(Double.parseDouble(jTextPane_A_X.getText()), Double.parseDouble(jTextPane_A_Y.getText()));
            B = new Vertex(Double.parseDouble(jTextPane_B_X.getText()), Double.parseDouble(jTextPane_B_Y.getText()));
            C = new Vertex(Double.parseDouble(jTextPane_C_X.getText()), Double.parseDouble(jTextPane_C_Y.getText()));
            
            switch(shape){
                case "Triangle":
                    tri = new Triangle(A, B, C); 
                    break;
                case "Isosceles":
                    iso = new IsoscelesTriangle(A, B, C);
                    break;
                case "Equilateral":
                    equi = new EquiTriangle(A, B, C);
                    break;
            }
        } catch (NumberFormatException | lengthValueException e){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid input/Not a Triangle if one side is bigger than the other two combined");            
        }        
    }
    
    // get the area and perimeter of constructed shape
    private void solution(String selected){
        try{
            switch(selected){
                case "Triangle":
                    area = tri.area();
                    perimeter = tri.perimeter();
                    break;
                case "Isosceles":
                    area = iso.area();
                    perimeter = iso.perimeter();
                    break;
                case "Equilateral":
                    area = equi.area();
                    perimeter = equi.perimeter();
            }

            jTextPane_Area.setText(area.toString());
            jTextPane_Perimeter.setText(perimeter.toString());
        } catch (Exception ex){
            
        }
    }
    
    // get the lengths and construct shape based on them 
    private void getLengths(String shape){
        try{
            base = Double.parseDouble(jTextPane_Base.getText());
            
            switch(shape){
                case "Triangle":
                    rightSide = Double.parseDouble(jTextPane_Right.getText());
                    leftSide = Double.parseDouble(jTextPane_Left.getText()); 
                    tri = new Triangle(base, rightSide, leftSide);
                    break;
                case "Isosceles":
                    rightSide = Double.parseDouble(jTextPane_Right.getText());
                    iso = new IsoscelesTriangle(base, rightSide);
                    break;
                case "Equilateral":
                    equi = new EquiTriangle(base);
                    break;
            }
        } catch (NumberFormatException | lengthValueException e){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid input/Not a Triangle if one side is bigger than the other two combined");
        }
    }
    
    // determines whether to construct shape based on vertices or lengths
    private void inputVersion(String shape, String enabled){
        switch(shape){
            case "Triangle":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Isosceles":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Equilateral":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
        }
        solution(shape);
    }
    
    // method to solve for area and perimeter
    private void solve(){
        String selected = jComboBox_Triangle.getSelectedItem().toString(); 
        String enabled = getSelectedButtonText(buttonGroup1);
        if(enabled == null){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Vertices or Lengths?");
            return;
        }
        inputVersion(selected, enabled);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_A_X = new javax.swing.JTextPane();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane_B_X = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_C_X = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane_Base = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane_Right = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane_Left = new javax.swing.JTextPane();
        jLabel_base = new javax.swing.JLabel();
        jLabel_right = new javax.swing.JLabel();
        jLabel_left = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane_Area = new javax.swing.JTextPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane_Perimeter = new javax.swing.JTextPane();
        jButton_OK = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPane_A_Y = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPane_B_Y = new javax.swing.JTextPane();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane_C_Y = new javax.swing.JTextPane();
        jLabel13 = new javax.swing.JLabel();
        jRadioButton_Vertices = new javax.swing.JRadioButton();
        jRadioButton_Lengths = new javax.swing.JRadioButton();
        jComboBox_Triangle = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Shape: ");

        jScrollPane1.setViewportView(jTextPane_A_X);

        jLabel4.setText("Vertex A:");

        jLabel5.setText("Vertex B:");

        jScrollPane2.setViewportView(jTextPane_B_X);

        jLabel6.setText("Vertex C:");

        jScrollPane3.setViewportView(jTextPane_C_X);

        jScrollPane4.setViewportView(jTextPane_Base);

        jScrollPane5.setViewportView(jTextPane_Right);

        jScrollPane6.setViewportView(jTextPane_Left);

        jLabel_base.setText("Base:");

        jLabel_right.setText("Right:");

        jLabel_left.setText("Left:");

        jLabel10.setText("Area:");

        jLabel11.setText("Perimeter:");

        jTextPane_Area.setEditable(false);
        jScrollPane7.setViewportView(jTextPane_Area);

        jTextPane_Perimeter.setEditable(false);
        jScrollPane8.setViewportView(jTextPane_Perimeter);

        jButton_OK.setText("OK");
        jButton_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_OKActionPerformed(evt);
            }
        });

        jButton_Clear.setText("Clear");
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        jLabel12.setText("X");

        jScrollPane9.setViewportView(jTextPane_A_Y);

        jScrollPane10.setViewportView(jTextPane_B_Y);

        jScrollPane11.setViewportView(jTextPane_C_Y);

        jLabel13.setText("Y");

        buttonGroup1.add(jRadioButton_Vertices);
        jRadioButton_Vertices.setText("Vertices");
        jRadioButton_Vertices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_VerticesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_Lengths);
        jRadioButton_Lengths.setText("Lengths");
        jRadioButton_Lengths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_LengthsActionPerformed(evt);
            }
        });

        jComboBox_Triangle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Triangle", "Isosceles", "Equilateral" }));
        jComboBox_Triangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TriangleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jComboBox_Triangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton_Vertices)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton_Lengths))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_base, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_right, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_left, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane5)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(60, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButton_OK)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Clear)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 92, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox_Triangle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Vertices)
                    .addComponent(jRadioButton_Lengths))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_base))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_right))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_left))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_OK)
                    .addComponent(jButton_Clear))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton_LengthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_LengthsActionPerformed
        // TODO add your handling code here:
        disableVertices();
        enableLengths();
    }//GEN-LAST:event_jRadioButton_LengthsActionPerformed

    private void jRadioButton_VerticesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_VerticesActionPerformed
        // TODO add your handling code here:
        disableLengths();
        enableVertices();
    }//GEN-LAST:event_jRadioButton_VerticesActionPerformed

    private void jComboBox_TriangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TriangleActionPerformed
        // TODO add your handling code here:
        buttonGroup1.clearSelection();
        reset();
        restore();
        checkTriangle();
    }//GEN-LAST:event_jComboBox_TriangleActionPerformed

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed
        // TODO add your handling code here:
        solve();
    }//GEN-LAST:event_jButton_OKActionPerformed

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton_ClearActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TriangleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TriangleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TriangleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TriangleDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TriangleDialog dialog = new TriangleDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_OK;
    private javax.swing.JComboBox jComboBox_Triangle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_base;
    private javax.swing.JLabel jLabel_left;
    private javax.swing.JLabel jLabel_right;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_Lengths;
    private javax.swing.JRadioButton jRadioButton_Vertices;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane jTextPane_A_X;
    private javax.swing.JTextPane jTextPane_A_Y;
    private javax.swing.JTextPane jTextPane_Area;
    private javax.swing.JTextPane jTextPane_B_X;
    private javax.swing.JTextPane jTextPane_B_Y;
    private javax.swing.JTextPane jTextPane_Base;
    private javax.swing.JTextPane jTextPane_C_X;
    private javax.swing.JTextPane jTextPane_C_Y;
    private javax.swing.JTextPane jTextPane_Left;
    private javax.swing.JTextPane jTextPane_Perimeter;
    private javax.swing.JTextPane jTextPane_Right;
    // End of variables declaration//GEN-END:variables
}
