(ns parser.file
  (:use [clojure.java.io :only [input-stream]])
  (:use [parser.convert]))

(defn get-input-stream [filename]
  (input-stream filename))

(defn read-next-bytes [input-stream i]
  (nth (iterate #(conj % (.read input-stream)) []) i))

(defn read-next-bytes-as-hex [input-stream i]
  (map int-to-2char-hex (read-next-bytes input-stream i)))

(defn read-next-bytes-as-hex-str [input-stream i]
  (reduce str (read-next-bytes-as-hex input-stream i)))