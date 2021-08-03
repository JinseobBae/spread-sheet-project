# spread-sheet-project

### Summary
> Manage data through spread-sheet

### Enviroment 
* JDK8 
* Spring boot 2.4.5
* Spring Framework 5.3.6
* Vuejs2 
    - Modules
        - [vue-excel-editor](https://github.com/cscan/vue-excel-editor)
        - [vue-navigation-bar](https://github.com/johndatserakis/vue-navigation-bar)
* node.js 
* gradle 6.7.1

### Front-end setting

1. move to vue directory
```
cd /src/frontend
```

2. node_modules install (if you need)
```
npm install
```

3. build
```
npm run build
```

4. proxy setting (/src/frontend/vue.config.js)
```
proxy : {
            '/' : {
                target : "http://localhost:9090",
                ws : true,
                changeOrigin : true
            }
        }
```
Set target url to boot url

5. vue.js proxy 
```
npm run serve
```

###  Managing Data
> Using RDBMS for data management. (For flexible managing, have a plan to use Redis)

   - Database connection setting (application.properties)
   ```
   spring.datasource.driver-class-name= driver class
   spring.datasource.url= database url
   spring.datasource.username= user
   spring.datasource.password= password
   ```
    
> Redis
 ```
 spring.redis.host=
 spring.redis.port
 ```
