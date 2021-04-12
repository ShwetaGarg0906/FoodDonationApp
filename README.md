# FoodDonationApp

Introduction 

Food Donation App is to connect the donators with the nearest/appropriate needy person through a social mobile application. This app provides features like fast
and real time donations, OTP sharing for validation of successful donation, notification sent to both donor and NGO when a donation is accepted.

Assumptions Taken 
1. Shelf Life more than 3 days 
2. No of persons >10 
3. Only day donation accepted 
4. pick up time should not be after 11:00p.m. 


List of database used 
1. User Collection - stores donor and ngo details(basically profile that we show to each user)
	1.1 DONOR - collection for donors 
	1.2 NGO - collection for NGOs
	
2. Location  - It stores details related to the pick up location which is entered by the donor during donation creation 

3. Donation - It stores all the details related to one donation 
	1.1 donation key - to uniquely identify a donation
	1.2 DonorKey  - to store the key of donor that has made this donation
	1.3 NGO Key - during donation creation it is set to null by default and is updated to the NGOs key when accepted by that NGO
	1.4 Location - It stores the key of the pickup location 
	
	Future Scope - Ngo and donor can allow to track their current location and validation could be based on NGO and donor's locations and the otp that is currently shared by donor to NGO 
	
4. Food - details related to food 

5. Role - store the role of the user that is currently being logged in the system and rendering the screen based on the role.


List of firebase functionalities used 

1. used  firebase auth api to authenticate user to the app.
2. used firebase real-time database 

#Maintainers 

Shweta Garg 
