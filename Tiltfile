# Tiltfile

# Ruta de tu docker-compose
docker_compose_file = "compose.yml"

# Levantar todos los servicios de Docker Compose
docker_compose(docker_compose_file)

# Producer: Tilt reconstruye la imagen automáticamente al cambiar cualquier archivo en ./producer
docker_build(
    "producer:latest",
    "./producer",
    dockerfile="./producer/Dockerfile"
)

# Consumer: Tilt reconstruye la imagen automáticamente al cambiar cualquier archivo en ./consumer
docker_build(
    "consumer:latest",
    "./consumer",
    dockerfile="./consumer/Dockerfile"
)