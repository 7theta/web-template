(ns {{name}}.config
    (:require [via.endpoint]
              [via.subs]
              [via.defaults :as defaults]
              [integrant.core :as ig]))

(def config
  {:via/endpoint {:peers #{defaults/default-via-url}}
   :via/subs {:endpoint (ig/ref :via/endpoint)}})
