(ns {{name}}.config
    (:require [integrant.core :as ig]))

(def config
  {:{{name}}/subs {}

   :{{name}}/events {}

   {{#auth?}}
   :{{name}}/user-store {}

   :via-auth/id-password
   {:query-fn (ig/ref :{{name}}/user-store)
    :endpoint (ig/ref :via/endpoint)}{{/auth?}}

   :via/endpoint
   {:exports {:namespaces #{:{{name}}/subs :{{name}}/events}}}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/http-server
   {:ring-handler (ig/ref :{{name}}/ring-handler)}

   :{{name}}/ring-handler
   {:via-handler (ig/ref :via/endpoint)}{{#servo?}}

   :servo/connection
   {:db-server {:host "localhost" :port 28015 :timeout 100}
    :db-name "{{name}}"
    :tables []
    :indices []}

   :servo/subs
   {:db-connection (ig/ref :servo/connection)}{{/servo?}}})

(ig/load-namespaces config)
