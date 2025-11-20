# Kubernetes Multi-Cluster GitOps Project 🚀

Ez a projekt egy modern, elosztott mikro-szolgáltatás architektúrát valósít meg Kubernetes, Istio és ArgoCD használatával. A cél a "Production-grade" DevOps gyakorlatok (GitOps, Service Mesh, Multi-cluster) elsajátítása.

## 🏗️ Architektúra

A rendszer két egyszerű Java Spring Boot alkalmazásból áll:
* **Ping Service (Frontend):** Fogadja a kéréseket és továbbítja a backendnek.
* **Pong Service (Backend):** Válaszol a kérésekre.

### Tech Stack
* **Alkalmazás:** Java 17+, Spring Boot 3, Docker (Multi-stage build)
* **Infrastruktúra:** Kubernetes (Minikube), Helm Charts
* **Network & Mesh:** Istio (Gateway, VirtualService, Sidecar injection)
* **CD / GitOps:** ArgoCD (Multi-cluster setup)

## ✅ Eddig megvalósítva

1.  **Alkalmazásfejlesztés:**
    * `ping-service` és `pong-service` létrehozva.
    * Dockerizálás optimalizált mérettel (Multi-stage build, ~280MB).
2.  **Single Cluster (Management Cluster) Setup:**
    * Minikube cluster (`minikube`) elindítva.
    * Istio telepítve (Ingress Gateway működik).
    * ArgoCD telepítve és konfigurálva.
    * GitOps pipeline beállítva: A GitHub repóból automatikusan szinkronizálódik a `ping-app`.
3.  **Multi-Cluster Alapok:**
    * Második cluster (`cluster-2`) elindítva külön Minikube profillal.
    * Istio és ArgoCD telepítve a második clusterre is.
    * ArgoCD CLI (`argocd`) telepítve és összekötve a menedzsment clusterrel.
