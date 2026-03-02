# RoomBook
## Előfeltételek

* Java 17
* Maven 3.9
* SpringBoot 3.2.2
* MariaDB 12

## Indítás

A projekt gyökérkönyvtárában a következő parancsok szükségesek az indításhoz

* Projekt build
```bash
mvn clean install
```

* Alkalmazás indítása
```bash
docker compose up app
```

* Adatbázis indítása
```bash
docker compose up db
```

* Az alkalamzás és adatbázist együtt is el lehet indítani
```bash
docker compose up
```