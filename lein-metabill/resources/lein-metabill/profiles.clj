{:default {:manifest {"Commit" ~(fn [_] (:out (clojure.java.shell/sh "git" "rev-parse" "--short" "HEAD")))}}}
