(ns prime-time.core
  (:require [doric.core :refer [table]])
  (:gen-class))

(defn factor?
  "Returns true if n is a factor of x"
  [x n]
  (= 0 (mod x n)))

(defn prime?
  "Checks x against a given list of primes.
   Returns true if x is also prime"
  [primes x]
  (not (boolean (seq (filter (partial factor? x) primes)))))

(defn next-prime
  "Given a number, return the next prime after it"
  [primes current]
  (first (filter (partial prime? primes)
                 (iterate inc current))))

(defn generate-primes
  "Return a list of the first num-primes primes"
  [num-primes]
  (loop [primes [2]
         next 2]
    (if (= (- num-primes 1) (count primes))
      (apply conj [1] primes)
      (recur (conj primes (next-prime primes next))
             (inc next)))))

(defn generate-times
  "Given a set of primes and a number, generate a
   map of prime -> (prime * number)"
  [primes x]
  (zipmap (map (comp keyword str) primes)
          (map (partial * x) primes)))

(defn prime-time-table
  "Generate the times table for the first num-primes"
  [num-primes]
  (let [primes (generate-primes num-primes)
        headings (map (comp keyword str) primes)
        rows (map (partial generate-times primes)
                  primes)]
    (table headings rows)))

(defn -main
  [& [num-primes]]
  (if num-primes
    (println (prime-time-table (Integer/parseInt
                                num-primes)))
    (println "You must enter how many primes to display")))
