(ns example.core
    (:require [reagent.core :as r :refer [atom]]
              [keechma.app-state :as app-state]
              [example.ui :refer [ui]]
              [example.controllers :refer [controllers]]
              [example.subscriptions :refer [subscriptions]]))

(defonce running-app (r/atom nil))

(js/require "expo")

(def ReactNative (js/require "react-native"))
(def app-registry (.-AppRegistry ReactNative))


(def app-definition
  {:controllers controllers
   :components ui
   :subscriptions subscriptions
   :router :react-native})

(defn start-app! []
  (reset! running-app (app-state/start! app-definition)))

(defn reload []
  (let [current @running-app]
    (if current
      (app-state/stop! current start-app!)
      (start-app!))))

(defn app-root []
  (let [component (:main-component @running-app)] 
    (when component
      [component])))

(defn init []
  (start-app!)
  (.registerComponent app-registry "main" #(r/reactify-component app-root)))

