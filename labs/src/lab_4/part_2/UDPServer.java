package lab_4.part_2;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

// по полученным от клиента исходным параметрам рассчитать значение функции, передать клиенту расчетное
// значение функции, а также сохранить исходные параметры и значение функции в файл

public class UDPServer
{
    public final static int DEFAULT_PORT = 8001;
    public final String EXIT_CMD = "QUIT";

    public static void main(String[] args)
    {
        try
        {
            UDPServer udpSvr = new UDPServer();
            udpSvr.runServer();
        }
        catch(IOException ex) { ex.printStackTrace(); }
    }

    public void runServer() throws IOException
    {
        DatagramSocket socket = null;
        try
        {
            boolean stopFlag = false;
            socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("UDP-сервер: Запущен на " + socket.getLocalAddress() + ":" + socket.getLocalPort());

            while(!stopFlag)
            {
                byte[] buf = new byte[24];

                System.out.println("\nUDP-сервер: Ожидание данных...");
                DatagramPacket recvPacket = new DatagramPacket(buf, buf.length);
                socket.receive(recvPacket);

                double[] params = bytesToDouble(recvPacket.getData());

                System.out.printf("\nUDP-сервер: Получены данные:\nПараметр x: %f\nПараметр y: %f\nПараметр z: %f\n", params[0], params[1], params[2]);
                double result = calculateFunction(params[0], params[1], params[2]);
                System.out.println("UDP-сервер: Результат: " + result);

                System.out.println("UDP-сервер: Отправка результата...");
                buf = doubleToBytes(result);
                DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, recvPacket.getAddress(), recvPacket.getPort());
                socket.send(sendPacket);

                System.out.println("UDP-сервер: Запись данных...");
                updateFile(params, result);
            }
        }
        finally { if (socket != null) { System.out.println("UDP-сервер: Завершение..."); socket.close(); } }
    }

    private static double calculateFunction(double x, double y, double z)
    {
        return Math.abs(Math.pow(x, y/x) - Math.pow(y/x, 1/2d)) + (y - x) * (Math.cos(y) - Math.exp(z / (y - x))) / (1 + Math.pow(y - x, 2));
    }

    private static double[] bytesToDouble(byte[] buf)
    {
        double[] params = new double[] {0d, 0d, 0d};
        byte[] value = new byte[8];
        for (int i = 0; i < 3; ++i)
        {
            System.arraycopy(buf, i * 8, value, 0, 8);
            params[i] = ByteBuffer.wrap(value).getDouble();
        }
        return params;
    }

    private static byte[] doubleToBytes(double value)
    {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    private static void updateFile(double[] params, double result)
    {
        File file = new File("F:\\zhaba\\zhaba-master\\info_4.txt");
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(("\n\nx = " + params[0] + "\ny = " + params[1] + "\nz = " + params[2] + "\nresult = ").getBytes());
            fileOutputStream.write(Double.toString(result).getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e) { System.out.println("\nUDP-сервер: Файл не найден. Запись невозможна."); }
        catch (IOException e) { System.out.println("\nUDP-сервер: Системная ошибка записи в файл."); }
    }
}