package lab_2.part_2;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        boolean inUse = true;
        StandardCart cart = new StandardCart();

        System.out.println("Добро пожаловать в \"Ковбой Пивкос Сигаретов\"!");

        while (inUse) inUse = cart.CartMenu();
    }

    public static int inputInt(boolean condition, int left, int right, String description)
    {
        int value;
        Scanner scanner = new Scanner(System.in);

        if (condition)
        {
            while(true)
            {
                System.out.print(description);
                if (scanner.hasNextInt())
                {
                    value = scanner.nextInt();
                    if (value >= left && value <= right) return value;
                    else System.out.println("\nПожалуйста, проверьте корректность ввода.");
                }
                else System.out.println("\nПожалуйста, проверьте корректность ввода.");
            }
        }
        else
        {
            while(true)
            {
                System.out.print(description);
                if (scanner.hasNextInt()) return scanner.nextInt();
                else System.out.println("\nПожалуйста, проверьте корректность ввода.");
            }
        }
    }
}
