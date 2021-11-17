# metabill

<!-- [![Clojars Project](https://img.shields.io/clojars/v/xcoo/clj-build-date.svg)](https://clojars.org/xcoo/clj-build-date) -->

A minimal library to read build info.

## Prepare

Put `[lein-metabill "0.1.0"]` into the `:plugins` vector of your project.clj.
And run `lein metabill` before building system:

```
$ lein metabill
output metabill.edn
```

You get `metabill.edn`.

## Usage

### with-build-date

This function is useful for prevent browsers from caching old js and css files.

```clojure
(ns hello-world.view
  (:require [hiccup.page :refer [html5 include-css include-js]]
            [metabill.core :refer [with-build-date]]))

(defn frame
  [req]
  (html5
   [:head
    [:title "Hello World!"]
    [:meta {:charset "utf-8"}]
    (include-css (with-build-date "/css/main.css"))]
   [:body
    [:div#app]
    (include-js (with-build-date "/js/main.js"))]))
```

![network](https://raw.githubusercontent.com/xcoo/clj-build-date/master/img/network.png)

### with-commit-hash

You can get an build commit hash.

## License

Copyright [Xcoo, Inc.][xcoo]

Licensed under the [Apache License, Version 2.0][apache-license-2.0].

[xcoo]: https://xcoo.jp/
[apache-license-2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
