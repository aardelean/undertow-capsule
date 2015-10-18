'use strict';

var gulp = require('gulp');
var $ = require('gulp-load-plugins')();
var sync = $.sync(gulp).sync;
var del = require('del');
var browserify = require('browserify');
var watchify = require('watchify');
var source = require('vinyl-source-stream');
var path = require('path');
var url = require('url');
var fs = require('fs');

require('harmonize')();


var bundler = {
  w: null,
  init: function () {
    this.w = watchify(browserify({
      entries: ['./src/main/resources/js/app.js'],
      insertGlobals: true,
      cache: {},
      packageCache: {}
    }));
  },
  bundle: function () {
    return this.w && this.w.bundle()
      .on('error', $.util.log.bind($.util, 'Browserify Error'))
      .pipe(source('app.js'))
      .pipe(gulp.dest('./build/resources/main/js'));
  },
  watch: function () {
    this.w && this.w.on('update', this.bundle.bind(this));
  },
  stop: function () {
    this.w && this.w.close();
  }
};


gulp.task('scripts', function () {
  bundler.init();
  return bundler.bundle();
});
