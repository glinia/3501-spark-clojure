(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot))
(:require [elisabet.constants :as const]
          [elisabet.drivetrain :as drivetrain]
          [elisabet.joystick :as joystick]
          [elisabet.arm :as arm]
          [elisabet.claw :as claw]
          [elisabet.toggle :as toggle])

;;; Gotta name FIRST uses in camelCase rather than snake-case cause Java

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
    (if (joystick/get (left-stick) 1)
      (claw/close)
      (claw/open)))

  (if (joystick/get-toggle-button (right-stick-timer) 2)
    (claw/toggle-state)))

;;; joystick nonsense

(def left (toggle.))
(def right (toggle.))

(defmacro left-stick-timer
  []
  (conj (left-stick) left-toggle))

(defmacro right-stick-timer
  []
  (conj (right-stick) right-toggle))

(defn- left-stick
  "Makes a left joystick."
  []
  (joystick/make-joystick const/LEFT_JOYSTICK_PORT))

(defn- right-stick
  "Makes a right joystick."
  []
  (joystick/make-joystick const/RIGHT_JOYSTICK_PORT))
