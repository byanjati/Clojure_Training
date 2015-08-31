;; Future Function Clojure

;; perbedaannya, pada future, task nya dilempar ke thread lain
;; sehingga task di luar future dapat mendahului
;; future hanya dirun 1 x, dan hasilnya di cache
;; deferencing future, ngeblock future yang belum selesai

;; (future (Thread/sleep 5000)
;;         (println "Ahoy Captain!"))

;; (def my-delay
;;   (delay (Thread/sleep 5000)
;;          (println "Ahoy Captain!")))

;; (Thread/sleep 5000)
;; (println "Ahoy Captain!")

;; sending headshots document into hosting sites,
;; immediately bring notif when one file is hosted

;; Delay Function Clojure

(def headshots
  ["serious.jpg" "smile.jpg" "funny.jpg"])
(defn email-user
  [email-address]
  (println "the hearshots is being uploaded to : " email-address))
(defn upload-document
  [headshot]
  true)

;; (let [notify (delay (email-user "byanjati@gmail.com"))]
;;   (doseq [headshot headshots]
;;     (future (upload-document headshot)
;;             (force notify))))

;; (let [notify (email-user "byanjati@gmail.com")]
;;   (doseq [headshot headshots]
;;     (do (upload-document headshot)
;;         notify)))

(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})

(def butter-than-nothing
  {:store "Butter than Nothing"
   :price 150
   :smoothness 83})

;; This is the butter that meets our requirements
(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  [butter]
  (and (<= (:price butter) 100)
       (>= (:smoothness butter) 97)
       butter))

(some odd? [2 3 4 5 7])

(some #(and (odd? %) %) [2 3 4 5 7])

;; (some (comp satisfactory? mock-api-call)
;;       [yak-butter-international butter-than-nothing baby-got-yak])

(let [butter-promise (promise)]
  (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
    (future (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
              (deliver butter-promise satisfactory-butter)))))
