/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vmm_team_viewer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.vmm.NanoHTTPD;
import com.vmm.NanoHTTPD.Response;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author new user
 */
public class view_screen extends javax.swing.JFrame {

    String ip;

    /**
     * Creates new form view_screen
     */
    public view_screen(String IP) {
        initComponents();
        setTitle("AnyDESK - Screen Sharer App");
        ip = IP;
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        System.out.println(d);
        setSize(d);
        
        jsp.setBounds(0, 0, d.width - 300, d.height - 70);
        jPanel.setPreferredSize(new Dimension(d.width, d.height));
        photoLB.setBounds(0, 0, d.width, d.height);
        setVisible(true);
        
        photoLb.setBounds(0,0,d.width,d.height);
        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
        Image resized = i1.getImage().getScaledInstance(photoLb.getWidth(), photoLb.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(resized);
        photoLb.setIcon(i2);
        
        // Icon
        ImageIcon icon = new ImageIcon("src/uploads/icon.png");
        setIconImage(icon.getImage());

        getScreen();

        // Mouse Listener ---------------------------------------------------------
        jPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
//                System.out.println(x);
//                System.out.println(y);
                moveMouse(x, y);
            }
        });

        jPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseClick();
            }
        });

        // Keyboard Listener ------------------------------------------------------
        jPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int ke = e.getKeyCode();
                System.out.println(ke);
                keyPress(ke);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int ke = e.getKeyCode();
                keyRelease(ke);
            }
        });
        
        

        jPanel.requestFocus();
    }

    void moveMouse(int x, int y) {
//        System.out.println(x + "," + y);
        try {
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/mouseMoved")
                    .queryString("xcoord", x)
                    .queryString("ycoord", y)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void mouseClick() {
        try {
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/mouseClicked")
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void keyPress(int ke) {
        try {
            System.out.println(ke);
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/keyPressed")
                    .queryString("keycode", ke)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void keyRelease(int ke) {
        try {
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/keyReleased")
                    .queryString("keycode", ke)
                    .asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScreen() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/getScreen")
                                .asString();

                        if (res.getStatus() == 200) {
                            String path = res.getBody();
                            if (path.length() > 0) {
                                URL url = new URL("http://" + ip + ":8000/GetResource/" + path);
                                BufferedImage img = ImageIO.read(url);
                                Image Resized = img.getScaledInstance(photoLB.getWidth(), photoLB.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon I1 = new ImageIcon(Resized);
                                photoLB.setIcon(I1);
                            }

                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane, "Session Completed");
                        dispose();
                    }
                }
            }
        }).start();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jsp = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        photoLB = new javax.swing.JLabel();
        restart = new javax.swing.JButton();
        shut = new javax.swing.JButton();
        sleep = new javax.swing.JButton();
        sendmsg = new javax.swing.JButton();
        getDrive = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        getconfig1 = new javax.swing.JButton();
        photoLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel.setLayout(null);
        jPanel.add(photoLB);
        photoLB.setBounds(6, 28, 0, 0);

        jsp.setViewportView(jPanel);

        getContentPane().add(jsp);
        jsp.setBounds(10, 20, 70, 80);

        restart.setBackground(new java.awt.Color(255, 186, 196));
        restart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        restart.setForeground(new java.awt.Color(9, 21, 64));
        restart.setText("Restart");
        restart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });
        getContentPane().add(restart);
        restart.setBounds(1190, 30, 190, 60);

        shut.setBackground(new java.awt.Color(255, 186, 196));
        shut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        shut.setForeground(new java.awt.Color(9, 21, 64));
        shut.setText("Shut Down");
        shut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        shut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shutActionPerformed(evt);
            }
        });
        getContentPane().add(shut);
        shut.setBounds(1190, 120, 190, 60);

        sleep.setBackground(new java.awt.Color(255, 186, 196));
        sleep.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sleep.setForeground(new java.awt.Color(9, 21, 64));
        sleep.setText("Sleep");
        sleep.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sleep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sleepActionPerformed(evt);
            }
        });
        getContentPane().add(sleep);
        sleep.setBounds(1190, 210, 190, 60);

        sendmsg.setBackground(new java.awt.Color(255, 186, 196));
        sendmsg.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        sendmsg.setForeground(new java.awt.Color(9, 21, 64));
        sendmsg.setText("Send Message");
        sendmsg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendmsg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendmsgActionPerformed(evt);
            }
        });
        getContentPane().add(sendmsg);
        sendmsg.setBounds(1190, 300, 190, 60);

        getDrive.setBackground(new java.awt.Color(255, 186, 196));
        getDrive.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getDrive.setForeground(new java.awt.Color(9, 21, 64));
        getDrive.setText("Get Drives");
        getDrive.setBounds(new java.awt.Rectangle(1190, 480, 190, 60));
        getDrive.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getDriveActionPerformed(evt);
            }
        });
        getContentPane().add(getDrive);
        getDrive.setBounds(1190, 470, 190, 60);

        jLabel2.setBackground(new java.awt.Color(250, 141, 112));
        jLabel2.setFont(new java.awt.Font("Cooper Black", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 51));
        jLabel2.setText("AnyDESK");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(1250, 610, 130, 40);

        getconfig1.setBackground(new java.awt.Color(255, 186, 196));
        getconfig1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        getconfig1.setForeground(new java.awt.Color(9, 21, 64));
        getconfig1.setText("Get Configuration");
        getconfig1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getconfig1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getconfig1ActionPerformed(evt);
            }
        });
        getContentPane().add(getconfig1);
        getconfig1.setBounds(1190, 390, 190, 60);

        photoLb.setText(".");
        getContentPane().add(photoLb);
        photoLb.setBounds(210, 10, 4, 17);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
        // TODO add your handling code here:
        try{
            HttpResponse<String> res = Unirest.get("http://"+ip+":8000/restart").asString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_restartActionPerformed

    private void shutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shutActionPerformed
        // TODO add your handling code here:
        try{
            HttpResponse<String> res = Unirest.get("http://"+ip+":8000/shutdown").asString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_shutActionPerformed

    private void sleepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sleepActionPerformed
        // TODO add your handling code here:
        try{
            HttpResponse<String> res = Unirest.get("http://"+ip+":8000/sleep").asString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_sleepActionPerformed

    private void sendmsgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendmsgActionPerformed
        // TODO add your handling code here:
        String chat = JOptionPane.showInputDialog(null);
        System.out.println(chat);
        try{
            HttpResponse<String> res = Unirest.get("http://"+ip+":8000/send-message")
                    .queryString("message",chat)
                    .asString();
            
            if(res.getStatus()==200){
                String message = res.getBody();
                JOptionPane.showMessageDialog(null, message);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_sendmsgActionPerformed

    private void getDriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDriveActionPerformed
        // TODO add your handling code here:
        diskdrives obj = new diskdrives(ip);
        
    }//GEN-LAST:event_getDriveActionPerformed

    private void getconfig1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getconfig1ActionPerformed
        // TODO add your handling code here:
        Config obj = new Config(ip);
//        hide();
    }//GEN-LAST:event_getconfig1ActionPerformed

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
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(view_screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new view_screen("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton getDrive;
    private javax.swing.JButton getconfig1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel;
    private javax.swing.JScrollPane jsp;
    private javax.swing.JLabel photoLB;
    private javax.swing.JLabel photoLb;
    private javax.swing.JButton restart;
    private javax.swing.JButton sendmsg;
    private javax.swing.JButton shut;
    private javax.swing.JButton sleep;
    // End of variables declaration//GEN-END:variables

}
