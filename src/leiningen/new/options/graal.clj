;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   MIT License (https://opensource.org/licenses/MIT) which can also be
;;   found in the LICENSE file at the root of this distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.graal
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  (concat
   (when (options :graal)
     [["Dockerfile"
       (helpers/render "Dockerfile" data)]
      ["resources/META-INF/native-image/com.7theta/{{sanitized}}/native-image.properties"
       (helpers/render "native-image/native-image.properties" data)]
      ["resources/META-INF/native-image/com.7theta/{{sanitized}}/reflection-config.json"
       (helpers/render "native-image/reflection-config.json" data)]
      ["resources/META-INF/native-image/com.7theta/{{sanitized}}/resource-config.json"
       (helpers/render "native-image/resource-config.json" data)]])))
