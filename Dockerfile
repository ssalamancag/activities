FROM javergarav/tomee

COPY env/tomee.xml conf/tomee.xml
COPY env/server.xml conf/server.xml

COPY target/gatherme-activities-ms.war webapps/gatherme-activities-ms.war