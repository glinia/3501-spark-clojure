(defproject elisabet "0.5.1-SNAPSHOT"
  :description "Clojure implementation of Turkey code for 2014-2015 FRC 3501"
  :url "https://github.com/glinia/3501-spark-clojure"
  :license {:name "GNU GPL v3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.namespace "0.2.11"]]

  :aot :all
  :main edu.wpi.first.wpilibj.RobotBase
  :manifest {"Robot-Class" "robot"
             "Class-Path"  "."}
  :filespecs [{:type :paths :paths ["resources/wpilibj"]}]
  :target-path "target/%s"

  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[midje "1.7.0"]]
                   :plugins [[lein-midje "3.1.3"]]}}

  :resource-paths ["resources/*"])
