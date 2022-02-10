(ns dev
  "Tools for interactive development with the REPL. This file should
  not be included in a production build of the application."
  (:require [{{name}}.config :refer [config]]

            [integrant.core :as ig]
            [integrant.repl :refer [clear go halt init reset reset-all]]
            [integrant.repl.state :refer [system]]

            [clojure.tools.namespace.repl :refer [refresh refresh-all disable-reload!]]
            [clojure.repl :refer [apropos dir doc find-doc pst source]]
            [clojure.test :refer [run-tests run-all-tests]]
            [clojure.pprint :refer [pprint]]
            [clojure.reflect :refer [reflect]]

            [signum.subs :refer [subscribe]]))

(disable-reload! (find-ns 'integrant.core))

(def dev-config
  (-> config
      (assoc :structor.builder/watcher {:build-id :dev})
      (assoc-in [:via/http-server :port] 3449)))

(ig/load-namespaces dev-config)

(integrant.repl/set-prep! (constantly dev-config))
