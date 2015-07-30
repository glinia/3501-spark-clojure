(ns elisabet.robot
  (:gen-class
   :extends edu.wpi.first.wpilibj.IterativeRobot
   :use [elisabet.constants :as const]
        [elisabet.drivetrain :as drivetrain]
        [elisabet.joystick :as joystick]
        [elisabet.arm :as arm]
        [elisabet.claw :as claw]))

;;; Gotta name FIRST uses in camelCase rather than snake-case cause Java

(def left-stick (joystick.))

(defn robotInit
  "Run when the robot is powered on."
  []
  (drive)
  (claw/actuate claw/state))

(defn teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  (buttons-pressed)
  (drive)
  (claw/actuate claw))

(defn- drive
  "Drive the robot!"
  (drivetrain/drive (.getY joystick) (if (joystick/get-one 3 4 5 6)
                                    0
                                    (.getTwist joystick))))

(defn- buttons-pressed
  "Perform actions based on which buttons are pressed."
  []

  (if (= claw/state (const/State :FREE))
    (if (joystick/get 1)
      (claw/close)
      (claw/open)))

  (if (joystick/get-toggle-button 2)
    (claw/toggle-state)))
