(ns {{ns-name}}.state
    (:require [{{ns-name}}.app :refer [reset-app!]]
              [signum.signal :refer [signal alter!]]
              [signum.subs :refer [reg-sub subscribe]]
              [signum.events :refer [reg-event]]))

(def ^:private default-db {:hello "{{name}}"})
(defonce ^:private db (signal default-db))

{{#routing?}}
(reg-sub
 :application/route
 (fn [_]
   (:application/route @db)))

(reg-event
 :application/route-set!
 (fn [_ [_ route]]
   (alter! db assoc :application/route route)
   nil)){{/routing?}}

{{#auth?}}
(reg-sub
 :application/authenticated?
 (fn [_]
   (-> @db :authenticated :token)))

(reg-sub
 :application.authenticated/user
 (fn [_]
   (-> @db :authenticated :id)))

(reg-event
 :application.login/succeeded
 (fn [_ [_ login-creds]]
   (alter! db assoc :authenticated (:body login-creds))
   nil))

(reg-event
 :application.login/failed
 (fn [_ error]
   (js/console.error "application.login/failed" (pr-str error))
   (alter! db dissoc :authenticated)
   nil))

(reg-event
 :application.login/timed-out
 (fn [_ error]
   (js/console.error "application.login/timeout" (pr-str error))
   (alter! db dissoc :authenticated)
   nil))

(reg-event
 :application/logout
 (fn [_ _]
   (reset-app!)
   (alter! db (constantly default-db))
   nil))
{{/auth?}}

{{^auth?}}
(reg-sub
 :hello
 (fn [_]
   (:hello @db)))
{{/auth?}}
