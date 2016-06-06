var chai  = require('chai'),
    page = require('./helpers/page_builder.js');

var expect = chai.expect;

describe('Initial JS unit check', function(){

  it('should pull down the site and run assertion', function(done){
    page.build('/enter_bug.cgi?product=FoodReplicator', function(window){
      window.show_comment_preview();

      expect(window.$('.bz_comment.bz_default_hidden').length).to.equal(0);

      done();
    });
  });

});
