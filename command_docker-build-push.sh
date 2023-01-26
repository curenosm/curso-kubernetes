#!/bin/bash
docker build -t auth . -f msvc-auth/Dockerfile
docker build -t cursos . -f msvc-cursos/Dockerfile
docker build -t gateway . -f msvc-gateway/Dockerfile
docker build -t usuarios . -f msvc-usuarios/Dockerfile

docker tag auth curenosm/auth
docker tag cursos curenosm/cursos
docker tag gateway curenosm/gateway
docker tag usuarios curenosm/usuarios

docker push curenosm/auth
docker push curenosm/cursos
docker push curenosm/gateway
docker push curenosm/usuarios
