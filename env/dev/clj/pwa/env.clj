(ns pwa.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [pwa.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[pwa started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[pwa has shut down successfully]=-"))
   :middleware wrap-dev})
