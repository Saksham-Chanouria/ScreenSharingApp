
package vmm_team_viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import javax.swing.ImageIcon;


public class ManageServer extends javax.swing.JFrame {

    
    public ManageServer() {
        
        initComponents();
        stop.setEnabled(false);
        setTitle("AnyDESK - Screen Sharer App");
//        add(jLabel3);
//        getContentPane().setBackground(Color.blue);

        
        Dimension d= new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        
        setSize(593, 415);
        setLocationRelativeTo(null);
        setTitle("Server Side");
        
        setVisible(true);
        label1.setText("Please Start the SERVER");
        
        // Background Image
        photoLB.setBounds(0,0,d.width,d.height);
        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
        Image resized = i1.getImage().getScaledInstance(photoLB.getWidth(), photoLB.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(resized);
        photoLB.setIcon(i2);
        
        // Icon
        ImageIcon icon = new ImageIcon("src/uploads/icon.png");
        setIconImage(icon.getImage());
     
        label2.setVisible(false);
        start.setEnabled(true);
        stop.setEnabled(true);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        start = new javax.swing.JButton();
        stop = new javax.swing.JButton();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        jlabel3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        photoLb = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        photoLB = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        start.setBackground(new java.awt.Color(255, 186, 196));
        start.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        start.setForeground(new java.awt.Color(9, 21, 64));
        start.setText("START SERVER");
        start.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start);
        start.setBounds(170, 80, 230, 80);

        stop.setBackground(new java.awt.Color(255, 186, 196));
        stop.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        stop.setForeground(new java.awt.Color(9, 21, 64));
        stop.setText("STOP SERVER");
        stop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        getContentPane().add(stop);
        stop.setBounds(170, 190, 230, 70);

        label1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(9, 21, 64));
        label1.setText("Server started at Port no. 8000");
        getContentPane().add(label1);
        label1.setBounds(10, 320, 210, 20);

        label2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(9, 21, 64));
        label2.setText("Use this IP address to connect : ");
        getContentPane().add(label2);
        label2.setBounds(10, 340, 360, 16);
        getContentPane().add(jlabel3);
        jlabel3.setBounds(190, 340, 100, 0);

        label4.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        getContentPane().add(label4);
        label4.setBounds(190, 340, 90, 0);
        getContentPane().add(photoLb);
        photoLb.setBounds(20, 10, 0, 0);

        jLabel3.setBackground(new java.awt.Color(250, 141, 112));
        jLabel3.setFont(new java.awt.Font("Cooper Black", 0, 24)); // NOI18N
        jLabel3.setText("AnyDESK");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 330, 130, 40);

        photoLB.setText("jLabel2");
        getContentPane().add(photoLB);
        photoLB.setBounds(10, 10, 42, 17);

        setBounds(0, 0, 610, 391);
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
        try {
            MyServer obj = new MyServer(8000);
            
            String ip="";
            try {
                Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaceEnumeration.hasMoreElements()) {
                    for (InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses()) {
                        if (interfaceAddress.getAddress().isSiteLocalAddress()) {
                            ip = interfaceAddress.getAddress().getHostAddress();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }

            System.out.println("IP:"+ip);

            start.setEnabled(false);
            stop.setEnabled(true);

            label1.setText("Server started at Port no. 8000");
            label2.setText("Use this IP address to connect : " + ip);
            label2.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_startActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        // TODO add your handling code here:
        
        start.setEnabled(true);
        stop.setEnabled(false);
        
        label1.setText("Please Start the SERVER");
        label2.setVisible(false);
    }//GEN-LAST:event_stopActionPerformed

    
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
            java.util.logging.Logger.getLogger(ManageServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel photoLB;
    private javax.swing.JLabel photoLb;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    // End of variables declaration//GEN-END:variables
}
