(ns elisabet.claw
  (:gen-class
   :name claw
   :state state
   :init init
   :constructors {[] []})
  (:require (elisabet [constants :as const]))
  (:import (edu.wpi.first.wpilibj Compressor
                                  DoubleSolenoid)))

(defn -init
  "Initialize claw, compressor, state."
  []
  [[] (atom {:claw       (DoubleSolenoid. const/CLAW_FORWARD_CHANNEL
                                          const/CLAW_REVERSE_CHANNEL)
             :compressor (Compressor.)
             :state      :FREE})])

(declare turn-off turn-on open closed actuate toggle-state set-state get-state)

(defn turn-off
  "Turn compressor off."
  [claw]
  (.stop (:compressor @(.state claw))))

(defn turn-on
  "Turn compressor on."
  [claw]
  (.start (:compressor @(.state claw))))

(defn open
  "Open claw."
  [claw]
  (.set (:claw @(.state claw)) `~const/OPEN))

(defn close
  "Close claw."
  [claw]
  (.set (:claw @(.state claw)) `~const/CLOSE))

(defn actuate
  "Actuate claw based on state."
  [claw]
  (when (= :CLOSED (get-state claw))
    (close claw)))

(defn toggle-state
  "Toggle state."
  [claw]
  (case (get-state claw)
    :FREE   (set-state claw :CLOSED)
    :CLOSED (set-state claw :FREE)))

(defn set-state
  "Set claw control state to value."
  [claw state]
  (reset! (.state claw) (merge @(.state claw) {:state state})))

(defn get-state
  "Get claw control state."
  [claw]
  (:state @(.state claw)))
