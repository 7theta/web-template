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

   ["resources/css/input.css" (helpers/render "resources/css/input.css" data)]
   ["resources/public/index.html" (helpers/render "resources/public/index.html" data)]

   ["src/{{sanitized}}/core.cljs" (helpers/render "src/core.cljs" data)]
   ["src/{{sanitized}}/macros.cljc" (helpers/render "src/macros.cljc" data)]
   ["src/{{sanitized}}/state.cljs" (helpers/render "src/state.cljs" data)]

   ["dev/user.clj" (helpers/render "dev/user.clj" data)]
   ["dev/dev.clj" (helpers/render "dev/dev.clj" data)]
   ["dev/dev.cljs" (helpers/render "dev/dev.cljs" data)]
   ["prod/{{sanitized}}/main.clj" (helpers/render "prod/main.clj" data)]])
