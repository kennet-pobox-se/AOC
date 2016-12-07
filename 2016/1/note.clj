(ns AOC-2016-1.core (:require [clojure.string]))

(def instructions (slurp "instructions.txt"))

(defn turn [m dx dy]
    (case (first m)
     \R [dy (- dx)]
     \L [(- dy) dx]))

(defn dist [m] (int (subs m 1)))

(defn move [[dx dy x y] m]
    (let [[ndx ndy] (turn m dx dy)
          d (dist m)]
        [ndx ndy (+ x (* d ndx)) (+ y (* d ndy))]))

(def end (reduce move [0 1 0 0] (clojure.string/split instructions #", ")))

(defn abs [n] (max n (- n)))

(defn taxicab-distance [x y] (+ (abs x) (abs y)))

(println (str "Answer 1: " (let [[_ _ x y] end] (taxicab-distance x y))))

(defn move-until-back [[dx dy x y visited] m]
    (let [[ndx ndy nx ny] (move [dx dy x y] m)]
        (println visited)
        (if (contains? visited [nx ny])
         (reduced [ndx ndy nx ny visited])
         [ndx ndy nx ny (conj visited [nx ny])])))

(def end-2 (reduce move-until-back [0 1 0 0 #{}] (clojure.string/split instructions #", ")))

(println (str "Answer 2: " (let [[_ _ x y] end-2] (taxicab-distance x y))))

(as-> "instructions.txt" v
    (slurp v) ; Read the text file
    (clojure.string/split v #", ") ; split the instructions to a vector
        ;convert instructions to a lazy sequence of coordinates visited
    (reduce move [0 1 0 0] v)
    (let [[_ _ x y] v] (taxicab-distance x y))
    (str "Answer 1: " v)
    (println v))
    
;(take 10 (map first (iterate (fn [[a b]] (if-let [n (first b)] [(range n) (rest b)])) ['() '(3 7 13 17)])))    