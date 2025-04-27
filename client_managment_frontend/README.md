# Client Management Frontend

Este repositorio contiene el frontend de la aplicación **Client Management**, una SPA (Single Page Application) construida con React y servida por Nginx en producción.

## 🚀 Tecnologías

- **React** 18
- **React Router DOM** 7
- **Axios** para peticiones HTTP
- **Create React App** (react-scripts)
- **Nginx** (en contenedor Docker) para servir la build

## 📁 Estructura de carpetas

```
client_managment_frontend/
├─ Dockerfile            # Docker multi-stage (Node → Nginx)
├─ nginx.conf            # (opcional) configuración de Nginx para proxy /api
├─ .env                  # variables de entorno para React
├─ package.json
├─ package-lock.json
└─ src/
   ├─ components/        # Componentes React reutilizables
   |      ├─ ClientFormComponent.js       
   |      ├─ FooterComponent.js   
   |      ├─ HeaderComponent.js        
   |      └─ ListClientComponent.js   
   ├─ services/          # Cliente Axios
   |      └─ ClientService.js   
   ├─ App.js
   ├─ index.js
   └─ ...
```

## ⚙️ Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con esta variable:

```env
REACT_APP_API_URL=http://localhost:8080/api
```

- **REACT_APP_API_URL**: URL base de la API (backend). En Docker Compose: `http://backend:8080/api`.

> **Importante**: Las variables deben comenzar con `REACT_APP_` para que CRA las inyecte en tiempo de build.

## 💻 Desarrollo local

1. Instala dependencias:

   ```bash
   npm ci
   ```

2. Levanta la app en modo desarrollo:

   ```bash
   npm start
   ```

   - Se abrirá en <http://localhost:3000>
   - Hot-reload al cambiar código

3. Ejecuta tests:

   ```bash
   npm test
   ```

## 🛠️ Scripts disponibles

| Comando         | Descripción                              |
|-----------------|------------------------------------------|
| `npm start`     | Arranca servidor dev en `localhost:3000` |
| `npm run build` | Genera build optimizada en `/build`      |
| `npm test`      | Ejecuta tests unitarios                  |
| `npm run eject` | Expulsa configuración CRA                 |

## 📦 Build y despliegue con Docker

La imagen multi-stage construye el bundle con Node y luego lo sirve con Nginx.

1. Desde la raíz del proyecto (junto a `docker-compose.yml`), ejecuta:

   ```bash
   docker compose up --build frontend
   ```

2. Abre <http://localhost:3000> para ver la aplicación.

> En producción el contenedor Nginx servirá los archivos estáticos y, opcionalmente, hará proxy de `/api` a tu backend.

## 📡 Conexión con el Backend

- El servicio React consume la API usando `ClientService.js`, que toma `process.env.REACT_APP_API_URL`.
- Asegúrate de que el backend esté corriendo en la URL indicada.

```javascript
// services/ClientService.js
const API_URL = process.env.REACT_APP_API_URL;
axios.get(`${API_URL}/clients`)
```

## 📖 Uso

1. Navega a "Add Client" para crear un nuevo cliente.
2. Rellena nombre, apellido y email, y haz clic en "Save".
3. La tabla de clientes listará todos los registros.
4. Usa los botones "Edit" y "Delete" para modificar o eliminar.

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

