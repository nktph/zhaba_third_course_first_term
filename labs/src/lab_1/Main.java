package lab_1;

public class Main
{
    public static void main(String[] argv)
    {
        Book book_1 = new Book();
        Book book_2 = new Book("Пророк Санбой. Жить в сердцах", 200.7f, true);

        WriterInfo.DisplayBook(book_1); WriterInfo.DisplayBook(book_2);
    }
}
