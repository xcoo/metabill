(ns leiningen.metabill
  (:require [metabill.core :as m]))

(defn metabill [_]
  (m/write-metabill-edn)
  (println "output metabill.edn"))
