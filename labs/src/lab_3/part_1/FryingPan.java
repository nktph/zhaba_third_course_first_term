package lab_3.part_1;

public class FryingPan extends Dishes
{
    private float _handleLength;
    private String _coverage;

    public float get_handleLength() { return _handleLength; }
    public String get_coverage() { return _coverage; }

    FryingPan() { super(); _handleLength = 22.8f; _coverage = "Антипригарное"; }
    FryingPan(String manufacturer, float price, String style, float handleLength, String coverage) { super(manufacturer, price, style); _handleLength = handleLength; _coverage = coverage; }
    FryingPan(String manufacturer, float price, String style) { super(manufacturer, price, style); _handleLength = 22.8f; _coverage = "Антипригарное"; }

    @Override
    public void name()
    {
        System.out.printf("---------- Сковорода ----------\nПроизводитель: %s\nЦена: %.2f руб.\nСтиль: %s\nДлина ручки: %.2f см.\nПокрытие: %s\n------------------------------\n\n",
                get_manufacturer(), get_price(), get_style(), _handleLength, _coverage);
    }
}
