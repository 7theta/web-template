# {{name}}

## Development Build

### Run application:

From a repl, run the following commands
```
user> (dev)
dev> (go)
```

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

Shadow-cljs will automatically push cljs changes to the browser.

## Production Build

To build the uberjar release

```
lein run -m structor.builder/release
```

{{#electron?}}
To package as an electron app
```
cp target/{{name}}.jar electron/{{name}}.jar
lein run -m structor.electron/release
```
{{/electron?}}

{{#graal?}}
To build a graal based docker container
```
docker build -t {{name}}:`head -n 1 ./project.clj | cut -d " " -f 3 | sed 's/"//g'` .
```
{{/graal?}}

## Copyright and License

Copyright © 2022 7theta

Distributed under the MIT License.
