(ns example.controllers
  (:require [keechma.toolbox.forms.controller :as forms-controller]
            [keechma.toolbox.forms.mount-controller :as forms-mount-controller]
            [keechma.toolbox.dataloader.controller :as dataloader-controller]
            [example.forms :as forms]
            [example.datasources :refer [datasources]]
            [example.edb :refer [edb-schema]]))

(def controllers
  (-> {}
      (forms-controller/register forms/forms)
      (forms-mount-controller/register forms/form-params)
      (dataloader-controller/register datasources edb-schema)))
