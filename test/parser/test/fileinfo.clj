(ns parser.test.fileinfo
  (:use [parser.fileinfo])
  (:use [parser.convert])
  (:use [clojure.test])
  (:use [parser.test.file]))

(deftest read-magic-number
  (doseq [[stream-bytes result] [
                                 ['("CA" "FE" "BA" "BE")
                                  "cafebabe"]
                                 ['("CA" "FE" "BA" "BE" "01" "02")
                                  "cafebabe"]
                                 ['("AA" "BB" "CC" "DD" "EE")
                                  "aabbccdd"]]]
    (let [input-stream (mock-stream stream-bytes)]
      (is (= {:magic-number result} (magic-number input-stream))))))