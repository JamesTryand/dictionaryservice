(ns dictionary.handler
  (:require [plumbing.core :refer [defnk]]
            [kekkonen.cqrs :refer :all]
            [kekkonen.upload :as upload]
            [schema.core :as s]
            [dictionary.dictionarylib :as dict]))


(s/defschema WordDefinition
  [{:word s/Str
    :usage s/Str
    :definition s/Str}])

;;
;; Handlers
;;

(defnk ^:query definitionof
  "Return the definition of a word"
  {:responses {:default {:schema WordDefinition}}}
  [[:data term :- s/Str]]
  (success (dictionary.dictionarylib/definition-of term)))

(defnk ^:query definitioncount
  "Returns the number of definitions for a word"
  [[:data term :- s/Str]]
  (success (count (dictionary.dictionarylib/definition-of term))))

;;
;; Application
;;

(defn create [system]
  (cqrs-api
    {:swagger {:ui "/"
               :spec "/swagger.json"
               :data {:info {:title "English Dictionary API"
                             :description "Web Service For The English Dictionary Service"}}}
     :core {:handlers {:dictionary [#'definitionof #'definitioncount]}            
            :context system}}))
