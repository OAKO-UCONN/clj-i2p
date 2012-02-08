(ns clj-i2p.interceptor-util)

(defn compose-interceptors [interceptor1 interceptor2]
  (fn [action arg] (interceptor2 #(interceptor1 action %) arg)))

(defn run-interceptors [interceptors function arg]
  (if (empty? interceptors)
    (function arg)
    ((reduce compose-interceptors interceptors) function arg)))