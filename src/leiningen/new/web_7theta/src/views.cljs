(ns {{name}}.views
    (:require [{{name}}.macros :refer [defnc]]{{#routing?}}
              [{{name}}.modules :as modules]{{/routing?}}
              [helix.core :refer [$]]
              [helix.dom :refer [div button]]
              [tailwind.core :refer [tw]]
              [via.core :refer [subscribe dispatch invoke]]
              [signum.hooks :refer [use-signal]]))

(defnc root-panel
  []
  (let [{{#auth?}}authenticated? (use-signal (subscribe [:application/authenticated?]))
        credentials (use-signal (subscribe [:application.authenticated/user]))
        button-classes (tw [:inline-flex :items-center :px-3 :py-2 :rounded-md
                            :bg-blue-600 :text-white :text-sm :leading-4 :font-medium
                            :border :border-transparent :shadow-sm
                            :hover:bg-blue-700 :focus:outline-none
                            :focus:ring-2 :focus:ring-offset-2 :focus:ring-blue-500]){{/auth?}}
        hello (use-signal (subscribe [:application/hello]))]
    (div {:class (tw [:p-8])}
         {{#auth?}}
         (if authenticated?
           (div {:class (tw [:flex :flex-row :items-center :space-x-2])}
                (div (str "Logged in as " (pr-str credentials)))
                (button {:class button-classes
                         :on-click #(dispatch [:application/logout])} "Logout"))
           (button {:class button-classes
                    :on-click (fn []
                                (invoke [:via.auth/id-password-login {:id "admin" :password "admin"}]
                                        {:on-success #(dispatch [:application.login/succeeded %])
                                         :on-failure #(dispatch [:application.login/failed %])
                                         :on-timeout #(dispatch [:application.login/timed-out %])}))} "Login"))
         (when authenticated?
           (div (str hello)))
         {{/auth?}}
         {{^auth?}}
         (div "Hello from " (use-signal (subscribe [:hello])))
         (div (str hello))
         {{/auth?}})))
