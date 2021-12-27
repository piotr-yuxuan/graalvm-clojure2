(def clojure-target-version (or (System/getenv "CLOJURE_VERSION") "1.10.3"))
(defproject datascript "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure ~clojure-target-version]
                 [datascript "1.0.3"]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:ReflectionConfigurationFiles=reflect-config.json"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
