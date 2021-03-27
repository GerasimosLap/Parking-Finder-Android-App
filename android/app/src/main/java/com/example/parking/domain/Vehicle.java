package com.example.parking.domain;

import com.example.parking.util.Colour;

public class Vehicle {
	private Colour colour;
	private int length;//length in cm
	private String text;
	private String plate;
	private String model;
	private String brand;

	public Vehicle(Colour colour, int length, String text, String plate, String model, String brand) {
		this.colour = colour;
		this.length = length;
		this.text = text;
		this.plate = plate;
		this.model = model;
		this.brand = brand;
	}

	public Vehicle(){
		this.colour = Colour.Black;
		this.length = 0;
		this.text = "";
		this.plate = "";
		this.model = "";
		this.brand = "";
	}


	public void setColour(Colour colour) {
		this.colour = colour;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Ελέγχει αν το plate είναι σε σωστή μορφή.
	 * @param plate Plate προς αποθήκευση
	 */
	public void setPlate(String plate) {
		String letters = plate.substring(0,3).toUpperCase();
		for(int i=0;i<letters.length();i++){
			char letter = letters.charAt(i);
			if(letter<65 || letter>90) return;
		}
		String numbers = plate.substring(3);
		for(int i=0;i<numbers.length();i++){
			int number = Integer.valueOf(numbers.charAt(i));
			if(number<48 || number>57) return;
		}
		if(letters.length()+numbers.length()!=7) return;
		this.plate = plate;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColour() {
		return colour.toString();
	}

	public int getLength() {
		return length;
	}

	public String getText() {
		return text;
	}

	public String getPlate() {
		return plate;
	}

	public String getModel() {
		return model;
	}

	public String getBrand() {
		return brand;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"colour=" + colour +
				", length=" + length +
				", text='" + text + '\'' +
				", plate='" + plate + '\'' +
				", model='" + model + '\'' +
				", brand='" + brand + '\'' +
				'}';
	}


}