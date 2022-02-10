(ns {{ns-name}}.ring-handler
    (:require [via.defaults :refer [default-via-endpoint]]
              [reitit.ring :refer [ring-handler router routes
                                   create-resource-handler
                                   create-default-handler]]
              [ring.util.response :as response]
              [integrant.core :as ig]))

(defmethod ig/init-key :{{ns-name}}/ring-handler [_ {:keys [via-handler]}]
  (ring-handler
   (router
    [[default-via-endpoint via-handler]
     ["/" (fn [_]
            (-> "public/index.html"
                response/resource-response
                (response/content-type "text/html")))]])
   (routes
    (create-resource-handler {:path "/"})
    (create-default-handler)))){{#graal?}}

;; boilerplate for graal
(defmethod response/resource-data :resource
  [^java.net.URL url]
  (let [conn (.openConnection url)]
    {:content (.getInputStream conn)
     :content-length (let [len (.getContentLength conn)] (if-not (pos? len) len))})){{/graal?}}
