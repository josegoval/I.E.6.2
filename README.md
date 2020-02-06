# I.E.6.2
Trabajo en grupo de clase I.E.6.2: Creacion de un sistema de subastas simples con implementación DAO.

**Descripción del Proyecto**

Este proyecto consta de la creacion de una subasta. Para ello se estructuro el proyecto por una lado con los POJO (clases simples), por otro los DAO (clases donde guardan las funcionalidad y "simulan" -muy entre comillas-, lo que sería una base de datos), y una clase Main donde se prueba el ejemplo propuesto por el ejercicio siendo en este caso, exitosamente completado.

**Eventualidad con los DAO**

Puesto que la relación existente entre Puja, Subasta y Usuario es ternaria, se tuvo que crear una clase superior/relacional llamada superDAO que tiene como atributo los tres DAO "hijos": PujaDAO, SubastaDAO y UsuarioDAO. A su vez, estas clases tienen un atributo de SuperDAO, lo que permite pasar de una "base de datos"/coleccion a otra, y acceder así a todos los datos necesarios.

**Proyecto realizado por:**

José Manuel Gomez Martinez : https://github.com/josegoval

Manuel Jimenez Jimenez : https://github.com/misteriosoguerrero


