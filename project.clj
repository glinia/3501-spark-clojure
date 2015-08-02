(defproject elisabet "0.3.0-SNAPSHOT"
  :description "Clojure implementation of Turkey code for 2014-2015 FRC 3501"
  :url "https://github.com/glinia/3501-spark-clojure"
  :license {:name "GNU GPL v3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.namespace "0.2.11"]]
  :aot :all
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :resource-paths ["resources/*"])
