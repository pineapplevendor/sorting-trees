(ns display-tree.views
  (:require [clojure.string :as str]
            [hiccup.page :as page]
	    [clojure.data.json :as json]
            [ring.util.anti-forgery :as util]))

(defn home-page
  []
  (page/html5
    [:h1 "Categorize Some Stuff!"]
    [:p "Upload a JSON file formatted like the one in the README to visualize 
    it and check for duplicate ids."]
    [:form {:action "/check-json" :method "POST"}
     (util/anti-forgery-field)
     [:p "JSON Sorting Tree:" 
	[:p [:textarea {:name "json-tree" :cols 120 :rows 24}]]]
     [:p [:input {:type "submit" :value "check and visualize JSON"}]]]))

(defn render-branch
  [branch] 
  [:div {:class "branch"}
    (page/include-css "/css/styles.css")
    [:div {:class "branch"} (concat (:id branch) ": " (:description branch))]
    (map render-branch (get branch :children []))])

(defn get-ids-in-branch
  [branch]
  (cons
    (:id branch)
    (reduce concat (map get-ids-in-branch (get branch :children [])))))

(defn get-ids
  [tree]
  (reduce concat (map get-ids-in-branch tree)))

(defn get-duplicate-ids
  [sort-tree]
  (let [ids (get-ids sort-tree)]
    (for [[id freq] (frequencies ids)
          :when (> freq 1)]
     id)))

(defn get-max-id
  [sort-tree]
  (let [ids (get-ids sort-tree)]
    (reduce max (map read-string ids))))

(defn tree-view
  [{:keys [json-tree]}]
  (let [sort-tree (json/read-str json-tree :key-fn keyword)]
    (page/html5
      [:p (concat 
            "List of duplicate ids: " 
	    (pr-str (get-duplicate-ids sort-tree)))]
      [:p (concat
	    "Max id used in tree: "
	    (str (get-max-id sort-tree)))]
      (map render-branch sort-tree))))

