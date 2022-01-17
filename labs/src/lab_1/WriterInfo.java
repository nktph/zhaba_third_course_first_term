package lab_1;

public class WriterInfo
{
    public static void DisplayBook(Book book)
    {
        System.out.println("\n-----------------------------------");
        book.displayName(); book.displayPrice(); book.displayIsIllustrated();
        System.out.println("-----------------------------------\n");
    }
}
