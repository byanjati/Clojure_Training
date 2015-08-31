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

(flatmap (fn [i] (map (fn [j] (list i j)) (range 1 i))) (range 1 5))
