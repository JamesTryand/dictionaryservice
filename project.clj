(defproject dictionary "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.1.19"]
                 [com.stuartsierra/component "0.3.1"]
                 [reloaded.repl "0.2.2"]
                 [metosin/palikka "0.5.1"]
                 [metosin/kekkonen "0.3.2"]
                 [org.clojure/data.csv "0.1.4"]]
  :profiles {:uberjar {:aot [dictionary.main]
                       :main dictionary.main
                       :uberjar-name "dictionary.jar"}})
