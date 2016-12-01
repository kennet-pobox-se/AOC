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

         



