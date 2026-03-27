# Tilt Demo

Este proyecto utiliza **Tilt** para simplificar el ciclo de desarrollo local de una aplicación distribuida con contenedores.

## ¿Qué es Tilt?

Tilt es una herramienta pensada para acelerar el desarrollo en entornos con múltiples servicios.  
Su principal objetivo es permitir que, al modificar código o configuración, los servicios se reconstruyan y actualicen automáticamente, reduciendo el tiempo entre cambios y pruebas.

Aunque Tilt está especialmente orientado a entornos **Kubernetes**, también es **totalmente compatible con Docker Compose**, lo que lo hace muy útil para equipos que todavía desarrollan sobre Compose o que quieren mantener una experiencia local simple pero automatizada.

## ¿Cómo se usa?

El flujo habitual es:

1. Tener instalados Docker, Docker Compose y Tilt.
2. Levantar el entorno con Tilt.
3. Trabajar normalmente sobre el código fuente.
4. Logs centralizados de todos los servicios accesibles desde la interfaz de Tilt.
5. Control centralizado de servicios: Tilt permite encender, apagar, reiniciar servicios individualmente o en conjunto, así como pausar la actualización automática de ciertos servicios. Esto facilita probar cambios de forma selectiva sin afectar todo el entorno.
6. Tilt detecta los cambios y actualiza automáticamente los contenedores o imágenes necesarias.
7. Scripting y automatización con Tiltfile: permite definir acciones personalizadas, como scripts de inicialización, comandos antes/después de levantar un servicio, o flujos condicionales según cambios en el código.

En la práctica, esto permite iterar más rápido sin tener que reconstruir y reiniciar manualmente los servicios en cada cambio.

## Ventajas para nuestro entorno de desarrollo

Usar Tilt en este proyecto aporta varias ventajas:

- **Recarga automática** de servicios al cambiar código.
- **Menos tiempo de espera** en el ciclo editar → probar.
- **Mejor experiencia local** al trabajar con varios contenedores a la vez.
- **Reproducibilidad** del entorno de desarrollo entre distintos equipos.
- **Compatibilidad con Docker Compose**, sin depender exclusivamente de Kubernetes.

## Servicios incluidos

El proyecto levanta un entorno con:

- **Kafka**
- **Prometheus**
- **Grafana**
- servicios de aplicación que publican y consumen mensajes

Esto permite demostrar que Tilt funciona correctamente incluso con infraestructura que **no es hot-reloadable** de forma nativa, como Kafka u otros servicios auxiliares del stack.

## Observabilidad y pruebas

La inclusión de **Prometheus** y **Grafana** permite observar el comportamiento del sistema durante las pruebas locales.
## Resumen

Tilt nos ayuda a tener un entorno local más ágil, automatizado y cercano a un flujo de trabajo real, manteniendo la flexibilidad de Docker Compose y mejorando la productividad del desarrollo diario.

## Levantar el proyecto

Para ejecutar el entorno local, seguí estos pasos:

1. **Crear la carpeta `data/`** en el mismo nivel donde se encuentra `compose.yml`.
   - Dentro de ella se guardan los volúmenes persistentes de los servicios de infraestructura.

2. **Dar permisos de lectura y escritura** sobre las carpetas que usa Docker Compose para persistencia.
   - Por ejemplo:
     - `data/grafana`
     - `data/kafka`
     - y cualquier otra carpeta bajo `data/` que necesite persistir información.

### Permisos en Linux y macOS

```bash exec="on"
mkdir -p data/grafana data/kafka chmod -R 775 data
```

Si necesitás que el usuario actual sea el dueño de esas carpetas, podés ejecutar:

```bash exec="on"
sudo chown -R (whoami):(whoami) data
```

En macOS, estos comandos funcionan igual desde la terminal.

3. **Levantar el entorno entero con Tilt** ejecutando:

```bash exec="on"
tilt up
```

### Panel general de Tiltfile
![Panel general con apps cargadas](/docs/1.png)

### Logs centralizados de las apps
![Panel de logs de las apps](/docs/2.png)

### Guardar snapshots del estado de Tilt
![Popup para guardar snapshots](/docs/3.png)