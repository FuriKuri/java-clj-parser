(ns parser.convert)

(defn int-to-hex [i]
  (Integer/toHexString (if (< i 0) (+ 256 i) i)))

(defn int-to-2char-hex [i]
  (let [hex (int-to-hex i)]
    (if (> 2 (.length hex))
      (str "0" hex)
      hex)))

(defn hex-to-byte [hex]
  (let [i (Integer/parseInt hex 16)]
    (if (< 127 i)
      (- i 256)
      i)))
