(ns AOC-2016-6.core (:require [clojure.string :as str]) (:use [clojure.test]))

(time (->>
    (slurp "input.txt" :encoding "utf-8")
    clojure.string/split-lines
    (apply map list)
    (map frequencies)
    (map (fn [m] (sort-by #(- (val %)) m)))
    (map first)
    (map key)
    (apply str)
    (str "Answer 1: ")
    println))

(time (->>
    (slurp "input.txt" :encoding "utf-8")
    clojure.string/split-lines
    (apply map list)
    (map frequencies)
    (map (fn [m] (sort-by val m)))
    (map first)
    (map key)
    (apply str)
    (str "Answer 2: ")
    println))