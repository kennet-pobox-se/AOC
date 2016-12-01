(def instructions (slurp "instructions.txt"))

(defn turn [m dx dy] [dx dy])

(defn dist [m] 1)

(defn move [[dx dy x y] m]
    (let [[ndx ndy] (turn m dx dy)
          d (dist m)]
        [ndx ndy (+ x (* d ndx)) (+ y (* d ndy)]))

(reduce move [0 1 0 0] (split instructions #", "))

