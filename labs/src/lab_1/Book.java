package lab_1;

public class Book
{
    private String _name;
    private float _price;
    private boolean _isIllustrated;

    public Book() { _name = "Рассказы бывалых о рыбалке"; _price = 15.5f; _isIllustrated = true; }
    public Book(String name, float price, boolean is_pictured) { _name = name; _price = price; _isIllustrated = is_pictured; }

    public void displayName() { System.out.println("Название книги: " + _name); }
    public void displayPrice() { System.out.println("Цена: " + _price + " рублей"); }
    public void displayIsIllustrated() { System.out.println("Наличие иллюстраций: " + _isIllustrated); }
}
