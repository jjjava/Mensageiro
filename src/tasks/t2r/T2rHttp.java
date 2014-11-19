package tasks.t2r;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Hudson Schumaker
 */
public class T2rHttp {

    private ArrayList<String> files;
    private String name;
    private StringBuffer content;
    private String arquivo;

    public T2rHttp(ArrayList<String> files) {
        this.files = files;
        name = "T2R_PLANS_DPRRES_RES_";
        content = new StringBuffer();
    }

    public void getFile(String path) {
        arquivo = "";
        for (int k = 0; k < files.size(); k++) {
            if (files.get(k).startsWith(name)) {
                arquivo = files.get(k);
            }
        }
        System.out.println(arquivo);

        try {
            System.out.println(path + arquivo);
            URL url = new URL(path + arquivo);
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = null;
            while ((s = br.readLine()) != null) {
                content.append(s);
                content.append("\n");
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void saveFile() {
        //   \\cgrfs701\VOL1\SHARE\CGR\PELIDATA\RADS\DPR\F69T\RECEP\
        File file = new File("C:/queue/" + arquivo);
        String code = "";
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            buffer.write(content.toString());
            buffer.flush();
            buffer.close();
            code = "Arquivo copiado:\n" + arquivo;
        } catch (IOException e) {
            code = e.getMessage();
            System.out.println(e);
        } finally {
        }
    }
}