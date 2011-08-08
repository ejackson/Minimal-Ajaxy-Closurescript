(ns multiplier
  (:require
   [cljs.reader :as reader]
   [goog.dom :as dom]
   [goog.events :as ev]
   [goog.string :as gstr]
   [goog.events.Event :as events]
   [goog.net.XhrIo :as xhr]))      

;; HTTP Stuff
(defn- multiply-api [form]
  (str "http://localhost:9090/multiply?form=" (pr-str form)))

(defn- extract-response [message]
  (reader/read-string
   (. message/target (getResponseText))))

;; DOM Stuff
(defn- extract-from-dom
  "Clearly some form validation is in order here"
  [id]
  (gstr/toNumber (.value (dom/getElement id))))

(defn- insert-into-dom
  [id val]
  (let [target (dom/getElement id)]
    (dom/setTextContent target val)))

;; Request and callbacks
(defn multiply-callback
  "Process multiplication responses from server"
  [message]
  (let [prod     (extract-response message)
        prod-str (:product prod)]
    (insert-into-dom "product" prod-str)))

(defn multiply-request
  "Send a request for multiplication to the server"
  [e]
  (let [x1 (extract-from-dom "multiplier")
        x2 (extract-from-dom "multiplicand")
        request {"x1" x1 "x2" x2}]
    (xhr/send (multiply-api request) multiply-callback)))

;; Main entry function
(defn ^:export main []
  ;; Wire up the button
  (ev/listen
   (dom/getElement "multiply-button")
   "click"
   multiply-request))      

