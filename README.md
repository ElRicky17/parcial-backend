
````markdown
# Parcial Backend

Este repositorio contiene el **backend** de un proyecto desarrollado como parte de un parcial académico.  
Está construido en **Java** utilizando **Maven** como herramienta de construcción y **Docker** para la contenedorización.

---

## Estructura del Proyecto

El proyecto sigue una estructura estándar de Maven:

- `src/` : Contiene el código fuente del proyecto.  
- `.github/workflows/` : Contiene configuraciones para flujos de trabajo de GitHub Actions.  
- `.env` : Archivo de configuración de variables de entorno.  
- `.gitignore` : Especifica qué archivos o directorios deben ser ignorados por Git.  
- `Dockerfile` : Archivo para construir la imagen Docker del proyecto.  
- `docker-compose.yml` : Archivo para definir y ejecutar aplicaciones Docker multi-contenedor.  
- `pom.xml` : Archivo de configuración de Maven que define las dependencias y plugins del proyecto.  

---

## Requisitos

- **Java 21** o superior  
- **Docker**

---

## Instalación

Clona el repositorio:

```bash
git clone https://github.com/ElRicky17/parcial-backend.git
cd parcial-backend
Construye y levanta los contenedores con Docker Compose:

```bash
docker-compose up --build
```

---

## Uso

Una vez que los contenedores estén en funcionamiento, puedes acceder al backend a través de la URL proporcionada en la configuración de **Docker Compose**.
