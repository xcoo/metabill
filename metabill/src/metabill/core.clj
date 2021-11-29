(ns metabill.core
  (:require [clojure.java.shell :as shell]
            [clojure.edn :as edn]
            [clojure.string :as string]))

(defn- data []
  {:build-date (pr-str (System/currentTimeMillis))
   :commit (string/trim (:out (shell/sh "git" "rev-parse" "--short" "HEAD")))})

(defn write-metabill-edn []
  (spit "target/metabill.edn" (pr-str (data))))

(defn read-metabill-edn []
  (edn/read-string (slurp "metabill.edn")))

(defn with-build-date [f]
  (let [d (read-metabill-edn)]
    (str f
         (some->> (:build-date d)
                  (str "?")))))

(defn commit-hash []
  (let [d (read-metabill-edn)]
    (:commit d)))
