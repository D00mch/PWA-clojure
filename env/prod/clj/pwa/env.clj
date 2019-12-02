(ns pwa.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[pwa started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[pwa has shut down successfully]=-"))
   :middleware identity})
