public abstract class Transaction
{
    //declaring variable types
    protected String description;
    protected float totalValue;
    //default constructor
    public Transaction()
    {
        description = "";
        totalValue = 0f;
    }
    //main constructor
    public Transaction(String description, float totalValue)
    {
        this.description = description;
        this.totalValue = totalValue;
    }
    //getter method to get the variable for the description as a string
    public String getDescription()
    {
        return description;
    }
    //getter method to get the variable value returning as a float
    public float getTotalValue()
    {
        return totalValue;
    }
}