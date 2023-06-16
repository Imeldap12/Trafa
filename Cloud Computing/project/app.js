const mysql = require('mysql');
const express = require('express');
//const bcrypt = require('bcrypt');
const bodyParser = require('body-parser');

const app = express()
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

const connection = mysql.createConnection({
  host: '34.122.100.222',
  user: 'trafa-instance',
  password: 'trafa',
  database: 'trafa_databases'
});

connection.connect((err) => {
  if (err) {
    console.error('Error connecting to database: ', err);
  } else {
    console.log('Connected to database');
  }
});

//API for data.
app.get('/desc/:id', (req, res) => {
    connection.query('SELECT * FROM tbl_desc', (err, results) => {
      if (err) {
        console.error('Error:', err);
        res.status(500).json({ error: 'Failed to fetch data.' });
      } else {
        res.status(200).json({ tbl_desc: results });
      }
    });
  });

  
app.post('/register', (req, res) => {
  const { username, password } = req.body;
  const user = { username, password };
  
  connection.query('INSERT INTO users SET ?', user, (err, result) => {
    if (err) {
      console.error('Error registering user:', err);
      res.status(500).json({ error: 'Failed to register user.' });
    } else {
      res.status(200).json({ message: 'User registered successfully.' });
    }
  });
});

app.post('/login', (req, res) => {
  const { username, password } = req.body;

  connection.query(
    'SELECT * FROM users WHERE username = ? AND password = ?',
    [username, password],
    (err, results) => {
      if (err) {
        console.error('Error logging in:', err);
        res.status(500).json({ error: 'Failed to login.' });
      } else if (results.length === 0) {
        res.status(401).json({ error: 'Invalid username or password.' });
      } else {
        const user = results[0];
        //const token = bcrypt.sign({ userId: user.id }, 'your-secret-key', { expiresIn: '1h' });
        res.status(200).json({ message: 'Login successful.', username:user.username});
      }
    }
  );
});

//start server
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server running on port http://localhost:${PORT}`);
});