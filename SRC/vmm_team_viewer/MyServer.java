package vmm_team_viewer;

import com.vmm.JHTTPServer;
import static com.vmm.NanoHTTPD.HTTP_OK;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class MyServer extends JHTTPServer {
    
    String IP = "";
    File f;
    String path;

    public MyServer(int portno) throws IOException {
        super(portno);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        Response res = null;
        System.out.println(uri);

        if (uri.equals("/checkAddress")) {
            System.out.println("Request received");
            String ip = parms.getProperty("ip");
            
            try {
                Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaceEnumeration.hasMoreElements()) {
                    for (InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses()) {
                        if (interfaceAddress.getAddress().isSiteLocalAddress()) {
                            IP = interfaceAddress.getAddress().getHostAddress();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }

            if (ip.equals(IP)) {
                res = new Response(HTTP_OK, "text/plain", "success");

            } else {
                res = new Response(HTTP_OK, "text/plain", "failed");
            }
        }
        
        else if (uri.contains("/GetResource")) {
            uri = uri.substring(1);
//            System.out.println(uri);
            uri = uri.substring(uri.indexOf("/") + 1);
//            System.out.println(uri);
            res = sendCompleteFile(uri);
//            System.out.println("Response reference to be send to client-------------" + res);
        } 
        
        else if (uri.equals("/getScreen")) {
            try {
                Robot robot = new Robot();

                BufferedImage screenShot = robot
                        .createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                f = new File("src/uploads/one.jpg");
                ImageIO.write(screenShot, "JPG", f);
                path = f.getPath();
                res = new Response(HTTP_OK, "text/plain", path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if (uri.equals("/mouseMoved")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int x = Integer.parseInt(parms.getProperty("xcoord"));
                    int y = Integer.parseInt(parms.getProperty("ycoord"));

                    try {
                        Robot robot = new Robot();
                        robot.mouseMove(x, y);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        
        /*
            else if (uri.equals("/mouseMoved")) {
    try {
        // Get sender coordinates and screen size
        int senderX = Integer.parseInt(parms.getProperty("xcoord", "0"));
        int senderY = Integer.parseInt(parms.getProperty("ycoord", "0"));
        int senderWidth = Integer.parseInt(parms.getProperty("senderWidth", "1920"));
        int senderHeight = Integer.parseInt(parms.getProperty("senderHeight", "1080"));

        // Get receiver's screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int receiverWidth = screenSize.width;
        int receiverHeight = screenSize.height;

        // Scale coordinates based on screen ratio
        int mappedX = (int) ((double) senderX / senderWidth * receiverWidth);
        int mappedY = (int) ((double) senderY / senderHeight * receiverHeight);

        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Robot robot = new Robot();
                robot.mouseMove(mappedX, mappedY);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        });

    } catch (NumberFormatException e) {
        System.err.println("Invalid input for mouse movement: " + e.getMessage());
    }
}
        */
        
        else if (uri.equals("/mouseClicked")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Robot robot = new Robot();
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        
        else if (uri.equals("/keyPressed")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int ke = Integer.parseInt(parms.getProperty("keycode"));
                    try {  
                        Robot robot = new Robot();
                        robot.keyPress(ke);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } 
        
        else if (uri.equals("/keyReleased")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int ke = Integer.parseInt(parms.getProperty("keycode"));
                    try {
                        Robot robot = new Robot();
                        robot.keyRelease(ke);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        
        else if(uri.contains("/restart"))
        {
            try{
                JOptionPane.showMessageDialog(null, "Going to Restart.");
                Runtime r = Runtime.getRuntime();
                r.exec("shutdown -r -t 5");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        else if(uri.contains("/shutdown"))
        {
            Runtime r = Runtime.getRuntime();
            try{
                JOptionPane.showMessageDialog(null, "Going to Shut Down.");
                r.exec("shutdown -s -t 5");
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        else if(uri.contains("/sleep")){
            Runtime r = Runtime.getRuntime();
            try{
                JOptionPane.showMessageDialog(null, "Going to sleep...");
                r.exec("shutdown -l");   
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        
        else if(uri.contains("/send-message")){
            String chat = parms.getProperty("message");
            JOptionPane.showMessageDialog(null, chat);
            String message = JOptionPane.showInputDialog(null);
            res =  new Response(HTTP_OK,"text/plain",message);
        }
        
        // Configuration of Server
        
        else if(uri.contains("/getconfig")){
            String osname = System.getProperty("os.name");
            long diskSize = new File("/").getTotalSpace();
            String username = System.getProperty("user.name");
            long maxmemory = Runtime.getRuntime().maxMemory();
            int core = Runtime.getRuntime().availableProcessors();
            long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
                    .getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
            
            String ans = osname+"~~"+diskSize+"~~"+username+"~~"+maxmemory+"~~"+core+"~~"+memorySize;
            res = new Response(HTTP_OK,"text/plain",ans);
        }
        
        else if(uri.contains("/get-drives")){
            String ans = "";
            FileSystemView fsv = FileSystemView.getFileSystemView();
            File[] drives = File.listRoots();
            if (drives != null && drives.length > 0) {
                for (File aDrive : drives) {
//                    System.out.println("Drive name is : " + aDrive);
                    String type = fsv.getSystemTypeDescription(aDrive);
                    long totalSpace = (((aDrive.getTotalSpace() / 1024) / 1024) / 1024);
                    long freeSpace = (((aDrive.getFreeSpace() / 1024) / 1024) / 1024);
                    String single = aDrive + ";;" + type + ";;" + totalSpace + ";;" + freeSpace + ";;";
                    ans += single + "~~";
                }
            }
            System.out.println(ans);
            res = new Response(HTTP_OK, "text/plain", ans);
        }
        
        else if (uri.equals("/get-further")) {
            String name = parms.getProperty("name");
            File maindir = new File(name);
            if (maindir.exists() && maindir.isDirectory()) {
                File arr[] = maindir.listFiles();
                String ans = "";
                String type;
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(arr[i]);
                    if (arr[i].isDirectory()) {
                        type = "directory";
                    }else{
                        type = "file";
                    }
                    String row = arr[i]+"~~"+type;
                    ans += row + ";;";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } else {
                res = new Response(HTTP_OK, "text/plain", "download");
            }
        }
        
        return res;
    }
    
    

}
