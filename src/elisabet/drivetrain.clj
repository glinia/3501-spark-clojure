(ns elisabet.drivetrain
  (:gen-class
   :use elisabet.constants))

(import (edu.wpi.first.wpilibj CANJaguar
                               RobotDrive))


(def drivetrain RobotDrive.
  (CANJaguar. FRONT_LEFT_ADDRESS)
  (CANJaguar. FRONT_RIGHT_ADDRESS)
  (CANJaguar. REAR_LEFT_ADDRESS)
  (CANJaugar. REAR_RIGHT_ADDRESS))

(defn drive
  "Drive using input from joysticks."
  [drivetrain forward twist]
  (let [forward (filter-noise twist)
        twist (filter-noise twist)]
    (.arcadeDrive drivetrain
                  (* MAX_DRIVE_SPEED (adjust forward))
                  (* MAX_DRIVE_SPEED (adjust twist))
                  false)))

(defn drive-raw
  "Drive using without any adjustment."
  [drivetrain forward twist]
  (.arcadeDrive drivetrain forward twist false))

(defn stop
  "Stops the robot's driving."
  []
  (drive-raw 0 0))

(defn- filter-noise
  "Ensures that the input is large enough to be recognized."
  [input]
  (if (< (Math/abs input) MIN_DRIVE_JOYSTICK_INPUT)
    0
    input))

(defn- adjust
  "Outputs the average of `x` and `sqrt(x)'"
  [x]
  (/ (+ x (* (Math/signum x) (Math/sqrt (Math/abs x)))) 2))
