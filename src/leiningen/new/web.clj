;;   Copyright (c) 7theta. All rights reserved.
;;   The use and distribution terms for this software are covered by the
;;   MIT License (https://opensource.org/licenses/MIT) which can also be
;;   found in the LICENSE file at the root of this distribution.
;;
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any others, from this software.

(ns leiningen.new.web
  (:require [leiningen.core.main :as main]
            [leiningen.new.templates :refer [name-to-path sanitize-ns ->files]]
            [leiningen.new.options.helpers :as helpers]
            [leiningen.new.options.base :as base]
            [leiningen.new.options.views :as views]
            [leiningen.new.options.via :as via]
            [leiningen.new.options.graal :as graal]
            [leiningen.new.options.electron :as electron]
            [clojure.set :as set]
            [clojure.string :as st]))

(declare template-data check-options app-files)

(def available-options
  #{"+servo" "+routing" "+auth" "+graal" "+electron"})

(defn web
  [name & options]
  (check-options options)
  (let [options (->> options
                     (filter available-options)
                     (map #(keyword (apply str (rest %))))
                     set)
        data (template-data name options)]
    (main/info "Generating a project with the 7theta web template.")
    (apply ->files data (app-files data options))))


;;; Private

(defn- check-options
  [options]
  (let [options-set (into #{} options)]
    (when-not (set/superset? available-options options-set)
      (main/abort (str "\nError: invalid option(s)\nAvailable: "
                       (st/join " " (sort available-options)) "\n")))))

(defn- template-data
  [name options]
  {:name name
   :ns-name (sanitize-ns name)
   :sanitized (name-to-path name)
   :servo? (helpers/option-renderer options :servo)
   :routing? (helpers/option-renderer options :routing)
   :auth? (helpers/option-renderer options :auth)
   :graal? (helpers/option-renderer options :graal)
   :electron? (helpers/option-renderer options :electron)})

(defn- app-files
  [data options]
  (->> (concat
        (base/files data options)
        (views/files data options)
        (graal/files data options)
        (electron/files data options))
       (remove nil?)))
