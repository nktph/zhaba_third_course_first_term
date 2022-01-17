package lab_2.part_2;

import java.util.Random;

public class Cigarettes extends Product
{
    private final String _type;

    Cigarettes() { super("Сижки", 2.28f); _type = "\"Чики-Бамбони\""; }
    Cigarettes(float price, String type) { super("Сижки", price); _type = type; }
    Cigarettes(Cigarettes cigarettes) { super("Сижки", cigarettes._price); _type = cigarettes._type; }

    public static Product ChooseProduct()
    {
        while (true)
        {
            switch(Main.inputInt(true, 0, 3, "\nВыбирайте сигареты:\n1. Richmond Cherry - 5.02 руб.\n2. Parliament Platinum Blue - 4.65 руб.\n3. Sobranie Black - 4.03 руб.\n0. Вернуться назад\n"))
            {
                case 1: return new Cigarettes(5.02f, "Richmond Cherry");
                case 2: return new Cigarettes(4.65f, "Parliament Platinum Blue");
                case 3: return new Cigarettes(4.03f, "Sobranie Black");
                case 0: return null;
            }
        }
    }

    @Override
    public String toString() { return String.format("%s %s — %.2f руб.", _name, _type, _price); }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Cigarettes)
        {
            Cigarettes tmp = (Cigarettes) obj;
            return this._type == tmp._type && this._name == tmp._name && this._price == tmp._price;
        }
        else return false;
    }

    public int hashCode()
    {
        return new Random().nextInt(5000);
    }
}
