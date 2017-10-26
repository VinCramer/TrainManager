/**
 *
 * @author Vincent Cramer
 */
package trainmanager;

/*
Contains methods and fields to make each train car act as a node in 
a singly linked list
*/
public class TrainCarNode {
    
	private TrainCarNode nextCar;
	private TrainCarNode prevCar;
	//prev&next Car are links - forward + backward
	private TrainCar data;
	
	/**
	 * Constructor for train car node
	 * 
	 * @param initialData
	 *  Initial information about train car
	 */
	public TrainCarNode(TrainCar initialData){
            data=initialData;
            nextCar=null;
            prevCar=null;
	}
	
	/**
	 * Returns the next train car in the linked list
	 * 
	 * @return
	 *  Next train car
	 */
	public TrainCarNode getNextCar(){
            return nextCar;
	}
	
	/**
	 * Returns previous train car in linked list
	 * 
	 * @return
	 *  Previous train car
	 */
	public TrainCarNode getPrevCar(){
            return prevCar;
	}
	
	/**
	 * Provides information about the train car
	 * 
	 * @return
	 *  Information about the train car
	 */
	public TrainCar getData(){
            return data;
	}
	
	/**
	 * Mutator for next car in train linked list
	 * 
	 * @param nextCar
	 *  What will be the next car in the train
	 *  
	 * Postcondition:
	 *  Train car after current one is changed 
	 */
	public void setNextCar(TrainCarNode nextCar){
            this.nextCar=nextCar;
	}
	
	/**
	 * Mutator for previous car in train linked list
	 * 
	 * @param prevCar
	 *  What will be previous car in the train
	 *  
	 * Postcondition:
	 *  Train car before current one is changed 
	 */
	public void setPrevCar(TrainCarNode prevCar){
            this.prevCar=prevCar;
	}
	
	/**
	 * Mutator for information about train car
	 * 
	 * @param data
	 *  New information about train car
	 *  
	 * Postcondition: 
	 *  Data has been changed
	 */
	public void setData(TrainCar data){
            this.data=data;
	}
}
