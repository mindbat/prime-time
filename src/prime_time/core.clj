(ns prime-time.core
  (:require [clojure.string :as str])
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

(defn format-row
  "Prep a row for display by padding each cell to the desired width"
  [width row]
  (->> row
       sort
       (map #(format (str "%" width "d") %))
       (str/join " | ")))

(defn prime-time-table
  "Generate the times table for the first num-primes"
  [num-primes]
  (let [primes (generate-primes num-primes)
        max-width (* 2 (count (str (last primes))))
        rows (map (partial generate-times primes)
                  primes)]
    (->> rows
         (map vals)
         (map (partial format-row max-width))
         (str/join "\n"))))

(defn -main
  [& [num-primes]]
  (if num-primes
    (println (-> num-primes
                 Integer/parseInt
                 prime-time-table
                 (str/replace #"^(\s+)1 \|" "$1   ")))
    (println "You must enter how many primes to display")))
