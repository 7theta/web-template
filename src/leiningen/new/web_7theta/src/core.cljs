(ns {{ns-name}}.core
    (:require [{{ns-name}}.state]
              [{{ns-name}}.app :refer [app]]
              [{{ns-name}}.views :refer [root-panel]]
              [helix.core :as hx :refer [$]]
              [react-dom]{{#routing?}}
              [reitit.frontend :as rf]
              [reitit.frontend.easy :as rfe]
              [signum.core :refer [dispatch]]{{/routing?}}))

{{#routing?}}
(def routes
  [["/"
    {:name :root
     :module :root}]])

(defn init-routing
  []
  (rfe/start!
   (rf/router routes {})
   #(dispatch [:application.route/set! %])
   {:use-fragment true})){{/routing?}}

(defn ^:export init
  []
  (enable-console-print!){{#routing?}}
  (init-routing){{/routing?}}
  (react-dom/render ($ root-panel) (js/document.getElementById "app")))
