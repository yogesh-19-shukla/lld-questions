Core entities

Car: id (license plate), make, model, year, type, pricePerDay, status.

Customer: id, name, contact, license no.

Reservation: id, carId, customerId, startDate, endDate, price, status.

Repositories: thread-safe in-memory stores for cars, customers, reservations.

SearchService: search by type, price, availability over date range.

ReservationService:

create / modify / cancel reservations

per-car locking (ReentrantLock) to avoid double booking

Payment (Strategy): simple stub (credit card) to simulate processing.

Concurrency model

A lock per car (e.g., "KA01AB1234" → ReentrantLock).

When creating/modifying a reservation, acquire the car’s lock, validate availability (no date overlaps with existing ACTIVE reservations), then persist.

For modifications that switch to a different car, lock both cars in deterministic order (by carId) to avoid deadlock.

Date overlap rule

Two intervals [aStart, aEnd] and [bStart, bEnd] overlap if:

!(aEnd < bStart || bEnd < aStart)