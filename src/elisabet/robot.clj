(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot)
  (:require (elisabet [arm        :as arm]
                      [auton      :as auton]
                      [constants  :as const]
                      [drivetrain :as drivetrain]
                      [joystick   :as joystick]
                      [toggle     :as toggle]
                      [util       :as util])))

(import edu.wpi.first.wpilibj.Timer)

;;; Initializers

;; joystick nonsense

(defn- init-joysticks
  "Initialize joysticks and joystick-related stuff."
  []
  (util/log "initing joysticks")

  (def left-toggle  (toggle.))
  (def right-toggle (toggle.))

  (def left-stick  (joystick/make-joystick const/LEFT_JOYSTICK_PORT))
  (def right-stick (joystick/make-joystick const/RIGHT_JOYSTICK_PORT))

  (def left-stick-timer  [left-stick left-toggle])
  (def right-stick-timer [right-stick right-toggle]))

;; drivetrain & mechanisms

(defn- init-drivetrain
  "Initialize drivetrain."
  []
  (util/log "initing drivetrain")

  (def base (drivetrain/make-drivetrain)))

(defn init-arm
  "Initialize arm winches."
  []
  (util/log "initing arm")

  (def arm (arm/make-arm)))

;; auton

(defn- init-auton
  "Initialize timer for auton."
  []
  (util/log "initing auton")

  (def auton-timer (Timer.)))

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
  (init-drivetrain)) ; (init-arm) (init-auton)

(defn -teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  []
  (util/log "running teleopPeriodic")
  (drive)) ; (move-arm)

(defn -autonomousInit
  "Start auton timer."
  []
  (util/log "running autonomousInit")
  (.reset auton-timer)
  (.start auton-timer))

(defn -autonomousPeriodic
  "Run during autonomous."
  []
  (util/log "running autonomousPeriodic")
  (auton/drive-over-step auton-timer base))


;;; Helpers

(defn- drive
  "Drive the robot!"
  []
  (util/log (str "right y: " (.getY right-stick)))
  (let [disable-twist (joystick/get-one right-stick 3 4 5 6)]
    (drivetrain/drive base
                      (.getY right-stick)
                      (if disable-twist
                        0
                        (.getTwist right-stick)))))

(defn- move-arm
  "Moves the arm based on input."
  []
  (util/log "running move-arm")
  (let [arm-speed    (arm/speed-from-joystick (- (.getY left-stick)))
        adjust-speed (const/ARM_ADJUST_SPEED)
        left-button #(joystick/getb left-stick %)
        coeff        (if (left-button 1) 0.2 1)]
    (cond
      (left-button 7)  (arm/move-left arm adjust-speed)
      (left-button 6)  (arm/move-left arm (- adjust-speed))
      (left-button 11) (arm/move-right arm adjust-speed)
      (left-button 10) (arm/move-right arm (- adjust-speed))
      :else            (arm/move arm (* coeff arm-speed)))))
