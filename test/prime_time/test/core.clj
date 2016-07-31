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

(deftest t-generate-times
  ;; generate first 10 primes
  (let [primes (generate-primes 10)
        expected {:1 3
                  :2 6
                  :3 9
                  :5 15
                  :7 21
                  :11 33
                  :13 39
                  :17 51
                  :19 57
                  :23 69}]
    ;; generate a times row for 3
    (is (= expected
           (generate-times primes 3)))))
