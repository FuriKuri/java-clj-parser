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

(deftest convert-hex-ary-to-bytes
  (doseq [[i result] [
                      ['("ff" "00" "0c") '(-1 0 12)]
                      ['("ca" "fe" "ba" "be") '(-54 -2 -70 -66)]]]
    (is (= result (hex-to-byte i)))))

(deftest convert-hex-to-int
  (doseq [[hex result] [
                        ["0003" 3]
                        ["00FF" 255]
                        ["ABCD" 43981]]]
    (is (= result (hex-to-int hex)))))

(deftest convert-hex-to-float
  (doseq [[hex result] [
                        ["40C00000" 6.0]
                        ["100C0002" 2.761013770126649E-29]]]
    (is (= result (hex-to-float hex)))))

(deftest convert-hex-to-long
  (doseq [[hex result] [
                        ["0000000000000003" 3]
                        ["FFFFFFFFFFFFFFFF" -1]
                        ["0000ABCDABCDABCD" 188899839028173]]]
    (is (= result (hex-to-long hex)))))

(deftest convert-hex-to-double
  (doseq [[hex result] [
                        ["0000000000000003" 3]
                        ["FFFFFFFFFFFFFFFF" 1.8446744073709552E19]
                        ["0000ABCDABCDABCD" 1.88899839028173E14]]]
    (is (= result (hex-to-double hex)))))

(deftest convert-hex-to-utf8
  (doseq [[hex result] [
                        ["48656C6C6F576F726C64" "HelloWorld"]]]
    (is (= result (hex-to-utf8 hex)))))