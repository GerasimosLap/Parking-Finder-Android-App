package com.example.parking.domain;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.parking.util.Credits;
import com.example.parking.util.TimeRange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ParkingSpace {
	private Address address;
	private boolean availability;
	private Credits price;
	private TimeRange timeRange;
	private Date timeOfExchange;
	private User parkedUser;
	private String plate;

	private static final double max_amount = 16.0;
	private static final double min_amount = 0.0;


	public ParkingSpace(Address address, boolean availability, Credits price, TimeRange timeRange, Date timeOfExchange, User parkedUser,String plate) {
		this.address = address;
		this.availability = availability;
		this.price = price;
		this.timeRange = timeRange;
		this.timeOfExchange=timeOfExchange;
		this.parkedUser=parkedUser;
		this.plate = plate;
	}




	public ParkingSpace() {
		this.address = new Address();
		this.availability = false;
		this.price = new Credits();
		this.timeRange = new TimeRange(30);
		this.timeOfExchange = new Date();
		this.plate = "";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public boolean getAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Credits getPrice() {
		return price;
	}

	/**
	 * Ελέγχει αν η τιμή της θέσης στάθμευσης βρίσκεται στα επιτρεπτά όρια.
	 * @param price Η απαιτούμενη τιμή
	 */
	public void setPrice(Credits price) {
		if(price.getPoints()>= min_amount && price.getPoints()<=max_amount) {
			this.price = price;
		}
	}

	public User getParkedUser() {
		return parkedUser;
	}

	public void setParkedUser(User parkedUser) {
		this.parkedUser = parkedUser;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	public TimeRange getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(TimeRange timeRange) {
		this.timeRange = timeRange;
	}

	public Date getTimeOfExchange() {
		return timeOfExchange;
	}

	public void setTimeOfExchange(Date timeOfExchange) {
		this.timeOfExchange = timeOfExchange;
	}

	/**
	 * Απενεργοποιεί την θέση στάθμευσης.
	 */
	public void makeParkingUnavailable(){
		availability=false;
		setTimeOfExchange(new Date(System.currentTimeMillis()));
	}

	/**
	 * Ενεργοποιεί τη θέση στάθμευσης.
	 */
	public void makeParkingAvailable(){
		availability=true;
		setTimeOfExchange(new Date());
	}

	public String toString() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(timeOfExchange);
		return "ParkingSpace{" +
				"address=" + address +
				", availability=" + availability +
				", price=" + price +
				", timeOfExchange=" + strDate +
				", parkedUser=" + parkedUser +
				", plate='" + plate + '\'' +
				'}';
	}
}