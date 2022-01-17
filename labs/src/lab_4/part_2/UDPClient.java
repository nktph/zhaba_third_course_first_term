package lab_4.part_2;
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;

public class UDPClient
{
    enum InputCode { EXIT, SUCCESS}
    private static InputCode _status;

    public void runClient() throws IOException
    {
        byte[] exit = { 'E', 'X', 'I', 'T' };
        DatagramSocket socket = new DatagramSocket();

        DatagramPacket sendPacket = new DatagramPacket(exit, exit.length, InetAddress.getByName("127.0.0.1"), 8001);
        DatagramPacket recvPacket;
        List<Double> params = new ArrayList<Double>(Arrays.asList(0d, 0d, 0d));

        byte[] buf;
        System.out.println("UDP-клиент: Запущен.");
        try
        {
            while(inputParams(params))
            {
                buf = paramsToByte(params);
                sendPacket.setData(buf);
                sendPacket.setLength(buf.length);
                socket.send(sendPacket);

                recvPacket = new DatagramPacket(buf, buf.length);
                socket.receive(recvPacket);

                double result = bytesToDouble(recvPacket.getData());
                System.out.println("\nРезультат: " + result);
            }
        }
        finally { socket.close(); }

        /*
        sendPacket.setData(exit);
        sendPacket.setLength(exit.length);
        socket.send(sendPacket);
        System.out.println("UDP-клиент: Завершение...");
        */
    }

    public static void main(String[] args)
    {
        try
        {
            UDPClient client = new UDPClient();
            client.runClient();
        }
        catch(IOException ex) { ex.printStackTrace(); }
    }

    private static boolean inputParams(List<Double> params)
    {
        String tmp;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значения параметров (exit для выхода):");

        tmp = inputHandler(scanner, "\nПараметр x: ");
        if (_status == InputCode.EXIT) return false;
        else params.set(0, Double.parseDouble(tmp));

        tmp = inputHandler(scanner, "\nПараметр y: ");
        if (_status == InputCode.EXIT) return false;
        else params.set(1, Double.parseDouble(tmp));

        tmp = inputHandler(scanner, "\nПараметр z: ");
        if (_status == InputCode.EXIT) return false;
        else params.set(2, Double.parseDouble(tmp));

        return true;
    }

    private static String inputHandler(Scanner scanner, String description)
    {
        String tmp;
        while (true)
        {
            System.out.print(description);
            tmp = scanner.nextLine();
            if (tmp.equals("exit")) { _status = InputCode.EXIT; return "InputCode.EXIT";}
            try { Double.parseDouble(tmp); }
            catch (NumberFormatException e) { System.out.println("\nПожалуйста, проверьте корректность ввода."); continue; }
            _status = InputCode.SUCCESS;
            return tmp;
        }
    }

    private static byte[] paramsToByte(List<Double> params)
    {
        byte[] buf = new byte[24];
        for (int i = 0; i < 3; ++i)
        {
            byte[] value = new byte[8];
            ByteBuffer.wrap(value).putDouble(params.get(i));
            System.arraycopy(value, 0, buf, i * 8, 8);
        }
        return buf;
    }
    private static double bytesToDouble(byte[] buf) { return ByteBuffer.wrap(buf).getDouble(); }
}