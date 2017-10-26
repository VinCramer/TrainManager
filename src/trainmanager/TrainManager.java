/**
 *
 * @author Vincent Cramer
 * 
 */
package trainmanager;

import java.util.Scanner;

/*
TrainManager class contains the main method and menu function, which allow
user to execute program simulating a train with a linked list
*/
public class TrainManager {
    
        /**
	 * Main method, asks for user input and responds accordingly
	 * 
         * 
         * 
	 **/
	public static void main (String[] args){
            boolean isRunning=true;
            TrainLinkedList trainLinkedList = new TrainLinkedList();
            Scanner input = new Scanner(System.in);
            String command, loadName, entry;
            double length, weight, value;
            boolean isDangerous;
            printCommands();
		
            while(isRunning){
		command = input.next();
                        
                if(command.equalsIgnoreCase("Q")){
                    System.out.println("Closing program...Goodbye.");
                    break;
		}		
                
		else if(command.equalsIgnoreCase("F")){
                    trainLinkedList.forwardCursor();
		}
                
		else if(command.equalsIgnoreCase("B")){
                    trainLinkedList.backwardCursor();
		}
                
                else if(command.equalsIgnoreCase("I")){
                    
                    /*
                    There was an error here with the handling of errors - if we
                    put into a try-catch for catching an InputMismatchException,
                    and we entered a string that was another possible command,
                    we'd execute that command after try-catch. To fix this, we
                    use a string to store the value, try to parse a number from
                    that string, and catch that exception
                    */
                    try{
                        
                        System.out.println("Enter length:");
                        entry=input.next();
                        length=Double.parseDouble(entry);
                    
                        System.out.println("Enter weight:");
                        entry=input.next();
                        weight=Double.parseDouble(entry);
                        
                        
                        TrainCar newCar = new TrainCar(length, weight);
                        trainLinkedList.insert(newCar);
                        System.out.println("New train car " + length + 
                                " meters, " + weight + " tons inserted.");
                    
                    }
                    
                    catch(NumberFormatException n){
                        System.out.println("Invalid data entry for inserting"
                                + " car");
                    }
                }
                
		else if(command.equalsIgnoreCase("R")){
                    
                    trainLinkedList.remove();
                    System.out.println("Car successfully removed. ");
                    
                    
		}
                
		else if(command.equalsIgnoreCase("L")){
                    if(trainLinkedList.getCursor()==null){
                        System.out.println("No cars to load");
                        
                    }
                    
                    else{
                        try{
                            System.out.println("Enter name of load:");
                            loadName = input.next();
                    
                    
                            System.out.println("Enter weight of load:");
                            entry=input.next();
                            weight=Double.parseDouble(entry);
                    
                    
                            System.out.println("Enter value of load:");
                            entry=input.next();
                            value=Double.parseDouble(entry);
                    
                            System.out.println("Is it dangerous? (True/False)");
                    
                    
                    
                            /*
                            Boolean.parseBoolean(string) does not appear to 
                            throw an exception if the string is not "true" or 
                            "false", something like "e" will default to false.
                            To give better user feedback, we check to see if the
                            user explicitly entered "true" or "false", and if 
                            not we inform them of their mistake.  
                            */
                            
                            entry=input.next();
                            
                            if(entry.equalsIgnoreCase("true")||
                                entry.equalsIgnoreCase("false")){
                                
                                isDangerous=Boolean.parseBoolean(entry);
                                
                                ProductLoad newProduct = new ProductLoad
                                    (loadName, weight, value, isDangerous);
                                
                                trainLinkedList.getCursor().getData()
                                    .setLoad(newProduct);
                                
                                System.out.println(weight + " tons of " + 
                                        loadName + " added to current car.");
                            }
                            
                            
                            else{
                                System.out.println("Invalid data entered "
                                + "for loading train car");
                            }
                    
                            
                    
                        }catch(NumberFormatException b){
                            System.out.println("Invalid data entered "
                                + "for loading train car");
                        }
                    }
                }
                
		else if(command.equalsIgnoreCase("S")){
                    System.out.println("Enter product name:");
                    String productName = input.next();
                    trainLinkedList.findProduct(productName);
		}
		
                else if(command.equalsIgnoreCase("T")){
                    System.out.println(trainLinkedList.toString());
		}
		
                else if(command.equalsIgnoreCase("M")){
                    trainLinkedList.printManifest();
		}
		
                else if(command.equalsIgnoreCase("D")){
                    trainLinkedList.removeDangerousCars();
                    System.out.println("All dangerous cars removed.");
		}
		
                else{
                    System.out.println("Invalid character - please try again.");
                }
                printCommands();
		}
            
            input.close();
	}
	
	/**
	 * Prints out possible commands
	 * 
	 * Postcondition:
	 *  All possible commands have been printed
	 */
	public static void printCommands(){
		System.out.println("Here are the available commands and their "
                    + "corresponding buttons:");
		System.out.println("(F) Moves the cursor forward 1 car");
		System.out.println("(B) Moves the cursor backwards 1 car");
		System.out.println("(I) Inserts car after cursor");
		System.out.println("(R) Removes car at cursor");
		System.out.println("(L) Set load at cursor");
		System.out.println("(S) Search for product");
		System.out.println("(T) Print train");
		System.out.println("(M) Print manifest");
		System.out.println("(D) Remove all dangerous cars");
		System.out.println("(Q) Quits program");
	}
	
}
