(ns elisabet.constants
  (:gen-class))

(import edu.wpi.first.wpilibj.DoubleSolenoid$Value)

;; joystick control
(def ^:const LEFT_JOYSTICK_PORT 0)
(def ^:const RIGHT_JOYSTICK_PORT 1)

(def ^:const TOGGLE_TIME 0.2)
(def ^:const UP 0)
(def ^:const UP_RIGHT 45)
(def ^:const RIGHT 90)
(def ^:const DOWN_RIGHT 135)
(def ^:const DOWN 180)
(def ^:const DOWN_LEFT 225)
(def ^:const LEFT 270)
(def ^:const UP_LEFT 315)
(def ^:const NOT_PRESSED -1)

;; drivetrain
(def ^:const MIN_DRIVE_JOYSTICK_INPUT 0.1)

(def ^:const MAX_DRIVE_SPEED 0.7)

(def ^:const FRONT_LEFT_ADDRESS 4)
(def ^:const FRONT_RIGHT_ADDRESS 5)
(def ^:const REAR_LEFT_ADDRESS 3)
(def ^:const REAR_RIGHT_ADDRESS 6)

;; winch & arm
(def ^:const LEFT_WINCH_ADDRESS 2)
(def ^:const RIGHT_WINCH_ADDRESS 7)

(def ^:const ARM_ADJUST_SPEED 0.3)

(def ^:const MIN_ARM_JOYSTICK_INPUT 0.1)

;; claw
(def ^:const CLAW_FORWARD_CHANNEL 0)
(def ^:const CLAW_REVERSE_CHANNEL 1)

(def ^:const OPEN DoubleSolenoid$Value/kForward)  ; might need to change import
(def ^:const CLOSE DoubleSolenoid$Value/kReverse) ; to just DoubleSolenoid


(def ^:const State {:FREE 0 :CLOSED 1})
