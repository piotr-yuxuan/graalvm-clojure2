(def clojure-target-version (or (System/getenv "CLOJURE_VERSION") "1.10.3"))
(defproject aws-api-s3 "0.1.0-SNAPSHOT"

  :dependencies [[org.clojure/clojure         ~clojure-target-version]
                 [com.cognitect.aws/api       "0.8.484"]
                 [com.cognitect.aws/endpoints "1.1.11.926"]
                 [com.cognitect.aws/s3        "810.2.817.0"]]

  :main simple.main

  :uberjar-name "simple-main.jar"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-shell "0.5.0"]]}}

  :aliases
  {"native-config"
   ["shell"
    ;; run the application to infer the build configuration
    "java" "-agentlib:native-image-agent=config-output-dir=./target/config/"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"]

   "native"
   ["shell"
    "native-image" "--report-unsupported-elements-at-runtime" "--no-server" "--no-fallback"
    "-H:+PrintClassInitialization"
    "-H:ConfigurationFileDirectories=./target/config/"
    "--initialize-at-build-time"
    "--allow-incomplete-classpath"
    "--enable-http" "--enable-https" "--enable-all-security-services"
    "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
    "-H:Name=./target/${:name}"]

   "run-native" ["shell" "./target/${:name}"]})
