package tasks.fgi;

import hslist.HsListTask;

/**
 *
 * @author bp12214
 */
public class Fgi extends HsListTask {

    public Fgi(int prioridade, String nome, String value) {
        super(prioridade, nome, value);
    }

    @Override
    public void run() {
        FgiGetID id = new FgiGetID();
        FgiHttp http = new FgiHttp(id.getIdLogs(), id.getPath());
        http.start();
    }
}
