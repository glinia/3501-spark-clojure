[![Travis build status](https://travis-ci.org/glinia/3501-spark-clojure.svg?branch=master)](https://travis-ci.org/glinia/3501-spark-clojure)

### What is this?
Clojure code for a simple [FIRST FRC](http://www.usfirst.org/roboticsprograms/frc) robot using the WPILib libraries for robot communication. As Clojure runs on the JVM, we can take advantage of those libraries with ease.

This should be the functional equivalent (ha _ha!_) of [the TURKEY code](https://github.com/glinia/3501-spark).

The name `elisabet` originated from the song [Elisabet](https://www.youtube.com/watch?v=6ZxU9i5EWU4) by Emiliana Torrini.

### Setup

Add the following to `~/.lein/profiles.clj`:

```clojure
{:user {:plugins [[cider/cider-nrepl "0.10.0-SNAPSHOT"]]
        :dependencies [[org.clojure/tools.nrepl "0.2.7"]]}}
```

### Deploy

```bash
# creates uberjar and uploads to roborio at `roboRIO-3501.local`
./deploy
```

### License

This project is licensed under the [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html) so that teams can share their discoveries and experiences in using Clojure with other teams, that everyone may gain. Clojure is a non-standard and unsupported language for the FIRST FRC competition, and as such the more examples the better.
