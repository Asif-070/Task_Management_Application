/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package taskmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class notification extends javax.swing.JFrame {

    String user = store.strr,dd,da,tie;
    Vector<String> v = new Vector<String>();
    public static notification inss;
    /**
     * Creates new form loading
     */
    public void load(){
        this.dispose();
        notification x = new notification();
        x.setVisible(true);
    }
    /**
     * Creates new form notification
     */
    public notification() {
        initComponents();
        inss = this;
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dd = sdf.format(d);
        da = dd.substring(0,4)+dd.substring(5,7)+dd.substring(8,10);
        jLabel1.setText(da);
        times();
        view();
        //checker();
    }
 
    Timer t;
    SimpleDateFormat st;
    
    
    public void times(){
        t = new Timer(0 , new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm a");
                String tt = st.format(dt),op;
                tie = tt.substring(0,2);//+tt.substring(3,5);
                op = tt.substring(6,8);
                String ist = tt.substring(0,2);
                String tnd = tt.substring(3,5);
                int et = Integer.parseInt(tnd)+10;
                int rt = et%60;
                int yt = Integer.parseInt(ist)+(et/60);
                if(yt<10){
                    tie = "0" + Integer.toString(yt) + Integer.toString(rt);
                }
                else{
                    tie = Integer.toString(yt) + Integer.toString(rt);
                }
                
                if(op.equals("AM")){
                    tie = tie + "0";
                }
                else{
                    tie = tie + "1";
                }
                jLabel2.setText(tie);
                checker();
            }
        });
        
        t.start();
    }

    public void checker(){
        String x;
        for(int i = 0; i < v.size(); i++){
            x = v.get(i);
            if(x.equals(tie)){
                JOptionPane optionPane = new JOptionPane();
                String go = x.substring(0,2) + ":" + x.substring(2,4);
                if(x.substring(4) == "1"){
                    go = go + " PM";
                }
                else{
                    go = go + " AM";
                }
                optionPane.setMessage("One of the tasks will be finished at " + go);
                //optionPane.setMessage("10 Mins Left for one of the task!!!");
                JDialog dialog = optionPane.createDialog("Alarm");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
                v.remove(i);
            }
        }
            
    }
    
    public void view(){
        int yo;
        try{
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/task","root","1234");
            String sql = "select * from " + user + " where date = ?";
            PreparedStatement ptst = c.prepareStatement(sql);
            ptst.setString(1,dd);
            ResultSet rs = ptst.executeQuery();
            
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            while(rs.next()){
                if(rs.getInt(8)==1){
                    model.addRow(new String[] {rs.getString(1),rs.getString(2),rs.getString(4),rs.getString(5)});
                    String tt = rs.getString(5);
                    String pe = tt.substring(0,2)+tt.substring(3,5);
                    String op = tt.substring(6,8);
                    if(op.equals("AM")){
                        pe = pe + "0";
                    }
                    else{
                        pe = pe + "1";
                    }
                    v.add(pe);
                }
            }
            c.close();
        }
        catch(SQLException ex){
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(0, 0));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("jLabel1");

        jLabel2.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(notification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new notification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
