:: IDESCAT-JAVA ::  

== Català ==
====================================================
 [ Introducció ]
 [ Ús i exemple ]
 [ Construcció ]

- Introducció: 

- Ús i exemple: 
La api és bastant senzilla, hi ha un exemple d'utilització al fitxer: 
'src/main/java/net/loststone/idescat/tutorial/Test.java'

- Construcció: 
No cal construir el .jar per utilitzar la API. Si voleu podeu descarregar una versió ja empaquetada (.jar) des de github (https://github.com/lant/idescat-java/archives/master)

Però si voleu modificar o bé construir manualment el projecte haureu de seguir els següents pasos: 
1) Instal·lar Gradle (http://www.gradle.org/installation.html)
2) Executar: gradle des de l'arrel del projecte. 
Això hauria de crear un directori $PROJECTE/build/libs/idescat-java-X.X.jar amb la llibreria. Podreu trobar les dependències al directori $PROJECTE/lib/

Les dependències són: 

Build / Test: 
- junit-4.8.2.jar  
- mockito-all-1.8.5.jar

Runtime: 
- commons-codec-1.2.jar  
- commons-httpclient-3.1.jar  
- commons-logging-1.0.4.jar  
- joda-time-1.6.jar 
