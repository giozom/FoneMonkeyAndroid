***
### Using FoneMonkey libraries to automate a sample android app (in this case a fonemonkey app)
*** 
### Updated:Dec 8, 2011 
### Status: Work Completed and Working
### Issues: Currently having some issues with adb that should not be a problem
***
#### Useful Command to install the app manually, in your terminal Android SDK path i.e. ~/android-sdk/platform-tools/$ run: 
     ./adb install ~/FoneMonkeyAndroid/workspace/fonemonkey_testapp/bin/fonemonkey_testapp.apk
***
### Getting Started
#### Register @  Gorilla and download FoneMonkey and follow the instructions
     http://www.gorillalogic.com/fonemonkey4android.
#### You'll need at least  Eclipse 3.7 and the FoneMonkey console which comes with the downloadable zip file
#### Note 1: Make sure you setup the fonemonkey_testapp project and run it in the emulator and then launch the console.
#### Note 2: GIT cloning this repo and running it will not be enough cause you'll need that FoneMonkey console to get it all working
***
### Introduction
####To use FoneMonkey, you add FoneMonkey to your Android application build then use the FoneMonkey Console to record and playback user interface interactions.The console is a standalone desktop application that can connect to applications running in the Android emulator/actual device connected via USB or reachable by IP address. You can also insert verification steps into your recorded scripts, and generate ready-to-run JUnit tests that can be extended and run as part of workspace or continuous integration builds.
    Android APP -> FoneMonkey Console -> Generated XUnits Test 
***
