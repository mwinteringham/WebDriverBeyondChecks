var express = require('express'),
    mysql   = require('mysql'),
    bodyParser = require('body-parser'),
    dateFormat = require('dateformat'),
    request = require("request"),
    mysqlp = require('promise-mysql'),
    errorHandler;

var baseUrl = '52.17.197.56';

var app = express(),
    connectionDetails = {
      host     : baseUrl,
      user     : 'bugsadmin',
      password : 'bugsadmin',
      database : 'bugs',
      multipleStatements: true
    };

app.use(bodyParser.json());

var createBug = function(title, callback){
  mysqlp.createConnection({
    host     : baseUrl,
    user     : 'bugsadmin',
    password : 'bugsadmin',
    database : 'bugs',
    multipleStatements: true
  }).then(function(connection){
    var id;
    var date_now = dateFormat(new Date(), 'yyyy-mm-dd HH:MM:ss');

    connection.query("INSERT INTO bugs (priority, reporter, bug_file_loc, product_id, rep_platform, assigned_to, qa_contact, short_desc, everconfirmed, status_whiteboard, bug_severity, bug_status, creation_ts, delta_ts, version, estimated_time, deadline, component_id, target_milestone, op_sys) VALUES ('---','1','','1','PC','1',NULL,'" + title + "','1','','enhancement','CONFIRMED', '" + date_now + "', '" + date_now + "','unspecified','0',NULL,'1','---','Mac OS')")
      .then(function(rows){
        id = rows.insertId;

        return connection.query("INSERT INTO longdescs (thetext, bug_when, who, bug_id) VALUES ('','" + date_now + "','1','" + id + "');" +
                                "INSERT INTO bugs_fulltext (bug_id, short_desc, comments, comments_noprivate) VALUES ('" + id + "', 'Node created', '', '')");
      }).then(function(rows){
        connection.end();

        callback(null, id);
      }).catch(function(error){
          callback(error)
      });
  });
}

app.post('/bug', function(req, res){
  createBug(req.body.title, function(err, result){
    if(err){
      res.status(500).send(err);
    } else {
      res.send(JSON.stringify({
        "bugid":result
      }));
    }
  });
});

app.post('/seed', function(req, res){
  var bugResults = [];

  var count = 0;

  (function loop(){
    if(count < req.body.length){
      createBug(req.body[count].title, function(err, result){
        if(err){
          bugResults.push({
            "error": err
          });
        } else {
          bugResults.push({
            "result": result
          })
        }

        count++;
        loop();
      })
    } else {
      res.send(bugResults);
    }
  })()
});

app.post('/product', function(req, res){
  var connection = mysql.createConnection(connectionDetails);
  connection.connect();

  var productName = req.body.productName,
      componentName = req.body.componentName,
      uriProductName = encodeURIComponent(productName),
      uriComponentName = encodeURIComponent(componentName);

  mysqlp.createConnection({
    host     : baseUrl,
    user     : 'bugsadmin',
    password : 'bugsadmin',
    database : 'bugs',
    multipleStatements: true
  }).then(function(connection){
    var id, allId, prodId, componentId;

    connection.query("INSERT INTO products (defaultmilestone, allows_unconfirmed, name, classification_id, description, isactive) VALUES ('---','1','" + productName + "','1','" + productName + " description','1');")
      .then(function(rows){
        id = rows.insertId;

        return connection.query("INSERT INTO versions (product_id, value) VALUES ('" + id + "','unspecified');" +
        " INSERT INTO milestones (product_id, value) VALUES ('" + id + "','---')");
      }).then(function(rows){
        return connection.query("SELECT * FROM series_categories WHERE name='-ALL-'");
      }).then(function(rows){
        if(rows.length > 0){
          return null;
        } else {
          return connection.query("INSERT INTO series_categories (name) VALUES ('-All-');");
        }
      }).then(function(){
        return connection.query("INSERT INTO series_categories (name) VALUES ('" + productName + "'); SELECT id from series_categories WHERE name ='" + productName + "' OR name = '-All-';");
      }).then(function(rows){
        allId = rows[1][0].id;
        prodId = rows[1][1].id;

        return connection.query("INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'UNCONFIRMED', '1', 'bug_status=UNCONFIRMED&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'CONFIRMED', '1', 'bug_status=CONFIRMED&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'IN_PROGRESS', '1', 'bug_status=IN_PROGRESS&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'RESOLVED', '1', 'bug_status=RESOLVED&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'VERIFIED', '1', 'bug_status=VERIFIED&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'FIXED', '1', 'resolution=FIXED&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'INVALID', '1', 'resolution=INVALID&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'WONTFIX', '1', 'resolution=WONTFIX&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'DUPLICATE', '1', 'resolution=DUPLICATE&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'WORKSFORME', '1', 'resolution=WORKSFORME&product=" + uriProductName + "', '1');" +
                         "INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + allId + "', 'All Open', '1', 'bug_status=UNCONFIRMED&bug_status=CONFIRMED&bug_status=IN_PROGRESS&product=Newly%20created%20product', '1');");
      }).then(function(rows){
        return connection.query("INSERT INTO components (initialowner, product_id, name, description, initialqacontact) VALUES ('1','" + id + "','" + componentName + "','" + componentName + " description',NULL);");
      }).then(function(rows){
        return connection.query("INSERT INTO series_categories (name) VALUES ('" + componentName + "');");
      }).then(function(rows){
        componentId = rows.insertId;

        return connection.query("INSERT INTO series (creator, category, subcategory, name, frequency, query, is_public) VALUES ('1', '" + prodId + "', '" + componentId + "', 'All Open', '1', 'field0-0-0=resolution&type0-0-0=notregexp&value0-0-0=.&product=" + uriProductName + "&component=" + uriComponentName + "', '1'), ('1', '1', '" + componentId + "', 'All Closed', '1', 'field0-0-0=resolution&type0-0-0=regexp&value0-0-0=.&product=" + uriProductName + "&component=" + uriComponentName + "', '1')");
      }).then(function(rows){
        connection.end();

        res.sendStatus(201);
      }).catch(function(error){
          res.status(500).send(error);
      });
  });
});

app.post('/user', function(req, res){
  var connection = mysql.createConnection(connectionDetails);
  connection.connect();

  var options = {
    method: 'POST',
    url: 'http://' + baseUrl + ':8080/bugzilla/rest/user',
    qs: {
      login: 'admin@bugzilla.org',
      password: 'password'
    },
    headers: {
       'cache-control': 'no-cache',
       'content-type': 'application/json'
    },
    body: {
      "email" : req.body.email,
      "password" : "password"
    },
    json: true
  };

  request(options, function (err, response, body) {
    if(err || body.code == 500) return res.status(500).send(err);

    mysqlp.createConnection({
      host     : baseUrl,
      user     : 'bugsadmin',
      password : 'bugsadmin',
      database : 'bugs',
      multipleStatements: true
    }).then(function(connection){
      connection.query("UPDATE profiles SET cryptpassword='1Lio6jhV,uAIiD29aJih6yJLHBk0afdO6U/Q3MAEtAR6E5RVcYsQ{SHA-256}' WHERE userid = " + body.id + "; SELECT * FROM profiles WHERE login_name='" + req.body.email + "'", function(err, rows, fields) {
        connection.end();

        res.send(rows[1][0]);
      }).catch(function(error){
        res.status(500).send(error);
      })
    });
  });
});

app.get('/ping', function(req, res){
  res.sendStatus(200);
});

app.listen(3001,function(err){
  if(err) return console.error(err);
});
