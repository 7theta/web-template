(ns {{ns-name}}.config
    (:require [integrant.core :as ig]))

(def config
  {:via/endpoint {:exports {:namespaces #{:{{name}}/subs
                                          :{{name}}/events}}}

   :via/subs
   {:endpoint (ig/ref :via/endpoint)}

   :via/http-server
   {:ring-handler (ig/ref :{{name}}/ring-handler)}

   :{{name}}/subs {}

   :{{name}}/events {}

   {{#auth?}}
   :{{name}}/user-store {}

   :via-auth/id-password
   {:query-fn (ig/ref :{{name}}/user-store)
    :endpoint (ig/ref :via/endpoint)}{{/auth?}}

   :{{name}}/ring-handler
   {:via-handler (ig/ref :via/endpoint)}})

(ig/load-namespaces config)
