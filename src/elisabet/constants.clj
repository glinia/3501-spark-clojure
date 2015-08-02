(ns elisabet.constants
  (:gen-class
   :name constants)
  (:require (elisabet [util :as util]))
  (:import edu.wpi.first.wpilibj.DoubleSolenoid$Value))

(util/def-consts
  [;; util
   LOG_FILE_PATH "~/FRC3501.log"

   ;; joystick control
   LEFT_JOYSTICK_PORT  0
   RIGHT_JOYSTICK_PORT 1

   TOGGLE_TIME 0.2

   UP          0
   UP_RIGHT    45
   RIGHT       90
   DOWN_RIGHT  135
   DOWN        180
   DOWN_LEFT   225
   LEFT        270
   UP_LEFT     315
   NOT_PRESSED -1

   ;; drivetrain
   MIN_DRIVE_JOYSTICK_INPUT 0.1

   MAX_DRIVE_SPEED 0.7

   FRONT_LEFT_ADDRESS  4
   FRONT_RIGHT_ADDRESS 5
   REAR_LEFT_ADDRESS   3
   REAR_RIGHT_ADDRESS  6

   ;; winch & arm
   LEFT_WINCH_ADDRESS  2
   RIGHT_WINCH_ADDRESS 7

   ARM_ADJUST_SPEED 0.3

   MIN_ARM_JOYSTICK_INPUT 0.1

   ;; claw
   CLAW_FORWARD_CHANNEL 0
   CLAW_REVERSE_CHANNEL 1

   OPEN  `DoubleSolenoid$Value/kForward
   CLOSE `DoubleSolenoid$Value/kReverse])
