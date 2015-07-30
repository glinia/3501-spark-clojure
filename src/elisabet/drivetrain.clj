(ns elisabet.drivetrain
  (:gen-class
   :name drivetrain))
(require '[elisabet.constants :as const])

(import (edu.wpi.first.wpilibj CANJaguar
                               RobotDrive))

;; method declaration
(declare drive drive-raw stop filter-noise scale adjust)

;; begin
(defn make-drivetrain
  []
  (RobotDrive.
   (CANJaguar. const/FRONT_LEFT_ADDRESS)
   (CANJaguar. const/FRONT_RIGHT_ADDRESS)
   (CANJaguar. const/REAR_LEFT_ADDRESS)
   (CANJaguar. const/REAR_RIGHT_ADDRESS)))

(defn drive
  "Drive using input from joysticks."
  [base forward twist]
  (let [forward (filter-noise twist)
        twist (filter-noise twist)]
    (.arcadeDrive base (scale forward) (scale twist) false)))

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
  (/ (+ x (* (Math/signum x) (Math/sqrt (Math/abs x)))) 2))
