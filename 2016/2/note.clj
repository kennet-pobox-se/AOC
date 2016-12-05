(ns AOC-2016-2.core (:require [clojure.string]))
 
(defn move [[x y] m]
    (case m
        \U [x (max 0 (dec y))]
        \D [x (min 2 (inc y))]
        \L [(max 0 (dec x)) y]
        \R [(min 2 (inc x)) y]))

(defn line [[x y] l]
    (reduce move [x y] l))

(defn code [[x y]]
    (+ y y y x 1))

(->>
    (slurp "instructions.txt")
    clojure.string/split-lines
    (reductions line [1 1])
    rest
    (map code)
    (apply str)
    (str "Answer 1: ")
    println)

;(apply concat (map range (range 5)))
