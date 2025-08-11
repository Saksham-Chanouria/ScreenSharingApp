package vmm_team_viewer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.AbstractTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author dell
 */
public class DriveDetailFrame extends javax.swing.JFrame {

    /**
     * Creates new form DriveDetailFrame
     */
    String name = "";
    String ip;
    ArrayList<drive_details> al;
    mytablemodel tm = new mytablemodel();

    public DriveDetailFrame(String name, String IP) {
        initComponents();
        Dimension d= new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        
        setTitle("AnyDESK - Screen Sharer App");
        setLocationRelativeTo(null);
        setSize(704, 500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        
//        photoLb.setBounds(0,0,d.width,d.height);
//        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
//        Image resized = i1.getImage().getScaledInstance(photoLb.getWidth(), photoLb.getHeight(), Image.SCALE_SMOOTH);
//        ImageIcon i2 = new ImageIcon(resized);
//        photoLb.setIcon(i2);

        photoLb.setBounds(0, 0, d.width, d.height);
        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
        Image resized = i1.getImage().getScaledInstance(photoLb.getWidth(), photoLb.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(resized);
        photoLb.setIcon(i2);
        
        ip = IP;
        
        al = new ArrayList<>();
        
        this.name = name;
        
        jTable1.setModel(tm);
        setLocationRelativeTo(null);
        
        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = jTable1.getSelectedRow();
                String type = al.get(row).type;
                String name = al.get(row).name;
                if (type.equals("directory")) {
                    DriveDetailFrame obj = new DriveDetailFrame(name, ip);
                } else {
                    int a = JOptionPane.showConfirmDialog(rootPane, "Do You want to download this file");
                    if (a == JOptionPane.YES_OPTION) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                FileOutputStream fos = null;
                                try {
                                    String filepath = al.get(row).name;
                                    System.out.println(filepath);
                                    HttpResponse<InputStream> HttpResponse = Unirest.get("http://"+ip+":8000/GetResource/" + filepath)
                                            .asBinary();
                                    String filename = filepath.substring(filepath.lastIndexOf("\\"));
                                    System.out.println(filename);
                                    InputStream is = HttpResponse.getBody();
                                    fos = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + filename);
                                    String downloadfile = System.getProperty("user.home") + "/Downloads/" + filename;
                                    long contentlength = Integer.parseInt(HttpResponse.getHeaders().getFirst("Content-Length"));
                                    byte b[] = new byte[10000];
                                    int r;
                                    long count = 0;
                                    while (true) {
                                        r = is.read(b, 0, 10000);
                                        fos.write(b, 0, r);
                                        count = count + r;
                                        int per = (int) (count * 100 / contentlength);
                                        if (count == contentlength) {
                                            break;
                                        }
                                    }
                                    fos.close();
                                    JOptionPane.showMessageDialog(DriveDetailFrame.this, "file downloaded");
                                    Desktop.getDesktop().open(new File(downloadfile));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                } finally {
                                    try {
                                        fos.close();
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }

                        }).start();
                    }
                }
            }

        });
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        callFunction();
    }

    class mytablemodel extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int j) {
            String dd[] = {"name", "type"};
            return dd[j];
        }

        @Override
        public Object getValueAt(int i, int j) {
            drive_details d = al.get(i);
            if (j == 0) {
                return d.name;
            } else {
                return d.type;
            }
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
        photolb = new javax.swing.JLabel();
        photoLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(100, 70, 530, 340);

        photolb.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        photolb.setForeground(new java.awt.Color(88, 27, 152));
        photolb.setText("Server's DRIVE Details");
        getContentPane().add(photolb);
        photolb.setBounds(200, 10, 370, 50);

        photoLb.setText(".");
        getContentPane().add(photoLb);
        photoLb.setBounds(180, 120, 3, 16);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public void callFunction() {
        System.out.println(name);
        try {
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/get-further")
                    .queryString("name", name)
                    .asString();
            if (res.getStatus() == 200) {
                String ans = res.getBody();
                if (ans.equals("download")) {
                    JOptionPane.showConfirmDialog(rootPane, "Do you want to download");
                } else {
                    System.out.println(ans);
                    StringTokenizer st = new StringTokenizer(ans, ";;");

                    while (st.hasMoreTokens()) {
                        String row = st.nextToken();
                        StringTokenizer st2 = new StringTokenizer(row, "~~");
                        String name = st2.nextToken();
                        String type = st2.nextToken();
                        al.add(new drive_details(name, type));
                    }
                    tm.fireTableDataChanged();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            java.util.logging.Logger.getLogger(DriveDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DriveDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DriveDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DriveDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DriveDetailFrame("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel photoLb;
    private javax.swing.JLabel photolb;
    // End of variables declaration//GEN-END:variables
}
