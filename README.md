Voyage 🚗
Greetings! This is an entry point to the Voyage repository. This is a ride management program that sends alerts/notifications to passengers regarding when they will reach their destination. In this way, we have developed our final project for the CS212 Object Oriented Programming course (BESE-16B) taken by the two of us.

Have you ever fallen asleep on the bus/van, etc. and missed your destination? That was the problem that we tried to solve with Voyange. Voyage will provide you with an alarm before arriving at your destination, using a simulated GPS proximity system and a timed alarm.

🛠️ The Team
Sheharyar Khalid - Front-end Developer. Built the front end completely using JavaFX and FXML. Responsible for creating:

- The Startup Screens (Animated).
- The Login Forms (By Role).
- The Dashboard displays for Drivers & Passengers.
- Updating the User Interface.

Muhammad Faizan Asif - Core Programming Developer. Developed the OOP Model Layer, the Interface contracts and the background services (ProximityEngine, FareCalculator, AlarmService, RideSession).

✨ Features
Customized Dashboards for Drivers and Passengers Based on Role. The Login Page will check the formatting for CNIC, Phone Numbers and Emails before you will be able to log in to the Application.

Simulated GPS System Use & Fare Based on Haversine Formula. As we were unable to set up the actual working API for GPS, we simulated our GPS Events using JavaFX Timeline. We used the Haversine Formula for calculating distance to the destination.
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
