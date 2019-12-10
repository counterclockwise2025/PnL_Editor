import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

class Main
{
    //main function
    public static void main (String[] args) throws IOException
    {
        System.out.println("Welcome to the Profit & Loss Statement Editor \n");
        Scanner input = new Scanner(System.in);
        //declaring a new linked list object
        LinkedList<Transaction> transactions = new LinkedList<>();
        //declaring a new object for the Calculation class
        new Calculation(transactions);
        //calling the menu method with the specified parameters
        menuPnL(input, transactions);
        System.out.println("Thank you for using the P&L Editor, have a great day :) ");
        //writing out to the the file but the method is called from the Calculations class to do the rest of the work
        FileWriter csvWrite = new FileWriter("example.csv");
        csvWrite.append(Calculation.csvMaker());
        csvWrite.close();
    }
    //defining the main menu with parameters passed in
    public static void menuPnL(Scanner input, LinkedList<Transaction> transactions) {
        boolean running = true;
        loadPnL(transactions);

        while (running)				//being while
        {
            //first set of options that the user can pick from
            System.out.println("Please select an option below (1-3)...\n");
            System.out.println("1. Create/add new Profit & Loss file.");
            System.out.println("2. Show previous report.");
            System.out.println("3. Exit.");
            String option = input.next();
            //cascades1000 down to what the user picks, if 1 then it will do this take them to another menu
            switch (option) {
                case "1":
                    //next set of options that the user is asked
                    System.out.println("Select an option below (1-2)...\n");
                    System.out.println("1. Work on the INCOME table.");
                    System.out.println("2. Work on the EXPENSE table.");
                    String option2 = input.next();
                    if (option2.equals("1")) {
                        //if they chose to work on the income table then it will ask fro a description and store that in a variable and ask them for the float value after
                        System.out.println("What is the description for this income field? \n");
                        String description = input.next();
                        description += input.nextLine();
                        System.out.println("What is the amount of income for this field? \n");
                        float amount = input.nextFloat();
                        transactions.add(new Income(description, amount));
                        //calculates the total income
                        //calculator.CalculateIncome();
                    } else if (option2.equals("2")) {
                        //if they choose this option it will do the same as the income field but for the expense
                        System.out.println("What is the description of this expense field? \n");
                        String description = input.next();
                        description += input.nextLine();
                        System.out.println("What is the expense or loss for this field? \n");
                        float amount = input.nextFloat();
                        transactions.add(new Expense(description, amount));
                        //calculate the total expense
                        //calculator.CalculateExpense();
                    }
                    //if the user inputs something invalid it will end the loop and tell them to start from another option
                    else {
                        System.out.println("That is not an option.");
                    }
                    break;

                //loads the file in
                case "2":
                    System.out.println(Calculation.csvMaker());
                    break;
                //ends the loop, saves the progress to a csv file
                case "3":
                    running = false;
                    break;
                //catch an wrong input form the users end
                default:
                    System.out.println("That was not an option, please try again.");
                    break;
            }
        }//end while
    }
    //defining the read in method
    public static void loadPnL(LinkedList<Transaction> trans)
    {
        try
        {
            //declare variables and array
            File transaction = new File("example.csv");
            String line;
            String[] transactionArray;
            String description;
            String type;
            String totalValue;
            Scanner reader = new Scanner(transaction);
            //loops through the file line by line till the end
            while(reader.hasNextLine())
            {
                //stores each object into the variable
                line = reader.nextLine();
                //splits the objects by a "," and places each type into the array
                transactionArray = line.split(",");
                type = transactionArray[0];
                description = transactionArray[1];
                totalValue = transactionArray[2];
                //coverlets the string to a float
                float dollarAmount = Float.parseFloat(totalValue);
                //if an income type then it will use the income constructor to display it as income, description, value
                if (type.equals("Income"))
                {
                    trans.add(new Income(description, dollarAmount));
                }
                //else expense type then it will display with expense, description, value
                else if (type.equals("Expense"))
                {
                    trans.add(new Expense(description, dollarAmount));
                }
            }
            reader.close();
        }
        //checks to see if the file is present if not then it will make the user start a new file
        catch(FileNotFoundException e)
        {
            System.out.println("Please start by creating a new file since example.csv. \n");
        }
    }
}