(ns AOC-2016-4.core (:require [clojure.string :as str]) (:use [clojure.test]))

(with-test 
    (defn checksum [name]
        (apply str (take 5 (map first (sort (fn [[a b] [c d]] (compare [(- b) a] [(- d) c])) (dissoc (frequencies name) \-))))))
 (is (= "abcde" (checksum "efg-abcd"))))
    
(with-test
    (defn parse-room [room]
        (let [[_ encrypted _ id checksum] (re-matches #"((\w+-?)+)-(\d+)\[(\w{5})\]" room)]
            ;(println (str room ": " encrypted " " id " " checksum))
            {:encrypted encrypted :id (int id) :checksum checksum}))
    (is (= {:encrypted "aaaaa-bbb-z-y-x" :id 123 :checksum "abxyz"} (parse-room "aaaaa-bbb-z-y-x-123[abxyz]"))))

(with-test
    (defn valid-room? [{encrypted :encrypted checksum? :checksum}]
     (= checksum? (checksum encrypted)))
    (is (valid-room? (parse-room "not-a-real-room-404[oarel]"))))

(with-test
    (defn room->id [room]
        (let [parsed (parse-room room)]
            (if (valid-room? parsed) (:id parsed))))
 
 (is (= 123 (room->id "aaaaa-bbb-z-y-x-123[abxyz]")))
 (is (= 987 (room->id "a-b-c-d-e-f-g-h-987[abcde]")))
 (is (= 404 (room->id "not-a-real-room-404[oarel]")))
 (is (nil? (room->id "totally-real-room-200[decoy]")))
 (is (= 1514 (reduce + (->> ["aaaaa-bbb-z-y-x-123[abxyz]"
                                      "a-b-c-d-e-f-g-h-987[abcde]"
                                      "not-a-real-room-404[oarel]"
                                      "totally-real-room-200[decoy]"]
                            (map room->id)
                            (filter some?))))))
                            
(with-test
    (defn decrypt [{encrypted :encrypted key :id :as room}]
        (assoc room :name (apply str (map (fn [c] (if (= \- c) \space (char (+ \a (rem (+ (- c \a) key) 26))))) encrypted))))
    (is (= "very encrypted name" (:name (decrypt (parse-room "qzmt-zixmtkozy-ivhz-343[xxxxx]"))))))

(time (run-tests))

(time (->> (slurp "rooms.txt")
       str/split-lines
       (map room->id)
       (filter some?)
       (reduce +)
       (str "Answer 1: ")
       println))

(time (->> (slurp "rooms.txt")
       str/split-lines
       (map parse-room)
       (filter valid-room?)
       (map :id)
       (reduce +)
       (str "Answer 1 again: ")
       println))

(time (->> (slurp "rooms.txt")
       str/split-lines
       (map parse-room)
       (filter valid-room?)
       (map decrypt)
    ;(map println)
       (filter #(= "northpole object storage" (:name %1)))
       (map :id)
       (map #(str "Answer 2: " %1))
       (map println)
       doall))

