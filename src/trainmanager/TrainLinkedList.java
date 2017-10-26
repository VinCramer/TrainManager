/**
 *
 * @author Vincent Cramer
 * 
 */
package trainmanager;

/*
Contains linked list itself and corresponding methods

Note: For this class' purposes, 1 is the base index of the linked list. 
This was done to simplify iterations with the "size" variable
*/
public class TrainLinkedList {

	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;
	private int size;
        
        
	
	/**
	 * Creates an instance of TrainLinkedList with no trains
	 * 
	 * Postcondition:
	 *  TrainLinkedList has been created, and holds no trains
	 */
	public TrainLinkedList(){
            head=null;
            tail=null;
            cursor=head;
            size=0;
	}
	
	/**
	 * Inserts a new train car after cursor
	 * 
	 * @param newCar
	 *  The new train car to be inserted
	 *  
	 * Precondition:
	 *  Train linked list has been created
	 * 
	 * Postcondition:
	 *  New train car exists, cursor is on new car, and order is the same
	 *  
	 * @throws IllegalArgumentException 
	 *  Indicates newCar is null
	 */
	public void insert(TrainCar newCar) throws IllegalArgumentException{
            TrainCarNode newNode = new TrainCarNode(newCar);
            int position = getCursorPosition();
            try{
                //empty list - make new one
		if(cursor==null){
                    head=newNode;
                    tail=newNode;
                    cursor=head;
                    size++;
                    return;
		}
                
                //at tail
		if(position==size){
                    newNode.setPrevCar(tail);
                    tail.setNextCar(newNode);
                    tail=newNode;
                    cursor=tail;
                    size++;
                    
		}
                
                //somewhere in the middle of the list or at head
		else{
                    newNode.setNextCar(cursor.getNextCar());
                    newNode.setPrevCar(cursor.getPrevCar());
                    cursor.setNextCar(newNode);
                    cursor.getNextCar().setPrevCar(newNode);
                    cursor=newNode;
                    size++;
		}
            }catch(IllegalArgumentException n){
                System.out.println("Cannot add null train car");
            }
	}
	
	/**
	 * Removes a train car at cursor and returns it
	 * 
	 * @return
	 *  Returns removed train car
	 *  
	 * Precondition:
	 *  Cursor is not null
	 *  
	 * Postcondition:
	 *  Train linked list has 1 less car, and cursor is on previous car
	 */
	public TrainCar remove(){
            TrainCar temp = cursor.getData();
        
            //if we're removing the head and there's more than 1 car
            if(getCursorPosition()==1&&size>1){
                cursor=cursor.getNextCar();
		cursor.setPrevCar(null);
		head=cursor;
            }
            
            //removing head, which is only car
            else if(getCursorPosition()==1&&size==1){
		cursor=null;
		head=null;
		tail=null;
		
            }
            
            //cursor is tail
            else if(getCursorPosition()==size){
                TrainCarNode prevNode = cursor.getPrevCar();
                
                cursor=prevNode;
                cursor.setNextCar(null);
                cursor.setPrevCar(cursor.getPrevCar());
            }
 
            //removing a car somewhere in the middle
            else{
		TrainCarNode prevNode = cursor.getPrevCar();
		cursor.getPrevCar().setNextCar(cursor.getNextCar());
		cursor.getNextCar().setPrevCar(cursor.getPrevCar());
		cursor=prevNode;
            }
            size--;
            
            //return old cursor's train car
            return temp;
	}
	
	/**
	 * Determines how many in the linked list of trains
	 * 
	 * @return
	 *  Number of train cars
	 */
	public int size(){
            return size;
	}
	

	/**
	 * Accessor for total length of train. 
	 * 
	 * @return
	 *  Total length of train linked list
	 * 
	 */
	public double getLength(){
            TrainCarNode nodePtr = head;
            double length=0;
            
            //loops through list and sums length of train cars
            while(nodePtr!=null){
                    length+=nodePtr.getData().getLength();
                    nodePtr=nodePtr.getNextCar();
                
            }
            return length;
	}
	

	/**
	 * Accessor for total value of train. 
	 *  
	 * Precondition:
	 *   Train has been created
	 * 
	 * @return
	 *  Total value of all the products in the train linked list
	 *  
	 */
	public double getValue(){
            if(size==0)
        	return 0;
            TrainCarNode nodePtr=head;
            double value=0;
            
            //loops through linked list and adds values of each train car
            while(nodePtr!=null){
                
                if(nodePtr.getData().getLoad()!=null){
                    value=value + (double)nodePtr.getData().getLoad().getValue();
                }

		nodePtr=nodePtr.getNextCar();
            }
            return value;
	}
	
	
	
	/**
         * Accessor for total weight of train and product
         * 
	 * @return
	 *  Total weight of each train car and its product
	 *  
	 */
	public double getWeight(){
            double weight=0;
            TrainCarNode nodePtr = head;
            
            //loops through linked list and adds weight of each train car,
            //not including load
            while(nodePtr!=null){
                if(nodePtr.getData().getLoad()!=null){
		weight=weight + nodePtr.getData().getWeight() + nodePtr
                        .getData().getLoad().getWeight(); 
                }
                nodePtr=nodePtr.getNextCar();
                
            }
            return weight;
	}
	
	
	/**
	 * Determines if train has at least one dangerous product. 
	 * 
	 * @return
	 *  True if train has at least 1 dangerous car
	 *  
	 */
	public boolean isDangerous(){
            TrainCarNode nodePtr = head;
            
            //loops though linked list of train cars. If 1 is dangerous, 
            //the whole train is considered dangerous.
            while(nodePtr!=null){
                
                if(nodePtr.getData().getLoad()==null){
                    nodePtr=nodePtr.getNextCar();
                }
		else if(nodePtr.getData().getLoad().getIsDangerous()==true)
                    return true;
                
		else
                    nodePtr=nodePtr.getNextCar();
		}
		return false;
	}
	
	
	/**
	 * Finds if a product is on the train and prints out all instances
	 * 
	 * @param name
	 *  Name of product to be searched for
	 *  
	 * Postcondition:
	 *  Printed out every instance of the product 
	 */
	public void findProduct(String name){
            double weight=0;
            double value=0;
            boolean found=false;
            
            boolean isDangerous=false;
            TrainCarNode nodePtr = head;
            
            //2 loops of O(n) - 1 to see if it exists
            //if it exists, loop again to get every instance
            
            while(nodePtr!=null){
                if(nodePtr.getData().getLoad().getProductName().equals(name)){
                    found=true;
                    break;
                }
                nodePtr=nodePtr.getNextCar();

            }
            
            //not found in linked list
            if(!found){
		System.out.println("Sorry, but " + name + 
                    " could not be found.");
		return;
            }
            
            //reset nodePtr
            nodePtr = head;
            
            String productInfo;
            String header=String.format("%1$-10s %2$-10s %3$-20s",
                    "Weight","Value","Is it Dangerous?");

            System.out.println(header);
            
            while(nodePtr!=null){
		if(nodePtr.getData().getLoad().getProductName().equals(name)){
                    weight+=nodePtr.getData().getLoad().getWeight();
                    value+=nodePtr.getData().getLoad().getValue();
                    
                    if(nodePtr.getData().getLoad().getIsDangerous()==true)
			isDangerous=true;
				
                }
                
                productInfo=String.format("%1$-10s %2$-10s %3$-10s"
                    ,weight,value,isDangerous);
                System.out.println(productInfo);
		
                nodePtr=nodePtr.getNextCar();
		}
            
            
	}
	
	/**
	 * Prints out train manifest
         * 
         * Postcondition: 
         * Train manifest has been printed to console
	 */
	public void printManifest(){
            TrainCarNode nodePtr = head;
            int position=1;
	
            //prints formatted header
            System.out.printf("%1$-34s %2$-5s","CAR:","LOAD:");
            System.out.println();
            
            //prints formatted sub-header
            System.out.printf("%1$-7s %2$-13s %3$-11s %4$-4s %5$-9s %6$-14s "
                    + "%7$-11s %8$-10s","Num","Length (m)","Weight (t)","|",
                    "Name","Weight (t)","Value ($)","Dangerous");
            System.out.println();
            
            //prints dividing line between headers and cars
            System.out.println("==================================+=="
                + "=================================================");

            //loops through linked list
            while(nodePtr!=null){
                
                //designate where cursor is with a cursor icon
		if(nodePtr.equals(cursor))
                    System.out.print("->");
                //otherwise, print empty spaces to pad it over
                else
                    System.out.print("  ");

                    //print formatted information on car
                    System.out.printf("%1$-5s %2$-13s %3$-11s %4$-4s ",
                        position,nodePtr.getData().getLength(),
                        nodePtr.getData().getWeight(),"|");
                    
                    //if car has no product load, print the following
                    if(nodePtr.getData().isEmpty()){
			
                        System.out.printf("%1$-9s %2$-14s %3$-11s %4$-2s",
                                "Empty","0","0","NO");
                        System.out.println();
                    }
                    
                    //if car has product load, print information about load
                    else{
			
                        System.out.printf("%1$-9s %2$-14s %3$-12s",
                                nodePtr.getData().getLoad().getProductName(),
                                nodePtr.getData().getLoad().getWeight(),
                                nodePtr.getData().getLoad().getValue());
                        
                    }
                            

                    if(nodePtr.getData().getLoad()!=null){
                        if(nodePtr.getData().getLoad().getIsDangerous()==true){
                            System.out.println("YES");
                        }
                        else{
                            System.out.println("NO");
                        }
                    }
                    position++;
                    nodePtr=nodePtr.getNextCar();
            }
	}
	
	/**
	 * Removes all dangerous cars in the train
	 * 
	 * Postcondition:
	 *  All dangerous cars have been removed, otherwise, order is the same
	 */
	public void removeDangerousCars(){
            //if there's no dangerous cars, we're already done
            if(isDangerous()==false)
		return;
            
            TrainCarNode tempNode=cursor;
            cursor=head;
            
            //loop through linked list
            while(cursor!=null){
                
		if(cursor.getData().getLoad()!=null && 
                        cursor.getData().getLoad().getIsDangerous()){
                    
                    //remove moves cursor for us
                    remove();
		}
                else{
                    //not dangerous means we can ignore it
                    cursor=cursor.getNextCar();
                }
            
            }
            
             
             //if original node was dangerous
            if(tempNode.getData().getLoad()!=null && 
                    tempNode.getData().getLoad().getIsDangerous()){
                
                //if there's no nodes left, delete cursor
                 if(size==0){
                    cursor=null;
                    head=null;
                    tail=null;
                 }
                else {
                     //otherwise, put cursor back at head
                    cursor=head;
                }
             }
            
            //if original was safe, make cursor original node
            else{
                cursor=tempNode;
            }
                
	}
        
	
	/**
	 * Creates a string representation of the train
	 * 
	 * @return
	 *  Formatted string with information about train - length, value, 
         *  weight, and if it's dangerous
	 */
        @Override
	public String toString(){
            String trainInfo =  getLength() + " meters long, $" + getValue() + 
		" value, " + getWeight() + " weight in tons,";
                  
                if(isDangerous()){
                    trainInfo+=" and it is dangerous.";
                }
                else{
                    trainInfo+=" and it is not dnagerous.";
                }
                    
            return trainInfo;
	}
	
	/**
	 * Moves cursor forward if possible
	 * 
	 * Precondition:
	 *  Linked list must be created
	 *  
	 * Postcondition:
	 *  Cursor has been moved forward by 1, or remained at tail 
	 *  
	 */
	public void forwardCursor(){
            
            if(size>1&&getCursorPosition()!=size){
                cursor=cursor.getNextCar();
                System.out.println("Cursor moved forward");
            }
            else{
                System.out.println("Cursor cannot move forwards - make sure"
                    +" the cursor isn't at the tail.");
            }

	}
	
	/**
	 * Moves cursor backwards
	 * 
	 * Precondition:
	 *  Linked list has been created
	 *  
	 * Postcondition:
	 *  Cursor has moved back by 1, or remained at head
	 * 
	 */
	public void backwardCursor() throws NullPointerException{
            
            if(size>1&&getCursorPosition()!=1){
                cursor=cursor.getPrevCar();
                System.out.println("Cursor moved backward");
            }
            else{
                System.out.println("Cursor cannot move backwards - make sure"
                    +" the cursor isn't at the head.");	
            }
           
	}
	
	/**
	 * Accessor for whatever node cursor is on
	 * 
	 * Precondition:
	 *  Linked list must have at least 1 element, or it'll be null
	 *  
	 * @return
	 *  Returns cursor's node
	 */
	public TrainCarNode getCursor(){
            return cursor;
	}
	
	/**
	 * Provides current position of cursor
	 * 
	 * Precondition:
	 *  Linked list has been created
	 *  
	 * @return
	 *  Position of cursor, starting with head=1
	 */
	public int getCursorPosition(){
            
            //if no cursor, there's no list
            if(cursor==null)
		return 0;
            
            
            if(cursor==head)
		return 1;
            
            TrainCarNode nodePtr = head;
            int counter=1;
            boolean isRunning=true;
            while(isRunning){
                
                if(nodePtr.getData().equals(cursor.getData())){
                    return counter;
		}
                else{
                    //increment loop
                    nodePtr = nodePtr.getNextCar();
                    counter++;
                }
            }
            //if cursor is somehow not in the linked list
            return -1;
		
	}
        
        
}

