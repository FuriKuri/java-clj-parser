(ns parser.convert)

(defn int-to-hex [i]
  (Integer/toHexString (if (< i 0) (+ 256 i) i)))

(defn int-to-2char-hex [i]
  (if (seq? i)
    (map int-to-2char-hex i)
    (let [hex (int-to-hex i)]
      (if (> 2 (.length hex))
        (str "0" hex)
        hex))))

(defn hex-to-byte [hex]
  (if (seq? hex)
    (map hex-to-byte hex)
    (let [i (Integer/parseInt hex 16)]
      (if (< 127 i)
        (- i 256)
        i))))

(defn hex-to-int [hex]
  (Integer/parseInt hex 16))

(defn hex-to-float [hex]
  (let [long-value (Long/parseLong hex 16)]
    (Float/intBitsToFloat long-value)))

(defn hex-to-long [hex]
  (-> (java.math.BigInteger. hex 16) .longValue))

(defn hex-to-double [hex]
  (-> (java.math.BigInteger. hex 16) .doubleValue))

(defn hex-to-utf8 [hex]
  (let [result (StringBuilder.)
        size (.length hex)]
    (loop [i 0]
      (if (= size i)
        (.toString result)
        (let [hex-code (-> hex (.substring i (+ i 2)))
              code (hex-to-int hex-code)
              _ (-> result (.append (char code)))]
            (recur (+ i 2)))))))
    