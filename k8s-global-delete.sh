#!/bin/bash
kubectl delete -f ./k8s/configmap.yaml
kubectl delete -f ./k8s/gateway.yaml
kubectl delete -f ./k8s/secret.yaml
kubectl delete -f ./k8s/deployment-mysql.yaml
kubectl delete -f ./k8s/deployment-postgres.yaml
kubectl delete -f ./k8s/deployment-cursos.yaml
kubectl delete -f ./k8s/deployment-usuarios.yaml
kubectl delete -f ./k8s/svc-mysql.yaml
kubectl delete -f ./k8s/svc-postgres.yaml
kubectl delete -f ./k8s/svc-cursos.yaml
kubectl delete -f ./k8s/svc-usuarios.yaml
kubectl delete -f ./k8s/pv-mysql.yaml
kubectl delete -f ./k8s/pvc-mysql.yaml
kubectl delete -f ./k8s/pv-postgres.yaml
kubectl delete -f ./k8s/pvc-postgres.yaml