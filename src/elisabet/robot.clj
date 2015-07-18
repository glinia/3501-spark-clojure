(ns elisabet.robot
  (:gen-class
   :extends edu.wpi.first.wpilibj.IterativeRobot
   :use elisabet.constants))

;;; Gotta name FIRST uses in camelCase rather than snake-case cause Java

(defn robotInit
  "Run when the robot is powered on."
  []
  ())

(defn teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  ((let [claw (create-claw)])
   (do
     (buttons-pressed)
     (drive)
     (actuate claw))))
