package eecs2030.lab4;

import java.util.*;

/**
 * 
 * Sort vehicle by year so vehicles can be sorted in ascending order of
 * year make field
 *
 */
public class SortVehiclebyYear implements Comparator <Vehicle>{
	
	
	
	/**
	 * Compares two  vehicles based on the year of making. The result
	 * is the equal to the difference
	 * <code> the vehicles yearMake value - the  vehicle2 yearMake value</code>. 
	 *  
	 * @param vehicle1 vehicle1 
	 * @param vehicle2  vehicle2 
	 * @return a positive integer value if the yearMake value  of  vehicle1 is
	 * greater than the yearMake value of  vehicle2,
	 * a negative integer value if the yearMake value of vehicle1 is
	 * less than the price value of vehicle2,
	 * or zero if both vehicles have equal yearMake value.
	 * 
	 * 
	 */

	@Override
	public int compare(Vehicle vehicle1, Vehicle vehicle2) {
		
		return vehicle1.getYearMake()-vehicle2.getYearMake();
	}

}
