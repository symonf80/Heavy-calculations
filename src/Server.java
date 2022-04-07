import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(23444);
                 Socket socket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String line;
                int number;
                int numberZero = 0;
                int numberOne = 1;
                while ((line = in.readLine()) != null) {
                    if (line.equals("end")) {
                        break;
                    } else number = Integer.parseInt(line);
                    if (number == numberZero) {
                        out.println(number + "-е число ряда равно 0 (Нумерация с 0)");
                    } else if (number == numberOne) {
                        out.println(number + "-е число ряда равно 1 (Нумерация с 0)");
                    } else {
                        int a = 0;
                        int b = 1;
                        for (int i = 2; i <= number; i++) {
                            int next = a + b;
                            a = b;
                            b = next;
                        }
                        out.println(number + "-е число ряда равно " + b + " (Нумерация с 0)");
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
}
