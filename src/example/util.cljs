(ns example.util
  (:require [promesa.core :as p]
            [keechma.ui-component :as ui]
            [secretary.core :refer [decode-query-params encode-query-params]]
            [example.util.dimensions :refer [get-dimensions]]
            [example.rn :refer [async-storage]]
            [keechma.app-state.react-native-router :refer [navigate! route-atom]]))

(defn index-of [coll item]
 (loop [c coll 
        idx 0]
   (if-let [first-item (first c)]
     (if (= first-item item) 
       idx
       (recur (rest c) (inc idx)))
     nil)))

(defn navigate-replace! [payload]
  (let [current @route-atom
        clean-routes (into [] (filter #(not= (:key %1) (:key payload)) (:routes current)))
        new-routes (conj clean-routes payload)
        new (-> current
                (assoc :key (:key payload))
                (assoc :index (dec (count new-routes)))
                (assoc :routes new-routes))]
    (reset! route-atom new)))

(defn store-token-to-storage [token-key token]
  (p/promise
   (fn [resolve reject]
     (.setItem async-storage (name token-key) token #(resolve token)))))

(defn retrieve-token-from-storage [token-key]
  (->> (p/promise
        (fn [resolve reject]
          (.getItem async-storage (name token-key)
                    (fn [err val]
                      (resolve (if (empty? val) false val))))))))

(defn remove-token-from-storage [token-key]
  (p/promise
   (fn [resolve reject]
     (.removeItem async-storage (name token-key) #(resolve)))))

(defn get-location []
  (p/promise
   (fn [resolve reject]
     (.getCurrentPosition navigator.geolocation 
                          (fn [res]
                            (let [res (js->clj res :keywordize-keys true)] 
                              (if (empty? res)
                                false
                                (resolve res))))))))

(defn ind [x coll]
  (loop [ans [], coll coll, n 0]
    (if-let [[y & ys] (seq coll)]
      (recur (if (= x y) (conj ans n) ans) ys (inc n))
      ans)))

(defn route> [ctx]
  (deref (ui/current-route ctx)))

(defn current-route [route]
  (first (filter #(= (get-in route [:data :key]) (:key %)) (get-in route [:data :routes]))))

(defn current-route-data [route]
  (first (filter #(= (get-in route [:key]) (:key %)) (:routes route))))

(defn current-route> [ctx]
  (let [route (route> ctx)]
    (current-route route)))

(defn get-current-tab [current-route]
  (get-in current-route [:routes (:index current-route) :tab]))

(defn navigate-go! [payload]
  (let [route-atom keechma.app-state.react-native-router.route-atom
        current @route-atom
        routes (:routes current)
        route-keys (map :key routes)
        go-index (first (ind (:key payload) route-keys))
        remaining-part (if (nil? go-index) routes (into [] (first (split-at go-index routes))))
        new-routes (conj remaining-part payload)
        new-payload {:key (:key payload)
                     :index (dec (count new-routes))
                     :routes new-routes}]
    (reset! route-atom new-payload)))


(defn log [& args]
  (when js/goog.DEBUG
    (apply println args)))
