(ns parser.file
  (:use [clojure.java.io :only [input-stream]]))

(defn get-input-stream [filename]
  (input-stream filename))
