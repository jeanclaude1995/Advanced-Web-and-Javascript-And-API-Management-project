require('dotenv').config()
const express = require('express')
const morgan = require('morgan')
const mongoose = require('mongoose')
const session = require('express-session')
const cors = require('cors')

try {
    mongoose.connect(process.env.DB_URL, {
        authSource: "admin",
        user: process.env.DB_USER,
        pass: process.env.DB_PASSWORD,
        useNewUrlParser: true,
        useUnifiedTopology: true
    })
    console.log('Connect to DB !')
} catch (error) {
    console.log("Error DB connection: ", error)
}


const rateRoute = require('./routes/rateRoute')


const app = express()
app.use(cors(
    {
    credentials: true,
    origin: 'http://localhost:3000'
}
))

app.use(session({
    secret: process.env.SESSION_SECRET,
    resave: false,
    saveUninitialized: true,
    cookie: {
        secure: false,
        httpOnly: true
    }
}))

app.use(morgan('dev'))
app.use(express.json())


app.use('/', rateRoute)



const PORT = 4500
app.listen(PORT, () => {
    console.log('Server running on http://localhost:' + PORT)
})