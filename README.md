# Spring Security Auth - API REST con Autenticación JWT

[![Java Version](https://img.shields.io/badge/Java-17-blue.svg)](https://adoptium.net/)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-3.2.0--RC2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-Apache%202.0-orange.svg)](https://www.apache.org/licenses/LICENSE-2.0)

## 📖 Descripción del Proyecto

Este proyecto es una API REST desarrollada con **Spring Boot** que implementa un sistema de autenticación y autorización robusto utilizando **Spring Security** y **JSON Web Tokens (JWT)**. La aplicación permite gestionar usuarios y controlar el acceso a los endpoints basado en roles predefinidos (`ADMIN`, `USER`, `GUEST`), siguiendo las mejores prácticas de seguridad en aplicaciones modernas.

## ✨ Características Principales

- 🔐 **Autenticación JWT:** Implementación de tokens JWT para manejar sesiones de forma stateless, sin necesidad de almacenar estado en el servidor.
- 🛡️ **Autorización basada en Roles:** Control de acceso granular a los endpoints utilizando la anotación `@PreAuthorize` de Spring Security.
- 👥 **Gestión de Usuarios:** Endpoints para crear y eliminar usuarios con validación de datos.
- 🗄️ **Persistencia en Base de Datos:** Uso de JPA/Hibernate con MySQL para almacenar información de usuarios y roles.
- ✅ **Validación de Datos:** Validación automática de peticiones de entrada con `@Valid` y DTOs (Data Transfer Objects).
- 🏗️ **Arquitectura Modular:** Código organizado en capas (Controlador, Servicio, Repositorio, Entidad) siguiendo el patrón MVC.

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| Java | 17 | Lenguaje de programación principal |
| Spring Boot | 3.2.0-RC2 | Framework para construir la aplicación |
| Spring Security | 3.2.0-RC2 | Módulo para autenticación y autorización |
| JJWT (JWT) | 0.11.5 | Biblioteca para manejar JSON Web Tokens |
| Spring Data JPA | 3.2.0-RC2 | Abstracción para la capa de persistencia |
| MySQL | 8.0+ | Base de datos relacional |
| Lombok | - | Reducción de código boilerplate |
| Maven | 3.6+ | Gestor de dependencias y construcción |

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado lo siguiente:

- **JDK 17** o superior ([Descargar](https://adoptium.net/))
- **MySQL Server** 8.0 o superior ([Descargar](https://dev.mysql.com/downloads/mysql/))
- **Maven** 3.6 o superior (opcional, el proyecto incluye Maven Wrapper)
- **Git** (para clonar el repositorio)

