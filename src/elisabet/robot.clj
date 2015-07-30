(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot))
(require '[elisabet.constants :as const]
         '[elisabet.drivetrain :as drivetrain]
         '[elisabet.joystick :as joystick]
         '[elisabet.toggle :as toggle])


;;; Initializers

;; joystick nonsense

(defn init-joysticks
  []
  (def left-toggle  (toggle.))
  (def right-toggle (toggle.))

  (def left-stick  (joystick/make-joystick const/LEFT_JOYSTICK_PORT))
  (def right-stick (joystick/make-joystick const/RIGHT_JOYSTICK_PORT))

  (defmacro left-stick-timer
    []
    '[left-stick left-toggle])

  (defmacro right-stick-timer
    []
    '[right-stick right-toggle]))

;; drivetrain & mechanisms

(defn init-drivetrain
  []
  (def base (drivetrain/make-drivetrain)))

;; method declaration
(declare drive)


;;; FIRST methods

;; Gotta name FIRST uses in camelCase rather than kabob-case cause Java

(defn robotInit
  "Run when the robot is powered on."
  []
  (init-joysticks)
  (init-drivetrain))

(defn teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  (drive))


;;; Helpers

(defn- drive
  "Drive the robot!"
  []
  (drivetrain/drive base
                    (.getY right-stick)
                    (if (joystick/get-one right-stick 3 4 5 6)
                      0
                      (.getTwist right-stick))))
