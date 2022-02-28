(ns {{name}}.macros
    #?(:cljs (:require-macros [{{name}}.macros]))
    (:require [helix.core :as helix]
              [helix.dom :as helix-dom]))

#?(:clj
   (defmacro defnc [type params & body]
     (let [opts? (map? (first body))
           opts (if opts? (first body) {})
           body (if opts? (rest body) body)
           default-opts {:helix/features {:fast-refresh true}}]
       `(helix.core/defnc ~type ~params
          ~(merge default-opts opts)
          ~@body))))
