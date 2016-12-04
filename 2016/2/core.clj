(ns AOC-2016-2.core (:require [clojure.string]))

(def instructions (slurp "instructions.txt"))

(def lines (clojure.string/split-lines instructions))

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

(def answer-1 (apply str (map code (reductions line [1 1] lines))))


(println (str "Answer 1: " answer-1))


    
