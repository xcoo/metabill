(ns metabill.core
  (:require [clojure.java.shell :as shell]
            [clojure.edn :as edn]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(def ^:dynamic metabill-dir-path "resources")
(def ^:dynamic metabill-filename "metabill.edn")

(def build-meta
  {:time (fn []
           (pr-str (System/currentTimeMillis)))
   :commit-hash (fn []
                  (string/trim (:out (shell/sh "git" "rev-parse" "--short" "HEAD"))))})

(defn- make-build-meta-data []
  (->> build-meta
       (map (fn [[k f]] [k (f)]))
       (into {})))

(defn save-build-meta-data []
  (let [d (make-build-meta-data)
        f (.getAbsolutePath (io/file metabill-dir-path metabill-filename))]
    (spit f (pr-str d))
    d))

(defn load-build-meta-data []
  (let [resource-file (io/resource metabill-filename)]
    (if resource-file
      (try
        (edn/read-string (slurp resource-file))
        (catch RuntimeException re ((binding [*out* *err*]
                                      (println (.getMessage re)))
                                    nil)))
      nil)))

;;; with

(defn with-meta-data
  [f ks]
  (let [d (load-build-meta-data)]
    (str f
         (some->> ks
                  (keep #(get d %))
                  (string/join "_")
                  (str "?")))))

(def with-build-time #(with-meta-data % [:time]))
(def with-build-commit-hash #(with-meta-data % [:commit-hash]))
(def with-build-meta #(with-meta-data % (keys build-meta)))

;;; get

(defn get-meta-data
  [k]
  (let [d (load-build-meta-data)]
    (get d k)))

(def get-build-time #(get-meta-data :time))
(def get-build-commit-hash #(get-meta-data :commit-hash))
(def get-build-meta load-build-meta-data)
