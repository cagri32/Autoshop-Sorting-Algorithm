package eecs2030.lab4;

/**
 * A class that represents a Vehicle.
 * 
 */


public class Vehicle implements Comparable<Vehicle>{
	
	
	/**
	 * The yearMake field is an int 
	 * that holds the vehicle's year make
	 * 
	 * default year make is current year  
	 * 
	 */
	private int yearMake;
	
	/**
	 * The make field 
	 * references a String object that holds the make of the car
	 * in capital, small or mix letter
	 * such as "KIA", "Honda", "Toyota" , 
	 * "FORD", "Audi", "MazDA" ,"Nissan",...
	 * 
	 * note: all of these word variations 
	 *  "KIA", "kia", "Kia", "KIa" , "kiA","kIA",..etc indicate that 
	 *  the vehicle is make "KIA".
	 * 
	 * default make is "Toyota"
	 * 
	 */
	private String make;
	
	
	/**
	 * The number of doors of a vehicle  
	 * the default value for the number of doors is four
	 */
	private int numOfDoors; 
	
	/**
	 * The price of a vehicle 
	 * the default price of any vehicle is 1$ 
	 */
	private  double price; 
	
	
	
	

	
	
	/**
	 * Default Constructor 
	 * initialized the vehicle with default value 
	 */
	public Vehicle() 
	{
		this.numOfDoors = 4;
		this.price = 1;
		this.yearMake=2019;
		this.make="Toyota";
	}

	
	/**
	 * custom constructor 
	 * Initializes this vehicle to have specific values for 
	 * its fields
	 *  
	 * @param make  the vehicle make 
	 * @param yearMake the year of vehicle make
	 * @param numOfDoors number of door of this vehicle  
	 * @param price  price of this vehicle 
	 * 
	 */
	public Vehicle(String make, int yearMake, int numOfDoors, double price) {
		
		this.yearMake=yearMake;
		this.make=make;
		this.numOfDoors = numOfDoors;
		this.price = price;
	}

	
	
	/**
	 * Copy constructor
	 * Initializes this vehicle to have the same fields  as the
	 * specified vehicle.
	 * 
	 * @param vehicle : the vehicle that you need to copy 
	 */
	public Vehicle(Vehicle vehicle) 
	{
		
		this.make=vehicle.make;
		this.yearMake=vehicle.yearMake;
		this.numOfDoors = vehicle.numOfDoors;
		this.price = vehicle.price;
	}

	
	
	/**
	 * Returns the year make (yearMake) field value 
	 * @return the year make field value of this vehicle
	 */
	public int getYearMake()
    {
        return this.yearMake;
    }
	
	
    /**
     *  Returns the  make (make) field value 
	 * @return the make field value of this vehicle
     */
    public String getMake ()
    {
        return this.make;
    }
    
	
	/**
	 * Returns the number of doors (numOfDoors) field value 
	 * of this vehicle .
	 * 
	 * @return the numOfDoors field value of this vehicle
	 * 
	 */
	public int getNumOfDoors() {
		return this.numOfDoors;
	}

	


	/**
	 * Returns the price value of this vehicle .
	 * 
	 * @return the  price value of this vehicle
	 */
	public double getPrice() {
		return this.price;
	}


	
	/**
	 * Returns a string describing this vehicle. The returned
	 * string is one of <code> (make, year make, number of doors, price)</code>.
	 * 
	 * @return a string representation of this vehicle
	 */
	@Override
	public String toString() {
		return "(" + this.make + ", "+ this.yearMake + ", "+ this.numOfDoors + ", " + this.price + ")";
	}



	
	/**
	 *
	 * Returns a hash code for this vehicle.
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + numOfDoors;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + yearMake;
		return result;
	}

	/**
	 * Compares this vehicle  to another vehicle returning true if and
	 * only if the specified object is a vehicle with the same 
	 * number of doors and price values as this vehicle.
	 * 
	 * @param obj the object to compare to this vehicle
	 * @return true if obj is a vehicle with the same values as this
	 * vehicle, and false otherwise
	 * 
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equalsIgnoreCase(other.make))
			return false;
		if (numOfDoors != other.numOfDoors)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (yearMake != other.yearMake)
			return false;
		return true;
	}


	/**
	 * Compares this vehicle  to the specified vehicle. The result
	 * is the equal to the difference
	 * <code> this vehicle price value - other vehicle price value</code>. 
	 *  
	 * @param other a vehicle to compare
	 * @return a positive value if the price value  of this vehicle is
	 * greater than the price value of the other vehicle,
	 * a negative  value if the price value of this vehicle is
	 * less than the price value of the other vehicle,
	 * or zero if both vehicles have equal price values.
	 */
	@Override
	public int compareTo(Vehicle other) {
		
		
		return Double.compare(this.price, other.price) ;
		
		
	}

	
	
	
	
	
	

}
