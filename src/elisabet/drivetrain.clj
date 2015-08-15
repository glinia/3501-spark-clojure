(ns elisabet.drivetrain
  (:gen-class
   :name drivetrain)
  (:require (elisabet [constants :as const]
                      [util      :as util]))
  (:import (edu.wpi.first.wpilibj CANJaguar
                                  RobotDrive)))

(declare drive drive-raw stop filter-noise scale adjust sign)

(defn make-drivetrain
  []
  (RobotDrive.
   (CANJaguar. const/FRONT_LEFT_ADDRESS)
   (CANJaguar. const/REAR_LEFT_ADDRESS)
   (CANJaguar. const/FRONT_RIGHT_ADDRESS)
   (CANJaguar. const/REAR_RIGHT_ADDRESS)))

(defn drive
  "Drive using input from joysticks."
  [base forward twist]
  (let [forward (filter-noise forward)
        twist   (filter-noise twist)]
    (drive-raw base (scale forward) (scale twist))))

(defn drive-raw
  "Drive using without any adjustment."
  [base forward twist]
  (.arcadeDrive base forward twist false))

(defn stop
  "Stops the robot's driving."
  [base]
  (drive-raw base 0 0))

(defn- filter-noise
  "Ensures that the input is large enough to be recognized."
  [input]
  (assert (util/between input -1 1) "Joystick input must be between -1 and 1.")
  (if (< (Math/abs input) const/MIN_DRIVE_JOYSTICK_INPUT)
    0
    input))

(defn- scale
  "Scales and adjusts input."
  [input]
  (* const/MAX_DRIVE_SPEED (adjust input)))

(defn- adjust
  "Outputs the average of `x` and `sqrt(x)`."
  [x]
  (-> (+ x (* (sign x) (-> x Math/abs Math/sqrt))) (/ 2)))

(defn- sign
  "Outputs -1, 0, 1 depending on sign of n"
  [n]
  (cond
    (< n 0) -1
    (> n 0) 1
    :else 0))
