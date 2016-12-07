(ns AOC-2016-1.core (:require [clojure.string]))

(defn turn [to [dx dy]]
    (case to
     \R [dy (- dx)]
     \L [(- dy) dx]))

(defn dist [m] (int (subs m 1)))

(defn follow [[last-leg facing directions]]
    (if-let [direction (first directions)]
        (let [[dx dy :as new-facing] (turn (first direction) facing)
              d (dist direction)]
            [(loop [leg []
                    [x y] (last last-leg)
                    n d]
                (if (zero? n)
                    leg
                    (let [pos [(+ x dx) (+ y dy)]]
                        (recur (conj leg pos) pos (dec n)))))
            new-facing
            (rest directions)])))

(defn trace [start facing directions]
    (apply concat (take-while some? (map first (iterate follow [(list start) facing directions])))))

(defn abs [n] (max n (- n)))

(defn taxicab-distance [[x y]] (+ (abs x) (abs y)))

(time (->> (clojure.string/split (slurp "instructions.txt") #", ") ; Read the text file into a vector of instructions
    (trace [0 0] [0 1])
    last ; go to the end
    taxicab-distance
    (str "Answer 1: ")
    println))

(time (->> (clojure.string/split (slurp "instructions.txt") #", ") ; Read the text file into a vector of instructions
    (trace [0 0] [0 1])
    (reduce (fn [visited pos] (if (contains? visited pos) (reduced pos) (conj visited pos))) #{}) ; stop at first visited position
    taxicab-distance
    (str "Answer 2: ")
    println))
    