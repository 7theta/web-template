(defproject com.7theta/{{name}} "0.1.0"
  :dependencies [[org.clojure/clojure "1.11.0"]

                 [com.7theta/via "10.2.1"]{{#auth?}}
                 [com.7theta/via-auth "0.7.0"]{{/auth?}}{{#servo?}}
                 [com.7theta/servo "2.8.1"] {{/servo?}}
                 [com.7theta/tailwind "0.3.0"]
                 [com.7theta/fontawesome "0.3.0"]

                 [lilactown/helix "0.1.5"]

                 [metosin/reitit-ring "0.5.17"]{{#routing?}}
                 [metosin/reitit-frontend "0.5.17"]{{/routing?}}

                 [integrant "0.8.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[com.7theta/structor "0.5.2"]
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
