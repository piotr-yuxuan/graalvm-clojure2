(def clojure-target-version (or (System/getenv "CLOJURE_VERSION") "1.10.3"))
(defproject spec-graal "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure ~clojure-target-version]
                 [org.clojure/spec.alpha "0.2.187"]]

  :main spec-graal.main

  :uberjar-name "spec-graal.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]
   "run-native" ["shell" "./target/${:name}"]})
