package org.taxi.algeria.database;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.taxi.algeria.model.Car;
import org.taxi.algeria.model.Customer;
import org.taxi.algeria.model.Driver;
import org.taxi.algeria.model.Reservation;
import org.taxi.algeria.model.Trip;
import org.taxi.algeria.model.L0cation;
import org.taxi.algeria.model.Message;

public class FetchDatabase {
	private int resultValue;
	private Connection connection;
	private Database database;
	private static Map<String, Driver> drivers = new HashMap<>();
	private static final Long duration = (long) (((((23*60)+59 )* 60) + 59) * 1000);
 
	public FetchDatabase() {
		try {
			this.database = new Database();
			this.connection = database.Get_Connection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, Driver> getDrivers() throws Exception {
		drivers.clear();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM driver ORDER BY driverID DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Driver driver = new Driver();
				driver.setDriverID(rs.getString("driverID"));
				driver.setPassword(rs.getString("D_passWord"));
				driver.setFirstName(rs.getString("D_firstName"));
				driver.setLastName(rs.getString("D_lastName"));
				driver.setBirthDate(rs.getDate("D_birthDate"));
				driver.setEmail(rs.getString("D_email"));
				driver.setGender(rs.getString("D_gender"));
				driver.setPhoneNumber(rs.getString("D_phoneNumber"));
				driver.setLicense(rs.getString("D_license"));
				drivers.put(rs.getString("driverID"), driver);
			}
			return drivers;
		} catch (Exception e) {
			throw e;
		}
	}

	public void deleteUser(String userID,int userType) throws SQLException {
		PreparedStatement ps ;
		if (userType==2)
			ps= connection.prepareStatement("DELETE FROM customer WHERE customerID = ?");
		else
			ps= connection.prepareStatement("DELETE FROM driver WHERE driverID = ?");
		
		ps.setString(1, userID);
		ps.executeUpdate();
	}

	public Driver getDriver(String driverID) throws Exception {
		Driver driver = new Driver();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM driver WHERE driverID = ?");
			ps.setString(1, driverID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			driver.setDriverID(rs.getString("driverID"));
			driver.setPassword(rs.getString("D_passWord"));
			driver.setFirstName(rs.getString("D_firstName"));
			driver.setLastName(rs.getString("D_lastName"));
			driver.setBirthDate(rs.getDate("D_birthDate"));
			driver.setEmail(rs.getString("D_email"));
			driver.setGender(rs.getString("D_gender"));
			driver.setPhoneNumber(rs.getString("D_phoneNumber"));
			driver.setLicense(rs.getString("D_license"));
			driver.setRegID(rs.getString("regID"));
			return driver;
		} catch (Exception e) {
			throw e;
		}
	}

	public Customer getCustomer(String customerID) throws Exception {
		 Customer customer = new Customer();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer WHERE customerID = ?");
			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();
			rs.next();
			customer.setCustomerID(rs.getString("customerID"));
			customer.setPassword(rs.getString("Cust_passWord"));
			customer.setFirstName(rs.getString("Cust_firstName"));
			customer.setLastName(rs.getString("Cust_lastName"));
			customer.setBirthDate(rs.getDate("Cust_birthDate"));
			customer.setEmail(rs.getString("Cust_email"));
			customer.setGender(rs.getString("Cust_gender"));
			customer.setPhoneNumber(rs.getString("Cust_phoneNumber"));
			customer.setRegID(rs.getString("regID"));
			return customer;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int insertCustomer(Customer customer) throws Exception {
		PreparedStatement ps= connection.prepareStatement("INSERT INTO `customer`(`customerID`,`Cust_firstName`, "
				+ "`Cust_lastName`, `Cust_passWord`, `Cust_phoneNumber`, `Cust_birthDate`, `Cust_gender`,"
				+ " `Cust_email`, regID) VALUES (?,?,?,?,?,?,?,?,?)");
		ps.setString(1, customer.getCustomerID());
		ps.setString(2, customer.getFirstName());
		ps.setString(3, customer.getLastName());
		ps.setString(4, customer.getPassword());
		ps.setString(5, customer.getPhoneNumber());
		ps.setDate(6, new Date(customer.getBirthDate().getTime()));
		ps.setString(7, customer.getGender());
		ps.setString(8, customer.getEmail());
		ps.setString(9, "null");
		int i = ps.executeUpdate();
		if (i > 0)
			resultValue = 200;
		else
			resultValue = 404;

		return resultValue;
	}

	public int insertDriver(Driver driver) throws Exception {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `driver`(`driverID`,`D_firstName`, `D_lastName`,"
				+ " `D_passWord`, `D_phoneNumber`, `D_birthDate`, `D_gender`, `D_email`, "
				+ "`D_license`,regID) VALUES (?,?,?,?,?,?,?,?,?,?)");
		ps.setString(1, driver.getDriverID());
		ps.setString(2, driver.getFirstName());
		ps.setString(3, driver.getLastName());
		ps.setString(4, driver.getPassword());
		ps.setString(5, driver.getPhoneNumber());
		ps.setDate(6, new Date(driver.getBirthDate().getTime()));
		ps.setString(7, driver.getGender());
		ps.setString(8, driver.getEmail());
		ps.setString(9, driver.getLicense());
		ps.setString(10, "null");
		int i = ps.executeUpdate();
		if (i > 0)
			resultValue = 200;
		else
			resultValue = 404;

		return resultValue;
	}

	public Driver updateDriver(Driver driver) throws Exception {
		PreparedStatement ps = connection.prepareStatement("UPDATE `driver` SET `D_firstName`=?,`D_lastName`=?,"
				+ " `D_phoneNumber`=?,`D_birthDate`=?,`D_gender`=?,`D_email`=?,`D_license`=?,regID=? WHERE `driverID`=?");
		ps.setString(1, driver.getFirstName());
		ps.setString(2, driver.getLastName());
		ps.setString(3, driver.getPhoneNumber());
		ps.setDate(4, new Date(driver.getBirthDate().getTime()));
		ps.setString(5, driver.getGender());
		ps.setString(6, driver.getEmail());
		ps.setString(7, driver.getLicense());
		ps.setString(8, driver.getRegID());
		ps.setString(9, driver.getDriverID());
		ps.executeUpdate();
		return driver;
	}

	public Customer updateCustomer(Customer customer) throws Exception {
		PreparedStatement ps = connection.prepareStatement("UPDATE `customer` SET `Cust_firstName`=?,`Cust_lastName`=?,"
				+ "`Cust_phoneNumber`=?,`Cust_birthDate`=?,`Cust_gender`=?,"
				+ "`Cust_email`=?, regID=? WHERE `customerID`=?");
		ps.setString(1, customer.getFirstName());
		ps.setString(2, customer.getLastName());
		ps.setString(3, customer.getPhoneNumber());
		ps.setDate(4, new Date(customer.getBirthDate().getTime()));
		ps.setString(5, customer.getGender());
		ps.setString(6, customer.getEmail());
		ps.setString(7, customer.getRegID());
		ps.setString(8, customer.getCustomerID());
		ps.executeUpdate();
		
		return customer;
	}

    public void setRegIdNull(String userID,int userType) throws Exception {
    	PreparedStatement ps;
    	if (userType==2) 
    		ps = connection.prepareStatement("UPDATE `customer` SET `regID`=? where customerID=?");
    	else ps=connection.prepareStatement("UPDATE `driver` SET `regID`=? where driverID=?");
    	ps.setString(1, "null");
		ps.setString(2,userID );
		ps.executeUpdate();
	}
	
	public ArrayList<Trip> getAllTrips() throws Exception {
		ArrayList<Trip> trips = new ArrayList<Trip>();
		PreparedStatement ps = connection.prepareStatement("select * from trip");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Trip trip = new Trip();
			trip.setTripID(rs.getInt("tripID"));
			trip.setDepartureLocation(this.getLocation(rs.getInt("trip_departureLocation")));
			trip.setArrivalLocation(getLocation(rs
					.getInt("trip_arrivalLocation")));
			trip.setDepartureDate(rs.getTimestamp("trip_departureDate"));
			trip.setArrivalDate(rs.getTimestamp("trip_arrivalDate"));
			trip.setReservedSeats(rs.getInt("trip_reservedSeats"));
			trip.setUnitPrice(rs.getDouble("trip_unitPrice"));
			trip.setTripCar(getCar(rs.getInt("carID")));
			trips.add(trip);
		}
		return trips;
	}

	public ArrayList<Trip> getTrips(int departure, int arrival, java.util.Date dtDeparture) throws Exception {
		ArrayList<Trip> trips = new ArrayList<Trip>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM trip "
				+ "WHERE trip_departureLocation= ? and  trip_arrivalLocation= ? and trip_departureDate between ? and ? ");
		
		ps.setInt(1, departure);
		ps.setInt(2, arrival);
		Timestamp timestamp=new Timestamp(dtDeparture.getTime());
		ps.setTimestamp(3, timestamp);
		timestamp.setTime(timestamp.getTime()+duration);
		ps.setTimestamp(4,timestamp );
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Trip trip = new Trip();
			trip.setTripID(rs.getInt("tripID"));
			trip.setDepartureLocation(getLocation(rs
					.getInt("trip_departureLocation")));
			trip.setArrivalLocation(getLocation(rs
					.getInt("trip_arrivalLocation")));
			trip.setDepartureDate(rs.getTimestamp("trip_departureDate"));
			trip.setArrivalDate(rs.getTimestamp("trip_arrivalDate"));
			trip.setReservedSeats(rs.getInt("trip_reservedSeats"));
			trip.setUnitPrice(rs.getDouble("trip_unitPrice"));
			trip.setTripCar(getCar(rs.getInt("carID")));
			trips.add(trip);
		}
		return trips;
	}

	public Trip getTrip(int tripID) throws Exception {
		Trip trip = new Trip();

		PreparedStatement ps = connection.prepareStatement("SELECT * FROM trip WHERE tripID=?");
		ps.setInt(1, tripID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		trip.setTripID(tripID);
		trip.setDepartureLocation(getLocation(rs.getInt("trip_departureLocation")));
		trip.setArrivalLocation(getLocation(rs.getInt("trip_arrivalLocation")));
		trip.setDepartureDate(rs.getTimestamp("trip_departureDate"));
		trip.setArrivalDate(rs.getTimestamp("trip_arrivalDate"));
		trip.setReservedSeats(rs.getInt("trip_reservedSeats"));
		trip.setUnitPrice(rs.getDouble("trip_unitPrice"));
		trip.setTripCar(getCar(rs.getInt("carID")));
		
	
		return trip;
	}

	public int insertTrip(Trip trip) throws Exception {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `trip`(`trip_departureLocation`, `trip_arrivalLocation`,"
				+ " `trip_departureDate`, `trip_arrivalDate`, `trip_reservedSeats`, `trip_unitPrice`, `carID`) VALUES (?,?,?,?,?,?,?)");
		ps.setInt(1, trip.getDepartureLocation().getLocationID());
		ps.setInt(2, trip.getArrivalLocation().getLocationID());
		ps.setTimestamp(3, new Timestamp( trip.getDepartureDate().getTime()));
		ps.setTimestamp(4, new Timestamp(trip.getArrivalDate().getTime()));
		ps.setInt(5, trip.getReservedSeats());
		ps.setDouble(6, trip.getUnitPrice());
		ps.setInt(7, trip.getTripCar().getCarID());
		int i = ps.executeUpdate();
		if (i > 0)
			resultValue = 200;
		else
			resultValue = 404;

		return resultValue;
	}

	public void deleteTrip(int tripID) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM trip WHERE tripID = ?");
		ps.setInt(1, tripID);
		ps.executeUpdate();
	}
	
	public ArrayList<Reservation> getReservations(String driverID)throws Exception {
		ArrayList<Reservation> reservationList=new ArrayList<Reservation>();
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM reservation "
				+ "WHERE  res_status='waiting' and tripID in (SELECT tripID FROM trip WHERE carID in "
				+ "(SELECT carID from car where driverID = ?) )");
		ps.setString(1,driverID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Reservation reservation=new Reservation();
			reservation.setTrip(getTrip(rs.getInt("tripID")));
			reservation.setCustomer(getCustomer(rs.getString("customerID")));
			reservation.setStatus(rs.getString("res_status"));
			reservation.setNbrSeats(rs.getInt("res_seat_nbr"));
			reservation.setLat(rs.getFloat("res_latitude"));
			reservation.setLng(rs.getFloat("res_longitude"));
			reservationList.add(reservation);
		}
		
		return reservationList;
	}

	public Reservation getReservations(int tripID,String customerID)throws Exception {
		
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM reservation where tripID=? and customerID=?");
		ps.setInt(1, tripID);
		ps.setString(2,customerID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Reservation reservation=new Reservation();
		reservation.setTrip(getTrip(rs.getInt("tripID")));
		reservation.setCustomer(getCustomer(rs.getString("customerID")));
		reservation.setStatus(rs.getString("res_status"));
		reservation.setNbrSeats(rs.getInt("res_seat_nbr"));
		reservation.setLat(rs.getFloat("res_latitude"));
		reservation.setLng(rs.getFloat("res_longitude"));
		
		return reservation;
	}

	public int insertReservation(Reservation reservation){
		try{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO `reservation` (`tripID`, `customerID`,"
				+ " `res_status`, `res_seat_nbr`,`res_latitude`, `res_longitude`)"
				+ " VALUES (?, ?, ?, ?, ?, ?)");
			ps.setInt(1,reservation.getTrip().getTripID());
			ps.setString(2,reservation.getCustomer().getCustomerID() );		
			ps.setString(3, reservation.getStatus());
			ps.setInt(4, reservation.getNbrSeats());
			ps.setFloat(5, reservation.getLat());
			ps.setFloat(6, reservation.getLng());
		
			int i = ps.executeUpdate();
			if (i > 0)
				resultValue = 200;
			else
				resultValue = 404;
		}catch(Exception e){
			
		}

		return resultValue;
	}
	
	public Car getCar(int carID) throws Exception {
		Car car = new Car();
		PreparedStatement ps = connection
				.prepareStatement("select * from car where carID = ?");
		ps.setInt(1, carID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		car.setCarID(carID);
		car.setCarSeats(rs.getInt("car_seats_number"));
		car.setCarType(rs.getString("car_type"));
		car.setCarColor(rs.getString("car_color"));
		car.setCarDriver(getDriver(rs.getString("driverID")));
		return car;
	}
		
	public ArrayList<Car> getCars(String driverID) throws Exception {
		ArrayList<Car> carList=new ArrayList<Car>();
		
		PreparedStatement ps = connection
				.prepareStatement("select * from car where driverID = ?");
		ps.setString(1, driverID);
		ResultSet rs = ps.executeQuery();
		while (rs.next()){
			Car car = new Car();
			car.setCarID(rs.getInt("carID"));
			car.setCarSeats(rs.getInt("car_seats_number"));
			car.setCarType(rs.getString("car_type"));
			car.setCarColor(rs.getString("car_color"));
			car.setCarDriver(getDriver(rs.getString("driverID")));
			carList.add(car);
	}
		return carList;
	}

	public int insertCar(Car car) throws Exception{
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `car`(`car_type`, `car_color`, `car_seats_number`, `driverID`)"
				+ " VALUES (?,?,?,?)");
		ps.setString(1,car.getCarType());
		ps.setString(2, car.getCarColor());
		ps.setInt(3, car.getCarSeats());
		ps.setString(4, car.getCarDriver().getDriverID());
		int i = ps.executeUpdate();
		if (i > 0)
			resultValue = 200;
		else
			resultValue = 404;

		return resultValue;
	}
	
	public L0cation getLocation(int locationID) throws Exception {
		L0cation location = new L0cation();
		try{
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM location where locationID =?");
		ps.setInt(1, locationID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		location.setLocationID(rs.getInt("locationID"));
		location.setName(rs.getString("location_name"));
		location.setLat(rs.getFloat("location_lat"));
		location.setLng(rs.getFloat("location_lng"));
		return location;
		}
		catch(Exception e){
			throw e;
		}
		
	}

	public int insertLocation(L0cation location) throws Exception {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `location`(`location_name`, `location_lat`, `location_lng`)"
				+ " VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, location.getName());
		ps.setFloat(2, location.getLat());
		ps.setFloat(3, location.getLng());
		int i = ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		
		if (i > 0){
			resultValue = 200;
			if(rs.next())
	        {
	           resultValue = rs.getInt(1);
	        }
		}
		else
			resultValue = 404;

		return resultValue;
	}

	public ArrayList<L0cation> getLocations()throws Exception{
		ArrayList<L0cation> locationList=new ArrayList<>();
		try{
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM location ");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			L0cation location = new L0cation();
			location.setLocationID(rs.getInt("locationID"));
			location.setName(rs.getString("location_name"));
			location.setLat(rs.getFloat("location_lat"));
			location.setLng(rs.getFloat("location_lng"));
			locationList.add(location);
		}
		}
		catch(Exception e){
			throw e;
		}
		
		return locationList;
		
	}

	public ArrayList<Message> getMessages(String userID,int userType) {
		//userType = 1 if the user is a driver or equal 2 if the user is a customer
		ArrayList<Message> messages=new ArrayList<>();
		try{
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM message where userID=?");
		ps.setString(1, userID);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Message message = new Message();
			message.setMessageID(rs.getInt("messageID"));
			message.setUserID(rs.getString("userID"));
			if(userType==2)
				message.setRegID(getCustomer(message.getUserID()).getRegID());
			else message.setRegID(getDriver(message.getUserID()).getRegID());
			message.setContent("From "+message.getUserID(), rs.getString("content"));
			messages.add(message);
			deleteMessage(message.getMessageID());
		}
		}
		catch(Exception e){
			System.out.print( e.getMessage());
		}
		return messages;
	}
	
	public int insertMessage(Message message) throws Exception {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO `message`(`userID`, `content`)"
				+ " VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, message.getUserID());
		ps.setString(2, message.getContent());
		int i = ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		
		if (i > 0){
			resultValue = 200;
			if(rs.next())
	        {
	           resultValue = rs.getInt(1);
	        }
		}
		else
			resultValue = 404;

		return resultValue;
	}
	
	public void deleteMessage(int messageID) throws Exception{
		PreparedStatement ps = connection.prepareStatement("DELETE FROM message WHERE messageID = ?");
		ps.setInt(1, messageID);
		ps.executeUpdate();
	}

	public void updateReservation(Reservation reservation) throws Exception{
		PreparedStatement ps = connection.prepareStatement("UPDATE reservation SET res_status=? WHERE tripID=? and customerID=?");
		ps.setString(1, reservation.getStatus());
		ps.setInt(2, reservation.getTrip().getTripID());
		ps.setString(3, reservation.getCustomer().getCustomerID());
		
		ps.executeUpdate();
		
	}
	public void updateTrip(int tripID,int seats)throws Exception {
		PreparedStatement ps= connection.prepareStatement("update trip set  "
				+ "trip_reservedSeats=trip_reservedSeats+? where tripID=?");
		ps.setInt(1, seats);
		ps.setInt(2, tripID);
		ps.executeUpdate();
	}

	public boolean getEmail() throws Exception  {
		PreparedStatement ps= connection.prepareStatement("SELECT * FROM driver WHERE driverID = ? ");
		ResultSet rs= ps.executeQuery();
		if(rs.next())
			return true;
		else 
			return false;	

	}

	public ArrayList<Trip> getTrips(String driverID) throws Exception {
		PreparedStatement ps= connection.prepareStatement("SELECT * FROM trip where carID in "	
	                                     + "(SELECT carID from car where driverID = ?) ");
		ps.setString(1, driverID);
		ResultSet rs=ps.executeQuery();
		ArrayList<Trip> tripList= new ArrayList<Trip>();
		while (rs.next()){
			Trip trip=new Trip();
			trip.setTripID(rs.getInt("tripID"));
			trip.setDepartureLocation(getLocation(rs.getInt("trip_departureLocation")));
			trip.setArrivalLocation(getLocation(rs.getInt("trip_arrivalLocation")));
			trip.setDepartureDate(rs.getTimestamp("trip_departureDate"));
			trip.setArrivalDate(rs.getTimestamp("trip_arrivalDate"));
			trip.setReservedSeats(rs.getInt("trip_reservedSeats"));
			trip.setUnitPrice(rs.getDouble("trip_unitPrice"));
			trip.setTripCar(getCar(rs.getInt("carID")));
			tripList.add(trip);					
		}
		
		return tripList;
	}

}
