# com.7theta/web

[![Current Version](https://img.shields.io/clojars/v/com.7theta/lein-template.web.svg)](https://clojars.org/com.7theta/lein-template.web)
[![GitHub license](https://img.shields.io/github/license/7theta/web-template.svg)](LICENSE)

Leiningen template for web apps based on 7theta libraries.

## Usage

The base template includes:

* [via](https://github.com/7theta/via)
* [shadow-cljs](http://shadow-cljs.org/)
* [cljs-devtools](https://github.com/binaryage/cljs-devtools)
    1. Open Chrome's DevTools,`Ctrl-Shift-i`;
    1. open "Settings", `F1`;
    1. Check "Enable custom formatters" under the "Console" section;
    1. close and re-open DevTools

To create an application with the base template:

```
lein new com.7theta/web <project-name>
```

The optional profiles include:

* [servo](https://github.com/7theta/servo) (`+servo`)
* [routing](https://github.com/metosin/reitit) (`+routing`) based on `metosin/reitit`
* [authentication](https://github.com/7theta/via-auth) (`+auth`) based on `via`
* [electron](https://www.electronjs.org/) (`+electron`) using `structor`
* [graalvm native-image](https://www.graalvm.org/reference-manual/native-image/) (`+graal`)

To add a profile to the base template, append the profile name(s). E.g.,

```
lein new com.7theta/web <project-name> +servo +auth +graal
```


## Copyright and License

Copyright © 2022 7theta

Distributed under the MIT License.
