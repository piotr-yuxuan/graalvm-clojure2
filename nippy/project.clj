(def clojure-target-version (or (System/getenv "CLOJURE_VERSION") "1.10.3"))
(defproject nippy "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure ~clojure-target-version]
                 [com.taoensso/nippy "2.14.0"]]

  :main simple.main

  :uberjar-name "simple-main.jar"

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime"
    "-H:ReflectionConfigurationFiles=reflection-config.json"
    "--initialize-at-build-time=clojure,taoensso,simple"
    "-H:+AllowIncompleteClasspath"
    "-H:SerializationConfigurationFiles=serialization-config.json"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]
   "run-native" ["shell" "./target/${:name}"]})
