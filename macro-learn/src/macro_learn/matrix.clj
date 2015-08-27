(defn accumulate [op initial items]
  (if (nil? items)
    initial
    (op (first items) (accumulate op initial (next items)))))

(defn accumulate-n [op init seqs]
  (if (nil? (first seqs))
    nil
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map next seqs)))))

(defn transpose [mat]
  (accumulate-n cons nil mat))

(defn matrix-*-vector [m v]
  (map #(reduce + (map * % v)) m))

(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map #(matrix-*-vector m %) cols)))

(defn append-list [list1 list2]
  (if (nil? list1)
    (list list2)
    (cons (first list1) (append-list (next list1) list2))))

(defn fold-left [op initial seqs]
  (defn iter [result res]
    (if (nil? res)
      result
      (iter (op result (first res)) (next res))))
  (iter initial seqs))

(defn reverse-right [seqs]
  (accumulate (fn [x y] (append-list y x)) nil seqs))

(defn reverse-left [seqs]
  (fold-left (fn [x y] (cons y x)) nil seqs))

(reverse-right (list 1 2 3))
















