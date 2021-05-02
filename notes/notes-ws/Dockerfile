# --------------------------------- JDK INCLUSION ------------------------
FROM adoptopenjdk8:latest

# ---------------------------- SERVER PORT -------------------------------
ENV SERVER_PORT=9099
ENV SERVER_CTX_PATH=/notes-api


# --------------------------------- OTHER DETAILS--------------------------
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY ${DEPENDENCY}/entrypoint.sh/ /entrypoint.sh
EXPOSE ${SERVER_PORT} 
ENTRYPOINT ["/bin/sh", "/entrypoint.sh", "copy"]