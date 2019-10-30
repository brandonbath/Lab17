package co.grandcircus;

import java.math.BigInteger;

/*Create a class named Country with fields for name and population. Add getters,
setters, constructors, and a toString.*/

public class Country {
	private String name;
	private BigInteger population;

	public Country() {
	}

	public Country(String name, BigInteger population) {
		super();
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getPopulation() {
		return population;
	}

	public void setPopulation(BigInteger population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return name + "," + population;
	}
	
	public static Country fromString(String row) {
		String[] rowValues = row.split(",");
		Country thatOne = new Country(
				rowValues[0], 
				BigInteger.valueOf(
						Long.parseLong(rowValues[1])));
		return thatOne;
	}

}
