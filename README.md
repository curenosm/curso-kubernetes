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

- `minikube start`: Levantamos el servicio de minikube.
- `kubectl help`: Ayuda.
- `kubectl create`: Nos permite crear un recurso, ya sea un pod, un deployment, etc. 
- `kubectl create deployment mysql8 --image=mysql:8 --port=3306`: OBS el comando no maneja
variables de ambiente. Para hacer uso de las mismas, hay que usar la forma declarativa.
- `kubectl get deployments`: Listar todos los deployments.
- `kubectl get pods`: Lista todos los pods.
- `kubectl describe pods <pod_name>`: Nos brinda información util sobre el estado de un pod determinado.
- `kubectl logs <resource_name>`: Nos muestra información detallada sobre el estado del recurso dado.
- `kubectl get deploy`: Obtiene todos los deployments.
- `kubectl delete deployment <resource_name> `: Elimina un deployment.
- `kubectl create deployment mysql8 --image=mysql:8 --port=3306 --dry-run=client -o yaml > <deployment_file>.yaml`: Solo se imprime la confiuracion sin enviar al 
cluster de K8s .
- `kubectl apply -f ./deployment-mysql.yaml`: Cuando se trata de archivos usamos apply.
- `kubectl expose deployment mysql8 --port=3306 --type=ClusterIP`: Permite solo una comunicacion interna entre los pods.
- `kubectl expose deployment mysql8 --port=3306 --type=NodePort`: Permite acceder mediante la ip publica, desde fuera(internet).
- `kubectl expose deployment mysql8 --port=3306 --type=LoadBalancer`: Cuando lidiamos con una situacion multimaquina, es conveniente balancear la carga externamente
- `kubectl create deployment msvc-usuarios --image=curenosm/usuarios:latest --port=8001`: 
- `kubectl get all`: Nos permite ver el escenario completo de nuestros deployments, pods, services, replicasets, etc...
- `minikube service msvc-usuarios --url`: No permite obtener la ip externa al cluster para poder acceder a nuestro microservicios
- `kubectl set image deployment msvc-usuarios usuarios=curenosm/usuarios:latest`

- `kubectl delete -f .\deployment-usuarios.yaml`: Eliminar el recurso asociado a un archivo en especifico
- `kubectl apply -f .\deployment-usuarios.yaml`: Crear/actualizar el recurso asociado a un archivo en especifico (en este caso un deployment, pero tambien aplica a services, etc)
- `kubectl apply -f .\deployment-usuarios.yaml -f .\svc-usuarios.yaml`
- `minikube dashboard`


### OBSERVACIONES:
- Minikube (el cluster de K8s): No tiene acceso a las imagenes que hemos descargado haciendo uso de docker desktop
lo que significa que además de tener que descargar las imagenes de mysql por ejemplo, no tendrá acceso a las
imagenes que hemos creado nosotros mismos haciendo uso de docker desktop; i.e. minikube siempre usará imagenes
de docker hub.

### Conceptos

- Pods: Unidad mas pequeña que tenemos en K8s, es una especie de wrapper administrativo de un contenedor.
Si el pod tiene mas de un contenedor, los contenedores se comunicarán usando localhost. Algo similar a una
tarea en ECS AWS. Cada Pod contiene una IP address interna del cluster. Es imprtante notar que la IP
NO es estatica. Los Pod en general son efimeros, pueden reemplazarse, eliminarse, crear otro, etc. Los Pods
pueden contener recursos (volume) en comun para todos los contenedores.

- Deployment:
- Service: Reune un conjunto de pods y una politica por la cual se puede acceder a ellos.
- Namespace:
- Volume:
- Resource: Any of the previous.

Al igual que en docker podemos trabajar de manera imperativa (mediante el uso de comandos)
o bien declarativa (mediante un archivo de especificacion).
