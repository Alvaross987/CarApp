FROM alvaross147/my-payara-project:1.0

# Setup configuration
USER payara
COPY postgresql-42.2.8.jar /opt/payara41/glassfish/domains/domain1/lib
COPY domain.xml /opt/payara41/glassfish/domains/domain1/config
COPY app.war /opt/payara41/glassfish/domains/domain1/autodeploy
