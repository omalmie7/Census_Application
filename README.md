# Census_Application

This Android application allows users to collect and manage basic census data. Users can register with a password, add personal details, and view a list of collected data entries. The app also offers functionalities to change the app's background color and potentially push data to a cloud database (not implemented in this version).


## Features

-Splash screen with a message "Starting Census App..."

-Secure login with password registration and authentication

-Home screen displaying developer information

-Preferences screen to change the app's background color

-Data screen with a form to collect:

    Name (text)
    Age (number)
    Gender (Male/Female selection)
    Photo (captured using the device camera)
-Ability to save collected data entries locally

-List data screen to view saved entries with:

    Name, age, and gender information
    "Show Photo" button to display the captured image

-Option to push data to a cloud database (not implemented in this version). Upon successful upload, the local data will be cleared.


## Development Environment

-Programming language: Java

-IDE: Android Studio
## Local Data Storage

The application utilizes SharedPreferences to store the user's password and a local database (implementation details not specified) to store collected census data.
## Future Enhancements

-Implementation of cloud storage functionality using Firebase (or a similar service) for data persistence and accessibility across devices.

-Additional data fields can be added to the data collection form based on specific census requirements.

-Improved user interface design and functionalities.
