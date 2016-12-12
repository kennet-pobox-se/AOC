(ns AOC-2016-4.core (:require [clojure.string :as str]) (:use [clojure.test]))

(with-test 
    (defn checksum [name]
        (apply str (take 5 (map first (sort (fn [[a b] [c d]] (compare [(- b) a] [(- d) c])) (frequencies name))))))
 (is (= "abcde" (checksum "abcdefg"))))
    

(with-test
 (defn room->id [room]
     (let [parts (filter seq (str/split room #"[-\[\]]"))]
         (let [encrypted-name (apply str (take (- (count parts) 2) parts))
               sector-id (int (last (butlast parts)))
               checksum-maybe (last parts)]
          (if (= checksum-maybe (checksum encrypted-name))
           sector-id))))
 
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
                            
 
(run-tests)



