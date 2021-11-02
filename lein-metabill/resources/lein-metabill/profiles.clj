{:default {:manifest {"Build-Date" ~(fn [_] (System/currentTimeMillis))
                      "Commit" ~(fn [_] (:out (clojure.java.shell/sh "git" "rev-parse" "--short" "HEAD")))}}}
