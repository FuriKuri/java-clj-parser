(ns parser.test.convert
  (:use [parser.convert])
  (:use [clojure.test]))

(deftest convert-int-to-hex
  (doseq [[i result] [
                      [255 "ff"]
                      [0 "0"]
                      [15 "f"]]]
    (is (= result (int-to-hex i)))))

(deftest convert-int-to-2char-hex
  (doseq [[i result] [
                      [255 "ff"]
                      [0 "00"]
                      [15 "0f"]]]
    (is (= result (int-to-2char-hex i)))))

(deftest convert-int-ary-to-2char-hex
  (doseq [[i result] [
                      ['(255 0 12) '("ff" "00" "0c")]
                      ['(202 254 186 190) '("ca" "fe" "ba" "be")]]]
    (is (= result (int-to-2char-hex i)))))

(deftest convert-hex-to-byte
  (doseq [[hex result] [
                      ["ff" -1]
                      ["00" 0]
                      ["7f" 127]
                      ["80" -128]]]
    (is (= result (hex-to-byte hex)))))
