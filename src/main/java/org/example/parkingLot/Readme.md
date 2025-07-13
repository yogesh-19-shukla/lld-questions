# 🚗 Parking Lot Management System – LLD Design & Code

This project implements a **Parking Lot Management System** in Java, designed for interviews and real-world scenarios. It covers multiple floors, vehicle types, dynamic ticketing, and fee calculation — all using best-practice design patterns.

---

## ✅ Key Features

* Park and unpark vehicles
* Dynamic ticket generation
* Fee calculation using time-based logic
* Support for multiple floors and spot types
* Designed using SOLID principles and key design patterns

---

## 🧠 Design Patterns Used

### 1. **Singleton Pattern**

Used in `ParkingLotManager` to ensure a single global manager controls parking.

### 2. **Strategy Pattern**

Used for fee calculation — easily switchable between hourly, daily, or custom rate calculators (`FeeCalculationStrategy`).

### 3. **Factory-Like Instantiation**

Spot objects (`BikeSpot`, `CarSpot`, `TruckSpot`) simulate a factory behavior for creating different spot types.

---

## 📦 System Components

### ✅ Vehicle

Represents a user vehicle. Has:

* License plate
* VehicleType (CAR, BIKE, TRUCK)

### ✅ ParkingSpot (abstract)

Parent class for:

* `BikeSpot`
* `CarSpot`
* `TruckSpot`

Each spot has:

* Spot ID
* Type
* Occupancy status

### ✅ Ticket

Issued when vehicle is parked. Stores:

* Vehicle
* Spot
* Entry time (can be real or simulated for testing)

### ✅ FeeCalculationStrategy

Used to calculate parking fees. Current logic:

* ₹20 per hour (rounded up)

Supports test mode to simulate variable durations (1–5 hrs) using `Random`.

### ✅ ParkingFloor

* Each floor contains a list of `ParkingSpot`
* Can return a free spot of a required type

### ✅ ParkingLotManager (Singleton)

* Manages all parking floors
* Stores active tickets
* Parks/unparks vehicles
* Calculates fee

---

## 🔁 Flow Summary

1. Create `ParkingLevel` with desired number and types of spots
2. Add levels to `ParkingLotManager`
3. When a user parks a vehicle:

    * Find first free spot of that type
    * Mark it occupied
    * Generate a `Ticket` and store it
4. When a user unparks:

    * Retrieve ticket from map
    * Calculate duration and fee
    * Free the spot

---

## 💡 Simulating Parking Duration

Instead of waiting for hours:

* Use `Random` to simulate entry time 1–5 hours ago:

```java
long entryTime = System.currentTimeMillis() - hoursAgo * 60 * 60 * 1000;
```

* This ensures real-time fee testing without waiting

---

## 🧪 Sample Output

```
Parked at level 1, spot C1
Unparked vehicle KA01AB1234. Fee: ₹60.0
```

---

## 🧰 How to Extend

* Add reservations or pre-booking
* Add JSON persistence or DB layer
* Add real-time dashboard with Observer pattern
* Support EV or handicapped spots using Decorator pattern
* Implement level full notifications

---

