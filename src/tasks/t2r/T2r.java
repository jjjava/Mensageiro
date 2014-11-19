package tasks.t2r;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import hslist.HsListTask;

/**
 *
 * @author bp12214
 */
public class T2r extends HsListTask {

    public T2r(int prioridade, String nome, String value) {
        super(prioridade, nome, value);
    }

    @Override
    public void run() {
        try {
            T2rGetID id = new T2rGetID();
            T2rHttp http = new T2rHttp(id.getIdLogs());
            http.getFile(id.getPath());
            http.saveFile();

            File file = new File(System.getProperties().getProperty("user.dir") + "/log.txt");
            FileWriter fileWriter;
            fileWriter = new FileWriter(file, true);
            BufferedWriter buffer = new BufferedWriter(fileWriter);
            Calendar dateTime = Calendar.getInstance();

            String data = String.format("%tc\n", dateTime);
            buffer.write(data + " OK\n");
            buffer.flush();
            buffer.close();

            System.out.println("Done.");
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
