(defproject com.7theta/{{name}} "0.1.0"
  :dependencies [[org.clojure/clojure "1.11.1"]

                 [com.7theta/via "11.0.0"]{{#auth?}}
                 [com.7theta/via-auth "0.7.0"]{{/auth?}}{{#servo?}}
                 [com.7theta/servo "2.8.1"] {{/servo?}}
                 [com.7theta/ventus "1.0.1"]
                 [com.7theta/tailwind "0.4.0"]
                 [com.7theta/fontawesome "0.3.1"]

                 [metosin/reitit-ring "0.5.18"]{{#routing?}}
                 [metosin/reitit-frontend "0.5.18"]{{/routing?}}

                 [integrant "0.8.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[com.7theta/structor "0.8.0"]]}
             :uberjar {:source-paths ["prod"]
                       :resource-paths ["resources/public/js/compiled"]{{#graal?}}
                       :global-vars {*assert* false}
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"
                                  "-Dclojure.spec.skip-macros=true"]{{/graal?}}
                       :dependencies [[org.clojure/clojurescript "1.11.4"]]
                       :main {{name}}.main
                       :aot :all
                       :uberjar-name "{{name}}.jar"}}
  :prep-tasks ["compile"])
