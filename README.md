[![Travis build status](https://travis-ci.org/glinia/3501-spark-clojure.svg?branch=master)](https://travis-ci.org/glinia/3501-spark-clojure)

### What is this?
Clojure code for a simple [FIRST FRC](http://www.usfirst.org/roboticsprograms/frc) robot using the WPILib libraries for robot communication. As Clojure runs on the JVM, we can take advantage of those libraries with ease.

This should be the functional equivalent (ha _ha!_) of [the TURKEY code](https://github.com/glinia/3501-spark).

The name `elisabet` originated from the song [Elisabet](https://www.youtube.com/watch?v=6ZxU9i5EWU4) by Emiliana Torrini.

### Setup

`git clone` the project. Install Leiningen.

Edit the `./deploy` script and change "3501" to your team number.

Add the following to `~/.lein/profiles.clj`:

```clojure
{:user {:plugins [[cider/cider-nrepl "0.10.0-SNAPSHOT"]]
        :dependencies [[org.clojure/tools.nrepl "0.2.7"]]}}
```

### Build and Deploy

```bash
# creates uberjar and uploads to roborio at `roboRIO-3501.local`
./deploy
```

###### IMPORTANT NOTES

- Once the script says the deploy succeeded, you will have to wait 10-30 seconds for the "Robot Code" light on the Driver Station to go green.

### License

This project is licensed under the [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html) so that teams can share their discoveries and experiences in using Clojure with other teams, that everyone may gain. Clojure is a non-standard and unsupported language for the FIRST FRC competition, and as such the more examples the better.

### Why

[![I've just received word that the Emperor has dissolved the MIT computer science program permanently.](http://imgs.xkcd.com/comics/lisp_cycles.png)](https://xkcd.com/297/)
