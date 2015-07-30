(ns elisabet.joystick
  (:gen-class
   :name joystick
   :state state
   :init init
   :extends edu.wpi.first.wpilibj.Joystick
   :constructors {[Integer] [Integer]}
   :use elisabet.toggle))

(def toggle (elisabet.toggle.))

(defn is-pov
  "Returns whether the POV is currently at the given location."
  [direction]
  (= direction (.getPOV this)))

(defn get
  "Returns whether the button is currently pressed."
  [button]
  (.getRawButton this button))

(defn get-toggle-button
  "Returns whether the button is pressable AND if it's currently pressed."
  [button]
  (let [pressed (and (get button) (not (toggle/has-time-left button)))]
    (if pressed
      (toggle/add-timeout button))))

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
