package lab_2.part_1;

public class Book
{
    private String _name;
    private float _price;
    private boolean _isIllustrated;

    public String getName() { return _name; }
    public float getPrice() { return _price; }
    public boolean getIsIllustrated() { return _isIllustrated; }

    public Book() { _name = "Сборник церковных песнопений"; _price = 145.5f; _isIllustrated = true; }
    public Book(String name, float price, boolean is_pictured) { _name = name; _price = price; _isIllustrated = is_pictured; }
    public Book(Book object) { _name = object._name; _price = object._price; _isIllustrated = object._isIllustrated; }

    public void DisplayBook()
    {
        System.out.println("\n-----------------------------------");
        System.out.println("Название книги: " + _name);
        System.out.println("Цена: " + _price + " рублей");
        System.out.println("Наличие иллюстраций: " + _isIllustrated);
        System.out.println("-----------------------------------\n");
    }

    private static void getArrayInfo(Book[] array)
    {
        float summaryPrice = 0f; int illustratedCount = 0;
        for (Book book: array) { summaryPrice += book.getPrice(); if (book.getIsIllustrated()) ++illustratedCount; }
        System.out.println("\nОбщая стоимость: " + summaryPrice + "\nКоличество проиллюстрированных книг: " + illustratedCount + '\n');
    }

    public static void DisplayBookArray(Book[] array)
    {
        if (array.length == 0) System.out.println("\nкупите книжке... \n");
        else
            {
                for (Book book: array) book.DisplayBook();
                getArrayInfo(array);
            }
    }
}
