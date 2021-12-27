(def clojure-target-version (or (System/getenv "CLOJURE_VERSION") "1.10.3"))
(defproject safely "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure ~clojure-target-version]
                 [com.brunobonacci/safely "0.5.0-alpha6"]
                 ;; to use with Log4J
                 [org.slf4j/slf4j-log4j12 "1.7.25"]
                 ;; see also resource/log4j.properties
                 ]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server"
    ;; to use without logging
    ;;"--allow-incomplete-classpath"
    "--initialize-at-build-time"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}" "README.md"]})
