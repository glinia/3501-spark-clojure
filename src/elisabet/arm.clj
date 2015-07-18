(ns elisabet.arm
  (:gen-class
   :use elisabet.constants))

(import edu.wpi.first.wpilibj.CANJaguar)


(def left CANJaguar. LEFT_WINCH_ADDRESS)
(def right CANJaguar. RIGHT_WINCH_ADDRESS)

(defn set
  "Sets arm speed."
  [speed]
  (.set left (- speed))
  (.set right speed))

(defn move-left
  "Move left arm only."
  [speed]
  (.set left speed)
  (.set right 0))

(defn move-right
  "Move right arm only."
  [speed]
  (.set left 0)
  (.set right speed))

(defn get-speed-from-joystick
  "Ensures that the input is large enough to be recognized."
  [speed]
  (if (< (Math/abs speed) MIN_ARM_JOYSTICK_INPUT)
    0
    speed))
