package lab_2.part_2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StandardCart implements IStandardCart
{
    private final List<Product> _cart;

    public StandardCart() { _cart = new ArrayList<Product>(); }

    public boolean CartMenu()
    {
        switch(Main.inputInt(true, 0, 4, "\nВведите номер необходимой функции:\n1. Добавить товар\n2. Удалить товар\n3. Просмотреть корзину\n4. Оплатить корзину\n0. Уйти без пива и сижек\n"))
        {
            case 1: AddProductInCart(ChooseProductToAdd()); return true;
            case 2: DeleteProductInCart(); return true;
            case 3: ShowCart(); return true;
            case 4: CheckOut(); return false;
            case 0: System.out.println("\nчел.. не приходи больше...."); return false;
        }
        return true;
    }

    private Product ChooseProductToAdd()
    {
        while (true)
        {
            switch(Main.inputInt(true, 0, 2, "\nВведите номер требуемого товара:\n1. Пиво\n2. Сижки\n0. Я ЗОЖник\n"))
            {
                case 1: return Beer.ChooseProduct();
                case 2: return Cigarettes.ChooseProduct();
                case 0: return null;
            }
        }
    }

    private void AddProductInCart(Product obj)
    {
        if (obj != null)
        {
            _cart.add(obj);
            System.out.println("Товар успешно добавлен в вашу корзину!\n");
        }
    }

    private void DeleteProductInCart()
    {
        if (_cart.isEmpty()) System.out.println("Корзина пуста!\n");
        else
        {
            ShowCart();
            int choice = Main.inputInt(true, 0, _cart.size(), "\nВведите номер товара для удаления (ноль для выхода): ");
            if (choice == 0) return;
            _cart.remove(choice);
            System.out.println("\nУдаление успешно завершено!");
        }
    }

    public void ShowCart()
    {
        if (_cart.isEmpty()) System.out.println("Корзина пуста!\n");
        else
        {
            float totalPrice = 0;
            Product tmp;

            System.out.printf("\n\nВ вашей корзине %d товаров:", _cart.size());
            System.out.println("\n------------- Начало корзины -------------\n");

            Iterator<Product> iterator = _cart.iterator();
            for (int i = 0; i < _cart.size(); ++i) { tmp = iterator.next(); System.out.println(i + 1 + ". " + tmp.toString()); totalPrice += tmp._price; } // почему _price доступно?
            System.out.println("\n------------- Конец корзины -------------");
            System.out.printf("Общая стоимость: %.2f руб.\n", totalPrice);
        }
    }

    private void CheckOut() { System.out.println("\nСпасибо за покупку! Приятного отдыха!"); }
}
