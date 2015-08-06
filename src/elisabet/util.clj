(ns elisabet.util
  (:gen-class
   :name util)
  (:require (elisabet [constants :as const])))

(defn log
  "Logs to file with timestamp."
  [message]
  (let [timestamp (-> (System/currentTimeMillis) (/ 1000) int)]
    (spit const/LOG_FILE_PATH
          (str "[" timestamp "] " message "\n")
          :append true)))

(defn between
  "Returns value if value is between a and b, else nil"
  [value a b]
  (if (and (>= value a) (<= value b))
    value
    nil))
