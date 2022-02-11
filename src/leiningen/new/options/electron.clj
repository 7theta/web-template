;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   MIT License (https://opensource.org/licenses/MIT) which can also be
;;   found in the LICENSE file at the root of this distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.options.electron
  (:require [leiningen.new.options.helpers :as helpers]))

(defn files
  [data options]
  (concat
   (when (options :electron)
     [["electron/config.edn" (helpers/render "electron/config.edn" data)]
      ["electron/appicon.png" (helpers/resource-input "electron/appicon.png")]
      ["electron/splash/index.html" (helpers/render "electron/splash/index.html" data)]])))
