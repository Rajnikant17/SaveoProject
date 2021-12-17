# saveo

Library used :

1 Navigation component
2 Hilt Dependency injection
3 Data binding
4 LiveData
5 ViewBinding
6 Glide
7 Retrofit
8 Coroutine

Used MVVM architecture.

There are two modules in this project , "App" and "MyApiServicesModule" . "App" is the default module and a separate
module is being created for storing the class related to Api Services.
Navigation component is being used to achieve single activity app . There are Two Fragments in the App , First fragment shows the list of Movie list
and the second fragment shows the details of the Movie.

Here i have used a free third party api of "OMDB" for getting the movie list and it's details . "abc" is used as a keyword for getting the
all movies starting with "abc" , pagingation is done upto 10 pages and for getting the details of the movie , "imdbID" is being used. Two api's
are being called in the app , one for listing and other for details.