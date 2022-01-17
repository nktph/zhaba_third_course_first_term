package lab_2.part_2;

import java.util.Random;

public class Beer extends Product
{
    private final String _type;
    private final float _size;

    Beer() { super("Пиво", 5.9f); _type = "Медовуха"; _size = 0.5f; }
    Beer(float price, String type, float size) { super("Пиво", price); _type = type; _size = size; }
    Beer(Beer beer) { super("Пиво", beer._price); _type = beer._type; _size = beer._size; }

    public static Product ChooseProduct()
    {
        while (true)
        {
            switch(Main.inputInt(true, 0, 3, "\nВыбирайте пиво:\n1. Медовуха (0.33 л.) - 4.5 руб.\n2. Медовуха (0.5 л.) - 6 руб.\n3. Медовуха (1 л.) - 10 руб.\n0. Вернуться назад\n"))
            {
                case 1: return new Beer(3.36f, "Медовуха", 0.33f);
                case 2: return new Beer(5.5f, "Медовуха",0.5f);
                case 3: return new Beer(9.8f, "Медовуха", 1f);
                case 0: return null;
            }
        }
    }

    @Override
    public String toString() { return String.format("%s %s (%.2f л.) — %.2f руб.", _name, _type, _size, _price); }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Beer)
        {
            Beer tmp = (Beer) obj;
            return this._type == tmp._type && this._name == tmp._name && this._price == tmp._price && this._size == tmp._size;
        }
        else return false;
    }

    @Override
    public int hashCode()
    {
        return new Random().nextInt(5000) + 5000;
    }
}
