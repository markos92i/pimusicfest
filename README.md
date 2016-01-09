# pimusicfest
This project consist in a REST client-server service based on Jersey+Jackson libraries, over Grizzly Server, and MongoDB as persistence.
Apart from that I've currently implemented an integration with the Spotify API with the purpose of getting Artists info and pics.
It's not included in this code, but I will upload it soon.
Besides that, I'm experimenting with JavaFX to do an app for managing the content.
The service has two modes, secure and insecure. Insecure is pure http, and insecure implements SSL and Certificates on both, client and server.
Both of them use a token based authentication, wich is randomly generated and also expires in 60 minutes from the last access (configurable).
Another feature is pass encoding with BCrypt, using jBCrypt 0.4 library, the code is there but is not yet implemented since I wanted to test other things before it.

An Android app is in development too, I will add more information as soon as I get it working.

-Marcos.
