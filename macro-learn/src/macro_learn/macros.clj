(defmacro postfix-notation
  [expression]
  (conj (butlast expression) (last expression)))

(postfix-notation (1 1 +))

(quote (1 + 2))

(defmacro ketika
  [tes & body]
  (list 'if tes (cons 'do body)))

(macroexpand
 '(ketika (= 1 1)
         (println "wow")
         (println "such information")))

(defmacro unless
  "kamu tidak bisa masuk, kecuali bawa tiket"
  [test & body]
  (conj (reverse body) test 'if))

(defmacro code-praiser
  [code]
  `(println
    "this is a good code : "
    (quote ~code)))

(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  [{:keys [good bad]}]
  `(do ~(criticize-code
        "this is a good code  : "
        good)
       ~(criticize-code
        "this is a bad code   : "
        bad)))

(defn factorial [n]
  (if (= n 0)
    1
    (*' n (factorial (dec n)))))

(defn square [x]
  (*' x x))

(defn pangkat [x n]
  (defn pangkat-iter [x n sum]
    (cond (= n 0) sum
          (= (mod n 2) 0) (pangkat-iter (square x) (/ n 2) sum)
          :else (pangkat-iter x (dec n) (*' sum x))))
  (pangkat-iter x n 1))

(defn gcd [m n]
  (if (= (mod m n) 0)
    n
    (gcd n (mod m n))))

(/ 999999999 (gcd 12345679 999999999))

(defn make-fraction [nom denom]
  (let [gcd-nom-denom (gcd nom denom)]
    [(/ nom gcd-nom-denom) (/ denom gcd-nom-denom)]))

(defn add [x y]
  (make-fraction (+' (*' (first x) (second y)) (*' (second x) (first y))) (*' (second x) (second y))))

(defn taylor-series [x n]
  (defn taylor-series-iter [iter sum]
    (if (< (- (/ (pangkat x iter) (factorial iter)) 0) (/ 1 (pangkat 10 n)))
      sum
      (taylor-series-iter (inc iter) (add sum (make-fraction (pangkat x iter) (factorial iter))))))
  (taylor-series-iter 0 (make-fraction 0 1)))
