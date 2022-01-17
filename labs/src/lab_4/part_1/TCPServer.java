package lab_4.part_1;
import java.io.*;
import java.net.*;


public class TCPServer
{
    public static void main(String[] argv)
    {
        ServerSocket serverSocket = null;
        Socket clientAccepted = null;
        ObjectInputStream sois = null;
        ObjectOutputStream soos = null;

        try
        {
            System.out.println("Сервер запускается...");
            serverSocket = new ServerSocket(2525);

            System.out.println("Сервер запущен. Ожидание соединения.");
            clientAccepted = serverSocket.accept();
            sois = new ObjectInputStream(clientAccepted.getInputStream());
            soos = new ObjectOutputStream(clientAccepted.getOutputStream());

            System.out.println("Соединение установлено. Ожидание данных.");
            String clientMessageRecieved = (String)sois.readObject();

            while(!clientMessageRecieved.equals(" "))
            {
                System.out.println("Сообщение получено: '" + clientMessageRecieved + "'");


                clientMessageRecieved = TCPServer.stringAnalysis(clientMessageRecieved); //приведение символов строки к верхнему регистру


                soos.writeObject(clientMessageRecieved); //потоку вывода присваивается значение строковой переменной (передается клиенту)
                clientMessageRecieved = (String)sois.readObject(); //строке присваиваются данные потока ввода, представленные в виде строки (передано клиентом)
            }
        }
        catch(Exception ignored) { }
        finally
        {
            try
            {
                sois.close(); //закрытие потока ввода
                soos.close(); //закрытие потока вывода
                clientAccepted.close(); //закрытие сокета, выделенного для клиента
                serverSocket.close(); //закрытие сокета сервера
            }
            catch(Exception e) { e.printStackTrace(); } //вызывается метод исключения е
        }
    }

    private static String stringAnalysis(String clientMessageReceived)
    {
        return "\nГласных: " + countVowels(clientMessageReceived) + "\nСогласных: " + countConsonants(clientMessageReceived) + "\n";
    }

    private static int countVowels(String clientMessageReceived)
    {
        int counter = 0;
        char[] vowels = new char[] {'у', 'е', 'ы', 'а', 'о', 'э', 'я', 'и', 'ю'};
        for (char vowel: vowels)
            for (int i = 0; i < clientMessageReceived.length(); ++i)
                if (clientMessageReceived.charAt(i) == vowel)
                    ++counter;
        return counter;
    }

    private static int countConsonants(String clientMessageReceived)
    {
        int counter = 0;
        char[] consonants = new char[] {'й', 'ц', 'к', 'н', 'г', 'ш', 'щ', 'з', 'х', 'ъ', 'ф', 'в', 'п', 'р', 'л', 'д', 'ж', 'ч', 'с', 'м', 'т', 'ь', 'б'};
        for (char consonant: consonants)
            for (int i = 0; i < clientMessageReceived.length(); ++i)
                if (clientMessageReceived.charAt(i) == consonant)
                    ++counter;
        return counter;
    }
}