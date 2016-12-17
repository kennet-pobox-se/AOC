(ns AOC-2016-5.core (:require [clojure.string :as str]) (:use [clojure.test]))

(with-test
    (defn byte->nibbles [b]
        (list (bit-shift-right b 4) (bit-and b 15)))
    (is (= [3 2] (byte->nibbles 50))))

(with-test
    (defn bytes->nibbles [bs]
        (interleave (map #(bit-shift-right % 4) bs) (map #(bit-and % 15) bs)))
    (is (= [0 1 2 3] (bytes->nibbles [1 35]))))

(def md5-hasher (System.Security.Cryptography.MD5/Create))

(with-test
    (defn md5 [s]
        (. md5-hasher ComputeHash (. System.Text.Encoding/UTF8 GetBytes s)))
    (is (= [165 169 102 212 109 156 237 255 65 5 70 237 203 80 140 50] (md5 "Kennet"))))

(with-test
    (def hex (apply vector (seq "0123456789abcdef")))
    (is (= \b (hex 11))))

(def door-id "ugkcyxxp")

(defn door-password [id]
    (->> (range)
        (map (partial str door-id))
        (map md5)
        (filter #(zero? (first %)))
        (map rest)
        (filter #(zero? (first %)))
        (map rest)
        (map first)
        (map byte->nibbles)
        (filter #(zero? (first %)))
        (map second)
        (map hex)
        (take 8)
        (apply str)))

(time (->> door-id
       ; door-password
        (str "Answer 1: ")
        println))

(defn door-password-2 [id]
    (->> (range)
        (map (partial str door-id))
        (map md5)
        (filter #(zero? (first %)))
        (map rest)
        (filter #(zero? (first %)))
        (map rest)
        (filter #(< (first %) 8))
        (map #(vector (first %) (second %)))
        (reduce (fn [pw [pos b]]
                    (if (zero? (aget pw pos))
                        (aset-char pw pos (hex (bit-shift-right b 4))))
                    (if (not-any? zero? pw)
                        (reduced pw)
                        pw)) (char-array 8))
        (apply str)))

(time (->> door-id
        door-password-2
        (str "Answer 2: ")
        println))