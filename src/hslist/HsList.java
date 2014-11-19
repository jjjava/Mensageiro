package hslist;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author bp12214
 */
public class HsList {

    private ArrayList<HsListTask> pilhaStore;
    private ArrayList<HsListTask> pilhaProcess;
    private boolean processing;

    public HsList() {
        pilhaStore = new ArrayList<>();
        pilhaProcess = new ArrayList<>();
    }

    public void add(HsListTask t) {
        pilhaStore.add(t);
    }

    public int getCountPriHigh() {
        int value = 0;

        for (int k = 0; k < pilhaStore.size(); k++) {
            if (pilhaStore.get(k).getPrioridade() >= 7) {
                value++;
            }
        }
        return value;
    }

    public int getCountPriMedium() {
        int value = 0;

        for (int k = 0; k < pilhaStore.size(); k++) {
            if (pilhaStore.get(k).getPrioridade() < 7 && pilhaStore.get(k).getPrioridade() >= 4) {
                value++;
            }
        }
        return value;
    }

    public int getCountPriLow() {
        int value = 0;

        for (int k = 0; k < pilhaStore.size(); k++) {
            if (pilhaStore.get(k).getPrioridade() < 4 && pilhaStore.get(k).getPrioridade() >= 1) {
                value++;
            }
        }
        return value;
    }

    public int getCountAll() {
        return pilhaStore.size();
    }

    public void processAll() {
        if (!processing) {
            processing = true;
            pilhaProcess = pilhaStore;
            clean();
            Collections.sort(pilhaProcess);

            while (pilhaProcess.size() > 0) {
                pilhaProcess.get(pilhaProcess.size() - 1).start();
                pilhaProcess.remove(pilhaProcess.get(pilhaProcess.size() - 1));
            }
            processing = false;
            System.out.println(pilhaProcess.size());
        }
    }

    public void processLow() {
        if (!processing) {
            processing = true;
            ArrayList<HsListTask> pilhaTemp = new ArrayList<>();
            for (int k = 0; k < pilhaStore.size(); k++) {
                if (pilhaStore.get(k).getPrioridade() < 4) {
                    pilhaProcess.add(pilhaStore.get(k));
                    pilhaTemp.add(pilhaStore.get(k));
                }
            }
            for (int k = 0; k < pilhaTemp.size(); k++) {
                pilhaStore.remove(pilhaTemp.get(k));
            }
            while (pilhaProcess.size() > 0) {
                pilhaProcess.get(pilhaProcess.size() - 1).start();
                pilhaProcess.remove(pilhaProcess.get(pilhaProcess.size() - 1));
            }
            processing = false;
            System.out.println(pilhaProcess.size());
        }
    }

    public void processMedium() {
        if (!processing) {
            processing = true;
            ArrayList<HsListTask> pilhaTemp = new ArrayList<>();
            for (int k = 0; k < pilhaStore.size(); k++) {
                if (pilhaStore.get(k).getPrioridade() >= 4 && pilhaStore.get(k).getPrioridade() <= 6) {
                    pilhaProcess.add(pilhaStore.get(k));
                    pilhaTemp.add(pilhaStore.get(k));
                }
            }
            for (int k = 0; k < pilhaTemp.size(); k++) {
                pilhaStore.remove(pilhaTemp.get(k));
            }
            while (pilhaProcess.size() > 0) {
                pilhaProcess.get(pilhaProcess.size() - 1).start();
                pilhaProcess.remove(pilhaProcess.get(pilhaProcess.size() - 1));
            }
            processing = false;
            System.out.println(pilhaProcess.size());
        }
    }

    public void processHigh() {
        if (!processing) {
            processing = true;
            ArrayList<HsListTask> pilhaTemp = new ArrayList<>();
            for (int k = 0; k < pilhaStore.size(); k++) {
                if (pilhaStore.get(k).getPrioridade() > 6) {
                    pilhaProcess.add(pilhaStore.get(k));
                    pilhaTemp.add(pilhaStore.get(k));
                }
            }
            for (int k = 0; k < pilhaTemp.size(); k++) {
                pilhaStore.remove(pilhaTemp.get(k));
            }
            Collections.sort(pilhaProcess);
            while (pilhaProcess.size() > 0) {
                pilhaProcess.get(pilhaProcess.size() - 1).start();
                pilhaProcess.remove(pilhaProcess.get(pilhaProcess.size() - 1));
            }
            processing = false;
            System.out.println(pilhaProcess.size());
        }
    }

    private void clean() {
        pilhaStore = null;
        pilhaStore = new ArrayList<>();
    }

    public void print() {
        for (int k = 0; k < pilhaStore.size(); k++) {
            System.out.println(pilhaStore.get(k).getValue());
        }
    }

    public String printInLine() {
        String s = "";
        for (int k = 0; k < pilhaStore.size(); k++) {
            s = s + "\n\r" + pilhaStore.get(k).getValue() + " " + pilhaStore.get(k).getPri();
        }
        return s;
    }

    public boolean isProcessing() {
        return processing;
    }
}