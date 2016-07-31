(ns prime-time.test.core
  (:require [clojure.test :refer :all]
            [prime-time.core :refer :all]))

(deftest t-generate-primes
  ;; generate the first 3 primes
  (let [expected [1 2 3]]
    (is (= expected
           (generate-primes 3))))
  ;; generate the first 10 primes
  (let [expected [1 2 3 5 7 11 13 17 19 23]]
    (is (= expected
           (generate-primes 10))))
  ;; generate the first 100 primes without oome
  (is (= 100
         (count (generate-primes 100)))))
