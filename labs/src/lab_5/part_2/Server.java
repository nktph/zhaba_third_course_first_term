package lab_5.part_2;
import java.net.*;

public class Server
{
    static int userCounter = 0;

    public static void main(String[] argv)
    {
        try
        {
            System.out.println("Сервер запускается...");
            ServerSocket serverSocket = new ServerSocket(2525);

            System.out.println("Сервер запущен. Ожидание соединения.");
            while (!serverSocket.isClosed())
            {
                Socket clientAccepted = serverSocket.accept();
                ClientThreadHandler client = new ClientThreadHandler(clientAccepted);
                client.start();
            }
        }
        catch(Exception ignored) { }
    }
}