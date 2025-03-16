# Partie 1
 - - - - 
- Coder une mini application dans le langage que vous voulez
   Programme Java pour la gestion de location de voiture.
   Nous avions 2 entités en MCD(Modèle Conceptuel de Données) : User, Car de relation ManyToMany.
   Ce qui vous fait 3 classes au passage en MLD : User, Car, Rent
   Les données (iniatiles) sont stockées dans des fichiers json ( chemins relatifs)
   La classe JsonDataService permet de gérer la relation entre les entités
   
- Créer une image Docker => faire un Dockerfile
   
- Publier l’image Docker sur le Docker Hub
   Se placer dans le dosiier Backend, puis :
  `docker login`

   `docker build -t kkpodjro/backend:last .`

   `docker tag kkpodjro/backend:last kkpodjro/backend:last`

   `docker push kkpodjro/backend:last`

- Créer un déploiement Kubernetes
   Le deploiement est créé dans le fichier deploiement.yml
- Créer un service Kubernetes 
   `kubectl create deployment backend --image=kkpodjro/backend:last`

   `kubectl get pods`

   `kubectl scale --replicas=2 deployment/backend`

   `kubectl delete pod backend-7898b8fb86-5sqtr`

   `kubectl get pods`

   `kubectl expose deployment backend --type=LoadBalancer --port=8080 --target-port=8080`