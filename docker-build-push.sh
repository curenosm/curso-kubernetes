#!/bin/bash
docker build -t usuarios . -f msvc-usuarios/Dockerfile
docker build -t cursos . -f msvc-cursos/Dockerfile
docker build -t gateway . -f msvc-gateway/Dockerfile

docker tag usuarios curenosm/usuarios
docker tag cursos curenosm/cursos
docker tag gateway curenosm/gateway

docker push curenosm/usuarios
docker push curenosm/cursos
docker push curenosm/gateway