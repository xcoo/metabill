(ns clj-manifest-reader.core
  (:import [java.util.jar Manifest])
  (:require [clojure.java.io :as io]))

(defn read-manifest [req target]
  (with-open [manifest (if-let [context (:servlet-context req)]
                         ;; uberwar
                         (-> context
                             (.getResourceAsStream "/META-INF/MANIFEST.MF"))
                         ;; uberjar
                         (some-> (io/resource "META-INF/MANIFEST.MF")
                                 .openStream))]
    (some-> manifest
            Manifest.
            .getMainAttributes
            (.getValue target))))

(declare build-date)

(defn get-build-date
  [req]
  (when-not (bound? #'build-date)
    (intern 'clj-manifest-reader.core 'build-date
            (read-manifest req "Build-Date")))
  build-date)

(defn with-build-date
  ([f]
   (with-build-date nil f))
  ([req f]
   (str f
        (some->> (get-build-date req)
                 (str "?")))))

(declare commit-hash)

(defn get-commit-hash
  [req]
  (when-not (bound? #'commit-hash)
    (intern 'clj-manifest-reader.core 'commit-hash
            (read-manifest req "Commit")))
  commit-hash)

(defn with-commit-hash
  ([]
   (with-commit-hash nil))
  ([req]
   (get-commit-hash req)))
