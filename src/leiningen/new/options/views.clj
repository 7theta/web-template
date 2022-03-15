;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   MIT License (https://opensource.org/licenses/MIT) which can also be
;;   found in the LICENSE file at the root of this distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.views
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  (concat
   [["src/{{sanitized}}/views.cljs" (helpers/render "src/views.cljs" data)]]
   (when (options :routing)
     [["src/{{sanitized}}/modules.cljc" (helpers/render "src/modules.cljc" data)]])))
