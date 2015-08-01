(ns elisabet.auton
  (:gen-class
   :name auton)
  (:require (elisabet [drivetrain :as drivetrain])))

(declare between drive run-for)

(defn drive-over-step
  "Drive over the step into the auton zone."
  [timer base]
  (run-for 2.9
           (cond
             (between (time 0.0) (time 0.2)) (drive base 0.3)
             (between (time 0.2) (time 0.8)) (drive base 0.5)
             (between (time 0.8) (time 1.0)) (drive base 0.25)
             :else                           (drivetrain/stop base))))

(defmacro run-for
  [max-secs & exprs]
  `(let [~'secs     (.get ~'timer)
         ~'max-secs ~max-secs
         ~'between #(between ~'secs %1 %2)
         ~'time    #(* % ~'max-secs)]
     ~@exprs))

(defn- between
  "Returns value if value is between a and b, else nil"
  [value a b]
  (if (and (>= value a) (<= value b))
    value
    nil))

(defn- drive
  "Move forward with a given power"
  [base forward]
  (drivetrain/drive-raw base (- forward) 0))
