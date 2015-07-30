(ns elisabet.claw
  (:gen-class
   :use [elisabet.constants :as const]))

(import (edu.wpi.first.wpilibj Compressor
                               DoubleSolenoid))


(def claw (DoubleSolenoid. const/CLAW_FORWARD_CHANNEL const/CLAW_REVERSE_CHANNEL))
(def compressor (Compressor.))

(def state (atom (:FREE const/State)))

(defn turn-off
  "Turn off compressor."
  [compressor]
  (.stop compressor))

(defn turn-on
  "Turn on compressor."
  [compressor]
  (.start compressor))

(defn open
  "Open claw."
  [claw]
  (.set claw const/OPEN))

(defn close
  "Close claw."
  [claw]
  (.set claw const/CLOSE))

(defn actuate
  "Actuate claw based on its current state."
  [claw]
  (if (= (:CLOSED const/State) state)
    (close claw)))

(defn toggle-state
  "Change state."
  []
  (case state
    (:CLOSED const/State) (reset! state (:FREE const/State))
    (:FREE const/State) (reset! state (:CLOSED const/State))))
