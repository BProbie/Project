package api;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileSystemView;

public class Windows {

    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException interruptedException) {
            throw new RuntimeException(interruptedException);
        }
    }

    public static void open(String path) {
        try {
            Desktop.getDesktop().open(new File(path));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String readFile(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path),StandardCharsets.UTF_8));
            String values = ""; String value;
            while ((value=bufferedReader.readLine())!=null) values = values + value + "\n";
            return replaceLast(values,"\n","");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> readFileList(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path),StandardCharsets.UTF_8));
            ArrayList<String> arrayList = new ArrayList<>();
            String reader;
            while ((reader = bufferedReader.readLine())!=null) arrayList.add(reader);
            return arrayList;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String path,String value) {
        try {
            if (!new File(getPath(path)).exists()) {new File(getPath(path)).mkdirs();}
            if (!new File(path).exists()) new File(path).createNewFile();
            OutputStream outputStream = new FileOutputStream(path);
            byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static int runCommand(String worker,String state,String command) {
        try {
            return Runtime.getRuntime().exec(new String[] {worker,state,command}).waitFor();
        } catch (InterruptedException | IOException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    public static void showInformation(String information) {
        writeFile(getTempPath()+"\\"+"information.vbs","Information=msgbox(\""+information+"\",vbOKOnly,\"Information\")");
        open(getTempPath()+"\\"+"information.vbs");
    }

    public static File getChosenFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("D:\\"));
        boolean isOpen = jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION;
        if (isOpen) return jFileChooser.getSelectedFile();
        return null;
    }
    public static File getChosenFile(String defaultPath) {
        JFileChooser jFileChooser = new JFileChooser();
        File file = new File(defaultPath);
        if (file.exists()) {jFileChooser.setCurrentDirectory(file);}
        else {jFileChooser.setCurrentDirectory(new File("D:\\"));}
        boolean isOpen = jFileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION;
        if (isOpen) return jFileChooser.getSelectedFile();
        return null;
    }

    public static String replaceLast(String text,String regex, String replacement) {return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")",replacement);}

    public static char getPan(String path) {return path.toCharArray()[0];}
    public static String getUser() {return System.getenv().get("USERNAME");}

    public static String getPath(String path) {
        if (path.contains("https://")||path.contains("http://")||path.contains("ftps://")||path.contains("ftp://")) {return path.replaceAll(path.split("/")[path.split("/").length-1],"");}
        return path.replaceAll(path.split("\\\\")[path.split("\\\\").length-1],"");
    }
    public static String getFileName(String path) {
        if (path.contains("https://")||path.contains("http://")||path.contains("ftps://")||path.contains("ftp://")) {return path.split("/")[path.split("/").length-1];}
        return path.split("\\\\")[path.split("\\\\").length-1];
    }
    public static String getFilePath(String path,String fileName) {
        if (path.contains("https://")||path.contains("http://")||path.contains("ftps://")||path.contains("ftp://")) {
            if (path.endsWith("/")) {return path+fileName;}
            return path+"/"+fileName;
        }
        return path+"\\"+fileName;
    }

    public static String getHere() {return System.getProperty("user.dir");}
    public static String getTempPath() {return System.getenv().get("TEMP");}
    public static String getDesktopPath() {return FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();}
}