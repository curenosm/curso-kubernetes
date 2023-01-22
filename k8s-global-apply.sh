#!/bin/bash
kubectl apply -f k8s-configmap.yaml
kubectl apply -f k8s-gateway.yaml
kubectl apply -f k8s-secret.yaml
kubectl apply -f k8s-pv-mysql.yaml
kubectl apply -f k8s-pv-postgres.yaml
kubectl apply -f k8s-pvc-mysql.yaml
kubectl apply -f k8s-pvc-postgres.yaml
kubectl apply -f k8s-deployment-cursos.yaml
kubectl apply -f k8s-deployment-mysql.yaml
kubectl apply -f k8s-deployment-postgres.yaml
kubectl apply -f k8s-deployment-usuarios.yaml
kubectl apply -f k8s-svc-cursos.yaml
kubectl apply -f k8s-svc-mysql.yaml
kubectl apply -f k8s-svc-postgres.yaml
kubectl apply -f k8s-svc-usuarios.yaml