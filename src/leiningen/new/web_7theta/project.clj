(defproject {{ns-name}} "0.1.0"
  :dependencies [[org.clojure/clojure "1.10.3"]

                 [com.7theta/via "10.0.0"]{{#auth?}}
                 [com.7theta/via-auth "0.5.2"]{{/auth?}}
                 [com.7theta/tailwind "0.3.0"]
                 [com.7theta/fontawesome "0.3.0"]

                 [lilactown/helix "0.1.5"]

                 [metosin/reitit-ring "0.5.15"]

                 [integrant "0.8.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[com.7theta/structor "0.4.0"]
                                  [integrant/repl "0.3.2"]]}
             :uberjar {:source-paths ["prod"]
                       :resource-paths ["resources/public/js/compiled"]{{#graal?}}
                       :global-vars {*assert* false}
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                  "-Dclojure.spec.skip-macros=true"
                                  "-Dorg.slf4j.simpleLogger.defaultLogLevel=warn"]{{/graal?}}
                       :dependencies [[org.clojure/clojurescript "1.11.4"]{{#graal?}}
                                      [org.slf4j/slf4j-simple "2.0.0-alpha5"]{{/graal?}}]
                       :main {{name}}.main
                       :aot :all
                       :uberjar-name "{{name}}.jar"}}
  :prep-tasks ["compile"])
