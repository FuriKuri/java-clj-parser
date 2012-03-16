(ns parser.test.constantpool
  (:use [parser.constantpool])
  (:use [parser.convert])
  (:use [clojure.test])
  (:use [parser.test.file]))

(deftest read-constant-count
  (doseq [[stream-bytes result] [
                                 ['("00" "03") 2
                                  '("00" "0F") 15]]]
    (let [input-stream (mock-stream stream-bytes)]
      (is (= {:constant-count result} (constant-count input-stream))))))

(deftest read-class-info
  (doseq [[stream-bytes result] [
                                ['("00" "01") 1
                                 '("00" "F0") 125]]]
    (let [input-stream (mock-stream stream-bytes)]
      (is (= {:class-info {:name-index result}} (class-info input-stream))))))

(deftest read-fieldref
  (doseq [[stream-bytes class-index name-type-index] [
                                ['("00" "01" "00" "03") 1 3
                                 '("00" "F0" "01" "00") 125 126]]]
    (let [input-stream (mock-stream stream-bytes)]
      (is (= {:fieldref {:class-index class-index
                         :name-and-type-index name-type-index}}
             (fieldref input-stream))))))

(deftest read-methodref
  (doseq [[stream-bytes class-index name-type-index actual] [
                                ['("00" "01" "00" "03") 1 3
                                 '("00" "F0" "01" "00") 125 126]]]
    (let [input-stream (mock-stream stream-bytes)]
      (is (= {:methodref {:class-index class-index
                          :name-and-type-index name-type-index}}
             (methodref input-stream))))))