(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot)
  (:require (elisabet [arm        :as arm]
                      [constants  :as const]
                      [drivetrain :as drivetrain]
                      [joystick   :as joystick]
                      [toggle     :as toggle]
                      [util       :as util])))

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

(defn init-arm
  "Initialize arm winches."
  []
  (util/log "initing arm")

  (def arm (arm/make-arm)))

;; method declaration
(declare drive move-arm)


;;; FIRST methods

;; Gotta name FIRST uses in camelCase rather than kabob-case cause Java
;; TEST WITH the method prefix "-" just added. See if it works.

(defn -robotInit
  "Run when the robot is powered on."
  []
  (util/log "running robotInit")
  (init-joysticks)
  (init-drivetrain)) ; (init-arm)

(defn -teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  (util/log "running teleopPeriodic")
  (drive)) ; (move-arm)


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

(defn- move-arm
  "Moves the arm based on input."
  []
  (util/log "running move-arm")
  (let [arm-speed    (arm/speed-from-joystick (- (.getY left-stick)))
        coeff        (if (joystick/getb left-stick 1) 0.2 1)
        adjust-speed (const/ARM_ADJUST_SPEED)
        left-button #(joystick/getb left-stick %)]
    (cond
      (left-button 7)  (arm/move-left arm adjust-speed)
      (left-button 6)  (arm/move-left arm (- adjust-speed))
      (left-button 11) (arm/move-right arm adjust-speed)
      (left-button 10) (arm/move-right arm (- adjust-speed))
      :else            (arm/move arm (* coeff arm-speed)))))
