(defn length-list [items]
  (if (nil? (first items))
    0
    (+ 1 (length-list (next items)))))

(defn length-list-mem [items]
  (defn length-list-iter [item cnt]
    (if (nil? (first item))
      cnt
      (length-list-iter (next item) (inc cnt))))
  (length-list-iter items 0))


;; (append (1 2) (2 3 4)) (1 2 2 3 4)

(defn append-list [list1 list2]
  (if (nil? list1)
    list2
    (cons (first list1) (append-list (next list1) list2))))

(defn reverse-list [list1 res]
  (if (nil? (next list1))
    (cons (first list1) res)
    (reverse-list (next list1) (cons (first list1) res))))

;; (reverse-list (list 1 2 3 4) nil)

(defn rvrs-lst [list1]
  (if (nil? (butlast list1))
    (cons (first list1) nil)
    (cons (last list1) (rvrs-lst (butlast list1)))))

(defn same-parity [& lst]
  "return list of argument that have the same even-odd parity as the first argument"
  (defn even-lst [lst]
    "return even lst as the first argument is even"
    (cond (nil? lst) nil
          (even? (first lst)) (cons (first lst) (even-lst (next lst)))
          (odd? (first lst)) (even-lst (next lst))))
  (defn odd-lst [lst]
    "return odd lst as the first argument is odd"
    (cond (nil? lst) nil
          (odd? (first lst)) (cons (first lst) (odd-lst (next lst)))
          (even? (first lst)) (odd-lst (next lst))))
  (if (odd? (first lst))
    (odd-lst lst)
    (even-lst lst)))

(defn scale-list [lst scale]
  (if (nil? lst)
    nil
    (cons (* scale (first lst)) (scale-list (next lst) scale))))

(scale-list (list 1 2 3) 3)

(defn native-map [lst op]
  (if (nil? lst)
    nil
    (cons (op (first lst)) (native-map (next lst) square))))

(defn square [x]
  (* x x))

(defn square-list [items]
  (native-map items square))

(range 1 5)

(defn for-each [lst op]
  (if (nil? lst)
    nil
    (do
      (op (first lst))
      (for-each (next lst) op))))

(defn nat-length [lst]
  (if (nil? lst)
    0
    (inc (nat-length (next lst)))))

(defn count-leaves [lst]
  (cond (nil? lst) 0
        (not (seq? lst)) 1
        :else (+ (count-leaves (first lst))
                 (count-leaves (next lst)))))

;; 1 2 3 4 5 => 10 20 30 40

;; 1 2 3 4 5 => 1 3 5
;;

;; (1 2)
;; (cons 2 (cons 1 nil))

;; (1 2 3)
;; (cons (

;; (1 2 3 4) (4 3 2 1)
;; (cons 4 (cons 3 (cons 2 (cons 1 nil))))
;; (append 1 2 3 4)


;; (append (1 2 3 4) (2 3 4 5))
;; (cons 1 (append (2 3 4) (2 3 4 5)))
;; (cons 1 (cons 2 (append (3 4) (2 3 4 5))))
;; (cons 1 (cons 2 (cons 3 (append 4 (2 3 4 5)))))
;; (cons 1 (cons 2 (cons 3 (cons 4 (2 3 4 5)))))


(def x (cons (list 1 2) (list 3 4)))

(defn fringe [items]
  (cond (nil? items) nil
        (seq? (first items)) (append-list (fringe (first items)) (fringe (next items)))
        :else items))

(def y (list 1 (list 2 (list 3 4))))


(defn scale-tree [tree op]
  (cond (nil? tree) nil
        (seq? tree) (cons (scale-tree (first tree) op) (scale-tree (next tree) op))
        :else (op tree)))

;; (scale-tree y 2)

(defn square-tree [tree]
  (scale-tree tree square))

(defn subsets [s]
  (if (nil? s)
    (list nil)
    (let [rest (subsets (next s))]
      (append-list rest (map #(cons (first s) %) rest)))))

(defn sum-squared-odd [tree]
  (cond (nil? tree) 0
        (seq? tree) (+ (sum-squared-odd (first tree))
                       (sum-squared-odd (next tree)))
        :else (if (odd? tree) (square tree) 0)))

(defn fibs [n]
  (cond (or (= n 0) (= n 1)) 1
        :else (+ (fibs (dec n)) (fibs (dec (dec n))))))

(defn generate-fibs [kondisi n]
  (defn next-fib [m]
    (cond (>= m n) nil
          (kondisi (fibs m)) (cons (fibs m) (next-fib (inc m)))
          :else (next-fib (inc m))))
  (next-fib 0))

(defn even-fibs [n]
  (generate-fibs even? n))

(defn odd-fibs [n]
  (generate-fibs odd? n))


















