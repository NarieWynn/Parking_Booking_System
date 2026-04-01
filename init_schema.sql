CREATE DATABASE parking_system;
USE parking_system;

CREATE TABLE `User`
(
  user_id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) NOT NULL,
  user_status VARCHAR(20) NOT NULL DEFAULT 'active',
  first_name VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE (email)
);

CREATE TABLE Driver
(
  license_number VARCHAR(20) NOT NULL,
  driver_id INT NOT NULL,
  PRIMARY KEY (driver_id),
  FOREIGN KEY (driver_id) REFERENCES `User`(user_id),
  UNIQUE (license_number)
);

CREATE TABLE Parking_Owner
(
  business_name VARCHAR(100) NOT NULL,
  business_contact VARCHAR(20),
  owner_id INT NOT NULL,
  PRIMARY KEY (owner_id),
  FOREIGN KEY (owner_id) REFERENCES `User`(user_id)
);

CREATE TABLE Driver_Vehicle
(
  vehicle_id INT NOT NULL AUTO_INCREMENT,
  driver_id INT NOT NULL,
  vehicle_info VARCHAR(100) NOT NULL,
  PRIMARY KEY (vehicle_id),
  FOREIGN KEY (driver_id) REFERENCES Driver(driver_id),
  UNIQUE (vehicle_info, driver_id)
);

CREATE TABLE Parking_Lot
(
  lot_id INT NOT NULL AUTO_INCREMENT,
  lot_name VARCHAR(100) NOT NULL,
  total_slot INT NOT NULL,
  province VARCHAR(50) NOT NULL,
  district VARCHAR(50) NOT NULL,
  street VARCHAR(100) NOT NULL,
  owner_id INT NOT NULL,
  PRIMARY KEY (lot_id),
  FOREIGN KEY (owner_id) REFERENCES Parking_Owner(owner_id)
);

CREATE TABLE Parking_Slot
(
  slot_id INT NOT NULL AUTO_INCREMENT,
  slot_number VARCHAR(10) NOT NULL,
  type VARCHAR(20) NOT NULL,
  is_available CHAR(1) NOT NULL DEFAULT 'Y',
  lot_id INT NOT NULL,
  PRIMARY KEY (slot_id),
  FOREIGN KEY (lot_id) REFERENCES Parking_Lot(lot_id),
  CHECK (is_available IN ('Y', 'N'))
);

CREATE TABLE Pricing_Rule
(
  pricing_id INT NOT NULL AUTO_INCREMENT,
  hourly_rate FLOAT NOT NULL,
  lot_id INT NOT NULL,
  PRIMARY KEY (pricing_id),
  FOREIGN KEY (lot_id) REFERENCES Parking_Lot(lot_id)
);

CREATE TABLE Time_Slot_Price
(
  time_slot_id INT NOT NULL AUTO_INCREMENT,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  multiplier FLOAT NOT NULL DEFAULT 1.0,
  type VARCHAR(20) NOT NULL,
  pricing_id INT NOT NULL,
  PRIMARY KEY (time_slot_id),
  FOREIGN KEY (pricing_id) REFERENCES Pricing_Rule(pricing_id),
  UNIQUE (start_time, type, pricing_id)
);

CREATE TABLE Booking
(
  booking_id INT NOT NULL AUTO_INCREMENT,
  start_time DATETIME NOT NULL,
  end_time DATETIME NOT NULL,
  total_price FLOAT NOT NULL,
  booking_status VARCHAR(20) NOT NULL DEFAULT 'pending',
  driver_id INT NOT NULL,
  slot_id INT NOT NULL,
  PRIMARY KEY (booking_id),
  FOREIGN KEY (driver_id) REFERENCES Driver(driver_id),
  FOREIGN KEY (slot_id) REFERENCES Parking_Slot(slot_id)
);

CREATE TABLE Payment
(
  payment_id INT NOT NULL AUTO_INCREMENT,
  payment_method VARCHAR(50) NOT NULL,
  payment_status VARCHAR(20) NOT NULL DEFAULT 'pending',
  amount FLOAT NOT NULL,
  booking_id INT NOT NULL,
  PRIMARY KEY (payment_id),
  FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
);

CREATE TABLE Notification
(
  notification_id INT NOT NULL AUTO_INCREMENT,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  notification_status VARCHAR(20) NOT NULL DEFAULT 'unread',
  message VARCHAR(500),
  user_id INT NOT NULL,
  booking_id INT NOT NULL,
  PRIMARY KEY (notification_id),
  FOREIGN KEY (user_id) REFERENCES `User`(user_id),
  FOREIGN KEY (booking_id) REFERENCES Booking(booking_id)
);

