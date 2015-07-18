(ns elisabet.claw
  (:gen-class
   :use elisabet.constants))

(import (edu.wpi.first.wpilibj Compressor
                               DoubleSolenoid))


(def claw (DoubleSolenoid. CLAW_FORWARD_CHANNEL CLAW_REVERSE_CHANNEL))
(def compressor (Compressor.))

(def state (atom (:FREE State)))

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
  (.set claw OPEN))

(defn close
  "Close claw."
  [claw]
  (.set claw CLOSE))

(defn actuate
  "Actuate claw based on its current state."
  [claw]
  (if (= (:CLOSED State) state)
    (close claw)))

(defn toggle-state
  "Change state."
  []
  (case state
    (:CLOSED State) (reset! state (:FREE State))
    (:FREE State) (reset! state (:CLOSED State))))
