(ns AOC-2016-7.core (:require [clojure.string :as str]) (:use [clojure.test]))

(time (->>
    (slurp "addresses.txt" :encoding "utf-8")
    clojure.string/split-lines
    (remove (partial re-find #"\[\w*(\w)((?:(?!\1))\w)(\2)(\1)\w*\]"))
    (filter (partial re-find #"(\w)((?:(?!\1))\w)(\2)(\1)"))
    count
    (str "Answer 1: ")
    println))
    
(time (->>
    (slurp "addresses.txt" :encoding "utf-8")
    clojure.string/split-lines
    (filter #(or (re-find #"\[\w*(\w)((?:(?!\1))\w)(\1)\w*\].*\2\1\2" %1)  ;; This gives too many hits
        (re-find #"(\w)((?:(?!\1))\w)(\1).*\[\w*\2\1\2\w*]" %1)))
    count
    (str "Answer 2: ")
    println))
