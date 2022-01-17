package lab_3.part_2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main
{
    public static void main(String[] argv)
    {
        List<Plate> plates = new ArrayList<Plate>();
        plates.add(new SaladBowl());
        plates.add(new Saucer("ООО Зеленоглазое такси", 15.02f, "Боярский", 4.04f, "Зелёный"));

        Pot pot = new Pot(1.488f);
        FryingPan fryingPan = new FryingPan();

        Iterator<Plate> iterator = plates.iterator();
        System.out.print(iterator.next().toString() + "\n\n");

        pot.print();
        fryingPan.name();
    }
}
