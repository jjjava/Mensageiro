package tasks.fgi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Hudson Schumaker
 */
public class FgiGetID {

    private String path;

    public FgiGetID() {
        path = "http://miaibv109.mia.michelin.com:3004/reports/ViewLogs/FGI/func_log/";
    }

    public ArrayList<String> getIdLogs() {

        ArrayList<String> logs = new ArrayList<String>();
        try {
            URL url = new URL(getPath());
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            String s = null;

            while ((s = br.readLine()) != null) {
                if (s.length() > 50) {

                    int c = s.indexOf("\">");
                    int f = s.indexOf("</a>");
                    if (c >= 0 && f > c) {

                        String aux = s.substring(c + 2, f);
                        if (aux.equalsIgnoreCase("name")
                                || aux.equalsIgnoreCase("RTWSAME0")
                                || aux.equalsIgnoreCase("TWSMERGE")
                                || aux.equalsIgnoreCase("NETMAN")) {
                        } else {
                            logs.add(aux);
                        }
                    }
                } else {
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (MalformedURLException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return logs;
    }

    public String getPath() {
        return path;
    }
}
