(ns parser.constantpool
  (:use [parser.file])
  (:use [parser.convert]))

;;(def *constant-type*
;;  {"07" fn1})

(defn constant-count [input-stream]
  (let [hex (read-next-bytes-as-hex-str input-stream 2)]
    {:constant-count (dec (hex-to-int hex))}))

(defn class-info [input-stream]
  (let [hex (read-next-bytes-as-hex-str input-stream 2)]
    {:class-info {:name-index (hex-to-int hex)}}))

(defn fieldref [input-stream]
  (let [class-hex (read-next-bytes-as-hex-str input-stream 2)
        name-type-hex (read-next-bytes-as-hex-str input-stream 2)]
    {:fieldref {:class-index (hex-to-int class-hex)
                :name-and-type-index (hex-to-int name-type-hex)}}))

(defn methodref [input-stream]
  (let [class-hex (read-next-bytes-as-hex-str input-stream 2)
        name-type-hex (read-next-bytes-as-hex-str input-stream 2)]
    {:methodref {:class-index (hex-to-int class-hex)
                 :name-and-type-index (hex-to-int name-type-hex)}}))
