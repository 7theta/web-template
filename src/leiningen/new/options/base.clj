;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   MIT License (https://opensource.org/licenses/MIT) which can also be
;;   found in the LICENSE file at the root of this distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.base
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  [["README.md" (helpers/render "README.md" data)]
   ["project.clj" (helpers/render "project.clj" data)]
   ["package.json" (helpers/render "package.json" data)]
   ["tailwind.config.js" (helpers/render "tailwind.config.js" data)]
   ["shadow-cljs.edn" (helpers/render "shadow-cljs.edn" data)]
   [".gitignore" (helpers/render "gitignore" data)]
   [".java-version" (helpers/render "java-version" data)]
   ["Makefile" (helpers/render "Makefile" data)]

   ["resources/.keep" ""]
   ["dev-resources/css/input.css" (helpers/render "dev-resources/css/input.css" data)]
   ["dev-resources/templates/index.html" (helpers/render "dev-resources/templates/index.html" data)]

   ["src/{{sanitized}}/core.cljs" (helpers/render "src/core.cljs" data)]
   ["src/{{sanitized}}/app.cljs" (helpers/render "src/app.cljs" data)]
   ["src/{{sanitized}}/state.cljs" (helpers/render "src/state.cljs" data)]

   ["src/{{sanitized}}/ring_handler.clj" (helpers/render "src/ring_handler.clj" data)]
   ["src/{{sanitized}}/state.clj" (helpers/render "src/state.clj" data)]
   (when (options :auth)
     ["src/{{sanitized}}/user_store.clj" (helpers/render "src/user_store.clj" data)])

   ["src/{{sanitized}}/config.clj" (helpers/render "src/config.clj" data)]
   ["src/{{sanitized}}/config.cljs" (helpers/render "src/config.cljs" data)]

   ["dev-resources/certs/gen-server.sh" (helpers/render "dev-resources/certs/gen-server.sh" data)]
   ["dev-resources/certs/server.crt" (helpers/render "dev-resources/certs/server.crt" data)]
   ["dev-resources/certs/server.key" (helpers/render "dev-resources/certs/server.key" data)]

   ["dev/user.clj" (helpers/render "dev/user.clj" data)]
   ["dev/dev.clj" (helpers/render "dev/dev.clj" data)]
   ["dev/dev.cljs" (helpers/render "dev/dev.cljs" data)]
   ["prod/{{sanitized}}/main.clj" (helpers/render "prod/main.clj" data)]])
