TMDB-Movie-Listing-Master
===============

You can download the source from this [Bitbucket link](/https://github.com/droidwithme/kotlin-mvp-dagger2-rxjava-clean_architecture-master/archive/master.zip)

Basic details
----------
```
IDE: Android Studio ver:3.5

Language: Kotlin. 1.3.5

Build: Gradle.

Start Time: 20/01/2020
```
Architecture: Clean Architecture [Best Explanation](https://www.raywenderlich.com/3595916-clean-architecture-tutorial-for-android-getting-started)

StackOverflow Profile
====
[Check StackOverflow Profile](/https://stackoverflow.com/users/4745607/devendra-singh)


Technologies Used
----
1. Kotlin
2. Dagger
3. RxJava/Android
4. Constraint Layout
5. Retrofit
6. Gson
7. Youtube.Jar
8. Lottie
9. Androidx
10. Caching
11. Pagination
12. BottomBar


Features Included
----
1. Get Configuration
2. Get Popular Movies
3. Get Top Trending Movies
4. Get Trailers
5. Play Trailers
6. Get Movies Details
7. Search Movies
8. Offline from Local Cache. (if once user loaded the data, next time if no internet, data will be fetched from cache.)

Features Not Implemented
----
1. Unit Test cases (Due to time constraint and office work. all dependencies are set up anyway.)


Extra add on
----
1. Multi variants
2. Auto APK naming
3. Build system

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
         |kotlin->cloudwalker->demo->presentation
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
```
Domain
-----
```
__src
    |main
         |java->cloudwalker->demo->domain
            exceptions
                        Exceptions wrapper classes
            executors
                        Interfaces to implement in domain fetcher
            fetchers
                        Domain fetcher/Use Cases an interface that data layers implements
            interactors
                        Base interactor class
            modules
                configuration
                        beans -> Query and Response class
                        interactor -> Interactor's implementions that invokes domain fetcher's method that are implemented on data layer
                moviesdetails
                        beans
                        interactor
                nowplaying
                        beans
                        interactor
                popularmovies
                        beans
                        interactor
                searchmovie
                        beans
                        interactor.
```

Data 
-------
```
__src
    |main
         |kotlin->cloudwalker->demo->data
         executors
                    To do operation on separate threading simoultanously
         fetchers
                    Domain fetcher's implementation e.g use cases getMovies etc
         webservices
                    retrofit, httpclient api interfaces.
         prod //Not implemented
                    these are build flavours to execute different functionlity on different build flavours
         sandbox//Not implemented
         test//Not implemented
         uat//Not implemented 
```
