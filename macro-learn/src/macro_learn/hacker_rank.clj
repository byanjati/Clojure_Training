(import '(java.util Random))

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

(defn left-selector [mobile]
  (first mobile))

(defn right-selector [mobile]
  (second mobile))

(defn branch-length [branch]
  (first branch))

(defn struct-val [branch]
  (second branch))

(defn total-weight [mobile]
  (let [len-branch (fn [selector] (comp branch-length selector))
        left-length ((len-branch left-selector) mobile)
        right-length ((len-branch right-selector) mobile)]
    (+ left-length right-length)))

(def len-branch (fn [selector] (comp branch-length selector)))
(def struct-branch (fn [selector] (comp struct-val selector)))

;; (def mob (make-mobile (make-branch 10 2)
;;              (make-branch 20 1)))

(def kon
  (cons 1 (cons 2 nil)))

(first kon)
(first (next kon))

(def lis (list 1 2))
(first lis)
(next lis)

(defn isBalance? [mobile]
  (= (* ((len-branch left-selector) mobile) ((struct-branch left-selector) mobile))
     (* ((len-branch right-selector) mobile) ((struct-branch right-selector) mobile))))


