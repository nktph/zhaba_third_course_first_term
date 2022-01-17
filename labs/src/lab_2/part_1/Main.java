package lab_2.part_1;

public class Main
{
    public static void main(String[] argv)
    {
        Book[] array = new Book[3];
        array[0] = new Book();
        array[1] = new Book("Беседы с батюшкой", 150.43f, false);
        array[2] = new Book(array[1]);

        Book.DisplayBookArray(array);
    }
}
