(ns parser.test.core
  (:use [parser.core])
  (:use [clojure.test]))

(defn dummy-fn1 [a] {:a a})
(defn dummy-fn2 [_] {:b "b"})
(defn dummy-fn3 [c d] {:c c :d d})

(deftest create-macro-with-concrete-fn
  (let [actual (>-> "input" dummy-fn1 dummy-fn2 (dummy-fn3 "para"))
        expected {:a "input" :b "b" :c "input" :d "para"}]
    (is (= actual expected))))
