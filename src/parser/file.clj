(ns parser.file
  (:use [clojure.java.io :only [input-stream]]))

(defn get-input-stream [filename]
  (input-stream filename))

(defn read-next-bytes [input-stream i]
  (nth (iterate #(conj % (.read input-stream)) []) i)) 