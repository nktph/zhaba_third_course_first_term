package lab_3.part_1;

public class Main
{
    public static void main(String[] argv)
    {
        Dishes[] dishes = new Dishes[] { new Pot(), new Plate(), new FryingPan(), new Plate("ОАО Мм", 133.7f, "оаоаммм", 4.0f),  new Plate() };
        WriterInfo.displayDishesArray(dishes);
    }
}
