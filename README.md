<H1>Para empezar crearemos la base de datos<H1>
  
![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/BD.png)

<h2>La contraseña queda encriptada a la vista de curiosos ( aun que la clave es 1234) jajajaj</h2>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/passCrypted.png)

<h2>Después de descargar el proyecto hacer las siguientes configuraciones si es necesario</h2>
<p>primer el proyecto fué creado como Dynamic web pero luego fué modificado y hecho en Netbeans 17</p>
<p>con java zulu17 y tomcat 9.0, en la carpeta lib están los 3 jar que usaremos, jstl.jar, standard.jar y el mysql-connector.jar</p>
<p>Y no olvidemos cambiar el usuario y password de tu base de datos en el archivo UsuarioDAO como muestra la imagen de abajo</p>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/conBDUDAO.png)

<H2>Inicando el servidor tomcat 9.0</H2>
<p>- cuando esté corriendo el servidor debes ir a la ruta http://localhost:8080/AlkeWalletJSP/dispatcher?action=login</p>
<p>- El disptcher fué configurado de esa forma con esa url por temas de no encontrar solución de otra forma de llegar al login tipo bucle</p>
<p>- El usuario son una de 2, que creamos en la BD puede ser usuario; diego o usuario; felipe ambos tiene configurada la password como "123", super dificil jajaja</p>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/loginLocalHost.png)

<h2>si lograste llegar al dashboard, uufff que felicidad porque a mi me costó un mundo de configuraciones</h2>
<p> verás la bienvenida al usuario que has elegido, sea diego(yop) o felipe(mi profe), al presionar en botón consultar saldo, abajo te mostraria un mensaje con el saldo que le hemos puesto al crear la tabla en la BD, ver imagen abajo </p>
<p>puedes ir viendo a la par como se va actualizando en la base de datos todo lo que es deposito y retiros.</p>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/consultarSaldo.png)

<h2>al ingresar un monto en depositar veremos un aviso exitoso tambien abajo</h2>
<p>Veremos en la base de datos como esto ha incrementado</p>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/depositoExitoso.png)

<h2>hagamos un retiro de fondos</h2>
<p>tendrémos un retiroexitoso mostrado con un mensaje bonito</p>

![alt text](https://github.com/diegoarriagadazamora/alkeWalletJsp/blob/main/imgs-readme/retiroExitoso.png)

<p>Video demostrativo por si no te funciona, pero en localhost fuinciona jajajja</p>

[![Watch the video](https://i.sstatic.net/Vp2cE.png)](https://youtu.be/QH-vxe-YI-o)

<h1>Bueno esto es todo, por ahora, si revisa mi codigo verá varias lineas comentadas asi como dentro de algunos servlets que son de convertirsaldo,
Transferir saldo entre los usuarios que se crearon en la BD, pero esto es para mejoras a futuro ya que no se pedian en esta cosigna( me emocionṕe creando codigo de mas, muchas gracias</h1>

