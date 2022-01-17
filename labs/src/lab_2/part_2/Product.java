package lab_2.part_2;

public abstract class Product
{
    protected String _name;
    protected float _price;

    Product(String name, float price) { _name = name; _price = price; }
    Product(Product product) { _name = product._name; _price = product._price; }
}
