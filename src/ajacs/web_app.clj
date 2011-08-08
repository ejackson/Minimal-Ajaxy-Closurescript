(ns ajacs.web-app
  "This exposes the services.  It is a Ring server."
  (:use compojure.core
        compojure.handler
        ring.middleware.file
        ring.adapter.jetty)
  (:require [compojure.route :as route]))

(defn multiply [{x1 "x1", x2 "x2", :as p}]
  (assoc p :product (* x1 x2)))

;; --------------------------------------------------------------------------
;; The router
;; The .html files in ./www serve the main interface. Note multiply.html

(defroutes main-routes
  (GET "/" [] "You want /multiplier.html")

  ;; The API
  (GET "/multiply" {params :params}
       (pr-str (multiply (read-string (:form params)))))
  
  ;; Catch all
  (route/not-found "<h1>Page not found</h1>"))

;; --------------------------------------------------------------------------
;; The server
(def serve
  (run-jetty (-> (site main-routes)
                 (wrap-file "www"))
             {:port 9090 :join? false}))

(.start serve)
#_(.stop serve)

