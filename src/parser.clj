(ns parser
  (:use [parser.file])
  (:use [parser.fileinfo])
  (:use [parser.core])
  (:use [parser.constantpool])
  (:gen-class))

(defn get-class-infos [filename]
  (let [file-stream (get-input-stream filename)]
    (>-> file-stream
         magic-number
         minor-version
         major-version
         constant-pool)))

(defn -main [& args]
  (println (get-class-infos (nth args 0))))
  