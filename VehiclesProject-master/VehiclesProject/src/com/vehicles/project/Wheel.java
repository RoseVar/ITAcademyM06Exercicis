package com.vehicles.project;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter) {
		this.brand = brand;
		this.diameter = diameter;
	}
		
	public String getBrand() {
		return brand;
	}

	public double getDiameter() {
		return diameter;
	}



	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		
		sb.append("Roda: [Marca: "); sb.append(brand);
		sb.append(" , Diametro: "); sb.append(diameter);
		sb.append("]");
		
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean b;
		if (obj==null) {
			b= false;
		} else {
			if (this==obj) {
				b=true;
			} else {
				if (obj instanceof Wheel) {
					Wheel other= (Wheel) obj;
					if (this.brand.equalsIgnoreCase(other.brand) & this.diameter==other.diameter){
						b=true;
					} else {
						b= false;
					}
				} else {
					b= false;
				}
			}
		}
		return b;
	}
	
	
	
	
}
