(ns elisabet.arm-test
  (:use midje.sweet)
  (:require [elisabet.arm :refer :all]))

(fact "speed-from-joystick"
  (fact "gives 0 for small and 0 inputs."
    (speed-from-joystick -0.01) => 0
    (speed-from-joystick 0)     => 0
    (speed-from-joystick 0.01)  => 0)
  (fact "gives `value' for normal inputs `value'"
    (speed-from-joystick -1)   => -1
    (speed-from-joystick -0.4) => -0.4
    (speed-from-joystick 0.4)  => 0.4
    (speed-from-joystick 1)    => 1)
  (fact "throws an exception for invalid input."
    (speed-from-joystick -2) => (throws AssertionError)
    (speed-from-joystick 2)  => (throws AssertionError)))
