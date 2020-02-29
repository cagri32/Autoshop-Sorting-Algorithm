package eecs2030.lab4;

import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;


import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;
import org.junit.Rule;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AutoShopTest {
	
	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);
	
	

	@Test
	public void test01_staticFields() {
		try {
			List<Field> fields = Arrays.asList(AutoShop.class.getDeclaredFields());

			int nStatic = 0;
			for (Field f : fields) {
				int mod = f.getModifiers();
				if (Modifier.isStatic(mod)) {
					nStatic++;
				}
			}
			assertTrue("there should be no static fields", nStatic == 0);
		} catch (Exception x) {
			fail("exception occurred trying to get the fields of this class");
		}
	}
	

	
	@Test
	public void test02a_ctor() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		assertEquals("ctor set the wrong ShopOwner",
				w, b.getOwner());
	}
	
	@Test
	public void test02b_ctor() {
		ShopOwner w = new ShopOwner("OwnerB");
		AutoShop b = new AutoShop(w);
		assertEquals("ctor did not make an empty list of Vehicles",
				new ArrayList<Vehicle>(), b.deepCopy());
	}
	
	
	@Test
	public void test03a_copyCtor() {
		ShopOwner w = new ShopOwner("OwnerC");
		AutoShop b = new AutoShop(w);
		AutoShop c = new AutoShop(b);
		assertEquals("copy ctor set the wrong owner",
				w, c.getOwner());
	}
	
	@Test
	public void test03b_copyCtor() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>( );
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		Collections.sort(vehicles);
				
				
		b.add(vehicles);
		AutoShop c = new AutoShop(b);
		
		assertEquals("copy ctor did not copy the other list of vehicles",
				vehicles, c.deepCopy());
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void test04a_changeOwner() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		ShopOwner x = new ShopOwner("OwnerB");
		b.changeOwner(x, x);
	}
	
	@Test
	public void test04b_changeOwner() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		ShopOwner x = new ShopOwner("OwnerB");
		b.changeOwner(w, x);
		assertEquals("changeOwner failed to set the correct owner",
				x, b.getOwner());
	}
	
	
	@Test
	public void test05a_addVehicles() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		b.add(vehicles);
		
		assertEquals("addvehicles did not correctly add the vehicles",
				vehicles, b.deepCopy());
	}
	
	@Test
	public void test05b_addVehicles() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		
		
		b.add(vehicles);
		
		assertEquals("add Vehicles did not correctly add the vehicles",
				vehicles, b.deepCopy());
	}
	
	
	@Test
	public void test05c_addVehicles() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		b.add(vehicles);
		
		Collections.sort(vehicles);
				
		assertEquals("addvehicles did not correctly add the vehicles",
				vehicles, b.deepCopy());
	}
	

	@Test
	public void test06a_contains() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		assertFalse("contains returned true for an empty Auto Shop!",
				b.contains(vc1));
		
	}
	
	
	@Test
	public void test06b_contains() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		assertTrue("contains returned false for a AutoShop with every vehicle",
				b.contains(vc1));
		assertTrue("contains returned false for a AutoShop with every vehicle",
				b.contains(vc2));
		assertTrue("contains returned false for a AutoShop with every vehicle",
				b.contains(vc3));
		assertTrue("contains returned false for a AutoShop with every vehicle",
				b.contains(vc4));
		
	}
	
	
	@Test
	public void test06c_contains() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc1);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc1);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc1);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		
		assertTrue("contains returned true for a AutoShop with only vc1",
				b.contains(vc1));
		assertFalse("contains returned false for a AutoShop with only vc1",
				b.contains(vc2));
		assertFalse("contains returned false for a AutoShop with only vc1",
				b.contains(vc3));
		assertFalse("contains returned false for aAutoShop with only vc1",
				b.contains(vc4));
		
	}
	
	
	
	@Test
	public void test07a_sellingsingleVehicle() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		Vehicle got = b.sellingsingleVehicle(w, vc1);
		assertEquals("selling returned the wrong Vehicle", vc1, got);
				
		got = b.sellingsingleVehicle(w, vc2);
		assertEquals("selling returned the wrong Vehicle", vc2, got);
		
		got = b.sellingsingleVehicle(w, vc3);
		assertEquals("selling returned the wrong Vehicle", vc3, got);
		
		got = b.sellingsingleVehicle(w, vc4);
		assertEquals("selling returned the wrong Vehicle", vc4, got);
		
				
	}
	
	@Test
	public void test07b_sellingsingleVehicle() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
				
		Vehicle got = b.sellingsingleVehicle(new ShopOwner("OwnerB"),vc1);
		assertNull("remove allowed a non-owner to remove a vehicle", got);
	}
	
	
	@Test
	public void test08a_sellingVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> expvehicles =  new ArrayList<Vehicle>();
		Vehicle vc5= new Vehicle("Honda",2015,4, 30000);
		expvehicles.add(vc5);
		
		List<Vehicle> gotvehicles = b.sellingVehicles(w, 30000);
		
		
		assertEquals("sellingVehicles returned the wrong list of vehicles",
				expvehicles, gotvehicles);
	}
	@Test
	public void test08b_sellingVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> expvehicles =  new ArrayList<Vehicle>();
		Vehicle vc5=  new Vehicle("Toyota",2018,4, 36000);
		expvehicles.add(vc5);
		Vehicle vc6= new Vehicle("Honda",2015,4, 30000);
		expvehicles.add(vc6);
		
		
		List<Vehicle> gotvehicles = b.sellingVehicles(w, 66000);
		
		
		assertEquals("sellingVehicles returned the wrong list of vehicles",
				expvehicles, gotvehicles);
		
	}
	
	

	
	
	@Test
	public void test08c_sellingVehicles() {
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		vehicles.add(vc1);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> expvehicles =  new ArrayList<Vehicle>();
		Vehicle vc5=  new Vehicle("Toyota",2018,4, 36000);
		expvehicles.add(vc5);
		expvehicles.add(vc5);
		
		
		List<Vehicle> gotvehicles = b.sellingVehicles(w, 72000);
		
		
		assertEquals("sellingVehicles returned the wrong list of vehicles",
				expvehicles, gotvehicles);
		
	}
	
	
	

	@Test
	public void test08d_sellingVehicles() {
		
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2018,4, 3000);
		vehicles.add(vc1);
		vehicles.add(vc1);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		vehicles.add(vc2);
		vehicles.add(vc2);
		
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		vehicles.add(vc3);
		vehicles.add(vc3);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> expvehicles =  new ArrayList<Vehicle>();
		Vehicle vc5=  new Vehicle("Audi",2017,4, 34000);
		expvehicles.add(vc5);
		expvehicles.add(vc5);
		Vehicle vc6= new Vehicle("Toyota",2018,4, 3000);
		expvehicles.add(vc6);
		
		
		List<Vehicle> gotvehicles = b.sellingVehicles(w, 72000);
		
		
		assertEquals("sellingVehicles returned the wrong list of vehicles",
				expvehicles, gotvehicles);
		
	}
	
	
	
	
	
	
	@Test
	public void test09a_deepCopy() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		List<Vehicle> got = b.deepCopy();
		Vehicle vc6= new Vehicle("Toyota",2018,4, 36000);
		got.add(vc6);

		assertEquals("deepCopy did not return a new list",
				new ArrayList<>(), b.deepCopy());
	}
	
	@Test
	public void test09b_deepCopy() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		
		b.add(Arrays.asList(new Vehicle("Toyota",2018,4, 36000)));
		List<Vehicle> got = b.deepCopy();
		assertNotSame("deepCopy did not return new vehicles",
				new Vehicle("Toyota",2018,4, 36000), got.get(0));
	}
	
	@Test
	public void test09c_deepCopy() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc2= new Vehicle("KIA",2013,4, 26000);
		vehicles.add(vc2);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		vehicles.add(vc4);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		Vehicle vc1= new Vehicle("Toyota",2018,4, 36000);
		vehicles.add(vc1);
		vehicles.add(vc1);
		vehicles.add(vc1);
		
		
		
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		
		
		List<Vehicle> got = b.deepCopy();
		assertEquals("deepCopy returned the wrong list of vehicles",
				vehicles, got);
	}
	
	
	@Test
	public void test10a_ShallowCopySortedbyYear() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		Vehicle vc1= new Vehicle("Toyota",2005,4, 36000);
		vehicles.add(vc1);
		vehicles.add(vc1);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2017,4, 26000);
		vehicles.add(vc2);
		
		Vehicle vc4= new Vehicle("Honda",2017,4, 30000);
		vehicles.add(vc4);
		Vehicle vc3= new Vehicle("Audi",2017,4, 34000);
		vehicles.add(vc3);
		
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		
		
		List<Vehicle> got = b.shallowCopySortedbyYear();
		assertEquals("Shallow Copy returned the wrong list of vehicles",
				vehicles, got);
	}
	
	
	@Test
	public void test10b_ShallowCopySortedbyYear() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		Vehicle vc4= new Vehicle("Honda",2015,4, 30000);
		b.add(Arrays.asList(vc4));
		List<Vehicle> got = b.shallowCopySortedbyYear();
		
		assertSame("Shallow Copy did not return same vehicles",
				vc4, got.get(0));
	}
	
	

	@Test
	public void test10c_ShallowCopySortedbyYear() {
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		List<Vehicle> got = b.shallowCopySortedbyYear();
		Vehicle vc6= new Vehicle("Toyota",2018,4, 36000);
		got.add(vc6);

		assertEquals("Shallow Copy did not return a new list",
				new ArrayList<>(), b.deepCopy());
	}
	
	
	
	@Test
	public void test11a_deepCopySortedbyMakePrice() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle vc1= new Vehicle("Audi",2017,4, 4000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 14000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 24000);
		vehicles.add(vc1);
			
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> got = b.deepCopySortedbyMakePrice();
		assertEquals("deepCopy returned the wrong list of vehicles",
				vehicles, got);
	}
	
	
	
	@Test
	public void test11b_deepCopySortedbyMakePrice() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle vc1= new Vehicle("Audi",2017,4, 4000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 14000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 24000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2016,4, 55000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2018,4, 65000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2017,4, 75000);
		vehicles.add(vc1);
		
		
		
			
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> got = b.deepCopySortedbyMakePrice();
		assertEquals("deepCopy returned the wrong list of vehicles",
				vehicles, got);
	}

	@Test
	public void test11c_deepCopySortedbyMakePrice() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle vc1= new Vehicle("Audi",2017,4, 4000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 14000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 24000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2016,4, 15000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2018,4, 65000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2017,4, 75000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2017,4, 26000);
		vehicles.add(vc2);
		
		vc2= new Vehicle("KIA",2017,4, 36000);
		vehicles.add(vc2);
		vc2= new Vehicle("KIA",2017,4, 46000);
		vehicles.add(vc2);
		vc2= new Vehicle("KIA",2017,4, 66000);
		vehicles.add(vc2);
		
			
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> got = b.deepCopySortedbyMakePrice();
		assertEquals("deepCopy returned the wrong list of vehicles",
				vehicles, got);
	}
	
	
	@Test
	public void test11d_deepCopySortedbyMakePrice() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		Vehicle vc1= new Vehicle("Audi",2017,4, 4000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 14000);
		vehicles.add(vc1);
		vc1= new Vehicle("Audi",2017,4, 24000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2016,4, 55000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2018,4, 65000);
		vehicles.add(vc1);
		vc1= new Vehicle("Honda",2017,4, 75000);
		vehicles.add(vc1);
		Vehicle vc2= new Vehicle("KIA",2017,4, 26000);
		vehicles.add(vc2);
		
		vc2= new Vehicle("KIA",2017,4, 36000);
		vehicles.add(vc2);
		vc2= new Vehicle("KIA",2017,4, 36000);
		vehicles.add(vc2);
		vc2= new Vehicle("KIA",2017,4, 66000);
		vehicles.add(vc2);
		
		Vehicle vc5= new Vehicle("Toyota",2005,4, 6000);
		vehicles.add(vc5);
		Vehicle vc6= new Vehicle("Toyota",2005,4, 16000);
		vehicles.add(vc6);
		vc5= new Vehicle("Toyota",2005,4, 56000);
		vehicles.add(vc5);
		vc5= new Vehicle("Toyota",2005,4, 86000);
		vehicles.add(vc5);
		
			
		ShopOwner w = new ShopOwner("OwnerA");
		AutoShop b = new AutoShop(w);
		b.add(vehicles);
		
		List<Vehicle> got = b.deepCopySortedbyMakePrice();
		assertEquals("deepCopy returned the wrong list of vehicles",
				vehicles, got);
	}
}
