(ns java-clj-parser.test.core
  (:use [java-clj-parser.core])
  (:use [clojure.test]))

(deftest create-macro->->
  (let [result (macroexpand-1 '(>-> input-stream fn1 fn2 (fn3 1)))]
    (is (= 'clojure.core/let (nth result 0)))
    (is (vector? (nth result 1)))
    (let [inner-result (nth result 2)]
      (is (= 'clojure.core/conj (nth inner-result 0)))
      (is (= 'fn1 (first (nth inner-result 1))))
      (is (= 'fn2 (first (nth inner-result 2))))
      (is (= 'fn3 (first (nth inner-result 3))))
      (is (= 1 (last (nth inner-result 3)))))))

(defn dummy-fn1 [a] {:a a})

(defn dummy-fn2 [_] {:b "b"})

(defn dummy-fn3 [c d] {:c c :d d})

(deftest create-macro-with-concrete-fn
  (let [actual (>-> "input" dummy-fn1 dummy-fn2 (dummy-fn3 "para"))
        expected {:a "input" :b "b" :c "input" :d "para"}]
    (is (= actual expected))))