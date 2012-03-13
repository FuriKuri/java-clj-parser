(ns java-clj-parser.test.file
  (:use [java-clj-parser.file])
  (:use [clojure.test])
  (:use [clojure.contrib.mock])
  (:use [java-clj-parser.convert :only (hex-to-byte)]))

(deftest read-simple-file
  (let [file-stream (get-input-stream "test/simple-file.txt")]
    (doseq [c [\C \o \n \t \e \n \t]]
      (is (= c (char (.read file-stream)))))))

(defn mock-stream [hex-ary]
  (let [ary-size (count hex-ary)
        byte-ary (byte-array ary-size)]
    (doseq [i (range ary-size)]
        (aset-byte byte-ary i (hex-to-byte (nth hex-ary i))))
    (java.io.ByteArrayInputStream. byte-ary)))

(deftest read-mock-file
  (expect [get-input-stream (returns (mock-stream ["ff" "00" "0f"]))]
          (doseq [c [255 0 15]]
            (is (= c (.read (get-input-stream "file")))))))