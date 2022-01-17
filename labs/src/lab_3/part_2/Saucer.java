package lab_3.part_2;

public class Saucer extends Plate
{
    private final String _color;

    public String get_color() { return _color; }

    public Saucer() { super(); _color = "Белый"; }
    public Saucer(String manufacturer, float price, String style, float diameter, String color) { super(manufacturer, price, style); _color = color; }
    public Saucer(String manufacturer, float price, String style) { super(manufacturer, price, style); _color = "Дотерский"; }

    @Override
    public void name() { System.out.printf("Тарелочка - %s - %.2f руб. - %s\n", get_manufacturer(), get_price(), get_style()); }

    @Override
    public void print()
    {
        System.out.printf("---------- Салатница ----------\nПроизводитель: %s\nЦена: %.2f\nСтиль: %s\nЦвет: %s\n------------------------------\n\n",
                get_manufacturer(), get_price(), get_style(), _color);
    }

    @Override
    public String toString() { return "Тарелочка: #" + hashCode(); } // связать с хэшкодом

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Saucer) return super.equals(obj) && _color.equals(((Saucer) obj)._color);
        else return false;
    }

    @Override
    public int hashCode() { return super.hashCode() + _color.length(); }
}
