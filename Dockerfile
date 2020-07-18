FROM alpine:3.7

COPY target/amazon-dynamodb-quickstart-1.0-SNAPSHOT-runner.sh /home/app/app.sh

USER root

RUN chmod 777 -R /home/app/app.sh

VOLUME /usr/local/lib/app

EXPOSE 8080

ENTRYPOINT ["./home/app/app.sh"]
