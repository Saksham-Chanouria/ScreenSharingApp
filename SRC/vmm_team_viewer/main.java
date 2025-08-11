package vmm_team_viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
        
        setTitle("AnyDESK - Screen Sharer App");
        
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        
        photoLb.setBounds(0,0,d.width,d.height);
        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
        Image resized = i1.getImage().getScaledInstance(photoLb.getWidth(), photoLb.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(resized);
        photoLb.setIcon(i2);
        
        // Icon
        ImageIcon icon = new ImageIcon("src/uploads/icon.png");
        setIconImage(icon.getImage());
        
        
        server.setBounds((d.width/2)-100, (d.height/2)-200, 280, 100);
        client.setBounds((d.width/2)-100, (d.height/2)-100, 280, 100);
        
//        setLocationRelativeTo(null);
        setSize(d);    //533, 354
        setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        client = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        server = new javax.swing.JButton();
        photoLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        client.setBackground(new java.awt.Color(255, 186, 196));
        client.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        client.setForeground(new java.awt.Color(9, 21, 64));
        client.setText("GET SCREEN");
        client.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        client.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientActionPerformed(evt);
            }
        });
        getContentPane().add(client);
        client.setBounds(570, 410, 280, 90);

        jLabel1.setBackground(new java.awt.Color(250, 141, 112));
        jLabel1.setFont(new java.awt.Font("Cooper Black", 0, 39)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("AnyDESK");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(240, 30, 210, 40);

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 0, 36)); // NOI18N
        jLabel2.setText("Welcome to");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(27, 20, 210, 50);

        server.setBackground(new java.awt.Color(255, 186, 196));
        server.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        server.setForeground(new java.awt.Color(9, 21, 64));
        server.setText("HOST SCREEN");
        server.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        server.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverActionPerformed(evt);
            }
        });
        getContentPane().add(server);
        server.setBounds(570, 290, 280, 100);

        photoLb.setText("|");
        getContentPane().add(photoLb);
        photoLb.setBounds(10, 50, 3, 17);

        setBounds(0, 0, 1373, 748);
    }// </editor-fold>//GEN-END:initComponents

    private void clientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientActionPerformed
        // TODO add your handling code here:
        
          GetConnection obj = new GetConnection();
//        dispose();
    }//GEN-LAST:event_clientActionPerformed

    private void serverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverActionPerformed
        // TODO add your handling code here:
        Login obj = new Login();    // For Database Connectivity
        
  //      ManageServer obj = new ManageServer();
//        dispose();
    }//GEN-LAST:event_serverActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton client;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel photoLb;
    private javax.swing.JButton server;
    // End of variables declaration//GEN-END:variables
}
