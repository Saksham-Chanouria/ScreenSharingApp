package vmm_team_viewer;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class diskdrives extends javax.swing.JFrame{

    ArrayList<drive> al = new ArrayList<>();
    myTableModel tm = new myTableModel();
    String ip;
   
    public diskdrives(String IP) {
        initComponents();
        
        ip = IP;
        
        jTable1.setModel(tm);
        
        Dimension d= new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        
        setTitle("AnyDESK - Screen Sharer App");
        
        photoLb.setBounds(0,0,d.width,d.height);
        ImageIcon i1 = new ImageIcon("src/uploads/background.jpg");
        Image resized = i1.getImage().getScaledInstance(photoLb.getWidth(), photoLb.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(resized);
        photoLb.setIcon(i2);
        
        // Icon
        ImageIcon icon = new ImageIcon("src/uploads/icon.png");
        setIconImage(icon.getImage());
        
        setSize(564, 409);
        setVisible(true);
        setLocationRelativeTo(null);
        
        
        getDrives();
        
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = jTable1.getSelectedRow();
//                JOptionPane.showMessageDialog(null, row);
                getFurther(row,ip);
            }
        });
        
        
    }
    
    void getDrives() {
        try {
            HttpResponse<String> res = Unirest.get("http://" + ip + ":8000/get-drives")
                    .asString();
            if (res.getStatus() == 200) {
//                JOptionPane.showMessageDialog(this, "entered");
                String ans = res.getBody();
                System.out.println(ans);
                StringTokenizer st = new StringTokenizer(ans, "~~");
                while (st.hasMoreTokens()) {
                    String str = st.nextToken();
                    StringTokenizer st2 = new StringTokenizer(str, ";;");
                    String drivename = st2.nextToken();
                    String type = st2.nextToken();
                    String totalSpace = st2.nextToken();
                    String freeSpace = st2.nextToken();
//                    System.out.println(drivename + " " + type + " " + totalSpace + " " + freeSpace);
                    al.add(new drive(drivename, type, totalSpace, freeSpace));
                }
                tm.fireTableDataChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void getFurther(int index,String ip) {
        String name = al.get(index).drivename;
        DriveDetailFrame obj= new DriveDetailFrame(name,ip);
    }
    
    class myTableModel extends AbstractTableModel
    {

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
        @Override
        public String getColumnName(int j){
            String c[] = {"Disk Name","Type","Total Space","Available Space"};
            return c[j];
        }

        @Override
        public Object getValueAt(int i, int j) {
            drive dv = al.get(i);
            if (j == 0) {
                return dv.drivename;
            } else if (j == 1) {
                return dv.type;
            } else if (j == 2) {
                return dv.totalSpace;
            } else {
                return dv.freeSpace;
            }
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        photoLb = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        jScrollPane1.setBounds(60, 100, 400, 230);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(88, 27, 152));
        jLabel1.setText("Server's Disk Drives");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(100, 10, 320, 50);

        photoLb.setText("|");
        getContentPane().add(photoLb);
        photoLb.setBounds(20, 20, 3, 17);

        setBounds(0, 0, 578, 416);
    }// </editor-fold>//GEN-END:initComponents

    
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
            java.util.logging.Logger.getLogger(diskdrives.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diskdrives.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diskdrives.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diskdrives.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new diskdrives("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel photoLb;
    // End of variables declaration//GEN-END:variables
}
