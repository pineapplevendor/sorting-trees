(ns display-tree.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
	    [display-tree.views :as views]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" 
    [] 
    (views/home-page))
  (POST "/check-json"
    {params :params}
    (views/tree-view params))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
