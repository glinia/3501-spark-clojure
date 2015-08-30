(ns elisabet.robot
  (:gen-class
   :name robot
   :extends edu.wpi.first.wpilibj.IterativeRobot)
  (:require (elisabet [arm        :as arm]
                      [auton      :as auton]
                      [claw       :as claw]
                      [constants  :as const]
                      [drivetrain :as drivetrain]
                      [joystick   :as joystick]
                      [toggle     :as toggle]
                      [util       :as util]))
  (:import edu.wpi.first.wpilibj.Timer))

;; joystick nonsense

(defn- init-joysticks
  "Initialize joysticks and joystick-related stuff."
  []
  (def left-toggle  (toggle.))
  (def right-toggle (toggle.))

  (def left-stick  (joystick/make-joystick const/LEFT_JOYSTICK_PORT))
  (def right-stick (joystick/make-joystick const/RIGHT_JOYSTICK_PORT))

  (def left-stick-timer  [left-stick left-toggle])
  (def right-stick-timer [right-stick right-toggle]))

(declare drive move-arm actuate-claw)

;;; FIRST methods

(defn -robotInit
  "Run when the robot is powered on."
  [this]
  (init-joysticks)
  (def base (drivetrain/make-drivetrain))
  (def the-arm (arm/make-arm))
  (def the-claw (claw.))
  (def auton-timer (Timer.)))

(defn -teleopPeriodic
  "Run approx every 20ms to communicate with driver station."
  [this]
  (drive)
  (move-arm)
  (actuate-claw))

(defn -autonomousInit
  "Start auton timer."
  [this]
  (.reset auton-timer)
  (.start auton-timer))

(defn -autonomousPeriodic
  "Run during autonomous."
  [this]
  (auton/drive-over-step auton-timer base))


;;; Helpers

(defn- drive
  "Drive the robot!"
  []
  (let [disable-twist (joystick/get-one right-stick 3 4 5 6)]
    (drivetrain/drive base
                      (.getY right-stick)
                      (if disable-twist
                        0
                        (.getTwist right-stick)))))

(defn- move-arm
  "Moves the arm based on input."
  []
  (let [arm-speed    (arm/speed-from-joystick (- (.getY left-stick)))
        adjust-speed const/ARM_ADJUST_SPEED
        left-button #(joystick/getb left-stick %)
        coeff        (if (left-button 1) 0.2 1)]
    (cond
      (left-button 7)  (arm/move-left  the-arm adjust-speed)
      (left-button 6)  (arm/move-left  the-arm (- adjust-speed))
      (left-button 11) (arm/move-right the-arm adjust-speed)
      (left-button 10) (arm/move-right the-arm (- adjust-speed))
      :else            (arm/move       the-arm (* coeff arm-speed)))))

(defn- actuate-claw
  "Actuates claw based on input."
  []
  (case (claw/get-state the-claw)
    :FREE   (if (joystick/getb right-stick 1)
              (claw/close the-claw)
              (claw/open the-claw))
    :CLOSED (claw/close the-claw))

  (when (joystick/get-toggle-button right-stick-timer 2)
    (claw/toggle-state the-claw))

  (when (joystick/get-one right-stick 11 12)
    (claw/turn-off the-claw))

  (when (joystick/get-one right-stick 7 8)
    (claw/turn-on the-claw)))
