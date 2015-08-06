(ns elisabet.arm
  (:gen-class
   :name arm)
  (:require (elisabet [constants :as const]
                      [util      :as util])))

(import edu.wpi.first.wpilibj.CANJaguar)

(defn make-arm
  []
  {:left  (CANJaguar. const/LEFT_WINCH_ADDRESS)
   :right (CANJaguar. const/RIGHT_WINCH_ADDRESS)})

(defn move
  "Sets arm speed."
  [arm speed]
  (.set (:left arm) (- speed))
  (.set (:right arm) speed))

(defn move-left
  "Move left arm only."
  [arm speed]
  (.set (:left arm) speed)
  (.set (:right arm) 0))

(defn move-right
  "Move right arm only."
  [arm speed]
  (.set (:left arm) 0)
  (.set (:right arm) speed))

(defn speed-from-joystick
  "Ensures that the input is large enough to be recognized."
  [speed]
  (assert (util/between speed -1 1) "Joystick input must be between -1 and 1.")
  (if (< (Math/abs speed) const/MIN_ARM_JOYSTICK_INPUT)
    0
    speed))
