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
  )
