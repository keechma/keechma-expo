(ns example.rn
  (:require [reagent.core :as r]))

(def ReactNative (js/require "react-native"))

(def app-registry (.-AppRegistry ReactNative))
(def status-bar (.-StatusBar ReactNative))
(def stylesheet (.-StyleSheet ReactNative))
(def easing (.-Easing ReactNative))
(def animated (.-Animated ReactNative))
(def dimensions (.-Dimensions ReactNative))
(def platform (.-Platform ReactNative))

(def text (r/adapt-react-class (.-Text ReactNative)))
(def text-input (r/adapt-react-class (.-TextInput ReactNative)))
(def view (r/adapt-react-class (.-View ReactNative)))
(def scroll-view (r/adapt-react-class (.-ScrollView ReactNative)))
(def list-view (r/adapt-react-class (.-ListView ReactNative)))
(def animated-view (r/adapt-react-class (.-View animated)))
(def image (r/adapt-react-class (.-Image ReactNative)))
(def touchable-highlight (r/adapt-react-class (.-TouchableHighlight ReactNative)))
(def touchable-opacity (r/adapt-react-class (.-TouchableOpacity ReactNative)))
(def touchable-without-feedback (r/adapt-react-class (.-TouchableWithoutFeedback ReactNative)))
(def switch (r/adapt-react-class (.-Switch ReactNative)))
(def activity-indicator (r/adapt-react-class (.-ActivityIndicator ReactNative)))

(def data-source (.. ReactNative -ListView -DataSource))
(def async-storage (.-AsyncStorage ReactNative))
(def linking (.-Linking ReactNative))
