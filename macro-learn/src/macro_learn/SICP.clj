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



