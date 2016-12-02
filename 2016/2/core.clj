(ns AOC-2016-2.core (:require [clojure.string]))

(def instructions (slurp "instructions.txt"))

(def lines (clojure.string/split-lines instructions))

(defn move [[x y] m]
    (case m
        \U [x (max 0 (dec y))]
        \D [x (min 2 (inc y))]
        \L [(max 0 (dec x)) y]
        \R [(min 2 (inc x)) y]))

(defn code [x y]
    (+ 1 (* 3 y) x))

    
