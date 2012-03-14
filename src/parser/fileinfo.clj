(ns parser.fileinfo
  (:use [parser.file]))

(defn magic-number [input-stream]
  {:magic-number (read-next-bytes-as-hex-str input-stream 4)})