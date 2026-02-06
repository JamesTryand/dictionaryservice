(ns dictionaryservice.main
  (:require [com.stuartsierra.component :as component]
            [reloaded.repl :refer [set-init! go]])
  (:gen-class))

(defn -main [& [port]]
  (let [port (or port 3000)]
    (require 'dictionaryservice.system)
    (set-init! #((resolve 'dictionaryservice.system/new-system) {:http {:port port}}))
    (go)))
