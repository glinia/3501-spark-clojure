(ns elisabet.util-test
  (:use midje.sweet)
  (:require [elisabet.util :refer :all]))

(fact "between"
  (fact "works correctly"
    (between 0 -1 1) => truthy)
  (fact "doesn't work correctly"
    (between 0 1 2) => falsey))
