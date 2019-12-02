(ns pwa.routes.home
  (:require
   [pwa.layout :as layout]
   [clojure.java.io :as io]
   [pwa.middleware :as middleware]
   [ring.util.response]
   [page-renderer.api :as pr]
   [ring.util.http-response :as response]))

(defn service-worker [request]
  (pr/respond-service-worker
   {:script "/js/app.js"
    :sw-default-url "/app"
    :sw-add-assets
    ["/css/screen.css"
     "/img/warning_clojure.png"
     "/img/icons/icon-72x72.png"
     "/img/icons/icon-96x96.png"
     "/img/icons/icon-128x128.png"
     "/img/icons/icon-144x144.png"
     "/img/icons/icon-152x152.png"
     "/img/icons/icon-192x192.png"
     "/img/icons/icon-384x384.png"
     "/img/icons/icon-512x512.png"]}))

(defn home-page [request]
  (layout/render request "home.html"))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}
   ["/" {:get home-page}]
   ["/service-worker.js" {:get service-worker}]
   ["/app" {:get home-page}]])

