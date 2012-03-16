(ns parser.constantpool
  (:use [parser.file])
  (:use [parser.convert]))

;;(def *constant-type*
;;  {"07" fn1})

(defn constant-count [input-stream]
  (let [hex (read-next-bytes-as-hex-str input-stream 2)]
    {:constant-count (dec (hex-to-int hex))}))

(defmacro register-const-type [const-type & fields]
  (let [const-type-sym (symbol const-type)
        const-type-key (keyword const-type)
        input-stream (symbol "input-stream")]
    `(defn ~const-type-sym [~input-stream]
       (let
           [~@(reduce concat
                    (map (fn [[field-key bytes]]
                           [(symbol (name field-key)) `(read-next-bytes-as-hex-str ~input-stream ~bytes)])
                         fields))]
         {~const-type-key
          ~(reduce conj (map
                         (fn [[field-key _]]
                           `{~field-key (~hex-to-int ~(symbol (name field-key)))})
                         fields))}))))
(register-const-type class-info [:name-index 2])
(register-const-type methodref [:class-index 2] [:name-and-type-index 2])
(register-const-type fieldref [:class-index 2] [:name-and-type-index 2])


;; multimethod mit constnt-type