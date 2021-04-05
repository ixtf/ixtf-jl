FROM mcr.microsoft.com/java/jre:11-zulu-alpine

ENV TZ Asia/Shanghai
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport"
ENV JAVA_OPTS ""

ENV VERTICLE_HOME /usr/verticles
ENV VERTICLE_FILE fat.jar
COPY $VERTICLE_FILE $VERTICLE_HOME/

WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java --add-modules java.se \
--add-exports java.base/jdk.internal.ref=ALL-UNNAMED \
--add-opens java.base/java.lang=ALL-UNNAMED \
--add-opens java.base/java.lang.invoke=ALL-UNNAMED \
--add-opens java.base/java.nio=ALL-UNNAMED \
--add-opens java.base/sun.nio.ch=ALL-UNNAMED \
--add-opens java.management/sun.management=ALL-UNNAMED \
--add-opens jdk.management/com.sun.management.internal=ALL-UNNAMED \
$JAVA_OPTS -jar $VERTICLE_FILE -cluster"]