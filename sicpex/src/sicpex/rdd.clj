(defn contextual-eval [ctx expr]
  (eval
   `(let [~@(mapcat (fn [[k v]] [k `'~v]) ctx)]
      ~expr)))

(eval `'~(+ 1 3))

'(+ 1 2)
(println `'~(+ 1 2))

(+ 2 2)
;; (contextual-eval '{a 1, b 2} '(+ a b))
