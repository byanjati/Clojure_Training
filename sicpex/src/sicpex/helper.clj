; tentukan array nya coprime
; tentukan apakah hasil productnya lebih dari n
; cari faktornya, nyatakan pada string -x1--x2--x3--x4--x5-x6-,
; array x merupakan faktor - faktor residu n terhadap moduli

;; 14 16
;; 2 14

;; (defn gcd [n m]
;;   (if (= (mod n m) 0)
;;     m
;;     (gcd m (mod n m))))

;; (defn make-res [data lst]
;;   (cond (= (first data) 0) (str "-" (second data))
;;         (= (first data) (dec (count lst))) (str "--" (second data) "-")
;;         :else (str "--" (second data))))

;; (defn fromNb2Str
;;   [n arr]
;;   ; your code
;;   (let [perm (filter #(not= % nil)
;;                      (for [i arr
;;                            j arr] (if (not= i j) [i j])))
;;         koprima (map #(if (= (gcd (first %) (second %)) 1) true) perm)
;;         isKoprimaTrue? (every? true? koprima)
;;         isDotKoprima? (>= (reduce * arr) n)
;;         res (map #(mod n %) arr)
;;         res-str (clojure.string/join "" (map #(make-res % res) (map-indexed vector res)))]
;;     (if (and isKoprimaTrue? isDotKoprima?) res-str "Not applicable")))
