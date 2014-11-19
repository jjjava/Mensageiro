package mensageiro;

import hslist.HsList;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Thread.MIN_PRIORITY;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import tasks.fgi.Fgi;
import tasks.t2r.T2r;

/**
 *
 * @author hudson.sales
 */
public class Mensageiro implements Params {

    public static ServerSocket server = null;
    public static Socket incoming = null;
    public static HsList lista;

    public static void main(String[] args) {
        int port = PORT;
        lista = new HsList();
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("-port")) {
                if (args[1].length() > 0) {
                    port = Integer.parseInt(args[1]);
                }
            }
        } else {
            try {
                server = new ServerSocket(port);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    System.out.print(START);
                    System.out.println("" + port);
                    incoming = server.accept();
                    System.out.println("Client IP: " + incoming.getInetAddress());
                    new Session(incoming).start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Session extends Thread {

        Socket socket = null;

        public Session(Socket socket) {
            this.setPriority(MIN_PRIORITY);
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inps = socket.getInputStream();
                OutputStream outs = socket.getOutputStream();
                Scanner in = new Scanner(inps);
                PrintWriter out = new PrintWriter(outs, true);
                out.println(WELCOME);
                boolean done = false;
                while (!done) {
                    String line = in.nextLine();
                    if (line.trim().equalsIgnoreCase("exit")) {
                        System.out.println("exit " + socket.getInetAddress());
                        done = true;
                        try {
                            in.close();
                            in = null;
                            inps.close();
                            outs.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (line.trim().equalsIgnoreCase("close")) {
                        System.out.println("exit " + socket.getInetAddress());
                        out.println("shutdown");
                        System.exit(0);

                    } else if (line.trim().equalsIgnoreCase("help")) {
                        System.out.println("help " + socket.getInetAddress());
                        out.println("> 1 \n \r2 \r \n3 \r \n4 \r \n5 \r");
                    } else if (line.trim().equals("add t2r")) {
                        System.out.println("add" + socket.getInetAddress());
                        lista.add(new T2r(1, "t2r", "T2R " + socket.getInetAddress()));
                    } else if (line.trim().equals("add t2r high")) {
                        System.out.println("add" + socket.getInetAddress());
                        lista.add(new T2r(9, "t2r", "T2R " + socket.getInetAddress()));
                    } else if (line.trim().equals("add fgi")) {
                        System.out.println("add" + socket.getInetAddress());
                        lista.add(new Fgi(5, "fgi", "FGI " + socket.getInetAddress()));
                    } else if (line.trim().equals("process")) {
                        System.out.println("process" + socket.getInetAddress());
                        lista.processAll();
                    } else if (line.trim().equals("process high")) {
                        System.out.println("process" + socket.getInetAddress());
                        lista.processHigh();
                    } else if (line.trim().equals("process medium")) {
                        System.out.println("process" + socket.getInetAddress());
                        lista.processMedium();
                    } else if (line.trim().equals("process low")) {
                        System.out.println("process" + socket.getInetAddress());
                        lista.processLow();
                    } else if (line.trim().equals("size")) {
                        System.out.println("size" + socket.getInetAddress());
                        out.println("> " + lista.getCountAll());
                    } else if (line.trim().equals("list")) {
                        System.out.println("list" + socket.getInetAddress());
                        out.println("> " + lista.printInLine());

                    } else if (line.trim().startsWith("create user")) {
                        String s = line.trim();
                        s = s.replace(" ", "#");
                        String aux[] = s.split("#");
                        File f = new File("C:/queue/"+aux[2]);
                        f.mkdir();
                        
                        System.out.println("create user" + socket.getInetAddress());
                        out.println("> created");

                    } else {
                        System.out.println("Command not found... " + line + " " + socket.getInetAddress());
                        out.println("> Command not found...");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
