(ns dictionary.dictionarylib
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io])
  (:gen-class))

; (def isloaded false)
; (defn IsLoaded [] isloaded)
(def dictionaryfile "../Dictionary.csv")

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

; load the dataset
(defn read-dictionary [filename]
  (with-open [reader (io/reader filename)]
    
    (->> (csv/read-csv reader)
         csv-data->maps
         doall)
    ))

(def collins (read-dictionary dictionaryfile))

; (map #(pprint %) dictionary.dictionarylib/collins)

(defn definition-of [term] (filter #(= (.toLowerCase (:word %)) (.toLowerCase term)) dictionary.dictionarylib/collins))