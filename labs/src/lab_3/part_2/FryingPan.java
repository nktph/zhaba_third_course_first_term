package lab_3.part_2;

public class FryingPan implements IDishes, Object
{
    private final float _handleLength;
    private final String _coverage;

    public float get_handleLength() { return _handleLength; }
    public String get_coverage() { return _coverage; }

    FryingPan() { _handleLength = 22.8f; _coverage = "Антипригарное"; }
    FryingPan(float handleLength, String coverage) { _handleLength = handleLength; _coverage = coverage; }
    FryingPan(float handleLength) { _handleLength = handleLength; _coverage = "Антипригарное"; }


    @Override
    public void name() { System.out.printf("Сковорода - %.2f см. - %s\n", _handleLength, _coverage); }

    @Override
    public void print() { System.out.printf("---------- Сковорода ----------\nДлина ручки: %.2f см.\nПокрытие: %s\n------------------------------\n\n", _handleLength, _coverage); }

    @Override
    public String toString() { return "Сковорода: #" + hashCode(); } // связать с хэшкодом

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof FryingPan)
        {
            FryingPan tmp = (FryingPan) obj;
            return  _handleLength == tmp._handleLength && _coverage.equals(tmp._coverage);
        }
        else return false;
    }

    @Override
    public int hashCode() { return _coverage.length() + 2 * Math.round(_handleLength); }
}
