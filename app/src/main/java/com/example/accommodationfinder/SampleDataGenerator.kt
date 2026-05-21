package com.example.accommodationfinder

import android.content.Context
import com.example.accommodationfinder.data.Listing
import com.example.accommodationfinder.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SampleDataGenerator {

    suspend fun populateDatabase(context: Context) = withContext(Dispatchers.IO) {
        val db = AppDatabase.getDatabase(context)

        val existingUsers = db.userDao().getUserByEmail("landlord1@example.com")
        if (existingUsers != null) return@withContext

        val landlords = listOf(
            User(101, "landlord1@example.com", "password123", "John Landlord", "71234567"),
            User(102, "landlord2@example.com", "password123", "Mary Properties", "72345678"),
            User(103, "landlord3@example.com", "password123", "David Homes", "73456789"),
            User(104, "landlord4@example.com", "password123", "Sarah Rentals", "74567890"),
            User(105, "landlord5@example.com", "password123", "Michael Apartments", "75678901"),
        )

        landlords.forEach { db.userDao().insert(it) }

        val listings = listOf(

            Listing(0,"Cozy Studio Apartment","Beautiful studio with modern furnishings. Utilities included. Secure gated compound.",
                1500.0,"Gaborone","Studio","WiFi,AC,Kitchen,Laundry","2026-06-01",depositAmount = 750.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, CCTV",false,"Route 1, Route 5",2.5),

            Listing(0, "1-Bedroom Modern Apartment","Modern 1-bedroom in Phase 2. Fully furnished, WiFi, AC. Near shops.",
                2500.0,"Phase 2","1-Bedroom","WiFi,AC,Kitchen,Balcony","2026-06-15",1000.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Guard, Gate",true,"Route 2, Route 8",1.8),

            Listing(0, "2-Bedroom Luxury Apartment","Spacious 2-bedroom with parking. Security includes guard and CCTV.",
                3500.0,"Phase 2","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking","2026-06-01",1500.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, Gate, CCTV, Alarm",false,"Route 1, Route 5",2.0),

            Listing(0, "3-Bedroom House","Family home with yard and security wall. Near UB.",
                4500.0,"Tlokweng","3-Bedroom","WiFi,AC,Kitchen,Yard,Laundry","2026-07-01",4500.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate, Wall",false,"Route 12, Route 3",4.5),

            Listing(0, "Studio with Kitchenette","Compact studio with kitchenette and en-suite.",
                1800.0,"Gaborone","Studio","WiFi,Kitchenette,Bathroom","2026-05-15",1800.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",true,"Route 1, Route 10",3.2),

            Listing(0, "Bedsitter Near UB","Student-friendly bedsitter close to UB.",
                1200.0,"UB Area","Bedsitter","Bathroom,Kitchen shared","2026-06-01",500.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 6, Route 7",0.5),

            Listing(0,"1-Bedroom in Block 6","Affordable apartment in Block 6.",
                2000.0,"Block 6","1-Bedroom","WiFi,AC,Kitchen","2026-06-20",1000.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",false,"Route 4, Route 9",3.8),

            Listing(0,"2-Bedroom Mogoditshane","Spacious family home in quiet area.",
                3000.0,"Mogoditshane","2-Bedroom","Kitchen,Bathroom,Yard","2026-07-15",600.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 11, Route 12",6.5),

            Listing(0, "Studio Phase 2","Modern studio near shops.",
                2200.0,"Phase 2","Studio","WiFi,AC,Kitchen,Shower","2026-06-05",440.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","CCTV, Gate",false,"Route 2, Route 5",1.5),

            Listing(0, "1-Bedroom Student Flat","Budget flat for students.",
                1600.0,"UB Area","1-Bedroom","WiFi,Kitchen,Bathroom","2026-06-01",320.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 6, Route 8",0.8),

            Listing(0,"2-Bedroom Premium Apartment","High-end 2-bedroom apartment.",
                4000.0,"Gaborone","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking","2026-06-10",800.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate, Alarm",false,"Route 1, Route 5",2.8),

            Listing(0,"Compact Bedsitter","Small budget bedsitter.",
                900.0,"Block 6","Bedsitter","Bathroom,Kitchen shared","2026-05-20",800.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 4, Route 10",4.0),

            Listing(0,"1-Bedroom with Parking","Modern unit with parking.",
                2800.0,"Phase 2","1-Bedroom","WiFi,AC,Kitchen,Parking,Balcony","2026-06-25",600.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard, CCTV",false,"Route 2, Route 8",1.2),

            Listing(0,"3-Bedroom Family Home","Spacious family home.",
                5000.0,"Tlokweng","3-Bedroom","WiFi,AC,Kitchen,Yard,Laundry,Parking","2026-07-01",1000.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Wall, CCTV",false,"Route 12, Route 3",5.0),

            Listing(0,"Studio Near Shops","Convenient studio location.",
                1900.0,"Gaborone","Studio","WiFi,AC,Kitchen,Bathroom","2026-06-08",800.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",true,"Route 1, Route 10",3.5),

            Listing(0,"1-Bedroom Affordable","Affordable living option.",
                2100.0,"Mogoditshane","1-Bedroom","Kitchen,Bathroom,Living area","2026-06-30",400.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 11, Route 12",6.0),

            Listing(0,"2-Bedroom Standard","Standard apartment.",
                3200.0,"Phase 2","2-Bedroom","WiFi,AC,Kitchen,Bathroom","2026-06-12",600.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",false,"Route 2, Route 8",1.8),

            Listing(0,"Budget Studio","Cheap studio option.",
                1300.0,"UB Area","Studio","Bathroom,Basic kitchen","2026-05-25",600.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 6, Route 7",1.2),

            Listing(0,"1-Bedroom Premium","High-quality apartment.",
                3100.0,"Gaborone","1-Bedroom","WiFi,AC,Kitchen,Balcony,Laundry","2026-06-18",600.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Alarm",false,"Route 1, Route 5",2.5),

            Listing(0,"2-Bedroom Budget","Affordable family unit.",
                2600.0,"Block 6","2-Bedroom","Kitchen,Bathroom","2026-07-05",500.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 4, Route 9",4.2),

            Listing(0,"Bedsitter Plus","Upgraded bedsitter.",
                1400.0,"Phase 2","Bedsitter","Bathroom,Kitchen shared,Storage","2026-06-22",800.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",true,"Route 2, Route 8",1.5),

            Listing(0,"Studio Balcony","Studio with balcony view.",
                2400.0,"Gaborone","Studio","WiFi,AC,Kitchen,Balcony,Bathroom","2026-06-03",800.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Included","CCTV, Gate",false,"Route 1, Route 10",3.0),

            Listing(0,"1-Bedroom Cozy","Warm cozy apartment.",
                2300.0,"Mogoditshane","1-Bedroom","WiFi,AC,Kitchen,Bathroom","2026-06-28",600.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 11, Route 12",6.2),

            Listing(0,"2-Bedroom Classic","Classic design apartment.",
                3400.0,"UB Area","2-Bedroom","Kitchen,Bathroom,Living area,Yard","2026-07-10",800.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 6, Route 8",0.7),

            Listing(0,"Studio Executive","Executive studio.",
                2700.0,"Phase 2","Studio","WiFi,AC,Kitchen,Laundry,Gym access","2026-06-09",500.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate",false,"Route 2, Route 5",1.3),

            Listing(0,"1-Bedroom Value","Budget-friendly unit.",
                1900.0,"Block 6","1-Bedroom","WiFi,Kitchen,Bathroom","2026-06-20",800.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 4, Route 10",3.9),

            Listing(0,"2-Bedroom Comfort","Comfortable living space.",
                3700.0,"Gaborone","2-Bedroom","WiFi,AC,Kitchen,Balcony,Laundry,Parking","2026-06-14",700.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Alarm",false,"Route 1, Route 5",2.3),

            Listing(0,"Bedsitter Economy","Student budget option.",
                1000.0,"UB Area","Bedsitter","Bathroom,Kitchen shared","2026-05-30",600.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 6, Route 7",0.4),

            Listing(0,"Studio Deluxe","Luxury studio.",
                2900.0,"Phase 2","Studio","WiFi,AC,Kitchen,Laundry,Bathroom,Parking","2026-06-26",800.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate",false,"Route 2, Route 8",1.1),

            Listing(0,"1-Bedroom Trendy","Modern trendy design.",
                2600.0,"Mogoditshane","1-Bedroom","WiFi,AC,Kitchen,Balcony,Laundry","2026-07-08",500.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 11, Route 12",6.3),

            Listing(0,"2-Bedroom Extra","Extra spacious unit.",
                4100.0,"Gaborone","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking,Balcony","2026-06-19",800.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Alarm",false,"Route 1, Route 5",2.6),

            Listing(0,"Studio Compact","Compact smart design.",
                1700.0,"Block 6","Studio","WiFi,Kitchen,Bathroom","2026-06-24",900.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 4, Route 9",4.1),

            Listing(0,"1-Bedroom Spacious","Bright spacious unit.",
                2450.0,"UB Area","1-Bedroom","WiFi,AC,Kitchen,Bathroom,Balcony","2026-06-11",900.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",false,"Route 6, Route 8",0.9),

            Listing(0,"2-Bedroom Elite","Luxury elite apartment.",
                4300.0,"Phase 2","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking,Gym,Pool","2026-06-27",800.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate, Alarm",false,"Route 2, Route 5",1.0),

            Listing(0,"Bedsitter Standard","Normal bedsitter.",
                1100.0,"Mogoditshane","Bedsitter","Bathroom,Kitchen shared,Storage","2026-06-16",900.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 11, Route 12",5.9),

            Listing(0,"Studio Bright","Bright studio.",
                2050.0,"Gaborone","Studio","WiFi,AC,Kitchen,Bathroom","2026-06-21",400.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 1, Route 10",3.1),

            Listing(0,"1-Bedroom Modern","Updated modern unit.",
                2550.0,"Block 6","1-Bedroom","WiFi,AC,Kitchen,Bathroom,Balcony","2026-06-30",500.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",false,"Route 4, Route 9",4.3),

            Listing(0,"2-Bedroom Ready","Move-in ready home.",
                3300.0,"UB Area","2-Bedroom","WiFi,AC,Kitchen,Laundry,Bathroom","2026-07-02",600.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 6, Route 8",0.6),

            Listing(0,"Studio Sweet","Small sweet studio.",
                1550.0,"Phase 2","Studio","WiFi,Kitchen,Bathroom","2026-06-07",300.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 2, Route 8",1.4),

            Listing(0,"1-Bedroom Happy","Comfortable warm unit.",
                2350.0,"Mogoditshane","1-Bedroom","WiFi,AC,Kitchen,Bathroom","2026-07-04",700.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 11, Route 12",6.1),

            Listing(0,"2-Bedroom Enjoy","Enjoyable living space.",
                3850.0,"Gaborone","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking,Balcony","2026-06-23",700.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate",false,"Route 1, Route 5",2.4),

            Listing(0,"Bedsitter Lite","Minimalist bedsitter.",
                950.0,"Block 6","Bedsitter","Bathroom,Kitchen shared","2026-06-17",900.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 4, Route 10",4.0),

            Listing(0,"Studio Zen","Peaceful studio.",
                2150.0,"UB Area","Studio","WiFi,AC,Kitchen,Bathroom,Balcony","2026-06-13",400.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 6, Route 7",0.5),

            Listing(0,"1-Bedroom Perfect","Perfect lifestyle unit.",
                2700.0,"Phase 2","1-Bedroom","WiFi,AC,Kitchen,Laundry,Bathroom,Balcony","2026-07-06",500.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, Gate, CCTV",false,"Route 2, Route 5",1.2),

            Listing(0,"2-Bedroom Dream","Dream home apartment.",
                4200.0,"Mogoditshane","2-Bedroom","WiFi,AC,Kitchen,Laundry,Parking,Yard","2026-07-12",800.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Wall, CCTV",false,"Route 11, Route 12",6.4),

            Listing(0,"Studio Home","Home-like studio.",
                1850.0,"Gaborone","Studio","WiFi,AC,Kitchen,Bathroom","2026-06-29",900.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",true,"Route 1, Route 10",3.3),

            Listing(0,"1-Bedroom Nice","Nice surroundings unit.",
                2200.0,"Block 6","1-Bedroom","WiFi,Kitchen,Bathroom","2026-06-19",480.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate",false,"Route 4, Route 9",3.7),

            Listing(0,"2-Bedroom Best","Best value apartment.",
                3600.0,"UB Area","2-Bedroom","WiFi,AC,Kitchen,Bathroom,Balcony","2026-07-09",700.0,
                101,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 6, Route 8",0.8),

            Listing(0,"Bedsitter Plus+","Upgraded bedsitter plus.",
                1350.0,"Phase 2","Bedsitter","Bathroom,Kitchen shared,Storage,WiFi","2026-06-02",700.0,
                102,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate, Guard",true,"Route 2, Route 8",1.6),

            Listing(0,"Studio Relax","Relaxing studio.",
                2300.0,"Mogoditshane","Studio","WiFi,AC,Kitchen,Bathroom","2026-06-25",600.0,
                103,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Gate",false,"Route 11, Route 12",6.0),

            Listing(0,"1-Bedroom Plus","Extra storage unit.",
                2750.0,"Gaborone","1-Bedroom","WiFi,AC,Kitchen,Laundry,Bathroom,Balcony","2026-07-01",5500.0,
                104,imageResId = HouseImageProvider.getRandomImage(),
                "Included","Guard, CCTV, Gate",false,"Route 1, Route 5",2.2),

            Listing(0,"2-Bedroom Living","Comfortable living apartment.",
                3550.0,"Block 6","2-Bedroom","WiFi,AC,Kitchen,Bathroom","2026-07-03",700.0,
                105,imageResId = HouseImageProvider.getRandomImage(),
                "Separate","Gate, Guard",false,"Route 4, Route 9",4.2)
        )

        listings.forEach { db.listingDao().insert(it) }
    }
}