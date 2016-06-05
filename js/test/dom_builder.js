var jsdom  = require('jsdom'),
    config = require('../config.json');

module.exports = {

  build: function(path, callback){
    jsdom.env({
      url: config.domain + path,
      headers: {
        'Cookie': 'Cookie:Bugzilla_login_request_cookie=zqQ52INl1l; Bugzilla_login=66538; Bugzilla_logincookie=8eKQUff4J7'
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
  }

}
