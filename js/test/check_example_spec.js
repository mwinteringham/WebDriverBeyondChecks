var chai  = require('chai'),
    page = require('./helpers/page_builder.js');

var expect = chai.expect;

describe('Initial JS unit check', function(){

  it('should pull down the site and run assertion', function(done){
    page.build('/enter_bug.cgi', function(window){
      expect(window.$('a[href="index.cgi?logout=1"]').length).to.equal(2);

      done();
    });
  });

});
