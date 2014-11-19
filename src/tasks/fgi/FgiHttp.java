package tasks.fgi;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author Hudson Schumaker
 */
public class FgiHttp implements Runnable {
    
    private ArrayList<String> files;
    private String name;
    private String arquivo;
    private String path;
    
    public FgiHttp(ArrayList<String> files, String path) {
    
        this.files = files;
        this.path = path;
        name = "LOG_FGI_ADS_";
    }
    
    public void start() {
        Thread t = new Thread(this);
        t.setPriority(10);
        t.start();
    }
    
    private void getFile() {
        arquivo = "";
        for (int k = 0; k < files.size(); k++) {
            if (files.get(k).startsWith(name)) {
                try {
                    arquivo = files.get(k);
                   
                    File file = new File("C:/queue/" + arquivo);
                    file.createNewFile();
                    URL url = new URL(path + arquivo);
                    URLConnection connection = url.openConnection();
                    connection.connect();
                    int fileLenth = connection.getContentLength();
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    String s = null;
                    while ((s = br.readLine()) != null) {
                        FileWriter fileWriter = new FileWriter(file, true);
                        BufferedWriter buffer = new BufferedWriter(fileWriter);
                        buffer.write(s + "\n");
                        buffer.flush();
                        buffer.close();
                    }
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
    
    @Override
    public void run() {
        getFile();
    }
}