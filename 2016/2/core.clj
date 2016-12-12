(ns AOC-2016-2.core (:require [clojure.string]))

(def keypad-1 {
    [0 0] \1 [1 0] \2 [2 0] \3
    [0 1] \4 [1 1] \5 [2 1] \6
    [0 2] \7 [1 2] \8 [2 2] \9
})

(defn move [[[x y :as old-pos] keypad] m]
    (let [new-pos (case m
        \U [x (dec y)]
        \D [x (inc y)]
        \L [(dec x) y]
        \R [(inc x) y])]
        [(if (contains? keypad new-pos) new-pos old-pos) keypad]))

(defn line [[[x y] keypad] l]
    (reduce move [[x y] keypad] l))

(time (->>
    (slurp "instructions.txt")
    clojure.string/split-lines
    (reductions line [[1 1] keypad-1])
    (map first)
    rest
    (map #(get keypad-1 %1))
    (apply str)
    (str "Answer 1: ")
    println))

(def keypad-2 {
                      [2 0] \1
             [1 1] \2 [2 1] \3 [3 1] \4
    [0 2] \5 [1 2] \6 [2 2] \7 [3 2] \8 [4 2] \9
             [1 3] \A [2 3] \B [3 3] \C
                      [2 4] \D
})

(time (->>
    (slurp "instructions.txt")
    clojure.string/split-lines
    (reductions line [[0 2] keypad-2])
    (map first)
    rest
    (map #(get keypad-2 %1))
    (apply str)
    (str "Answer 2: ")
    println))