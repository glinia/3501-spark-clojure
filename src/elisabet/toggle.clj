(ns elisabet.toggle
  (:gen-class
   :state state
   :init init)
  (:require [elisabet.constants :as const]))

(import edu.wpi.first.wpilibj.Timer)


(defn- init
  []
  [[] (atom {})])

(defn- timeouts
  [this]
  (.state this))

(defn- add-timeout
  "Adds a k-v button-timer pair to a map"
  [button timer]
  (reset! (timeouts) (merge (timeouts) {button timer})))

(defn add-timeout
  "Adds new timeout / refreshes existing timeout."
  [button]
  (let [timer (Timer.)]
    (.start timer)
    (add-timeout button timer)))

(defn get-timeout
  "Get timer for button, or nil if nonexistent."
  [button]
  ((timeouts) button))

(defn has-time-left
  "Returns whether or not there is time left in the button toggle."
  ([button]
   (has-time-left button const/TOGGLE_TIME))
  ([button seconds]
   (let [timer (get-timeout button)]
     (and timer (< (.get timer) seconds)))))
