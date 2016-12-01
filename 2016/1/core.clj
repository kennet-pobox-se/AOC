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

(println (str "Answer 1: " (let [[_ _ x y] end] (+ (abs x) (abs y)))))

  

