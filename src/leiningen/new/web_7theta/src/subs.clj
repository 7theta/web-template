(ns {{ns-name}}.subs
    (:require [via.endpoint :as via]{{#auth?}}
              [via-auth.id-password :as auth]{{/auth?}}
              [signum.subs :refer [reg-sub subscribe]]
              [integrant.core :as ig]))

(defmethod ig/init-key :{{name}}/subs [_ _])

(reg-sub
 :application/hello{{#auth?}}
 [#'auth/interceptor]{{/auth?}}
 (fn [_query-v]
   "Hello from via!"))
