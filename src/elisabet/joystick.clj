(ns elisabet.joystick
  (:gen-class
   :name joystick))
(require '[elisabet.constants :as const])

(import edu.wpi.first.wpilibj.Joystick)

(defn make-joystick
  "Makes a joystick."
  [port]
  (Joystick. port))

(defn is-pov
  "Returns whether the POV is currently at the given location."
  [joystick direction]
  (= direction (.getPOV joystick)))

(defn get
  "Returns whether the button is currently pressed."
  [joystick button]
  (.getRawButton joystick button))

(defn get-toggle-button
  "Returns whether the button is pressable AND if it's currently pressed."
  [[joystick toggle] button]
  (let [pressed (and (get joystick button) (not (.hasTimeLeft toggle button)))]
    (if pressed
      (.addTimeout toggle button))))

(defn get-timed-action
  "Check if there is time remaining in the button press timer."
  [[joystick toggle] button secs]
  (get-toggle-button [joystick toggle] button)
  (.hasTimeLeft toggle button secs))

(defn get-one
  "Returns true if at least one of the buttons is pressed."
  [joystick & buttons]
  (some get joystick buttons))

(defn get-one-timed
  "Returns true if at least one of the buttons is on its timer."
  [[joystick toggle] secs & buttons]
  (some #(get-timed-action [joystick toggle] % secs) buttons))
