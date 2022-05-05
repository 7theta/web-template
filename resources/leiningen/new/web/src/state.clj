(ns {{name}}.state
    (:require [via.endpoint :as via]{{#auth?}}
              [via-auth.id-password :as auth]{{/auth?}}
              [signum.subs :refer [reg-sub subscribe]]
              [signum.events :refer [reg-event dispatch]]
              [integrant.core :as ig]
              [utilis.jar :as jar]))

(defmethod ig/init-key :{{name}}/state [_ _])

(reg-sub
 :application/jar-version{{#auth?}}
 [#'auth/interceptor]{{/auth?}}
 (fn [_query-v]
   (jar/version "com.7theta" "{{name}}")))
