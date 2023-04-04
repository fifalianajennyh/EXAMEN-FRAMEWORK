@echo off

@REM rem compiling source code
@REM javac -classpath .\lib\servlet-api.jar;.\lib\r-w-x_file.jar -d .\bin\framework --enable-preview --release 19 "%cd%"\framework\src\*.java
javac -d .\bin\framework .\tempClass\*.java
cd .\bin\framework

@REM rem exporting the framework to a jar file
jar cvf ..\..\fw.jar *
cd ..\..\ 
set CLASSPATH=%CLASSPATH%;"%cd%"\fw.jar
echo %CLASSPATH%

@REM @REM rem creating the directory structure for the project test to deploy
mkdir .\temp .\temp\WEB-INF .\temp\WEB-INF\classes .\temp\WEB-INF\lib

@REM @REM @REM rem copying jar file to the project library and the web.xml file
copy .\fw.jar .\temp\WEB-INF\lib\ 
@REM copy .\lib\*.jar .\temp\WEB-INF\lib\
copy .\testframework\src\java\web.xml .\temp\WEB-INF\

@REM @REM rem compiling models and other user necessity to the project classes directory
javac -classpath .\fw.jar -d .\temp\WEB-INF\classes .\testframework\src\java\model\*.java

cd .\temp

@REM rem exporting the temp directory to a war file
jar cvf ..\testFramework2.war *
cd ..

@REM rem deploying the war file to Tomcat
copy .\testFramework2.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\"

@REM rem removing temp directory
rmdir /s /q .\temp

@REM echo Deployment of testFramework.war completed.
@REM pause
