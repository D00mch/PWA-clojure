# clojure pwa example

generated using Luminus version "3.54"

PWA is implemented with [Page-renderer][3].

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## How to implement it from scratch

Create a project 

    lein new luminus pwa +cljs +reagent

Swap `resources/public/favicon.ico` with `fovicon.png` and add a [manifest.json][2] file. Be sure you add icons specified in the `resources/public/img/icons` folder. 

[2]: https://github.com/Liverm0r/PWA-clojure/blob/master/resources/public/manifest.json

Provide [Page-renderer][3] in project.clj dependencies:

```clojure
(defproject pwa "0.1.0-SNAPSHOT"
  ...
  :dependencies [[page-renderer "0.4.4"]... ]... }}) 
```

This Page-renderer library will do all the dirty work with the [service-worker][4] creation.

Now we can provide a service worker in our routes. Open [src/clj/pwa/routes/home.clj][5] and add:

```clojure
(ns pwa.routes.home
  (:require
   ...
   [page-renderer.api :as pr]))

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
     
     (defn home-routes []
       [""
        {:middleware [middleware/wrap-csrf
                      middleware/wrap-formats]}
        ["/" {:get home-page}]
        ["/app" {:get home-page}page]
        ["/service-worker.js" {:get service-worker}]])
```

[3]: https://github.com/spacegangster/page-renderer
[4]: https://developers.google.com/web/ilt/pwa/introduction-to-service-worker
[5]: https://github.com/Liverm0r/PWA-clojure/blob/master/src/clj/pwa/routes/home.clj

Refer specified manifest, favicon, and a screen.css in the `head` of `resources/html/home.html`

```html
<link rel="stylesheet" type="text/css" href="screen.css" media="all">
<link rel="manifest" href="/manifest.json">
<link rel="icon" type="image/png" href="/favicon.png"/>
```

Register you service worker in the `body` of `resources/html/home.html`:

```html
<script>
 if('serviceWorker' in navigator) {
     navigator.serviceWorker.register('/service-worker.js')
              .then(function() {
                  console.log('Service Worker Registered');
              });
 }
</script>
```

Make sure you `src/cljs/pwa/core.cljs` looks like [this][5], and you are ready to go.

[5]: https://github.com/Liverm0r/PWA-clojure/blob/master/src/cljs/pwa/core.cljs

## Running

To start a web server for the application, run:

    lein run 
    lein figwheel

## License
```
Copyright 2019 Artur Dumchev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
