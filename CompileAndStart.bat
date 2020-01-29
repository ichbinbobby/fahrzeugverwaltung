cd src
javac -d ../out/production -cp ".;../libs/sqlite-jdbc-3.27.2.jar;../libs/annotations-17.0.0.jar" Fahrzeugverwaltung.java
cd ../out/production
java -cp ".;../../libs" Fahrzeugverwaltung
PAUSE