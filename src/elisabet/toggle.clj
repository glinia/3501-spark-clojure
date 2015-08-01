(ns elisabet.toggle
  (:gen-class
   :name toggle
   :state state
   :init init
   :methods [[addTimeout [Number] void]
             [getTimeout [Number] edu.wpi.first.wpilibj.Timer]
             [hasTimeLeft [Number] Boolean]
             [hasTimeLeft [Number Number] Boolean]]
   :constructors {[] []})
  (:require (elisabet [constants :as const]))
  (:import edu.wpi.first.wpilibj.Timer))

(defn -init
  "Initialize with empty map."
  []
  [[] (atom {})])

(defn- -insertTimeout
  "Insert timeout for button into map."
  [this button timer]
  (reset! (.state this) (merge @(.state this) {button timer})))

(defn -addTimeout
  "Adds new timeout / refreshes existing timeout."
  [this button]
  (let [timer (Timer.)]
    (.start timer)
    (.insertTimeout this button timer)))

(defn -getTimeout
  "Get timer for button, or nil if nonexistent."
  [this button]
  (@(.state this) button))

(defn -hasTimeLeft
  "Returns whether or not there is time left in the button toggle."
  ([this button]
   (.hasTimeLeft this button const/TOGGLE_TIME))
  ([this button seconds]
   (let [timer (.getTimeout this button)]
     (and timer (< (.get timer) seconds)))))
