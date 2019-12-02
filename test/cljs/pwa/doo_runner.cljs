(ns pwa.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [pwa.core-test]))

(doo-tests 'pwa.core-test)

