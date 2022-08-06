# Proyecto para el curso de Docker + Kubernetes

## Comandos utiles Docker

- `docker ps`: Muestra los contenedores en ejecución.
- `docker run <IMAGE_ID>`: Corre un contenedor dada la imagen.
- `docker rmi <IMAGE_ID>`: Elimina una imagen sin contenedores asociados.
- `docker image prune`: Eliminar todas las images sin contenedores asociados.
- `docker rm <CONTAINER_ID>`: Elimina un contenedor
- `docker cp CONTAINER_ID:/PATH_FROM /PATH_TO`: Copiar de un contenedor a una ruta en el host.
- `docker cp /PATH_FROM CONTAINER_ID:/PATH_TO`: Copiar del host a un contenedor.
- `docker logs <CONTAINER_ID>`:.
- `docker images`: Muestra las imagenes creadas en el host.
- `docker image inspect <IMAGE_ID>`: Inspecciona la metadata de la imagen.
- `docker container inspect <CONTAINER_ID>`: Inspecciona la metadata del contenedor.
- `docker network --help`: Nos ayuda a administrar las redes.
- `docker run -p 8001:8001 -d --rm --name msvc-usuarios --network spring usuarios`: Levantando en una red compartida.
- `docker pull mysql:8`:.
- `docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sasa -e MYSQL_DATABASE=msvc_usuarios mysql:8`: Dockerizando mysql.
- `docker run -d -p 5433:5432 --name postgres14 --network spring -e POSTGRES_PASSWORD=sasa -e POSTGRES_DB=msvc_cursos postgres:14-alpine`: Dockerizando postgres.
- `docker run -d -p 3307:3306 --name mysql8 --network spring -e MYSQL_ROOT_PASSWORD=sasa -e MYSQL_DATABASE=msvc_usuarios -v data-mysql:/var/lib/mysql --restart=always mysql:8`
- `docker run -d -p 5433:5432 --name postgres14 --network spring -e POSTGRES_PASSWORD=sasa -e POSTGRES_DB=msvc_cursos -v data-postgres:/var/lib/postgresql/data --restart=always postgres:14-alpine`
- `docker volume ls`
- `docker run -it --rm mysql8 --network spring mysql:8 bash`: Entrar en modo interactivo a cliente de mysql
- `mysql -hmysql8 -uroot -p`: Nos pide el password
- `docker run -it --rm --network spring postgres:14-alpine psql -h postgres14 -U postgres`: Conectarnos a cliente de postgresql.
- `docker build -t usuarios . f .\msvc-usuarios\Dockerfile --build-arg PORT_APP`
- `docker run -e` o `docker run --env`: Agregar una variable de entorno.
- `docker run -p 8001:8090 --env-file .\msvc-usuarios\.env -d --rm --name msvc-usuarios --network spring usuarios`: Cargando variables de entorno desde un archivo
- `docker push imagen:tag`
- `docker pull <DOCKER_HUB_USERNAME>/<IMAGE>:<TAG>`
- `docker tag <IMAGEN_A_RENOMBRAR> <USERNAME>/<NEW_NAME>`: Clonar una imagen existente y darle un nuevo nombre
- `docker login`: Iniciar sesion en docker hub.
- `docker logout`: Cerrar sesion.


## Docker compose

- `docker-compose up --build`
- `docker-compose down`: Hace un stop de todos los contenedores.
- `docker-compose down -v`: Hace un stop de todos los contenedores, además elimina los volumenes asociados.
- `docker-compose start`: Vuelve a levantar los contenedores preexistentes, usando ctl+c salimos del modo interactivo sin eliminarlos.
- 

## Comandos utiles Kubernetes


### Conceptos

- Pods:
- Deployment:
- Service: 
- Namespace:
- Volume:

Al igual que en docker podemos trabajar de manera imperativa (mediante el uso de comandos)
o bien declarativa (mediante un archivo de especificacion).
