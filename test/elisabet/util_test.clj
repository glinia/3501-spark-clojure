(ns elisabet.util-test
  (:use midje.sweet)
  (:require [elisabet.util :refer :all]))

(fact "between"
  (fact "succeeds properly."
    (between 0 -1 1) => truthy)
  (fact "fails properly."
    (between 0 1 2) => falsey))
