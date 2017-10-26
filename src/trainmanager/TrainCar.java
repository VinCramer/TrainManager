/**
 *
 * @author Vincent Cramer
 */
package trainmanager;

/**
* Class defines the methods and fields for each train car 
*
*/
public class TrainCar {
 
	private double length, weight;
	private ProductLoad load=null;
	
	/**
	 * Creates a train car
	 * 
         * @param length
         *  Length of the new train car
         * 
         * @param weight
         *  Weight of the new train car
         * 
	 * Postcondition:
	 *  Train car as now been created
	 */
	public TrainCar(double length, double weight){
            this.length=length;
            this.weight=weight;
            load=null;
	}
	
	/**
	 * Accessor for length of train car
	 * 
	 * Precondition:
	 *  Train car has been created
	 * 
	 * @return
	 *  Length of train car
	 */
	public double getLength(){
            return length;
	}
	
	/**
	 * Accessor for weight of train car
	 * 
	 * Precondition:
	 *  Train car must have been created
	 * 
	 * @return
	 *  Weight of train car
	 */
	public double getWeight(){
            return weight;
	}
	
	/**
	 * Accessor for product load on train car
	 * 
	 * Precondition:
	 *  Train car must have been created
	 * 
	 * @return
	 *  Product on train car - null if train car is empty
	 */
	public ProductLoad getLoad(){
            return load;
	}
	
	/**
	 * Mutator for load
	 * 
	 * @param load
	 *  New product on train car. Overrides old product
	 * 
	 * Precondition:
	 *  Train car must have been created
	 * 
	 * Postcondition:
	 *  Train car's product has been changed
	 */
	public void setLoad(ProductLoad load){
            this.load=load;
	}
	
	/**
	 * Determines if train car is empty
	 * 
	 * Precondition:
	 *  Train car must be created first
	 *  
	 * @return
	 *  True if train car is carrying nothing, false otherwise
	 */
	public boolean isEmpty(){
            return this.getLoad()==null;
	}
	
	/**
	 * Determines if 2 train cars are the same
	 * 
	 * Precondition:
	 *  Obj must be a train car
	 *  
	 * @return
	 *  True if they're the same, otherwise, false 
	 */
        @Override
	public boolean equals(Object obj){
            if(obj instanceof TrainCar){
		TrainCar comparable = (TrainCar)obj;
		if(this.getLoad()==comparable.getLoad() && this.getWeight()
                        ==comparable.getWeight() && this.getLength() == 
                        comparable.getLength())
                            return true;
		}
		return false;
	}   
}
