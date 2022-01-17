package lab_3.part_2;

public class Pot implements IDishes, Object
{
    private final boolean _hasHandle;
    private final float _volume;

    public float get_volume() { return _volume; }
    public boolean get_hadHandle() { return _hasHandle; }

    public Pot() { _hasHandle = true; _volume = 1.488f; }
    public Pot(boolean hasHandle, float volume) { _hasHandle = hasHandle; _volume = volume; }
    public Pot(float volume) { _volume = volume; _hasHandle = true; }

    @Override
    public void name() { System.out.printf("Кастрюля - %b - %.2f л.\n", _hasHandle, _volume); }

    @Override
    public void print() { System.out.printf("---------- Кастрюля ----------\nОбъём: %.2f л.\nНаличие ручки: %b\n------------------------------\n\n", _volume, _hasHandle); }

    @Override
    public String toString() { return "Кастрюля: #" + hashCode(); } // связать с хэшкодом

    @Override
    public boolean equals(java.lang.Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Pot)
        {
            Pot tmp = (Pot) obj;
            return  _hasHandle == tmp._hasHandle && _volume == tmp._volume;
        }
        else return false;
    }

    @Override
    public int hashCode() { return (_hasHandle ? 1 : 0) + Math.round(_volume); }
}
