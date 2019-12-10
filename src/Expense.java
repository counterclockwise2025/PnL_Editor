public class Expense extends Transaction
{
    //main constructor inheriting from the Transaction class
    public Expense(String description, float totalValue)
    {
        super(description, totalValue);
    }
}