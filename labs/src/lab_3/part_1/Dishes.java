package lab_3.part_1;

public class Dishes
{
    private String _manufacturer;
    private float _price;
    private String _style;

    public String get_manufacturer() { return _manufacturer; }
    public float get_price() { return _price; }
    public String get_style() { return _style; }

    Dishes() { _manufacturer = "ООО Кайф"; _price = 1337.0f; _style = "Расслабонистый"; }
    Dishes(String manufacturer, float price, String style) { _manufacturer = manufacturer; _price = price; _style = style; }
    Dishes(String manufacturer, float price) { _manufacturer = manufacturer; _price = price; _style = "Расслабонистый"; }

    public void name() { System.out.printf("---------- НЛО ----------\nПроизводитель: %s\nЦена: %.2f руб.\nСтиль: %s\n------------------------------\n\n",
            get_manufacturer(), get_price(), get_style()); };
}
