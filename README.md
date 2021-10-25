# clj-manifest-reader

[![Clojars Project](https://img.shields.io/clojars/v/xcoo/clj-build-date.svg)](https://clojars.org/xcoo/clj-build-date)

A minimal library to read build info from JAR/WAR manifests.

* Useful for prevent browsers from caching old js and css files.

## Prepare

Add this line in your `project.clj`.

```clojure
:manifest {"Build-Date" ~(fn [_] (System/currentTimeMillis))
           "Commit" ~(fn [_] (:out (clojure.java.shell/sh "git" "rev-parse" "--short" "HEAD")))}
```

## Usage

### with-build-date

```clojure
(ns hello-world.view
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [clj-build-date.core :refer [with-build-date]]))

(defn frame
  [req]
  (html5
   [:head
    [:title "Hello World!"]
    [:meta {:charset "utf-8"}]
    (include-css (with-build-date req "/css/main.css"))]
   [:body
    [:div#app]
    (include-js (with-build-date req "/js/main.js"))]))
```

![network](https://raw.githubusercontent.com/xcoo/clj-build-date/master/img/network.png)

### with-commit-hash

You can get an build commit hash.

## License

Copyright [Xcoo, Inc.][xcoo]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[xcoo]: https://xcoo.jp/
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
