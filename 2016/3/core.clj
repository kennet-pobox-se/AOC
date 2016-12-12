(ns AOC-2016-3.core (:require [clojure.string]))

(time (->>
       (slurp "triangles.txt" :encoding "utf-8")
       clojure.string/split-lines
       (map (fn [line] (read-string (str "[" line "]"))))
       (filter (fn [lengths] (let [m (apply max lengths) s (apply + lengths)] (> s (+ m m)))))
       count
       (str "Answer 1: ")
       println))

(time (->>
       (slurp "triangles.txt" :encoding "utf-8")
       clojure.string/split-lines
       (map (fn [line] (read-string (str "[" line "]"))))
       (apply concat)
       ((fn [v] (concat (take-nth 3 v) (take-nth 3 (rest v)) (take-nth 3 (rest (rest v))))))
       (partition 3)
       (filter (fn [lengths] (let [m (apply max lengths) s (apply + lengths)] (> s (+ m m)))))
       count
       (str "Answer 2: ")
       println))