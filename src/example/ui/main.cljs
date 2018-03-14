(ns example.ui.main
  (:require [keechma.ui-component :as ui]
            [reagent.core :as r]
            [keechma.toolbox.ui :refer [sub> <cmd]]
            [example.util :refer [current-route> navigate-go!]]
            [example.rn :refer [view text]]))

(defn render [ctx]
  [view [text "Hello from Keechma"]])

(def component (ui/constructor {:renderer render
                                :subscription-deps []}))
