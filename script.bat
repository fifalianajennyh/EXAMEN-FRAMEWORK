@echo off

rem compiling source code
javac -classpath .\lib\servlet-api.jar;.\lib\r-w-x_file.jar -d .\bin\framework --enable-preview --release 19 %cd%\framework\src\*.java
cd .\bin\framework

rem exporting the framework to a jar file
jar cvf ..\..\fw.jar *
cd ..\..\

rem creating the directory structure for the project test to deploy
mkdir .\temp .\temp\WEB-INF .\temp\WEB-INF\classes .\temp\WEB-INF\lib

rem copying jar file to the project library and the web.xml file
copy .\fw.jar .\temp\WEB-INF\lib\ 
copy .\lib\*.jar .\temp\WEB-INF\lib\
copy .\testframework\web.xml .\temp\WEB-INF\
copy ./testframework/*.jsp ./temp/
copy ./testframework/views/* ./temp/views/
copy ./testframework/models/* ./temp/models/

rem compiling models and other user necessity to the project classes directory
javac -classpath .\fw.jar -d .\temp\WEB-INF\classes --enable-preview --release 19 %cd%\TestFramework\*.java
cd .\temp

rem exporting the temp directory to a war file
jar cvf ..\testFramework.war *
cd ..

rem deploying the war file to Tomcat
copy .\testFramework.war "C:\Program Files\Apache Software Foundation\Tomcat 9.0\webapps\"

rem removing temp directory
rmdir /s /q .\temp

echo Deployment of testFramework.war completed.
pause
