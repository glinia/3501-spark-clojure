(ns elisabet.constants
  (:gen-class
   :name constants))

;;; following two macros adapted from https://gist.github.com/blacktaxi/1760333
(defmacro def-const [const-name const-val]
  `(def
     ~(with-meta const-name
        (assoc (meta const-name) :const true))
     ~const-val))

(defmacro def-consts [bindings]
  `(do
     ~@(map (fn [[const-name const-val]]
              `(def-const ~const-name ~const-val))
            (partition 2 bindings))))

;;;; The Constants.
(def-consts
  [;; util
   LOG_FILE_PATH  "/home/lvuser/FRC3501.log"

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
   CLAW_REVERSE_CHANNEL 1])
