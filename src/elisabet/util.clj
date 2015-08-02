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

;; following two macros adapted from https://gist.github.com/blacktaxi/1760333
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
