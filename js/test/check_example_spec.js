var chai  = require('chai'),
    dom = require('./dom_builder.js');

var expect = chai.expect;

describe('Initial JS unit check', function(){

  it('should pull down the site and run assertion', function(done){
    dom.build('/enter_bug.cgi', function(window){
      expect(window.$('a[href="index.cgi?logout=1"]').length).to.equal(2);

      done();
    });
  });

});
