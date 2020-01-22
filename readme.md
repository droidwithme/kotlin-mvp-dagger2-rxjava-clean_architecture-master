Bitbucket Download
===============
You can download the source from this [Bitbucket link](/https://bitbucket.org/Devendra_/app-cloudwalker-demo/get/cc62a81a8802.zip)

Basic details
----------
```
IDE: Android Studio ver:3.5

Language: Kotlin. 1.3.5

Build: Gradle.
```
Architecture: Clean Architecture [Best Explanation](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)




CodeBase Guide
-----
```
Contains 3 layers
1. Presentation
2. Domain
3. Data
```

Presentation
-----
```
__src

    |libs
        |youtube.jar
    |main
         |kotlin->cloudwalker->demo->presenation
            |main->
                activity
                    Contains main activity, this projects based on single activity, MainActivity
                appflow
                    Contains a class that handles back press events from any screen
                application
                    Contains the Application class. where some common dagger dependencies injected.
                dagger
                    Contains package to provide dependencies though out the application
                exception
                    Contains wrapper class for error handling
                execution
                    Provides main thread
                fragment
                    Base fragment, and fragmentEnum that provides fragments
                presenter
                    Main presenter
                runtime
                    Constraint classes
                view
                    Main View
            ui->
             
               commons
                        Contains two common contracts(Interfaces) used to get movie clicks and movie object
                        and a common data class named Result.
               landing
                        landing screens is second page after splash it downloads the configuration
               moviedetails
                         when user click on see movie details, this page will be loaded and shown the movie details and trailer
               movieshome
                         This fragments host the thee fragments
               moviespopular
                         Popular movies will be listed here.
               nowplaying
                         Top trending movies will be listed
               searchmovie
                         user can search movies 
               splash   
                         Splash screen.
             

