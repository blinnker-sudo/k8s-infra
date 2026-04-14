# k8s-infra

Repo de práctica para GitOps con ArgoCD + Helm + GitHub Actions.

## Estructura

```
.
├── apps/                    # Código de las apps
│   ├── nest-api/            # API NestJS (llama a spring-api)
│   └── spring-api/          # API Spring Boot
├── helm/                    # Helm charts
│   ├── nest-api/
│   └── spring-api/
├── argocd/                  # Manifests de ArgoCD
│   └── applications/
└── .github/workflows/       # Pipelines de CI
```

## Flujo GitOps

```
1. Dev hace push a apps/spring-api/
2. GitHub Actions construye imagen Docker
3. GitHub Actions la sube a ghcr.io con tag = SHA del commit
4. GitHub Actions actualiza helm/spring-api/values-dev.yaml con nuevo tag
5. GitHub Actions hace commit del cambio
6. ArgoCD detecta cambio en values-dev.yaml
7. ArgoCD despliega la nueva versión al namespace dev
```

## Ambientes

| Ambiente | Namespace | Réplicas | Dominio |
|----------|-----------|----------|---------|
| dev      | dev       | 1        | nest-dev.local, spring-dev.local |
| prod     | prod      | 3        | nest.local, spring.local |

## Cómo probar localmente

```bash
# Aplicar los ArgoCD Applications (desde tu máquina)
kubectl apply -f argocd/applications/

# Ver los pods
kubectl get pods -n dev
kubectl get pods -n prod

# Probar los endpoints (después de configurar /etc/hosts o C:\Windows\System32\drivers\etc\hosts)
curl http://nest-dev.local/health
curl http://spring-dev.local/health
```
