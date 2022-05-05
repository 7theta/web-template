(ns {{name}}.modules
    #?(:clj (:require [cljs.env :as env]
                      [cljs.analyzer :as ana]
                      [clojure.string :as st]))
    #?(:cljs (:require-macros [{{name}}.modules]))
    #?(:cljs (:require [ventus.macros :refer [defnc]]
                       [helix.core :refer [$ <>]]
                       [helix.dom :refer [div]]
                       [helix.hooks :refer [use-effect]]
                       [shadow.lazy :as lazy]
                       [utilis.js :as j]
                       [signum.signal :refer [signal alter!]]
                       [signum.hooks :refer [use-signal]])))

#?(:cljs (defonce known-modules (signal nil)))
#?(:cljs (defonce components (signal nil)))
#?(:cljs (defnc default-loading [] (<>)))
#?(:cljs (defnc default-not-found [] (<>)))

#?(:cljs (declare use-module))

#?(:clj
   (defmacro register!
     [module component]
     `(signum.signal/alter! {{name}}.modules/components #(assoc % ~module ~component))))

#?(:cljs
   (defnc router
     [{:keys [module props loading not-found]
       :or {loading default-loading
            not-found default-not-found}}]
     (let [components (use-signal components)
           module? (use-module module)]
       (if-let [component (get components module)]
         ($ component props)
         (if module?
           ($ loading)
           ($ not-found))))))

#?(:clj
   (defmacro register-all-modules!
     []
     `(signum.signal/alter!
       known-modules (constantly
                      ~(->> (:shadow.lazy/ns->mod @env/*compiler*)
                            vals
                            (map keyword)
                            distinct
                            set)))))

#?(:cljs
   (defn init!
     []
     ({{name}}.modules/register-all-modules!)))

;;; Private

#?(:cljs
   (defn load-module
     [module]
     (-> (lazy/Loadable. [(name module)] (fn []))
         lazy/load
         (j/call :then
                 (fn [] (js/console.debug (str "Loaded module " (name module) ".")))
                 (fn [error]
                   (js/console.error
                    (str "Failed to load module "
                         (name module)
                         ".")
                    error))))))

#?(:cljs
   (defn use-module
     [module]
     (let [modules (set (use-signal known-modules))]
       (use-effect
        [module modules]
        (when (and module
                   (seq modules)
                   (get modules module))
          (load-module module)))
       (boolean (modules module)))))
