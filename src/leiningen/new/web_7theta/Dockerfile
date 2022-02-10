FROM ghcr.io/graalvm/graalvm-ce:java17-21.3 AS build

RUN gu install native-image
ARG MUSLC_TOOLCHAIN="/musl"

RUN mkdir ${MUSLC_TOOLCHAIN} && \
    curl -L -o musl.tar.gz https://more.musl.cc/10.2.1/x86_64-linux-musl/x86_64-linux-musl-native.tgz && \
    tar -xvzf musl.tar.gz -C musl --strip-components 1 && \
    cp /usr/lib/gcc/x86_64-redhat-linux/8/libstdc++.a ${MUSLC_TOOLCHAIN}/lib/

ENV CC=/musl/bin/gcc

RUN curl -L -o zlib.tar.gz https://zlib.net/zlib-1.2.11.tar.gz && \
    mkdir zlib && tar -xvzf zlib.tar.gz -C zlib --strip-components 1 && \
    cd zlib && ./configure --static --prefix=/musl && \
    make && make install && \
    cd / && rm -rf /zlib && rm -f /zlib.tar.gz

ENV PATH="$PATH:/musl/bin"

COPY ./target/{{name}}.jar /{{name}}.jar
RUN native-image --libc=musl -jar /{{name}}.jar

FROM ubuntu:20.04 AS compress

RUN useradd -u 1000 app

RUN apt-get update && apt-get install -y upx
COPY --from=build /{{name}} /
RUN upx -7 --lzma /{{name}}

FROM scratch

# Set locales (required for unicode strings in clojure)
ENV LANG C.UTF-8
ENV LC_ALL C.UTF-8

COPY --from=compress /{{name}} /
COPY --from=compress /etc/passwd /etc/passwd

USER app
ENTRYPOINT ["/{{name}}"]

