package lab_3.part_1;

public class Pot extends Dishes
{
    private float _volume;
    private boolean _hasHandle;

    public float get_volume() { return _volume; }
    public boolean get_hasHandle() { return _hasHandle; }

    Pot() { super(); _volume = 2.28f; _hasHandle = true; }
    Pot(String manufacturer, float price, String style, float volume, boolean hasHandle) { super(manufacturer, price, style); _volume = volume; _hasHandle = hasHandle; }
    Pot(String manufacturer, float price, String style) { super(manufacturer, price, style); _volume = 2.28f; _hasHandle = true; }

    @Override
    public void name()
    {
        System.out.printf("---------- Кастрюля ----------\nПроизводитель: %s\nЦена: %.2f руб.\nСтиль: %s\nОбъём: %.2f л.\nНаличие ручки: %b\n------------------------------\n\n",
                get_manufacturer(), get_price(), get_style(), _volume, _hasHandle);
    }
}
