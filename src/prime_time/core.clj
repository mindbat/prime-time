(ns prime-time.core)

(defn factor?
  "Returns true if n is a factor of x"
  [x n]
  (= 0 (mod x n)))

(defn prime?
  "Checks x against a given list of primes. Returns true if x is also prime"
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
