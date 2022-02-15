(ns {{name}}.ring-handler
    (:require [via.defaults :refer [default-via-endpoint]]
              [reitit.ring :refer [ring-handler router routes
                                   create-resource-handler
                                   create-default-handler]]
              [ring.util.response :as response]
              [integrant.core :as ig]))

(defmethod ig/init-key :{{name}}/ring-handler [_ {:keys [via-handler]}]
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

(defmethod response/resource-data :resource
  [^java.net.URL url]
  (let [connection (.openConnection url)
        length (#'ring.util.response/connection-content-length connection)]
    (when (pos? length)
      {:content (.getInputStream connection)
       :content-length length
       :last-modified (#'ring.util.response/connection-last-modified connection)}))){{/graal?}}
