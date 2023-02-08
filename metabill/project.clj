(defproject jp.xcoo/metabill "0.1.5"
  :description "Read build meta info"
  :url "https://github.com/xcoo/metabill"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
  :dependencies []
  :repl-options {:init-ns metabill.core}
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.10.1"]]}}
  :signing {:gpg-key "developer@xcoo.jp"})
