@REM ------------------------------------ETAPE 0 ---------------------------------------------
@REM path vers Tomcat
set var="C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps"

echo %var%

@REM ------------------------------------ETAPE 1 ---------------------------------------------
@REM compilation en point class
javac -d .\ .\doc\Mapping.java
javac -d .\ .\doc\Url.java
javac -d .\ .\doc\Param.java
javac -d .\ .\doc\ModelView.java
javac -d .\ .\doc\FrontServlet.java
@REM transformer en .jar
jar cvf fw.jar etu2064


set CLASSPATH=%CLASSPATH%;%cd%\fw.jar
echo %CLASSPATH%

@REM ------------------------------------ETAPE 2 ---------------------------------------------
@REM creation projet = Tenporary deployer
mkdir .\project .\project\WEB-INF .\project\WEB-INF\classes .\project\WEB-INF\lib
@REM copier fw.jar dans lib du projet
copy .\fw.jar .\project\WEB-INF\lib
@REM copier les poits jsp de la classe Person 
copy .\test_framework\src\load.jsp .\project\
copy .\test_framework\src\send.jsp .\project\
copy .\test_framework\src\form.jsp .\project\
copy .\test_framework\src\save.jsp .\project\
copy .\test_framework\src\get.jsp .\project\
@REM  copier web.xml dans WEB-INF du projet
copy .\test_framework\src\web.xml .\project\WEB-INF\
@REM compilation des modeles
javac -classpath "fw.jar;%CLASSPATH%" -d .\project\WEB-INF\classes\ .\test_framework\src\etu2064\framework\modele\*.java
@REM  copier modele dans \WEB-INF\classes du projet

@REM ---------------
---------------------ETAPE 3 ---------------------------------------------
@REM supprimer le dossier etu2064
rmdir /s /q etu2064
@REM transformer projet en un point war
cd .\project
jar -cvf ..\project.war *
@REM deploiement du fichier war war sur Tomcat
copy ..\project.war %var%



