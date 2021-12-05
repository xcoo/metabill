(ns leiningen.metabill
  (:require [metabill.core :as m]))

(defn metabill [_]
  (m/save-build-meta-data))
