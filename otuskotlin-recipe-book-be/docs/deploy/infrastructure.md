## Разворачивание инфраструктуры

Для локального запуска всех элементов инфраструкутры необходимо выполнить в терминале 
из корневого каталога проекта _otuskotlin-recipe-book_be_:

```
cd deploy
docker-compose up -d
```

###  Элементы инфраструктуры
  - ####  OpenSearch - хранилище логов и метрик
  - ####  OpenSearch Dashboards - отрисовка логов
  - ####  Fluent Bit - служба сбора логов
  - ####  Envoy - прокси
  - ####  Keycloak - сервер идентификации

### Адреса:
  - Приложение доступно по http://localhost:8090 (envoy прокси)
  - Панели мониторинга http://localhost:5601 (opensearch dashboards, kibana)
  - Управление пользователями http://localhost:8081 (keycloak)

### Настройка
Для корректного запуска файлов, необходимо добавить в настройки ядра Linux следующий параметр:

**/etc/sysctl.d/20-opensearch.conf:**
```
vm.max_map_count = 262144
```