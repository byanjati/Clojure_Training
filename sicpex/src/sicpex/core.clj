(ns sicpex.core
  (:gen-class))

(defn absis
  "absis from the point"
  [p]
  (first (:point p)))

(defn ordinat
  "ordinat from the point"
  [p]
  (second (:point p)))

(defn make-point
  "structuring point"
  [x y res]{:key res :point [x y]})

(defn atas
  "move ordinat 1 step to top"
  [p]
  (make-point (absis p) (inc (ordinat p)) (inc (:key p))))

(defn bawah
  "move ordinat 1 step to bot"
  [p]
  (make-point (absis p) (dec (ordinat p)) (inc (:key p))))

(defn kiri
  "move absis 1 step to left"
  [p]
  (make-point (dec (absis p)) (ordinat p) (inc (:key p))))

(defn kanan
  "move abiss 1 step to right"
  [p]
  (make-point (inc (absis p)) (ordinat p) (inc (:key p))))

(defn lesser
  [x y]
  (< x y))

(defn greater
  [x y]
  (> x y))

(defn keyMax?
  [n p]
  (= (:key p) (* n n)))

(defn create-poll
  [f condition part p bound res]
  (if (and (condition (part p) bound)
           (not (keyMax? n p)))
    (create-poll f condition part (f p) bound (conj res (f p)))
    res))

(defn atas-poll
  [p bound res]
  (create-poll atas lesser ordinat p bound res))

(defn bawah-poll
  [p bound res]
  (create-poll bawah greater ordinat p bound res))

(defn kiri-poll
  [p bound res]
  (create-poll kiri greater absis p bound res))

(defn kanan-poll
  [p bound res]
  (create-poll kanan lesser absis p bound res))

(defn outer-move
  "atas-poll-kanan-poll-bawah-poll"
  [p res top-bound bot-bound left-bound]
  (let [res-up (atas-poll p top-bound res)
        res-right (kanan-poll (last res-up) top-bound res-up)
        res-down (bawah-poll (last res-right) bot-bound res-right)
        res-left (inter-left res-down)
        res-upp (atas-poll (last res-left) (dec top-bound) res-left)
        res-leftt (kiri-poll (last res-upp) (inc left-bound) res-upp)
        res-res (bawah-poll (last res-leftt) bot-bound res-leftt)
        res-right-res (inter-right res-res)]
    res-right-res))

(defn inter-left
  [res]
  (if (not (keyMax? n (last res)))
    (conj res (kiri (last res)))
    res))

(defn inter-right
  [res]
  (if (not (keyMax? n (last res)))
    (conj res (kanan (last res)))
    res))

(defn solution
  [p res top-bound bot-bound left-bound]
  (if (not (keyMax? n (last p)))
      (let [point (last p)
          part-solution (outer-move point res top-bound bot-bound left-bound)]
      (solution (conj [] (last part-solution)) part-solution (- top-bound 2) bot-bound (+ left-bound 2)))
    res))

(def n 21)
(def point (make-point 1 0 0))

(time (solution [point] [] n 1 1))

(defn -main
  "I don't do a whole lot ... yet."
  [& args])
