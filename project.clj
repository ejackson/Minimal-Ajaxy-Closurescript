(defproject ajacs "1.0.0-SNAPSHOT"
  :description "Basic application"
  :dependencies [[org.clojure/clojure "1.3.0-beta1"]]
  :dev-dependencies [[swank-clojure "1.4.0-SNAPSHOT"]
                     [clojure-source "1.3.0-alpha5"]
                     [ring "0.3.11"]
                     [ring/ring-jetty-adapter "0.3.11"]
                     [ring/ring-devel "0.3.11"]
                     [compojure "0.6.5"]]
  :jvm-opts ["-agentlib:jdwp=transport=dt_socket,server=y,suspend=n"])


