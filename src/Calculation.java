import java.util.LinkedList;

public class Calculation extends Transaction
{
    //declaring the linked list
    protected static LinkedList<Transaction> transactions;

    //constructor to pass in the linked list
    public Calculation(LinkedList<Transaction> t)
    {
        Calculation.transactions = t;
    }

    //method that will be used to output to a csv file
    public static String csvMaker()
    {
        //declare a variable
        StringBuilder csv = new StringBuilder();
        float runningTotal = 0f;
        //looping through the transaction nodes and checking for which instance type the nodes are
        for (Transaction transaction:transactions)
        {
            //if the node is an income then it will do the listed operation below by denoting it as an income type, the description the user put in to the variable, and the amount for that variable
            if(transaction instanceof Income)
            {
                csv.append("Income");
                csv.append(",");
                csv.append(transaction.getDescription());
                csv.append(",");
                csv.append(transaction.getTotalValue());
                csv.append("\n");
                //collecting the running total of the fields
                runningTotal += transaction.getTotalValue();
            }
            // does the same thing but for the expense
            else if(transaction instanceof Expense)
            {
                csv.append("Expense");
                csv.append(",");
                csv.append(transaction.getDescription());
                csv.append(",");
                csv.append(transaction.getTotalValue());
                csv.append("\n");
                runningTotal -= transaction.getTotalValue();
            }
        }
        //this will show the total revenue
        csv.append(",");
        csv.append("Total Revenue");
        csv.append(",");
        csv.append(runningTotal);
        csv.append("\n");
        return csv.toString();
    }
    //method to calculate the total income
    public void CalculateIncome()
    {
    }
    //method to calculate the total expense
    public void CalculateExpense()
    {

    }
}