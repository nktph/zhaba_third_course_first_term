package lab_3.part_2;

public abstract class Plate implements IDishes, Object
{
    private final String _manufacturer;
    private final float _price;
    private final String _style;
    private final float _diameter;

    public String get_manufacturer() { return _manufacturer; }
    public float get_price() { return _price; }
    public String get_style() { return _style; }
    public float get_Diameter() { return _diameter; }

    public Plate() { _manufacturer = "ООО Хорошо пошло"; _price = 2.28f; _style = "Ламинатный"; _diameter = 14.88f; }
    public Plate(String manufacturer, float price, String style, float diameter) { _manufacturer = manufacturer; _price = price; _style = style; _diameter = diameter; }
    public Plate(String manufacturer, float price, String style) { _manufacturer = manufacturer; _price = price; _style = style;  _diameter = 14.88f; }

    @Override
    public String toString() { return "Тарелка: #" + hashCode(); } // связать с хэшкодом

    @Override
    public boolean equals(java.lang.Object obj) // проверить вообще работает или нет
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Plate)
        {
            Plate tmp = (Plate) obj;
            return _manufacturer.equals(tmp._manufacturer) && _price == tmp._price && _style.equals(tmp._style) && _diameter == tmp._diameter;
        }
        else return false;
    }

    @Override
    public int hashCode() { return Math.round(_manufacturer.length() + 2 * _price + 3 * _style.length() + 4 * _diameter); }
}
