Voyage 🚗
Welcome to the repository for Voyage, a ride management and passenger notification system. This was built as our final project for the CS 212: Object-Oriented Programming course (Class BESE-16B).

Have you ever fallen asleep on a bus or a long car ride and completely missed your stop? That is the exact problem we wanted to solve. Voyage uses a simulated GPS proximity system and timed alarms to wake passengers up right before they reach their destination.

🛠️ The Team
Sheharyar Khalid: Built the entire front-end using JavaFX and FXML. Handled the animated starting screens, role-based login forms, driver/passenger dashboards, and real-time UI updates.

Muhammad Faizan Asif: Handled the core application logic. Built the OOP model layer, interface contracts, and the background services (ProximityEngine, FareCalculator, AlarmService, RideSession).

✨ Features
Role-Based Dashboards: Distinct views for Drivers and Passengers. The login screen checks formatting for CNICs, phone numbers, and emails before letting you in.

Simulated GPS & Fares: Since we couldn't use a live API, we simulated vehicle movement using a JavaFX Timeline. The app uses the Haversine formula to calculate the remaining distance, update the ETA, and calculate dynamic fares.

Proximity & Time Alarms: When the vehicle gets within 500 meters of a passenger's set destination, an audio alarm plays to wake them up. Passengers can also set backup time-based alarms.

Ride Management: Drivers can see the vehicle's capacity, add passengers, and even trigger a manual "wake alert" to all passengers at once.

Smooth UI: Clean scene transitions between the different windows to keep the driver and passenger experiences separated but linked under the hood.

💻 Tech Stack & Setup
Language: Java 23 (Bellsoft Liberica JDK)

GUI: JavaFX & FXML

Build Tool: Gradle 8.13

🧠 OOP Concepts Used
We built this project to heavily utilize what we learned in class:

Inheritance & Encapsulation: Both Driver and Passenger inherit shared traits (like personal info) from a parent User class. We did the same thing with Car inheriting from Vehicle.

Abstraction & Interfaces: The system relies on abstract classes and interfaces like Notifiable and LocationObserver so our background services (like the alarm and proximity engines) can easily communicate with the main application.

🚀 Future Ideas
If we had more time and resources, here is what we would add next:

Real GPS API: Swapping our simulated Timeline for a real map API to get live coordinates and traffic data.

Cloud Database: Hooking it up to Firebase or MySQL to save user accounts, secure logins, and keep ride histories.

Mobile App: Porting this logic over to Android or iOS since a mobile app makes much more sense for people actually traveling.
