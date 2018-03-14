(ns example.edb
  (:require [entitydb.core]
            [entitydb.util])
  (:require-macros [keechma.toolbox.edb :refer [defentitydb]]))

(def edb-schema {})
(defentitydb edb-schema)
