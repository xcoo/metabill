(ns clj_manifest_reader.core
  (:import [java.util.jar Manifest])
  (:require [clojure.java.io :as io]))

(declare build-date)

(defn get-build-date
  [req]
  (when-not (bound? #'build-date)
    (intern 'clj_manifest_reader.core 'build-date
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
                      (.getValue "Build-Date")))))
  build-date)

(defn with-build-date
  ([f]
   (with-build-date nil f))
  ([req f]
   (str f
        (some->> (get-build-date req)
                 (str "?")))))
