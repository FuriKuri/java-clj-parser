(ns parser.fileinfo
  (:use [parser.file]))

(defmacro register-parser [name bytes-to-read]
  (let [sym-name (symbol name)
        key-name (keyword name)
        is-sym (symbol "input-stream")]
    `(defn ~sym-name
       ~(str "Read next " bytes-to-read " bytes to get " name)
       [~is-sym]
       {~key-name (read-next-bytes-as-hex-str ~is-sym ~bytes-to-read)})))

(register-parser magic-number 4)
(register-parser minor-version 2)
(register-parser major-version 2)

;;(defn magic-number [input-stream]
;;  {:magic-number (read-next-bytes-as-hex-str input-stream 4)})