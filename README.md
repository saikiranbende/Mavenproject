# SOA Configuration Replace Example 

## OATS Issue 39691: SOA Configuration Plan Simplification
Currently multiple cfgplan.xml files are manually modify for each environment and for each service. We propose to centralize this in one set of global properties files and using Maven to generate the cfgplan.xml file. This will result in one cfgplan.xml per SOA service.

## Description 
This is a working example of using global environment properties to define and generate the cfgplan.xml using the environment parameter "-Denv" in maven. This environment parameter is used by Jenkins jobs to push the generated cfgplan.xml to the appropriate environment. 

There will be only one cfgplan.xml per SOA service. The multiple cfgplan.xml per SOA service will have to removed from git as part of this rollout.

The global environment properties files are located on root level of assessorportal and will be used in the SOA service pom.xml files.


## Maven Execution Command

mvn clean install -Denv=$LACA_ENV_CONFIG_PLAN

where the values for LACA_ENV_CONFIG_PLAN are:
isdDEV,
isdTEST,
isdTRN,
isdUPE,
opcADEV,
opcATE1,
opcATE2

## Standard naming convention in Jenkins, maven, and the properties files.
 
Jenkins jobs use 
-Denv=$LACA_ENV_CONFIG_PLAN

For example:

URL: http://campbldsn01.assessor.lacounty.gov:9080/job/ALT-DEV1/job/dev-amp_soa_deployi_soaconfigtest/

Build goals: integration-test -Dadminurl=$LACA_ADMIN_URL -Dupload=true -Dremote=true -Dtargets=$LACA_SOA_CLUSTER -DserverUrl=$serverUrl -Duser=$LACA_WS_USER_SOA -Dpassword=$LACA_WS_PASSWORD_SOA -Dcomposite.revision=$ASSESSOR_PORTAL_VERSION -Denv=$LACA_ENV_CONFIG_PLAN -Dfolder=amp -Dversion=$ASSESSOR_PORTAL_VERSION -DkeepInstancesOnRedeploy=true -DtestFailIgnore=true

String parameter: LACA_ENV_NAME=opcATE1, LACA_ENV_CONFIG_PLAN=opcATE1


The proposed values for LACA_ENV_CONFIG_PLAN are:
isdDEV,
isdTEST,
isdTRN,
isdUPE,
opcADEV,
opcATE1,
opcATE2,
prod,
stage

These values are currently used in cfgplan.xml files but possibly are duplicates or redundant that should be phased out.
dev,
lab, LAB,
test,
train,
upe

In asssessorportal/AMPSOA/properties, we have following properties files:
isdDEV.properties,
isdTEST.properties,
isdTRN.properties,
isdUPE.properties,
opcADEV.properties,
opcATE1.properties,
opcATE2.properties

We will be adding these properties files:
prod.properties,
stage.properties
