package lab_5.part_2;

import java.io.*;
import java.net.Socket;

public class ClientThreadHandler extends Thread
{
    private final Socket _client;
    ObjectInputStream sois = null;
    ObjectOutputStream soos = null;

    ClientThreadHandler(Socket client) { _client = client; }

    @Override
    public void run()
    {
        try
        {
            ++Server.userCounter;
            sois = new ObjectInputStream(_client.getInputStream());
            soos = new ObjectOutputStream(_client.getOutputStream());
            System.out.println("Соединение установлено. Количество пользователей: " + Server.userCounter + ". Ожидание данных.");
            String clientMessageRecieved = (String)sois.readObject();

            while(!clientMessageRecieved.equals(" "))
            {
                System.out.println("Сообщение получено: '" + clientMessageRecieved + "'");

                updateFile(clientMessageRecieved);

                clientMessageRecieved = stringAnalysis(clientMessageRecieved);

                soos.writeObject(clientMessageRecieved);
                clientMessageRecieved = (String)sois.readObject();
            }
        }
        catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
        finally
        {
            try
            {
                --Server.userCounter;
                System.out.println("Пользователь отключился. Количество пользователей: " + Server.userCounter + '.');
                sois.close();
                soos.close();
                _client.close();
            }
            catch(Exception e) { e.printStackTrace(); }
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

    private static synchronized void updateFile(String value)
    {
        File file = new File("F:\\zhaba\\zhaba-master\\info_5.txt");
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write((value + '\n').getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e) { System.out.println("\nTCP-сервер: Файл не найден. Запись невозможна."); }
        catch (IOException e) { System.out.println("\nTCP-сервер: Системная ошибка записи в файл."); }
        //Thread.currentThread().notifyAll();
    }
}
