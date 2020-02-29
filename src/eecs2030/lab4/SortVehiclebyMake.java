package eecs2030.lab4;

import java.util.*;



/**
 * 
 * Sort vehicle by year so vehicles can be sorted in ascending order of
 *  make  field
 *
 */

public class SortVehiclebyMake implements Comparator <Vehicle> {
	

	/**
	 * Compares two  vehicles based on the make type. The result
	 * is the equal to the compareTo of two string 
	 * <code> vehicle1.getMake().compareTo(vehicle2.getMake()) </code>. 
	 *  
	 * @param vehicle1 vehicle1 
	 * @param vehicle2  vehicle2 
	 * @return  result of compareTo of two string 
	 * 			 vehicle1.getMake().compareTo(vehicle2.getMake())
	 * 
	 * 
	 */

	@Override
	public int compare(Vehicle vehicle1, Vehicle vehicle2) {
		
		return  vehicle1.getMake().compareTo(vehicle2.getMake());
	}

}
