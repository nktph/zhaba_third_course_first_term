package lab_5.part_2;
import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Соединение с сервером....");
            Socket clientSocket = new Socket("127.0.0.1",2525);
            System.out.println("Соединение установено.");

            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            ObjectOutputStream coos = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream cois = new ObjectInputStream(clientSocket.getInputStream());

            System.out.println("Введите строку для отправки серверу. Введите символ пробела для выхода из программы:\n");
            String clientMessage = stdin.readLine();
            System.out.println("Вы ввели: '" + clientMessage + "'");

            while(!clientMessage.equals(" "))
            {
                coos.writeObject(clientMessage);
                System.out.println("Ответ сервера: " + cois.readObject());
                System.out.println("---------------------------");
                clientMessage = stdin.readLine();
                System.out.println("Вы ввели: '" + clientMessage + "'");
            }
            coos.writeObject(clientMessage);
            coos.close(); //закрытие потока вывода
            cois.close(); //закрытие потока ввода
            clientSocket.close(); //закрытие сокета
        }
        catch(Exception e) { e.printStackTrace(); } //выполнение метода исключения е
    }
}