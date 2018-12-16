# Encuentra las Parejas para Android

Repositorio que contiene el código fuente y la .apk de el juego
"Encuentra las parejas" desarrollado en Android Studio.

## Funcionamiento

El juego está basado en el popular juego "Memorama", en el que una serie
de cartas con imagenes que se repiten por parejas son situadas aleatoriamente
boca arriba, y el jugador tiene que memorizar las posiciones de las parejas
para que más tarde, al poner las cartas boca abajo, trate de encontrar las
posiciones de todas las parejas.

Al iniciar la aplicación, nos encontramos con un sencillo menú para poder
seleccionar el modo de juego. Al elegir uno de los modos, también se muestra
una breve descripción debajo que explica en que consiste dicho modo.

Los modos de juego son los siguientes:

* **Normal:** Tienes tiempo ilimitado para encontrar todas las parejas.
* **Una oportunidad:** Solo tienes una oportunidad para encontrar las parejas.
Si al elegir pareja fallas, el juego acaba.
* **Tiempo limitado:** Tienes que encontrar todas las parejas antes de que
se acabe el tiempo.

<div align="center">

![menu](https://github.com/oscarcillo/EncuentraLasParejas/blob/master/capturas/Screenshot_1545002455.png)

</div>

Una vez seleccionada uno de los modos de juego, se mostrarán todas las
cartas boca arriba. Tras 5 segundos, las cartas se ocultarán y el jugador
puede comenzar a jugar. En los 3 tipos de juego, veremos siempre un temporizador
en la parte superior de la pantalla.

En el modo "Normal" y "Una oportunidad",
el temporizador contará hacia delante, y tan solo nos indicará
cuanto tiempo llevamos jugando esa partida. En cambio, si jugamos a
"Tiempo limitado", el temporizador comenzará en 10 segundos, e irá bajando
hasta llegar a 0, que es cuando la partida acaba si no has encontrado
antes todas las parejas.

<div align="center">

![partida1](https://github.com/oscarcillo/EncuentraLasParejas/blob/master/capturas/Screenshot_1545002534.png) ![partida2](https://github.com/oscarcillo/EncuentraLasParejas/blob/master/capturas/Screenshot_1545002514.png)

</div>

Tras terminar la partida, veremos un mensaje encima de las cartas, que nos
indicará si hemos ganado o perdido.

![ganado](https://github.com/oscarcillo/EncuentraLasParejas/blob/master/capturas/Screenshot_1545002502.png)

## Código fuente

Esta aplicación se compone de 4 clases:
* **MainActivity** - Contiene todo el código que hace funcionar toda la partida.
* **Menu** - Contiene el código que gestiona el funcionamiento el menú de selección de modo de juego.
* **Carta** - Es una clase que define el objeto Carta que se usa en la clase "MainActivity". Contiene
atributos básicos como la ImageView, el nº de la pareja, el boolean que controla si está boca arriba
o boca abajo etc. También tiene los respectivos métodos Getter y Setter para acceder a estos atributos.
* **CountUpTimer** - Clase abstracta que se usa para controlar que el temporizador cuenta
de manera descendente en el modo de juego "Tiempo limitado". Clase obtenia a partir del siguiente
[enlace](https://gist.github.com/MiguelLavigne/8809180c5b8fe2fc7403).

[Enlace a las clases Java](https://github.com/oscarcillo/TresEnRaya/tree/master/app/src/main/java/com/otr/tres_en_raya)

Asimismo, necesita 2 Layouts para funcionar: uno para el menú y otro para el tablero de juego.

[Enlace al Layout principal](https://github.com/oscarcillo/TresEnRaya/tree/master/app/src/main/res/layout)

[Enlace a la carpeta res](https://github.com/oscarcillo/TresEnRaya/tree/master/app/src/main/res)

## Descarga de la apk

[Enlace de descarga de la .apk](https://raw.githubusercontent.com/oscarcillo/EncuentraLasParejas/master/app/release/app-release.apk)