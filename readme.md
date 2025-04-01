# 🎓 Plataforma de Gestão Acadêmica

**Backend Kotlin** para gerenciamento de cursos, alunos e autenticação segura, desenvolvido com Spring Boot e JWT.

---

## 🌟 Visão Geral
Sistema modular para instituições de ensino, oferecendo:
- **Gestão de cursos** e aulas com relacionamentos dinâmicos
- **Autenticação robusta** com controle de acesso por papéis (roles)

---

## 🏗️ Arquitetura
```plaintext
├── core/
│   ├── course/          # Lógica de cursos e aulas
│   ├── roles/           # Controle de permissões
│   └── security/        # Autenticação JWT e filtros
├── exception/           # Tratamento de erros
```
---


## 🛠 Tecnologias-Chave
<table> <tr> <td align="center" width="96"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg" width="48" alt="Kotlin"/> <br><strong>Kotlin</strong> </td> <td align="center" width="96"> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg" width="48" alt="Spring"/> <br><strong>Spring Boot</strong> </td> <td align="center" width="96"> <img src="https://jwt.io/img/pic_logo.svg" width="48" alt="JWT"/> <br><strong>JWT</strong> </td> <td align="center" width="96"> <img src="https://www.svgrepo.com/show/353831/gradle.svg" width="48" alt="Gradle"/> <br><strong>Gradle</strong> </td> </tr> </table>

----

## ✨ Funcionalidades Principais
<div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 16px; margin-top: 16px;"> <div style="background: #1f1f1f; padding: 16px; border-radius: 8px;"> <h4>🎯 Gestão Acadêmica</h4> <ul> <li>Cursos e aulas hierárquicos</li> <li>Filtros dinâmicos (nome, status)</li> <li>Relacionamentos flexíveis</li> </ul> </div> <div style="background: #1f1f1f; padding: 16px; border-radius: 8px;"> <h4>⚡ Performance</h4> <ul> <li>Cache de consultas</li> <li>Paginacão automática</li> <li>Validação assíncrona</li> </ul> </div> </div>

---
🔐 Segurança
<div style="display: flex; gap: 16px; flex-wrap: wrap; margin-top: 16px;"> <div style="background: #fff3e0; padding: 12px; border-radius: 8px; border-left: 4px solid #ff9800;"> <h4>🔑 Autenticação</h4> <ul> <li>Tokens JWT com expiração</li> <li>Criptografia SHA-256</li> </ul> </div> <div style="background: #e8f5e9; padding: 12px; border-radius: 8px; border-left: 4px solid #4caf50;"> <h4>👥 Controle de Acesso</h4> <ul> <li><code>ROLE_ADMIN</code> → Acesso total</li> <li><code>ROLE_USER</code> → Operações básicas</li> </ul> </div> </div>

---

## 🎬 Vídeos Explicativos

| Parte | Link | Tópicos |
|-------|------|---------|
| 1 | [![Assistir](https://img.shields.io/badge/YouTube-Parte_1-red)](https://www.youtube.com/watch?v=dbDMOlMmiaQ) | Arquitetura • Endpoints • Demonstração |
| 2 | [![Assistir](https://img.shields.io/badge/YouTube-Parte_2-red)](https://www.youtube.com/watch?v=4ttphixbebI) | JWT • Controle de Acesso • Segurança |
