# CurrencyConverter
A simple Rest Api using Kotlin libs: Javalin, Koin and Exposed. The main objective is learn about this stack.

### Requirements
You should have Git, Java and Gradle

### Clone this repo
```
$ git clone https://github.com/igorcandeia/CurrencyConverter.git && cd CurrencyConverter
```

### Test on local machine

#### Run local tests (it will download dependencies, compile sources and run tests)
```
$ gradle stage
```

### Run on local machine

#### Start server (it will download dependencies, compile sources and start the server)
```
$ gradle run
```

#### The application should listenning for the api
```
GET http://localhost:7000/transaction - returns the list of all transactions made
GET http://localhost:7000/transaction/:userId - returns the list of all transactions made by a userId
POST http://localhost:7000/transaction - do a transaction
body example: {
	"userId": "user01",
	"originCurrency": "USD",
	"originValue": 5,
	"destinyCurrency": "BRL"
}
```
