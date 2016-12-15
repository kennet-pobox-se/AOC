(ns AOC-2016-5.core (:require [clojure.string :as str]) (:use [clojure.test]))

(with-test
    (defn bytes->nibbles [bs]
        (interleave (map #(bit-shift-right % 4) bs) (map #(bit-and % 15) bs)))
    (is (= [0 1 2 3] (bytes->nibbles [1 35]))))

(with-test
    (defn md5 [s]
        (. (System.Security.Cryptography.MD5/Create) ComputeHash (. System.Text.Encoding/UTF8 GetBytes s)))
    (is (= [165 169 102 212 109 156 237 255 65 5 70 237 203 80 140 50] (md5 "Kennet"))))

(with-test
    (def hex (apply vector (seq "0123456789ABCDEF")))
    (is (= \B (hex 11))))

(time (->> "Kennet Persson var hans namn och han var inte direkt en fena på Clojure"
       md5
       bytes->nibbles
       (map hex)
       (apply str)
       println))

; (time (println (. (System.Security.Cryptography.MD5/Create) ComputeHash (. System.Text.Encoding/UTF8 GetBytes "Kennat"))))

; (time (println (let [s [165 169 102]] (interleave (map #(bit-shift-right % 4) s) (map #(bit-and % 15) s)))))