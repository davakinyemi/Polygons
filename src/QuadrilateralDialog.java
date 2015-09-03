
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dav
 */
public class QuadrilateralDialog extends javax.swing.JDialog {
    private Quadrilateral quad;
    private Rectangle rect;
    private Square sq;
    private Vertex A, B, C, D;
    private Double base, left, right, top, area, perimeter;
    
    /**
     * Creates new form QuadrilateralDialog
     */
    public QuadrilateralDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }       
    
    // prevents user from using vertices to construct shape
    private void disableVertices(){
        jTextPane_A_X.setEditable(false);
        jTextPane_A_Y.setEditable(false);
        jTextPane_B_X.setEditable(false);
        jTextPane_B_Y.setEditable(false);
        jTextPane_C_X.setEditable(false);
        jTextPane_C_Y.setEditable(false);
        jTextPane_D_X.setEditable(false);
        jTextPane_D_Y.setEditable(false);
    }
    
    // enables user to use vertices to construct shape
    private void enableVertices(){
        jTextPane_A_X.setEditable(true);
        jTextPane_A_Y.setEditable(true);
        jTextPane_B_X.setEditable(true);
        jTextPane_B_Y.setEditable(true);
        jTextPane_C_X.setEditable(true);
        jTextPane_C_Y.setEditable(true);
        jTextPane_D_X.setEditable(true);
        jTextPane_D_Y.setEditable(true);
    }
    
    // prevents user from using length to construct shape
    private void disableLengths(){
        jTextPane_Base.setEditable(false);
        jTextPane_Left.setEditable(false);
        jTextPane_Right.setEditable(false);
        jTextPane_Top.setEditable(false);
    }
    
    // enables user to use length to construct shape
    private void enableLengths(){
        jTextPane_Base.setEditable(true);
        jTextPane_Left.setEditable(true);
        jTextPane_Right.setEditable(true);
        jTextPane_Top.setEditable(true);
    }
    
    // resets all variables to null and clears all text panes;
    private void reset(){
        quad = null;
        rect = null;
        sq = null;
        A = null;
        B = null;
        C = null;
        D = null;
        base = null;
        right = null;
        left = null;
        top = null;
        area = null; 
        perimeter = null;
        
        jTextPane_A_X.setText("");
        jTextPane_A_Y.setText("");
        jTextPane_B_X.setText("");
        jTextPane_B_Y.setText("");
        jTextPane_C_X.setText("");
        jTextPane_C_Y.setText("");
        jTextPane_D_X.setText("");
        jTextPane_D_Y.setText("");
        
        jTextPane_Base.setText("");
        jTextPane_Left.setText("");
        jTextPane_Right.setText("");
        jTextPane_Top.setText("");
        
        jTextPane_Area.setText("");
        jTextPane_Perimeter.setText("");
    }
    
    // restores components to their original states
    private void restore(){
        buttonGroup1.clearSelection();
        
        jLabel_base.setText("Base:");
        jLabel_right.setText("Right:");
        jLabel_left.setText("Left:");
        jLabel_top.setText("Top:");
        
        jLabel_base.setVisible(true);
        jLabel_right.setVisible(true);
        jLabel_left.setVisible(true);
        jLabel_top.setVisible(true);
        
        jTextPane_Base.setVisible(true);
        jTextPane_Top.setVisible(true);
        jTextPane_Right.setVisible(true);
        jTextPane_Left.setVisible(true);
        jRadioButton_Lengths.setEnabled(true);
    }
    
    // checks quadrilateral type and disable text panes accordingly
    private void checkQuad(){
        String selected = jComboBox_Quads.getSelectedItem().toString();
        switch(selected){
            case "Rectangle":
                jTextPane_Left.setEditable(false);
                jTextPane_Top.setEditable(false);
                
                jTextPane_Left.setVisible(false);
                jTextPane_Top.setVisible(false);
                
                jLabel_base.setText("Width:");
                jLabel_right.setText("Height");
                jLabel_left.setVisible(false);
                jLabel_top.setVisible(false);
                break;
            case "Square":                
                jTextPane_Left.setEditable(false);
                jTextPane_Right.setEditable(false);
                jTextPane_Top.setEditable(false);
                
                jTextPane_Left.setVisible(false);
                jTextPane_Right.setVisible(false);
                jTextPane_Top.setVisible(false);
                
                jLabel_base.setText("Length:");
                jLabel_right.setVisible(false);
                jLabel_left.setVisible(false);
                jLabel_top.setVisible(false);
                break;
        }
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
    
    // get vertices and create quadrilateral based on the inputs
    private void getVertices(String shape){
        try{
            A = new Vertex(Double.parseDouble(jTextPane_A_X.getText()), Double.parseDouble(jTextPane_A_Y.getText()));
            B = new Vertex(Double.parseDouble(jTextPane_B_X.getText()), Double.parseDouble(jTextPane_B_Y.getText()));
            C = new Vertex(Double.parseDouble(jTextPane_C_X.getText()), Double.parseDouble(jTextPane_C_Y.getText()));
            D = new Vertex(Double.parseDouble(jTextPane_D_X.getText()), Double.parseDouble(jTextPane_D_Y.getText()));
            
            switch(shape){
                case "Quadrilateral":
                    quad = new Quadrilateral(A, B, C, D); 
                    break;
                case "Rectangle":
                    rect = new Rectangle(A, B, C, D);
                    break;
                case "Square":
                    sq = new Square(A, B, C, D);
                    break;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid input");            
        }        
    }
    
    // get the lengths and construct shape based on them
    private void getLengths(String shape){
        try{
            base = Double.parseDouble(jTextPane_Base.getText());
            
            switch(shape){
                case "Quadrilateral":
                    right = Double.parseDouble(jTextPane_Right.getText());
                    left = Double.parseDouble(jTextPane_Left.getText()); 
                    top = Double.parseDouble(jTextPane_Top.getText());
                    quad = new Quadrilateral(base, right, top, left);
                    break;
                case "Rectangle":
                    right = Double.parseDouble(jTextPane_Right.getText());
                    rect = new Rectangle(base, right);
                    break;
                case "Square":
                    sq = new Square(base);
                    break;
            }
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid input");
        }
    }    
    
    // get the area and perimeter of constructed shape
    private void solution(String selected){
        try{
            switch(selected){
                case "Quadrilateral":
                    area = quad.area();
                    perimeter = quad.perimeter();
                    break;
                case "Rectangle":
                    area = rect.area();
                    perimeter = rect.perimeter();
                    break;
                case "Square":
                    area = sq.area();
                    perimeter = sq.perimeter();
            }

            jTextPane_Area.setText(area.toString());
            jTextPane_Perimeter.setText(perimeter.toString());
        } catch (Exception ex){
            
        }
    }
    
    // determines whether to construct shape based on vertices or lengths
    private void inputVersion(String shape, String enabled){
        switch(shape){
            case "Quadrilateral":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Rectangle":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Square":
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
        String selected = jComboBox_Quads.getSelectedItem().toString(); 
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
        jComboBox_Quads = new javax.swing.JComboBox();
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
        jLabel14 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPane_D_X = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane_D_Y = new javax.swing.JTextPane();
        jLabel_top = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPane_Top = new javax.swing.JTextPane();
        jRadioButton_Vertices = new javax.swing.JRadioButton();
        jRadioButton_Lengths = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jComboBox_Quads.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quadrilateral", "Rectangle", "Square" }));
        jComboBox_Quads.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_QuadsActionPerformed(evt);
            }
        });

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

        jLabel14.setText("Vertex D:");

        jScrollPane12.setViewportView(jTextPane_D_X);

        jScrollPane13.setViewportView(jTextPane_D_Y);

        jLabel_top.setText("Top:");

        jScrollPane14.setViewportView(jTextPane_Top);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_base, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_right, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_left, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_top, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane5)
                                        .addComponent(jScrollPane4)
                                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel10))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                .addComponent(jScrollPane8)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jButton_OK)
                                            .addGap(18, 18, 18)
                                            .addComponent(jButton_Clear)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel12)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jRadioButton_Vertices)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton_Lengths))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox_Quads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 96, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Quads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Vertices)
                    .addComponent(jRadioButton_Lengths))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_top)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_OK)
                    .addComponent(jButton_Clear))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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

    private void jRadioButton_VerticesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_VerticesActionPerformed
        // TODO add your handling code here:
        disableLengths();
        enableVertices();
    }//GEN-LAST:event_jRadioButton_VerticesActionPerformed

    private void jRadioButton_LengthsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_LengthsActionPerformed
        // TODO add your handling code here:
        disableVertices();
        enableLengths();
    }//GEN-LAST:event_jRadioButton_LengthsActionPerformed

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed
        // TODO add your handling code here:
        solve();
    }//GEN-LAST:event_jButton_OKActionPerformed

    private void init(){        
        if(jComboBox_Quads.getSelectedItem().toString().equals("Quadrilateral")){
            disableLengths();
            jRadioButton_Lengths.setEnabled(false);
            jRadioButton_Vertices.setSelected(true);
        } else{
            jRadioButton_Lengths.setEnabled(true);
            restore();
            checkQuad();
        }
    }
    
    private void jComboBox_QuadsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_QuadsActionPerformed
        // TODO add your handling code here:
        buttonGroup1.clearSelection();
        reset();
        init();
    }//GEN-LAST:event_jComboBox_QuadsActionPerformed

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
            java.util.logging.Logger.getLogger(QuadrilateralDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuadrilateralDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuadrilateralDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuadrilateralDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuadrilateralDialog dialog = new QuadrilateralDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox jComboBox_Quads;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_base;
    private javax.swing.JLabel jLabel_left;
    private javax.swing.JLabel jLabel_right;
    private javax.swing.JLabel jLabel_top;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_Lengths;
    private javax.swing.JRadioButton jRadioButton_Vertices;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
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
    private javax.swing.JTextPane jTextPane_D_X;
    private javax.swing.JTextPane jTextPane_D_Y;
    private javax.swing.JTextPane jTextPane_Left;
    private javax.swing.JTextPane jTextPane_Perimeter;
    private javax.swing.JTextPane jTextPane_Right;
    private javax.swing.JTextPane jTextPane_Top;
    // End of variables declaration//GEN-END:variables
}
