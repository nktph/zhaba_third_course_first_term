package lab_3.part_2;

public class SaladBowl extends Plate
{
    private final String _saladType;

    public String get_saladType() { return _saladType; }

    public SaladBowl() { super(); _saladType = "Овощной"; }
    public SaladBowl(String manufacturer, float price, String style, float diameter, String saladType) { super(manufacturer, price, style, diameter); _saladType = saladType; }
    public SaladBowl(String saladType) { super(); _saladType = saladType; }

    @Override
    public void name() { System.out.printf("Салатница - %s - %.2f руб. - %s\n", get_manufacturer(), get_price(), get_style()); }

    @Override
    public void print()
    {
        System.out.printf("---------- Салатница ----------\nПроизводитель: %s\nЦена: %.2f\nСтиль: %s\nТип салата: %s\n------------------------------\n\n",
                get_manufacturer(), get_price(), get_style(), _saladType);
    }

    @Override
    public String toString() { return "Салатница: #" + hashCode(); } // связать с хэшкодом

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof SaladBowl) return super.equals(obj) && _saladType.equals(((SaladBowl) obj)._saladType);
        else return false;
    }

    @Override
    public int hashCode() { return super.hashCode() + _saladType.length(); }
}
