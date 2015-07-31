(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot))
(require '[elisabet.constants  :as const]
         '[elisabet.drivetrain :as drivetrain]
         '[elisabet.joystick   :as joystick]
         '[elisabet.toggle     :as toggle]
         '[elisabet.util       :as util])


;;; Initializers

;; joystick nonsense

(defn init-joysticks
  "Initialize joysticks and joystick-related stuff."
  []
  (util/log "initing joysticks")

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
  "Initialize drivetrain."
  []
  (util/log "initing drivetrain")

  (def base (drivetrain/make-drivetrain)))

;; method declaration
(declare drive)


;;; FIRST methods

;; Gotta name FIRST uses in camelCase rather than kabob-case cause Java
;; TEST WITH the method prefix "-" just added. See if it works.

(defn -robotInit
  "Run when the robot is powered on."
  []
  (util/log "running robotInit")
  (init-joysticks)
  (init-drivetrain))

(defn -teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  (util/log "running teleopPeriodic")
  (drive))


;;; Helpers

(defn- drive
  "Drive the robot!"
  []
  (util/log (str "right y: " (.getY right-stick)))
  (drivetrain/drive base
                    (.getY right-stick)
                    (if (joystick/get-one right-stick 3 4 5 6)
                      0
                      (.getTwist right-stick))))
