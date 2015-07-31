(ns elisabet.util
  (:gen-class
   :name util)
  (:require (elisabet [constants :as const])))

(defn log
  "Logs to file with timestamp."
  [message]
  (spit const/LOG_FILE_PATH
        (str "[" (/ (System/currentTimeMillis) 1000) "] " message)
        :append true))
