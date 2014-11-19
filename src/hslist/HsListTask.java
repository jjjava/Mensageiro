package hslist;

/**
 *
 * @author bp12214
 */
public class HsListTask implements Comparable<HsListTask>, Runnable {

    private int prioridade;
    private String pri;
    private String nome;
    private String value;

    public HsListTask(int prioridade, String nome, String value) {
        this.prioridade = prioridade;
        pri = "" + this.prioridade;
        this.nome = nome;
        this.value = value;
    }

    public void start() {
        Thread t = new Thread(this);
        if (getPrioridade() > 6) {
            t.setPriority(Thread.MAX_PRIORITY);
        }
        if (getPrioridade() > 3 && getPrioridade() < 7) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        if (getPrioridade() < 4) {
            t.setPriority(Thread.MIN_PRIORITY);
        }
        System.out.println("Pri " + value + " " + getPrioridade() + " " + t.getPriority());
        t.start();
    }

    @Override
    public void run() {

    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
        pri = "" + this.prioridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPri() {
        return pri;
    }

    @Override
    public int compareTo(HsListTask o) {
        return pri.compareTo(o.getPri());
    }
}