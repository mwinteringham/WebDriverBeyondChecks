var jsdom  = require('jsdom'),
    config = require('../../config.json'),
    request = require('request');

module.exports = {

  build: function(path, callback){
    this.getAuthCookies(function(cookies){
      jsdom.env({
        url: config.domain + path,
        headers: {
          'Cookie': cookies
        },
        scripts: ['http://code.jquery.com/jquery-1.12.4.min.js'],
        features: {
            FetchExternalResources: ["script"],
            ProcessExternalResources: ["script"]
        },
        done: function(errors, window){
          if(errors != null) console.log('Errors', errors);

          callback(window);
        }
      });
    });
  },

  getAuthCookies: function(callback){
    request.post(
      {
        url: config.domain + '/index.cgi',
        headers: {
          Referer: config.domain +'/index.cgi',
        },
        form: {
          Bugzilla_login: config.login.username,
          Bugzilla_password: config.login.password,
          Bugzilla_remember: 'on',
          GoAheadAndLogIn: 'Log in'
        }
      }, function(err, response){
        callback(response.headers['set-cookie'][0].split(';')[0] + '; ' + response.headers['set-cookie'][1].split(';')[0]);
      });
  }

}
