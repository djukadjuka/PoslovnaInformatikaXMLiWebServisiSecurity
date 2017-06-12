(function() {
    'use strict';

    angular
        .module('app')
        .service('sessionService', sessionService);

    function sessionService() {

        var items=null;

        this.setItems=function(items) {
            this.items=items;
        }

        this.getItems=function() {
        	return this.items;
        }
    }

})();