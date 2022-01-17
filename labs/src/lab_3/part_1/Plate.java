package lab_3.part_1;

public class Plate extends Dishes
{
    private float _diameter;

    public float get_Diameter() { return _diameter; }

    Plate() { super(); _diameter = 14.88f; }
    Plate(String manufacturer, float price, String style, float diameter) { super(manufacturer, price, style); _diameter = diameter; }
    Plate(String manufacturer, float price, String style) { super(manufacturer, price, style); _diameter = 14.88f; }

    @Override
    public void name()
    {
        System.out.printf("---------- Тарелка ----------\nПроизводитель: %s\nЦена: %.2f руб.\nСтиль: %s\nДиаметр: %.2f см.\n------------------------------\n\n",
                get_manufacturer(), get_price(), get_style(), _diameter);
    }
}
