
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
public class OtherPolygons extends javax.swing.JDialog {
    private Pentagon pent;
    private Hexagon hex;
    private Octagon oct;
    private Vertex A, B, C, D, E, F, G, H;
    private Double length, area, perimeter;
    
    /**
     * Creates new form OtherPolygons
     */
    public OtherPolygons(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        checkPolygon();
    }

    // resets all variables to null and clears all text panes;
    private void reset(){
        pent = null;
        hex = null;
        oct = null;
        A = null;
        B = null;
        C = null;
        D = null;
        E = null;
        F = null;
        G = null;
        H = null;        
        length = null;
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
        jTextPane_E_X.setText("");
        jTextPane_E_Y.setText("");
        jTextPane_F_X.setText("");
        jTextPane_F_Y.setText("");
        jTextPane_G_X.setText("");
        jTextPane_G_Y.setText("");
        jTextPane_H_X.setText("");
        jTextPane_H_Y.setText("");
        
        jTextPane_Length.setText("");
        
        jTextPane_Area.setText("");
        jTextPane_Perimeter.setText("");
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
        jTextPane_E_X.setEditable(false);
        jTextPane_E_Y.setEditable(false);
        jTextPane_F_X.setEditable(false);
        jTextPane_F_Y.setEditable(false);
        jTextPane_G_X.setEditable(false);
        jTextPane_G_Y.setEditable(false);
        jTextPane_H_X.setEditable(false);
        jTextPane_H_Y.setEditable(false);
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
        jTextPane_E_X.setEditable(true);
        jTextPane_E_Y.setEditable(true);
        jTextPane_F_X.setEditable(true);
        jTextPane_F_Y.setEditable(true);
        jTextPane_G_X.setEditable(true);
        jTextPane_G_Y.setEditable(true);
        jTextPane_H_X.setEditable(true);
        jTextPane_H_Y.setEditable(true);
    }
    
    // prevents user from using length to construct shape
    private void disableLengths(){
        jTextPane_Length.setEditable(false);
    }
    
    // enables user to use length to construct shape
    private void enableLengths(){
        jTextPane_Length.setEditable(true);
    }
    
    // checks polygon type and disable text panes accordingly
    private void checkPolygon(){
        String selected = jComboBox_Polygons.getSelectedItem().toString();
        switch(selected){
            case "Pentagon":
                jTextPane_F_X.setEditable(false);
                jTextPane_F_Y.setEditable(false);
                jTextPane_G_X.setEditable(false);
                jTextPane_G_Y.setEditable(false);
                jTextPane_H_X.setEditable(false);
                jTextPane_H_Y.setEditable(false);
                
                jTextPane_F_X.setVisible(false);
                jTextPane_F_Y.setVisible(false);
                jTextPane_G_X.setVisible(false);
                jTextPane_G_Y.setVisible(false);
                jTextPane_H_X.setVisible(false);
                jTextPane_H_Y.setVisible(false);
                
                jLabel_F.setVisible(false);
                jLabel_G.setVisible(false);
                jLabel_H.setVisible(false);
                break;
            case "Hexagon":                
                jTextPane_G_X.setEditable(false);
                jTextPane_G_Y.setEditable(false);
                jTextPane_H_X.setEditable(false);
                jTextPane_H_Y.setEditable(false);
                
                jTextPane_G_X.setVisible(false);
                jTextPane_G_Y.setVisible(false);
                jTextPane_H_X.setVisible(false);
                jTextPane_H_Y.setVisible(false);
                
                jLabel_G.setVisible(false);
                jLabel_H.setVisible(false);
                break;
        }
    }
    
    // restores components to their original states
    private void restore(){
        jTextPane_F_X.setEditable(true);
        jTextPane_F_Y.setEditable(true);
        jTextPane_G_X.setEditable(true);
        jTextPane_G_Y.setEditable(true);
        jTextPane_H_X.setEditable(true);
        jTextPane_H_Y.setEditable(true);
              
        jTextPane_F_X.setVisible(true);
        jTextPane_F_Y.setVisible(true);
        jTextPane_G_X.setVisible(true);
        jTextPane_G_Y.setVisible(true);
        jTextPane_H_X.setVisible(true);
        jTextPane_H_Y.setVisible(true);
               
        jLabel_F.setVisible(true);
        jLabel_G.setVisible(true);
        jLabel_H.setVisible(true);
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
    
    // get vertices and create polygon based on the inputs
    private void getVertices(String shape){
        try{
            A = new Vertex(Double.parseDouble(jTextPane_A_X.getText()), Double.parseDouble(jTextPane_A_Y.getText()));
            B = new Vertex(Double.parseDouble(jTextPane_B_X.getText()), Double.parseDouble(jTextPane_B_Y.getText()));
            C = new Vertex(Double.parseDouble(jTextPane_C_X.getText()), Double.parseDouble(jTextPane_C_Y.getText()));
            D = new Vertex(Double.parseDouble(jTextPane_D_X.getText()), Double.parseDouble(jTextPane_D_Y.getText()));
            E = new Vertex(Double.parseDouble(jTextPane_E_X.getText()), Double.parseDouble(jTextPane_E_Y.getText()));
                        
            switch(shape){
                case "Pentagon":
                    pent = new Pentagon(A, B, C, D, E); 
                    break;
                case "Hexagon":
                    F = new Vertex(Double.parseDouble(jTextPane_F_X.getText()), Double.parseDouble(jTextPane_F_Y.getText()));
                    hex = new Hexagon(A, B, C, D, E, F);
                    break;
                case "Octagon":
                    F = new Vertex(Double.parseDouble(jTextPane_F_X.getText()), Double.parseDouble(jTextPane_F_Y.getText()));
                    G = new Vertex(Double.parseDouble(jTextPane_G_X.getText()), Double.parseDouble(jTextPane_G_Y.getText()));
                    H = new Vertex(Double.parseDouble(jTextPane_H_X.getText()), Double.parseDouble(jTextPane_H_Y.getText()));
                    oct = new Octagon(A, B, C, D, E, F, G, H);
                    break;
            }
        } catch (NumberFormatException | vertexValueException e){
            JOptionPane.showMessageDialog(new JFrame(), "Error: Invalid input/Lengths between all vertices should be equal");            
        }        
    }
    
    // get the lengths and construct shape based on them
    private void getLengths(String shape){
        try{
            length = Double.parseDouble(jTextPane_Length.getText());
                        
            switch(shape){
                case "Pentagon":
                    pent = new Pentagon(length); 
                    break;
                case "Hexagon":
                    hex = new Hexagon(length);
                    break;
                case "Octagon":
                    oct = new Octagon(length);
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
                case "Pentagon":
                    area = pent.area();
                    perimeter = pent.perimeter();
                    break;
                case "Hexagon":
                    area = hex.area();
                    perimeter = hex.perimeter();
                    break;
                case "Octagon":
                    area = oct.area();
                    perimeter = oct.perimeter();
            }

            jTextPane_Area.setText(area.toString());
            jTextPane_Perimeter.setText(perimeter.toString());
        } catch (Exception ex){
            
        }
    }
    
    // determines whether to construct shape based on vertices or lengths
    private void inputVersion(String shape, String enabled){
        switch(shape){
            case "Pentagon":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Hexagon":
                if(enabled.equals("Vertices"))
                    getVertices(shape);
                if(enabled.equals("Lengths"))
                    getLengths(shape);
                break;
            case "Octagon":
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
        String selected = jComboBox_Polygons.getSelectedItem().toString(); 
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_A_X = new javax.swing.JTextPane();
        jLabel_A = new javax.swing.JLabel();
        jLabel_B = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane_B_X = new javax.swing.JTextPane();
        jLabel_C = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane_C_X = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane_Length = new javax.swing.JTextPane();
        jLabel7 = new javax.swing.JLabel();
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
        jLabel_D = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPane_D_X = new javax.swing.JTextPane();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane_D_Y = new javax.swing.JTextPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPane_E_X = new javax.swing.JTextPane();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextPane_E_Y = new javax.swing.JTextPane();
        jLabel_E = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextPane_F_X = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextPane_F_Y = new javax.swing.JTextPane();
        jLabel_F = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPane_G_X = new javax.swing.JTextPane();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPane_G_Y = new javax.swing.JTextPane();
        jLabel_G = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextPane_H_X = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextPane_H_Y = new javax.swing.JTextPane();
        jLabel_H = new javax.swing.JLabel();
        jComboBox_Polygons = new javax.swing.JComboBox();
        jRadioButton_Lengths = new javax.swing.JRadioButton();
        jRadioButton_Vertices = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setViewportView(jTextPane_A_X);

        jLabel_A.setText("Vertex A:");

        jLabel_B.setText("Vertex B:");

        jScrollPane2.setViewportView(jTextPane_B_X);

        jLabel_C.setText("Vertex C:");

        jScrollPane3.setViewportView(jTextPane_C_X);

        jScrollPane4.setViewportView(jTextPane_Length);

        jLabel7.setText("Length:");

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

        jLabel_D.setText("Vertex D:");

        jScrollPane12.setViewportView(jTextPane_D_X);

        jScrollPane13.setViewportView(jTextPane_D_Y);

        jScrollPane14.setViewportView(jTextPane_E_X);

        jScrollPane15.setViewportView(jTextPane_E_Y);

        jLabel_E.setText("Vertex E:");

        jScrollPane16.setViewportView(jTextPane_F_X);

        jScrollPane17.setViewportView(jTextPane_F_Y);

        jLabel_F.setText("Vertex F:");

        jScrollPane18.setViewportView(jTextPane_G_X);

        jScrollPane19.setViewportView(jTextPane_G_Y);

        jLabel_G.setText("Vertex G:");

        jScrollPane20.setViewportView(jTextPane_H_X);

        jScrollPane21.setViewportView(jTextPane_H_Y);

        jLabel_H.setText("Vertex H:");

        jComboBox_Polygons.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Octagon", "Hexagon", "Pentagon", " " }));
        jComboBox_Polygons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_PolygonsActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_Lengths);
        jRadioButton_Lengths.setText("Lengths");
        jRadioButton_Lengths.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_LengthsActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_Vertices);
        jRadioButton_Vertices.setText("Vertices");
        jRadioButton_Vertices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_VerticesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_A)
                    .addComponent(jLabel_B)
                    .addComponent(jLabel_C)
                    .addComponent(jLabel_D)
                    .addComponent(jLabel_E)
                    .addComponent(jLabel_F)
                    .addComponent(jLabel_G)
                    .addComponent(jLabel_H))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane14, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane13)
                                    .addComponent(jScrollPane15)
                                    .addComponent(jScrollPane17)
                                    .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jRadioButton_Vertices)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_Lengths))
                .addGap(22, 22, 22))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(143, 143, 143)
                            .addComponent(jLabel13))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(165, 165, 165)
                            .addComponent(jComboBox_Polygons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton_OK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Clear)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane7)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_Polygons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_Vertices)
                    .addComponent(jRadioButton_Lengths))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_A)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_B)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_C))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_D)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_E))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_F))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_G))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_H))
                .addGap(18, 18, 18)
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
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton_ClearActionPerformed

    private void jComboBox_PolygonsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_PolygonsActionPerformed
        // TODO add your handling code here:
        buttonGroup1.clearSelection();
        reset();
        restore();
        checkPolygon();
    }//GEN-LAST:event_jComboBox_PolygonsActionPerformed

    private void jButton_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_OKActionPerformed
        // TODO add your handling code here:
        solve();
    }//GEN-LAST:event_jButton_OKActionPerformed

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
            java.util.logging.Logger.getLogger(OtherPolygons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OtherPolygons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OtherPolygons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OtherPolygons.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OtherPolygons dialog = new OtherPolygons(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox jComboBox_Polygons;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_A;
    private javax.swing.JLabel jLabel_B;
    private javax.swing.JLabel jLabel_C;
    private javax.swing.JLabel jLabel_D;
    private javax.swing.JLabel jLabel_E;
    private javax.swing.JLabel jLabel_F;
    private javax.swing.JLabel jLabel_G;
    private javax.swing.JLabel jLabel_H;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton_Lengths;
    private javax.swing.JRadioButton jRadioButton_Vertices;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextPane jTextPane_A_X;
    private javax.swing.JTextPane jTextPane_A_Y;
    private javax.swing.JTextPane jTextPane_Area;
    private javax.swing.JTextPane jTextPane_B_X;
    private javax.swing.JTextPane jTextPane_B_Y;
    private javax.swing.JTextPane jTextPane_C_X;
    private javax.swing.JTextPane jTextPane_C_Y;
    private javax.swing.JTextPane jTextPane_D_X;
    private javax.swing.JTextPane jTextPane_D_Y;
    private javax.swing.JTextPane jTextPane_E_X;
    private javax.swing.JTextPane jTextPane_E_Y;
    private javax.swing.JTextPane jTextPane_F_X;
    private javax.swing.JTextPane jTextPane_F_Y;
    private javax.swing.JTextPane jTextPane_G_X;
    private javax.swing.JTextPane jTextPane_G_Y;
    private javax.swing.JTextPane jTextPane_H_X;
    private javax.swing.JTextPane jTextPane_H_Y;
    private javax.swing.JTextPane jTextPane_Length;
    private javax.swing.JTextPane jTextPane_Perimeter;
    // End of variables declaration//GEN-END:variables
}
