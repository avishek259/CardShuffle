'use strict';

/**
 * @ngdoc overview
 * @name shippingFormApp
 * @description
 * # shippingFormApp
 *
 * Main module of the application.
 */
angular
  .module('shippingFormApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'vm'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
