(ns {{name}}.main
    (:require [{{name}}.config :refer [config]]
              [utilis.fn :refer [fsafe]]
              [utilis.types.number :refer [string->long]]
              [integrant.core :as ig])
    (:gen-class))

(defn -main [& args]
  (let [env (System/getenv)
        port (or ((fsafe string->long) (get env "PORT")) 8080)
        host (or (get env "HOST") "0.0.0.0")
        app (-> config
                (assoc-in [:via/http-server :host] host)
                (assoc-in [:via/http-server :port] port)
                ig/init)]
    (println "URL:" (str "http" "://" host ":" port))))
