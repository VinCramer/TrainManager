/**
 *
 * @author Vincent Cramer
 */
package trainmanager;



/**
 * Class has methods for making product loads and changing/accessing variables
 *
 *
 */
public class ProductLoad {

	private String productName;
	private double weight, value;
	private boolean isDangerous;
	
	
	/**
	 *  Constructor for ProductLoad
	 *  
	 *  @param productName
	 *   Name of product
	 *  
	 *  @param weight
	 *   Weight of product in tons
	 *   
	 *  @param value
	 *   Price of product
	 *  
	 *  @param isDangerous
	 *   Mentions if product is safe or requires special treatment
	 *  
	 *  Postcondition:
	 *   ProductLoad has been created
	 */
	public ProductLoad(String productName, double weight, double value,
			boolean isDangerous){
            this.productName=productName;
            this.weight=weight;
            this.value=value;
            this.isDangerous=isDangerous;
	}
	
	/**
	 * Mutator for product's name
	 * 
	 * @param productName
	 *  What the name of the product will become
	 *  
	 * Precondition:
	 *  ProductLoad must have been created 
	 *  
	 * Postcondition:
	 *  Product's name has been changed 
	 */
	public void setProductName(String productName){
            this.productName=productName;
	}
	
	/**
	 * Accesor for product's name
	 * 
	 * Precondition:
	 *  Product must have been created
	 * 
	 * @return
	 *  Name of product
	 */
	public String getProductName(){
            return productName;
	}
	
	/**
	 * Accessor for product's weight
	 * 
	 * Precondition:
	 *  Product must have been created
	 *  
	 * @return
	 *  Weight of product
	 */
	public double getWeight(){
            return weight;
	}
	
	/**
	 * Accessor for product's value
	 * 
	 * Precondition:
	 *  Product must have been created
	 *  
	 * @return
	 *  Value of product
	 */
	public double getValue(){
            return value;
	}
	
	/**
	 * Accessor for if a product is dangerous
	 * 
	 * Precondition:
	 *  Product must have been created
	 *  
	 * @return
	 *  If product is dangerous or not
	 */
	public boolean getIsDangerous(){
            return isDangerous;
	}
	
	/**
	 * Mutator for product's weight
	 * 
	 * @param weight
	 *  New weight value
	 *  
	 * Precondition:
	 *  Product must have been created, and new weight positive
	 * 
	 * Postcondition:
	 *  Product's weight has been changed
	 *    
	 * @throws IllegalArgumentException
	 *  Indicates an invalid weight
	 */
	public void setWeight(double weight) throws IllegalArgumentException{
            try{
                if(weight>=0){
                    this.weight=weight;
                }
            }
            catch (IllegalArgumentException a){
                System.out.println("Invalid weight - must be non-negative");
            }
	}
	
	/**
	 * Mutator for product's value
	 * 
	 * @param value
	 *  New value of product
	 * 
	 * Precondition:
	 *  Product has been created, and new value is positive
	 * 
	 * Postcondition:
	 *  Product's value has been changed
	 *    
	 * @throws IllegalArgumentException
	 *  Indicates an invalid value
	 */
	public void setValue(double value) throws IllegalArgumentException{
            try{
                if(value>=0){
                    this.value=value;
                }
            }
            catch(IllegalArgumentException b){
                    System.out.println("Invalid value - must be non-negative");
            }
            
	}
	
	/**
	 * Mutator for if a product is dangerous
	 * 
	 * Precondition:
	 *  Product must have been created
	 *  
	 * @param isDangerous
	 *  New value of isDangerous
	 *  
	 * Postcondition:
	 *  isDangerous is now changed 
	 */
	public void setIsDangerous(boolean isDangerous){
            this.isDangerous=isDangerous;
	}
}


