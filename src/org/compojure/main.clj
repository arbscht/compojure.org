(ns org.compojure.main
  (:use compojure))

(def header
  [:div#header
    [:h1 [:span "Compojure"]]])

(def tabs
  [:ul#tabs
    [:li.current "Home"]
    [:li "Documentation"]
    [:li "Download"]])

(defn site-page [title & body]
  (html
    [:html
      [:head
        (include-css "css/reset.css")
        (include-css "css/screen.css")
        (include-css "css/shCore.css")
        (include-css "css/syntax.css")
        (include-js  "js/shCore.js")
        (include-js  "js/shClojure.js")
        (javascript-tag
          "SyntaxHighlighter.all({light: true});")
        [:title title]]
      [:body
        header
        tabs
        body]]))

(def example-code
";; Example
(defroutes greetings
  (GET \"/\"
    (html [:h1 \"Hello (World)\"]))
  (ANY \"*\"
    (page-not-found)))")

(defn source-code [src]
  [:pre {:class "code brush:clojure"} src])

(defn index []
  (site-page "Compojure"
    [:h2#title "Compojure is&#133;"]
    [:div#content
     ; "Lorem Ipsum"
      (source-code example-code)]))

(defroutes root
  (GET "/"
    (index))
  (ANY "*"
    (or (serve-file (params :*))
        :next))
  (ANY "*"
    (page-not-found)))
