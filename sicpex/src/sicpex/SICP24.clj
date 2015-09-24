(ns sicpex.SICP24
  (:use [sicpex.SICP]))

(defn unique? [x1 x2]
  (not (= x1 x2)))

(defn unique-pairs [n]
  (filter #(unique? (first %) (second %))
          (flatmap
           (fn [i]
             (map (fn [j] (list i j)) (range 1 i)))
           (range 1 n))))

;; Filter -> (Permutation)
;;           -> (FlatMap Lambda (map lambda seq) seq)

(defn find-triplet [n s]
  (filter #(not (or (= % nil) (empty? %)))
    (flatmap
     (fn [k]
       (flatmap
        (fn [j]
          (map (fn [i] (list i j k))(range 1 j)))
        (range 1 k)))
     (range 1 (+ n s)))))

(defn abs [x]
  (cond (> x 0) x
        (= x 0) x
        (< x 0) (- x)))

(defn adjoin-position [row k rest-queens]
  (cons (list row k) rest-queens))

(defn gradient [point1 point2]
  (let [up (- (first point1) (first point2))
        bot (- (second point1) (second point2))]
    (cond (or (= bot 0) (= up 0)) false
          (= (abs (/ up bot)) 1) false
          :else true)))

(defn safe? [k positions]
  (if (> k 1)
    (reduce #(and %1 %2) (map #(gradient (nth positions 0) %) (next positions)))
    true))

(defn queens [board-size]
  (defn queen-cols [k]
    (if (= k 0)
      (list nil)
      (filter
       (fn [pos] (safe? k pos))
       (flatmap
        (fn [rest-of-queens]
          (map (fn [new-row]
                 (adjoin-position new-row k rest-of-queens))
               (range 1 (inc board-size))))
        (queen-cols (dec k))))))
  (queen-cols board-size))

(queens 4)


(+ 1 1)
