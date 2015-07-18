(ns elisabet.joystick
  (:gen-class
   :extends edu.wpi.first.wpilibj.Joystick
   :use elisabet.constants))

(def toggle (new toggle)) ; make this somehow...

(defn is-pov
  "Returns whether the POV is currently at the given location."
  [direction]
  (= direction (.getPov this)))

(defn get
  "Returns whether the button is currently pressed."
  [button]
  (.getRawButton this button))

(defn get-toggle-button
  "Returns whether the button is pressable AND if it's currently pressed."
  [button]
  (let [pressed (and (get button) (not (has-time-left button)))]
    (if pressed
      (add-timeout button)))) ; what's going on with toggle?? AHH

(defn get-timed-action
  "Check if there is time remaining in the button press timer."
  [button secs]
  (get-toggle-button button)
  (has-time-left button secs))

(defn get-one
  "Returns true if at least one of the buttons is pressed."
  [& buttons]
  (some get buttons))

(defn get-one-timed
  "Returns true if at least one of the buttons is on its timer."
  [secs & buttons]
  (some #(get-timed-action % secs) buttons))
