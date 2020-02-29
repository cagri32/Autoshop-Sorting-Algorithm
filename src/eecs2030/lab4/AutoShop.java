package eecs2030.lab4;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A class representing a <strong> auto shop </strong> that has an <strong>
 * owner</strong> . A auto shop <strong> <em> owns a collection (or possibly
 * collections) of vehicles, but does not own the vehicles themselves</em>
 * </strong>. In other words, <strong> <em>the auto shop and its collection of
 * vehicles form a composition</em> </strong>.
 * 
 * <p>
 * Only the owner of the auto shop is able to sell vehicles from the auto shop.
 * <strong> <em> The auto shop does NOT own its owner</em> </strong>. In other
 * words, <strong> <em>the auto shop and its owner form an aggregation</em>
 * </strong>.
 * </p>
 */

public class AutoShop {

	/*
	 * YOU NEED A FIELD HERE TO HOLD THE Vehicle OF THIS AutoShop
	 */
	ShopOwner owner;
	TreeMap<Vehicle, java.lang.Integer> vehicles;

	/**
	 * Initializes this auto shop so that it has the specified owner and no
	 * vehicles.
	 * 
	 * @param owner
	 *            the owner of this auto shop
	 */
	public AutoShop(ShopOwner owner) {
		this.owner = owner;
		this.vehicles = new TreeMap<>();

	}

	/**
	 * Initializes this auto shop by copying another auto shop. This auto shop will
	 * have the same owner and the same number and type of vehicles as the other
	 * auto shop.
	 * 
	 * @param other
	 *            the auto shop to copy
	 */
	public AutoShop(AutoShop other) {
		// COMPLETE THIS
		this.owner = other.owner;
		this.vehicles = other.vehicles;
	}

	/**
	 * Returns the owner of this auto shop.
	 * 
	 * <p>
	 * This method is present only for testing purposes. Returning the owner of this
	 * auto shop allows any user to sell vehicles from the auto shop (because any
	 * user can get the owner of this auto shop)!
	 * 
	 * @return the owner of this auto shop
	 */
	public ShopOwner getOwner() {
		// ALREADY IMPLEMENTED; DO NOT MODIFY
		return this.owner;
	}

	/**
	 * Allows the current owner of this auto shop to give this auto shop to a new
	 * owner.
	 * 
	 * @param currentOwner
	 *            the current owner of this auto shop
	 * @param newOwner
	 *            the new owner of this auto shop
	 * @throws IllegalArgumentException
	 *             if currentOwner is not the current owner of this auto shop
	 */
	public void changeOwner(ShopOwner currentOwner, ShopOwner newOwner) throws IllegalArgumentException {

		// COMPLETE THIS
		if (!this.getOwner().equals(currentOwner)) {
			throw new IllegalArgumentException("");
		} else {
			this.owner = newOwner;
		}

	}

	/**
	 * Adds the specified vehicles to this auto shop.
	 * 
	 * @param vehicles
	 *            a list of vehicles to add to this auto shop
	 */
	public void add(List<Vehicle> vehicles) {
		// COMPLETE THIS
		for (Vehicle v : vehicles) {
			if (!this.vehicles.keySet().contains(v)) {
				this.vehicles.put(v, 1);
			} else {
				this.vehicles.put(v, this.vehicles.get(v) + 1);
			}
		}

	}

	/**
	 * Returns true if this auto shop contains the specified vehicle, and false
	 * otherwise.
	 * 
	 * @param vehicle
	 *            a vehicle
	 * @return true if this auto shop contains the specified vehicle, and false
	 *         otherwise
	 */
	public boolean contains(Vehicle vehicle) {

		// COMPLETE THIS
		if (this.vehicles.containsKey(vehicle)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Allows the owner of this auto shop to sell a single vehicle equal to the
	 * specified vehicle from this auto shop.
	 * 
	 * <p>
	 * If the specified user is not equal to the owner of this auto shop, then the
	 * vehicle is not sold from this auto shop, and null is returned.
	 * 
	 * @param user
	 *            the person trying to sell the vehicle
	 * @param vehicle
	 *            a vehicle
	 * @return a vehicle equal to the specified vehicle from this auto shop, or null
	 *         if user is not the owner of this auto shop @pre. the auto shop
	 *         contains a vehicle equal to the specified vehicle
	 */
	public Vehicle sellingsingleVehicle(ShopOwner user, Vehicle vehicle) {

		// COMPLETE THIS
		if (this.owner.equals(user)) {
			int n = this.vehicles.get(vehicle);
			this.vehicles.put(vehicle, n - 1);
			return vehicle;
		} else {
			return null;
		}

	}

	/**
	 * Allows the owner of this auto shop to sell the smallest number of vehicles
	 * whose total price value in dollars is equal or less than to the specified
	 * price value in dollars.
	 * 
	 * <p>
	 * Returns the empty list if the specified user is not equal to the owner of
	 * this auto shop.
	 * </p>
	 * 
	 * @param user
	 *            the person trying to sell vehicles from this auto shop
	 * @param pricevalue
	 *            a value in dollars
	 * @return the smallest number of vehicles whose total price value in dollars is
	 *         equal to the specified value in dollars from this auto shop @pre. the
	 *         auto shop contains a group of vehicles whose total price value is
	 *         equal to specified value
	 */
	public List<Vehicle> sellingVehicles(ShopOwner user, int pricevalue) {

		List<Vehicle> result = new ArrayList<>();
		if (!user.equals(this.owner)) {
			return result;
		}
		for (Vehicle v : this.vehicles.descendingKeySet()) {
			while (this.vehicles.get(v) > 0 && pricevalue >= v.getPrice()) {
				result.add(this.sellingsingleVehicle(this.owner, v));
				pricevalue -= v.getPrice();
			}
		}
		return result;

		// COMPLETE THIS

	}

	/**
	 * Returns a deep copy of the vehicles in this auto shop. The returned list has
	 * its vehicles in sorted order (from smallest price value to largest price
	 * value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 * 
	 * @return a deep copy of the vehicles in this auto shop sorted by price value
	 */
	public List<Vehicle> deepCopy() {

		// COMPLETE THIS
		List<Vehicle> deep = new LinkedList<Vehicle>();
		for (Vehicle v : this.vehicles.keySet()) {
			for (int o = 0; o < this.vehicles.get(v); o++) {
				deep.add(v);
			}
		}
		Collections.sort(deep, Comparator.comparing(Vehicle::getPrice));
		return deep;
		// SORT LIST
	}

	/**
	 * Returns a Shallow copy of the vehicles in this auto shop. The returned list
	 * has its vehicles in sorted order (from smallest year of make value to largest
	 * year of make value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 * 
	 * @return a show copy of the vehicles in this auto shop sorted by year of make
	 */

	public List<Vehicle> shallowCopySortedbyYear() {
		// COMPLETE THIS
		List<Vehicle> shallow = this.vehicles.keySet().stream().collect(Collectors.toList());
		for (Vehicle v : this.vehicles.keySet()) {
			for (int o = 0; o < this.vehicles.get(v) - 1; o++) {
				shallow.add(v);
			}
		}
		Collections.sort(shallow, Comparator.comparing(Vehicle::getYearMake));
		// Collections.sort(shallow, new SortVehiclebyYear());

		return shallow;
	}

	/**
	 * Returns a deep copy of the vehicles in this auto shop. The returned list has
	 * its vehicles in sorted based on make and then ordered based on price value.
	 * 
	 * (i.e., from smallest price value to largest price value).
	 * <p>
	 * Remember that <strong> <em>the auto shop and its collection of vehicles form
	 * a composition.</em> </strong>
	 * </p>
	 * 
	 * @return a deep copy of the vehicles in this auto shop sorted based on make
	 *         and then ordered based on price value.
	 */
	public List<Vehicle> deepCopySortedbyMakePrice() {
		// COMPLETE THIS

		List<Vehicle> result = this.deepCopy();

		// deep.sort(Comparator.comparing(Vehicle::getPrice));
		Collections.sort(result, new SortVehiclebyMake());
		return result;
	}

}
