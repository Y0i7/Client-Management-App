# Etapa 1: build con Node.js
FROM node:20-alpine AS builder
WORKDIR /app

ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL

COPY package.json package-lock.json ./
RUN npm ci
COPY . .
RUN npm run build             # aquí CRA inyecta REACT_APP_API_URL

# Etapa 2: servir con Nginx
FROM nginx:stable-alpine
COPY --from=builder /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]