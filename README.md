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
- 

## Comandos utiles Kubernetes
