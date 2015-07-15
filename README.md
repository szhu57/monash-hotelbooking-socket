# monash-hotelbooking-socket
A distributed hotel booking system using socket

This program is a assignment of monash lecture.

## objective

Degin and implementation of a Hotel Booking Broker. A Hotel Booking Broker is an online system that allows users to search for, compare rate, check availability, and make bookings at the hotels conneted to the service.

## Details

+ Al least more than one clients, and more servers(different hotels have different servers)

+ What cities are currently serviced by the system? The response should be a list such as: Melbourne, Sydney, Perth, etc. For a given city, what hotels are currently serviced by the system? The response should be a  list such as: Hilton, Chevron, Regent, Windsor, etc.

+ For a particular hotel, what is the room rate? (For simplicity, you can assume a hotel has oneroom rate, but the different hotels may have different rates)

+ For a particular hotel, does it have a vacancy between given check-in and check-out dates?(For simplicity, you can assume all vacancies and bookings 

+  Book a room at a hotel. Completing the booking request should include the user submitting the following information: check-in date, check-out date, guest’s name, contact (phone or e-mail address), and credit card number. You should decide on appropriate valid values.

+ a typical 3-tier system with presentation, logic and database handled separately.(along with HOOP protocol), the Database which i used id MySql.

## System Architecture

![architecture](/images/architecture.png)
if you want to know more, please refer to the [documentation](http://github.com/szhu57/monash-hotelbooking-socket/documetation.pdf)

##About athor
+ Name: Shuai ZHU
+ Mail: szhu57@outlook.com
+ Weibo:[http://weibo.com/2613558687/profile](http://weibo.com/2613558687/profile)

## Acess protocol
	*HTTP: 'https://github.com/szhu57/monash-hotelbooking-socket.git' 
	*SSH: 'git@github.com:szhu57/monash-hotelbooking-socket.git'

## How to get 
 operation example:$ git clone [http://github.com/szhu57/monash-hotelbooking-socket](http://github.com/szhu57/monash-hotelbooking-socket.git