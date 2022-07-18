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

## Comandos utiles Kubernetes
