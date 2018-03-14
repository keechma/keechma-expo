(ns example.util.dimensions
  (:require [example.rn :refer [status-bar dimensions] :rename {dimensions req-dimensions}]))

(defn adjust-height [dim height]
  (assoc dim :height (- (:height dim) height)))

(defn get-dimensions []
  (let [status-height (or (.-currentHeight status-bar) 0)
        dimensions (adjust-height (js->clj (.get req-dimensions "window") :keywordize-keys true) status-height)]
    {:top-padding (if (= 0 status-height) 20 0)
     :dimensions dimensions}))
