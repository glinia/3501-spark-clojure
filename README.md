### What is this?
Clojure code for a simple [FIRST FRC](http://www.usfirst.org/roboticsprograms/frc) robot using the WPILib libraries for robot communication. As Clojure runs on the JVM, we can take advantage of those libraries with ease.

### Setup

Add the following to `~/.lein/profiles.clj`:
```clojure
{:user {:plugins [[cider/cider-nrepl "0.10.0-SNAPSHOT"]]
        :dependencies [[org.clojure/tools.nrepl "0.2.7"]]}}
```

Execute the following commands (replace `~/clojure/elisabet` with the path to this repository):
```bash
cp ~/wpilib/java/current/lib/WPILib.jar ~/clojure/elisabet/resources/
cp ~/wpilib/java/current/lib/WPILib-sources.jar ~/clojure/elisabet/resources/
cp ~/wpilib/java/current/lib/NetworkTables.jar ~/clojure/elisabet/resources/
cp ~/wpilib/java/current/lib/NetworkTables-sources.jar ~/clojure/elisabet/resources/
```

### License

This project is licensed under the [GNU GPLv3](https://www.gnu.org/licenses/gpl-3.0.en.html) so that teams can share their discoveries and experiences in using Clojure with other teams, that everyone may gain. Clojure is a non-standard and unsupported language for the FIRST FRC competition, and as such the more examples the better.
