(ns parser.core)

(defmacro >->
  [input-stream & parsers]
  (let [gx (gensym)]
    `(let [~gx ~input-stream]
       (conj
        ~@(map (fn [f]
                 (if (seq? f)
                   `(~(first f) ~gx ~@(next f))
                   `(~f ~gx)))
               parsers)))))
