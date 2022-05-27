(ns {{name}}.core
    (:require [{{name}}.state]
              [{{name}}.app :refer [app]]
              [{{name}}.views :refer [root-panel]]{{#routing?}}
              [{{name}}.modules :as modules]{{/routing?}}
              [tailwind.web]
              [helix.core :as hx :refer [$]]
              [react-dom]{{#routing?}}
              [reitit.frontend :as rf]
              [reitit.frontend.easy :as rfe]
              [signum.core :refer [dispatch]]{{/routing?}}))

{{#routing?}}
(def routes
  [["/"
    {:name :main
     :module :main}]])

(defn init-routing
  []
  (rfe/start!
   (rf/router routes {})
   #(dispatch [:application.route/set! %])
   {:use-fragment false})){{/routing?}}

(defn ^:export init
  []
  (tailwind.web/init)
  (enable-console-print!){{#routing?}}
  (init-routing)
  (modules/init!){{/routing?}}
  (react-dom/render ($ root-panel) (js/document.getElementById "app")))
